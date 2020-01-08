package com.inno.ConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@EJB
public class ConnectionManagerJdbcImpl implements ConnectionManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManagerJdbcImpl.class);
  private static ConnectionManager connectionManager;

  private ConnectionManagerJdbcImpl() {
  }

  public static ConnectionManager getInstance() {
    if (connectionManager == null) {
      connectionManager = new ConnectionManagerJdbcImpl();
    }
    return connectionManager;
  }

  @Override
  public Connection getConnection(){
    Connection conn = null;
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/mobile");
      conn = ds.getConnection();
    }catch (SQLException | NamingException e) {
      LOGGER.error("Some thing wrong in getConnection method", e);
    }
    return conn;
  }

  @Override public int get15() {
    return 15;
  }
}
