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
  int a;
  String year, month, day;
  
  
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
  
  public void dateYear() {
    loop3:while(true) {
      System.out.println("연>>>");
    year = sc.nextLine();
    if(year.equals("") || year==null) {
      System.out.println("오류!\t네 자릿수의 연도를 입력 하세요.");
      continue loop3;
    } if(year.equals("back")) {
      new CalFuntion().mainMenu();
    } else { a = Integer.parseInt(year);
    int length = (int)(Math.log10(a)+1);
    if(length != 4) {
      System.out.println("오류!\t네 자릿수의 연도를 입력 하세요.");
      } else {break;}
    }
    }//year while end
  }//dateYear end
  
  public void dateMonth() {
  
  loop4:while(true) {
    System.out.println("월>>>");
  month = sc.nextLine();
  if(month.equals("") || month==null) {
    System.out.println("오류!\t이 칸은 비울수 없습니다.");
    continue loop4;
  } if(month.equals("back")) {
    new CalFuntion().mainMenu();
  } else {
    if(month.length()!=2) {
      System.out.println("오류!\t두 자릿수로 월을 입력 하세요.");
      continue loop4;
    } else { 
      a = Integer.parseInt(month);}//2else end
  }//1else end
  if(a < 1 || a > 12 ) {
    System.out.println("오류!\t 1~12 월을 입력 하세요.");
  } else {break loop4;}//3else end
  } //month while end
}//dateMonth end
  
  public void dateDay() {
    loop6:while(true) {
      System.out.println("일>>>");
    day = sc.nextLine();
    if(day.equals("") || day==null) {
      System.out.println("오류!\t이 칸은 비울수 없습니다.");
      continue loop6;
    } if(day.equals("back")) {
      new CalFuntion().mainMenu();
    } else {
      if(day.length()!=2) {
        System.out.println("오류!\t두 자릿수로 일을 입력 하세요.");
        continue loop6;
      } else { 
        a = Integer.parseInt(day);}//2else end
    }//1else end
    if(a < 1 || a > 31 ) {
      System.out.println("오류!\t 1~31 일을 입력 하세요.");
    } else {break loop6;}//3else end
    } //day while end
  }

}//C end
