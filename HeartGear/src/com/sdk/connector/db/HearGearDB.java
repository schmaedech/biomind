/*
 * @copyright Copyright (c) 2010 Laboratório de Educação Cerebral. (http://www.educacaocerebral.com.br)
 *
 * This file is part of SoftVFC.
 *
 * SoftVFC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SoftVFC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SoftVFC.  If not, see <http://www.gnu.org/licenses/>.
 *
 * *********************
 *
 * Este arquivo é parte do programa SoftVFC.
 *
 * SoftVFC é um software livre; você pode redistribui-lo e/ou
 * modifica-lo dentro dos termos da Licença Pública Geral GNU como
 * publicada pela Fundação do Software Livre (FSF); na versão 3 da
 * Licença.
 *
 * Este programa é distribuido na esperança que possa ser util,
 * mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 * Licença Pública Geral GNU para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa, se não, acesse no website oficial:
 * http://www.gnu.org/licenses/gpl.html
 *
 */
/**
 *
 * @author Diego Schmaedech Martins (schmaedech@gmail.com)
 * @version 29/07/2010
 */

package com.sdk.connector.db;


import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HearGearDB {

    String userDir = System.getProperty("user.dir", ".");
    String separatorDir = System.getProperty("file.separator", ".");

    private void debug(Exception ex){
        Logger.getLogger(HearGearDB.class.getName()).log(Level.INFO, null, ex);
    }

    public HearGearDB() {
        this("HEARTGEARDB");
    }
    
    public HearGearDB(String dbName) {
        this.dbName = dbName;
        setDBSystemDir();
        String driverName = ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("derby.driver");
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            debug(ex);
        }
        if(!dbExists()) {
            createDatabase();
        }
        
    }
    
    private boolean dbExists() {
        boolean bExists = false;
        String dbLocation = getDatabaseLocation();
        File dbFileDir = new File(dbLocation);
        if (dbFileDir.exists()) {
            bExists = true;
        }
        return bExists;
    }
    
    private void setDBSystemDir() {
        String systemDir = userDir + separatorDir+".heartgeardb";
        System.setProperty("derby.system.home", systemDir);
        File fileSystemDir = new File(systemDir);
        fileSystemDir.mkdir();
    }


    private boolean createTables(Connection dbConnection) {
        boolean bCreatedTables = false;
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.execute(strCreateAquisitionTable);
            bCreatedTables = true;
        } catch (SQLException ex) {
            debug(ex);
        }
        
        return bCreatedTables;
    }

    private boolean createDatabase() {
        boolean bCreated = false;
        dbConnection = null;
        
        String dbUrl = getDatabaseUrl();
        
        Properties dbProperties = new Properties();
        dbProperties.put("create", "true");
        dbProperties.put("user", ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("user"));
        dbProperties.put("password", ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("password"));
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
            bCreated = createTables(dbConnection);
        } catch (SQLException ex) {
        }
        dbProperties.remove("create");
      
        return bCreated;
    }
    
    public boolean connect() {
        String dbUrl = getDatabaseUrl();
        try {
            dbConnection = DriverManager.getConnection(dbUrl);
            stmtSaveNewRecord = dbConnection.prepareStatement(strSaveAquisition, Statement.RETURN_GENERATED_KEYS);
            stmtSaveLeftRRRecord = dbConnection.prepareStatement(strSaveLeftRRAquisition, Statement.RETURN_GENERATED_KEYS);
            stmtSaveRightRRRecord = dbConnection.prepareStatement(strSaveRightRRAquisition, Statement.RETURN_GENERATED_KEYS);
            stmtUpdateExistingRecord = dbConnection.prepareStatement(strUpdateAquisition);
            stmtGetAquisition = dbConnection.prepareStatement(strGetAquisition);
            stmtDeleteAquisition = dbConnection.prepareStatement(strDeleteAquisition);
            isConnected = dbConnection != null;

        } catch (SQLException ex) {
            debug(ex);
            isConnected = false;
        }
        return isConnected;
    }

    public void disconnect() {
        if(isConnected) {
            String dbUrl = getDatabaseUrl();
            Properties dbProperties = new Properties();
            dbProperties.put("shutdown", "true");
            try {
                DriverManager.getConnection(dbUrl, dbProperties );  
            } catch (SQLException ex) {
                debug(ex);

            }
            isConnected = false;
        }
    }
    
    public String getDatabaseLocation() {
        String dbLocation = System.getProperty("derby.system.home") + separatorDir + dbName;
        return dbLocation;
    }
    
    public String getDatabaseUrl() {
        String dbUrl = ResourceBundle.getBundle("com/sdk/connector/db/Bundle").getString("derby.url")+ dbName;
        return dbUrl;
    }
      
    public int saveRecord(Aquisition record) {
        int id = -1;

        if(record.getLeftRR() != 0 && record.getRightRR() != 0){
        
            try {
                stmtSaveNewRecord.clearParameters();
                stmtSaveNewRecord.setInt(1, record.getIdRegister());
                stmtSaveNewRecord.setTimestamp(2, record.getLeftTimestamp());
                stmtSaveNewRecord.setInt(3, record.getLeftRR());
                stmtSaveNewRecord.setTimestamp(4, record.getRightTimestamp());
                stmtSaveNewRecord.setInt(5, record.getRightRR());

                stmtSaveNewRecord.executeUpdate();
                ResultSet results = stmtSaveNewRecord.getGeneratedKeys();
                if (results.next()) {
                    id = results.getInt(1);
                }
            } catch(SQLException ex) {
                debug(ex);
            }
        }else if(record.getLeftRR() != 0){
            id = saveLeftRRRecord(record);
        }else if(record.getRightRR() != 0){
            id = saveRightRRRecord(record);
        }
        return id;
    }

    public int saveLeftRRRecord(Aquisition record) {

        int id = -1;
        try {
            stmtSaveLeftRRRecord.clearParameters();
            stmtSaveLeftRRRecord.setInt(1, record.getIdRegister());
            stmtSaveLeftRRRecord.setTimestamp(2, record.getLeftTimestamp());
            stmtSaveLeftRRRecord.setInt(3, record.getLeftRR());
            stmtSaveLeftRRRecord.executeUpdate();
            ResultSet results = stmtSaveLeftRRRecord.getGeneratedKeys();
            if (results.next()) {
                id = results.getInt(1);
            }
        } catch(SQLException ex) {
            debug(ex);
        }
        return id;
    }
    public int saveRightRRRecord(Aquisition record) {
        int id = -1;
        try {
            stmtSaveRightRRRecord.clearParameters();
            stmtSaveRightRRRecord.setInt(1, record.getIdRegister());
            stmtSaveRightRRRecord.setTimestamp(2, record.getRightTimestamp());
            stmtSaveRightRRRecord.setInt(3, record.getRightRR());

            stmtSaveRightRRRecord.executeUpdate();
            ResultSet results = stmtSaveRightRRRecord.getGeneratedKeys();
            if (results.next()) {
                id = results.getInt(1);
            }
        } catch(SQLException ex) {
            debug(ex);
        }
        return id;
    }

    public boolean editRecord(Aquisition record) {
        boolean bEdited = false;
        try {
            stmtUpdateExistingRecord.clearParameters();
            stmtUpdateExistingRecord.setInt(1, record.getIdRegister());
            stmtUpdateExistingRecord.setTimestamp(2, record.getLeftTimestamp());
            stmtUpdateExistingRecord.setInt(3, record.getLeftRR());
            stmtUpdateExistingRecord.setTimestamp(4, record.getRightTimestamp());
            stmtUpdateExistingRecord.setInt(5, record.getRightRR());
            stmtUpdateExistingRecord.setInt(12, record.getId());
            stmtUpdateExistingRecord.executeUpdate();
            bEdited = true;
        } catch(SQLException ex) {
             debug(ex);
        }
        return bEdited;
        
    }
    
    public boolean deleteRecord(int id) {
        boolean bDeleted = false;
        try {
            stmtDeleteAquisition.clearParameters();
            stmtDeleteAquisition.setInt(1, id);
            stmtDeleteAquisition.executeUpdate();
            bDeleted = true;
        } catch (SQLException ex) {
             debug(ex);
        }
        
        return bDeleted;
    }
    
    public boolean deleteRecord(Aquisition record) {
        int id = record.getId();
        return deleteRecord(id);
    }
    
    public List<ListEntry> getListEntries() {
        List<ListEntry> listEntries = new ArrayList<ListEntry>();
        Statement queryStatement = null;
        ResultSet results = null;
        
        try {
            queryStatement = dbConnection.createStatement();
            results = queryStatement.executeQuery(strGetListEntries);
            while(results.next()) {
                int id = results.getInt(1);
                int idRegister = results.getInt(2);
                Timestamp lt = results.getTimestamp(3);
                int lr = results.getInt(4);
                Timestamp rt = results.getTimestamp(5);
                int rr = results.getInt(6);
                ListEntry entry = new ListEntry(idRegister, lt, lr, rt,rr, id);
                listEntries.add(entry);
            }
            
        } catch (SQLException ex) {
            debug(ex);
            
        }
        
        return listEntries;
    }

    public List<ListEntry> getDateDiffListEntries(Timestamp startDate, Timestamp endDate) {
        String strDateDiffListEntries =
            "SELECT ID, ID_REGISTER, LEFT_TIMESTAMP, LEFT_RR, RIGHT_TIMESTAMP, RIGHT_RR  FROM HEARTGEARDB.AQUISITION "  +
            "WHERE (LEFT_TIMESTAMP BETWEEN '" +startDate+ "' AND '" + endDate + "') OR (RIGHT_TIMESTAMP BETWEEN '" +startDate+ "' AND '" + endDate + "') "+
            "ORDER BY ID ASC";
        List<ListEntry> listEntries = new ArrayList<ListEntry>();
        Statement queryStatement = null;
        ResultSet results = null;

        try {
            queryStatement = dbConnection.createStatement();
            results = queryStatement.executeQuery(strDateDiffListEntries);
            while(results.next()) {
                int id = results.getInt(1);
                int idRegister = results.getInt(2);
                Timestamp lt = results.getTimestamp(3);
                int lr = results.getInt(4);
                Timestamp rt = results.getTimestamp(5);
                int rr = results.getInt(6);
                
                ListEntry entry = new ListEntry(idRegister, lt, lr, rt,rr, id);
                listEntries.add(entry);
            }

        } catch (SQLException ex) {
            debug(ex);

        }

        return listEntries;
    }

    public Aquisition getAquisition(int index) {
        Aquisition aquisition = null;
        try {
            stmtGetAquisition.clearParameters();
            stmtGetAquisition.setInt(1, index);
            ResultSet result = stmtGetAquisition.executeQuery();
            if (result.next()) {

                int idRegister = result.getInt("ID_REGISTER");
                Timestamp leftTimestamp = result.getTimestamp("LEFT_TIMESTAMP");
                int leftRR = result.getInt("LEFT_RR");
                Timestamp rightTimestamp = result.getTimestamp("RIGHT_TIMESTAMP");
                int rightRR = result.getInt("RIGHT_RR");
                int id = result.getInt("ID");
                aquisition = new Aquisition(idRegister, leftTimestamp, leftRR, rightTimestamp, rightRR, id);
            }
        } catch(SQLException ex) {
            debug(ex);
        }
        
        return aquisition;
    }
    
    public static void main(String[] args) {
        HearGearDB db = new HearGearDB();
        System.out.println(db.getDatabaseLocation());
        System.out.println(db.getDatabaseUrl());

        db.connect();
        db.disconnect();
    }

    private Connection dbConnection;
   
    private boolean isConnected;
    private String dbName;
    private PreparedStatement stmtSaveNewRecord;
    private PreparedStatement stmtSaveLeftRRRecord;
    private PreparedStatement stmtSaveRightRRRecord;
    private PreparedStatement stmtUpdateExistingRecord;
    private PreparedStatement stmtGetAquisition;
    private PreparedStatement stmtDeleteAquisition;
    
    private static final String strCreateAquisitionTable =
            "create table HEARTGEARDB.AQUISITION (" +
            "    ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "    ID_REGISTER INTEGER NOT NULL, " +
            "    LEFT_TIMESTAMP TIMESTAMP, " +
            "    LEFT_RR INTEGER, " +
            "    RIGHT_TIMESTAMP TIMESTAMP, " +
            "    RIGHT_RR INTEGER " +
            ")";
    
    private static final String strGetAquisition =
            "SELECT * FROM HEARTGEARDB.AQUISITION " +
            "WHERE ID = ?";
    
    private static final String strSaveAquisition =
            "INSERT INTO HEARTGEARDB.AQUISITION" +
            " (ID_REGISTER, LEFT_TIMESTAMP, LEFT_RR, RIGHT_TIMESTAMP, RIGHT_RR)"  +
            " VALUES (?, ?, ?, ?, ?)";

    private static final String strSaveLeftRRAquisition =
            "INSERT INTO HEARTGEARDB.AQUISITION" +
            " (ID_REGISTER, LEFT_TIMESTAMP, LEFT_RR)"  +
            " VALUES (?, ?, ?)";

    private static final String strSaveRightRRAquisition =
            "INSERT INTO HEARTGEARDB.AQUISITION" +
            " (ID_REGISTER, RIGHT_TIMESTAMP, RIGHT_RR)"  +
            " VALUES (?, ?, ?)";
    
    private static final String strGetListEntries =
            "SELECT ID, ID_REGISTER, LEFT_TIMESTAMP, LEFT_RR, RIGHT_TIMESTAMP, RIGHT_RR  FROM HEARTGEARDB.AQUISITION "  +
            "ORDER BY ID ASC";
    
    private static final String strUpdateAquisition =
            "UPDATE HEARTGEARDB.AQUISITION " +
            "SET ID_REGISTER = ?, " +
            "    LEFT_TIMESTAMP = ?, " +
            "    LEFT_RR = ?, " +
            "    RIGHT_TIMESTAMP = ?, " +
            "    RIGHT_RR = ? " +
            "WHERE ID = ?";
    
    private static final String strDeleteAquisition =
            "DELETE FROM HEARTGEARDB.AQUISITION " +
            "WHERE ID = ?";
    
}
