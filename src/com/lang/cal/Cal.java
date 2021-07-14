package com.lang.cal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Cal extends MainCalender{

  Connection CN = null;
  Statement ST = null; 
  ResultSet RS = null; 
  PreparedStatement PST = null;
  Date DT = new Date();
  String msg = "isud = crud 쿼리문기술";
  String input = "";
  Scanner sc = new Scanner(System.in);
  int total = 0;
  MainCalender MC = new MainCalender();



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

      int sel = 0;
      while(true) {
        System.out.println("\n1.입력  2.삭제  3.수정  4.전체조회 5.개별조회 6.달력조회  0.종료");
        sel = Integer.parseInt(sc.nextLine());
        if(sel==0) {
          System.out.println("DB프로그램 종료");
          System.exit(0);
        }//if end
        switch(sel) {
          case 1 : add(); break;
          case 2 : delete(); break;
          case 3 : update(); break;
          case 4 : AllList(); break;
          case 5 : view(); break;
          case 6 : MC.Scan_Insert(); break;
          default : System.out.println("올바른 메뉴를 선택해 주세요.");
          break;
        }//switch end

      }//while end
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end
  }//커넥션 end

  public void add() throws Exception {



    System.out.print("제목입력>>>");
    String title = sc.nextLine();
    System.out.print("내용입력>>>");
    String contents = sc.nextLine();
    System.out.print("장소입력>>>");
    String location = sc.nextLine();
    System.out.print("일정입력(****.**.**)>>>");
    String dDate = sc.nextLine();
    String[] dateArr = dDate.split("\\.");
    int year = Integer.parseInt(dateArr[0]);
    int month = Integer.parseInt(dateArr[1]);
    int day = Integer.parseInt(dateArr[2]);
    MainCalender.arr[year-1900][month][day-1] = title;

    msg = "insert into cal(Caltitle, Calcontents, Callocation, Caldate) "
        + "values(?, ?, ?, TO_DATE('" + dDate + "','yyyy-MM-dd'))";

    PST = CN.prepareStatement(msg);
    PST.setString(1, title);
    PST.setString(2, contents);
    PST.setString(3, location);

    System.out.println(msg);

    int OK = PST.executeUpdate();
    if (OK>0) {
      System.out.println("데이터 저장 성공");
    } else {
      System.out.println("데이터 저장 실패");
    }
  }//add end

  public void delete() throws Exception {

    System.out.println("삭제할 일정의 제목을 입력하세요 : ");
    String title = sc.nextLine();
    msg = "delete from cal where Caltitle = " + title ;
    System.out.println(msg);
    int OK = ST.executeUpdate(msg);
    if (OK>0) {
      System.out.println("데이터 삭제 성공");
      AllList();
    } else {
      System.out.println("데이터 삭제 실패");
    }
  }//delete end


  public void update() throws Exception {

    System.out.println("검색할 제목을 입력하세요.");
    String title = sc.nextLine();
    msg = "select * from cal where Caltitle = "+ "'"+ title +"'";
    System.out.print("수정 할 제목 입력>>>");
    String settitle = sc.nextLine();
    System.out.print("수정 할 내용 입력>>>");
    String setcontents = sc.nextLine();


    msg = "update cal set "
        + "Caltitle = '"+ settitle+"', "
        + "Calcontents = '"+ setcontents 
        +"' where Caltitle = '" + title + "'" ;

    System.out.println(msg);

    int OK = ST.executeUpdate(msg);
    if (OK>0) {
      System.out.println("데이터 수정 성공");
    } else {
      System.out.println("데이터 수정 실패");
    }
  }

  public void AllList() {
    try {
      msg = "select * from cal ";
      RS = ST.executeQuery(msg);

      System.out.println("\t\t\t 전체 일정 갯수 : " + total);
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end

    listin();
  }//list end

  public void view() throws Exception {

    try {
      loop : 
        while(true) {
          System.out.println("검색할 제목을 입력하세요.");
          String title = sc.nextLine();
          msg = "select * from cal where Caltitle = "+ "'"+ title +"'";

          int OK = ST.executeUpdate(msg);
          if (OK==0) {
            System.out.println("데이터 조회 실패");
          } else {
            System.out.println("데이터 조회 성공");
            break loop;
          }
        }//while end
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end

    listin();
  }

  public void listin() {
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
  }

}//C end
