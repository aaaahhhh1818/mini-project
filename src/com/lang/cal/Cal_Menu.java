package com.lang.cal;

import java.sql.SQLException;

public class Cal_Menu {
  Cal cl = new Cal();

  void run() throws SQLException {
    
  cl.connect();
  cl.add();
  cl.delete();
  cl.AllList();
  cl.view();
  
  }
}
