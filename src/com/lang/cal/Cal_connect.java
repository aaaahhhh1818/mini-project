package com.lang.cal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Cal_connect {

  Connection CN = null;
  Statement ST = null; 
  ResultSet RS = null; 
  PreparedStatement PST = null;
  Scanner sc = new Scanner(System.in);
  Date DT = new Date();
  String msg = "isud = crud 쿼리문기술";
  String input = "";
  int total = 0;
  int a = 0;
  String year = null, month = null, day = null;
  String [][][] arr = new String[1101][12][31];

  public void connect() {

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      String url = "jdbc:oracle:thin:@localhost:1521:XE";
      CN =  DriverManager.getConnection(url, "system", "1234");
      //System.out.println( DT + "드라이브 & 서버 연결성공");

      ST = CN.createStatement();

      msg = "select count(*) as hit from cal";
      RS = ST.executeQuery(msg);
      if(RS.next()==true)  {
        total = RS.getInt("hit");
      }
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }
  }//connect end
}
