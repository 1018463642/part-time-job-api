package com.proj.api.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.proj.api.exception.database.RelationalDatabaseException;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class RelationalDatabase {
    private static DataSource ds = new ComboPooledDataSource();
    Connection conn=null;
    PreparedStatement pstmt=null;

    public RelationalDatabase() throws RelationalDatabaseException {
        try {
            this.conn=RelationalDatabase.ds.getConnection();
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
    }

    public void commit() throws RelationalDatabaseException {
        try {
            this.conn.commit();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e,e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public boolean doSQL(String _sSQL) throws RelationalDatabaseException {
        try {
            this.pstmt = this.conn.prepareStatement(_sSQL);
            boolean result=this.pstmt.execute();
            this.pstmt.close();
            return result;
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e,e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public ResultSet doQuery(String _sSQL,Object[] aParams) throws RelationalDatabaseException {
        try {
            this.pstmt = this.conn.prepareStatement(_sSQL);
            for(int i=0;i<aParams.length;i++){
                this.pstmt.setObject(i+1,aParams[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                throw new RelationalDatabaseException(e,e1);
            }
            throw new RelationalDatabaseException(e);
        }
    }

    public void rollback() throws RelationalDatabaseException {
        try {
            this.conn.rollback();
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }
    }

    public void close() throws RelationalDatabaseException {
        try {
            if(pstmt==null){
                pstmt.close();
            }
            this.conn.commit();
            this.conn.setAutoCommit(true);
            this.conn.close();
        } catch (SQLException e) {
            throw new RelationalDatabaseException(e);
        }

    }
}
