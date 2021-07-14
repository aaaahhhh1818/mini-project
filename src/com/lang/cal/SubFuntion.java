package com.lang.cal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class SubFuntion extends MainCalender{
  
  Scanner sc = new Scanner(System.in);
  Connection CN = null;
  Statement ST = null; 
  ResultSet RS = null; 
  PreparedStatement PST = null;
  Date DT = new Date();
  String msg = "isud = crud 쿼리문기술";
  String input = "";
  int total = 0;
  
  
  public void connect() {
    
    try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    CN =  DriverManager.getConnection(url, "system", "1234");
    System.out.println( DT + "드라이브 & 서버 연결성공");
    
    ST = CN.createStatement();
    
    msg = "select count(*) as hit from cal";
    RS = ST.executeQuery(msg);
    if(RS.next()==true)  {
      total = RS.getInt("hit");
    }
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }
  }//connect end
  
  public void listIn() {
    try {
      System.out.println("제 목\t내 용\t장 소\t날 짜\t");
      RS = ST.executeQuery(msg);
      while(RS.next()==true) {

        String ucode = RS.getString("Caltitle");
        String uname = RS.getString("Calcontents");
        String utitle = RS.getNString("Callocation");
        Date udate = RS.getDate("Caldate");
        
        System.out.println(
            ucode + "\t" + uname + "\t" + utitle + "\t" + udate + "\t");
      }
      
      } catch (Exception ex) { System.out.println("에러이유 " + ex);
      }//try C end
  }//listIn end
  

}//C end
