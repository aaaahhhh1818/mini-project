package com.lang.cal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Diary {

  Connection CN=null; //DB서버연결정보 서버ip주소 계정id,pwd
  Statement ST=null;  //ST=CN.createStatement()명령어생성 삭제,신규등록,조회하라
  ResultSet RS=null;  //select조회결과값 전체데이터를 기억합니다
  PreparedStatement PST=null ;
  String msg ="";
  Scanner sc = new Scanner(System.in);

  String wdate; 
  String weather; 
  String feel; 
  String contents; 
  String wcomment;
  

  public void diaryConnect() {

    try {   
//      Class.forName("oracle.jdbc.driver.OracleDriver");
//      String url = "jdbc:oracle:thin:@localhost:1521:XE";
//      CN =  DriverManager.getConnection(url, "system", "1234");
//      ST = CN.createStatement();
                  Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클드라이브로드
                  String url = "jdbc:oracle:thin:@175.210.92.176:1521:XE" ;
                  CN=DriverManager.getConnection(url,"hhwanseung","1234");
                  System.out.println("오라클 드라이브및 서버연결성공 ");
                  ST = CN.createStatement();

    }catch (Exception e) { }
  }//diaryConnect end

  public void diaryMenu() {
    diaryConnect();


    loop: while(true) {
      System.out.println("\n[일기장 메뉴]");
      System.out.println("┌──────────────────");  
      System.out.println("│ 1. 일기장 쓰기");
      System.out.println("│ 2. 일기장 삭제");
      System.out.println("│ 3. 일기장 수정");
      System.out.println("│ 4. 일기장 목록");
      System.out.println("│ 5. 일기장 보기");
      System.out.println("│ q. 일기장 종료");
      System.out.println("└──────────────────");
      System.out.print("[선택] ");
      String sel = sc.nextLine();
      switch (sel) {
        case "1" : diaryInsert(); break;
        case "2" : diaryDelete(); break;
        case "3" : diaryUpdate(); break;
        case "4" : diaryList(); break;
        case "5" : diaryView(); break;
        case "q" :
          System.out.println("일기장 종료");
          break loop;
        default :
          System.out.println("\n-잘못된 입력입니다. 다시 입력해주세요.");
          break;
      }//switch end
    }//while end
  }//diaryMenu end

  public void diaryInsert () {
    try {
    	loopI :while(true) {
      System.out.println("\n[일기장 쓰기]");
      System.out.print("오늘의 날짜 : "); 
      wdate = sc.nextLine();
      if(wdate == null || wdate.equals("")) { 
    	  System.out.println("제대로 된 날짜를 입력해 주세요");
      continue loopI;
      }
      msg = "select wdate from diary where wdate = '"+wdate+"' ";
      RS = ST.executeQuery(msg);
      if(RS.next()==true) {
    	  System.out.println("이미 있는 일기입니다") ;
    	  continue loopI;
      }//if end
      System.out.print("오늘의 날씨 : "); 
      weather=sc.nextLine();  
      System.out.print("오늘의 기분 : "); 
      feel=sc.nextLine();
      System.out.print("오늘의 일기 : "); 
      contents=sc.nextLine();
      System.out.print("오늘의 반성 : "); 
      wcomment=sc.nextLine();        

      msg = "insert into diary(wdate, weather, feel, contents, wcomment) "
          + "values(TO_DATE(?,'yyyy-MM-dd'),?,?,?,?)";   
      PST = CN.prepareStatement(msg);
      PST.setString(1,wdate);
      PST.setString(2,weather);
      PST.setString(3,feel);
      PST.setString(4,contents);
      PST.setString(5,wcomment);

      int OK = PST.executeUpdate();
      if (OK>0){
        System.out.println("'" + wdate + "의 일기장' 저장 성공");
      }else{ System.out.println("'" + wdate + "의 일기장' 저장 실패");}
      break;
    	}//loop end
    }catch (Exception e) {System.out.println(e + " -일기장 저장을 실패했습니다");}
    
  }//diaryInsert end

  public void diaryList () {
    try {
      System.out.println("\n[일기장 목록]");
      msg = "select wdate from diary order by wdate" ; //문자열을 명령어 인식해서 실행하도록 Statement
      RS = ST.executeQuery(msg);

      while(RS.next()==true) {
        Date wdate = RS.getDate("wdate");
        System.out.println("> " + wdate + "의 일기");
      }
    }catch (Exception e) {}

  }//diaryList end

  public void diaryView () {
    try {
    	loopV : while(true) {
      System.out.println("\n[일기장 보기]");
      System.out.print("보고싶은 일기장 날짜를 입력해주세요 : ");
      wdate = sc.nextLine();
      if(wdate == null || wdate.equals("")) { 
    	  System.out.println("제대로 된 날짜를 입력해 주세요");
      continue loopV;
      }
      msg = "select wdate from diary where wdate = '"+wdate+"' ";
      RS = ST.executeQuery(msg);
      if(RS.next()!=true) {
    	  System.out.println("존재하지 않는 일기장 입니다.") ;
    	  continue loopV;
      }//if end
      
      msg = "select * from  diary where wdate = '"+wdate+"'";
      RS = ST.executeQuery(msg);

      while(RS.next()==true) {
        Date wdate = RS.getDate("wdate");

        weather = RS.getString("weather");
        feel = RS.getString("feel");
        contents = RS.getString("contents");
        wcomment = RS.getString("wcomment");
        System.out.println("───────────────────────────────────────");
        System.out.println("\t" + wdate + "의 일기");
        System.out.println("날씨 : " + weather + "\t 기분 : " + feel);
        System.out.println("내용 :\n" + contents);
        System.out.println("오늘의 반성할 일 : " + wcomment);
        System.out.println("───────────────────────────────────────");
      }
      break;
    	}//while end
    }catch (Exception e) {System.out.println(e + " -일기장 보기를 실패했습니다");}

  }//diaryView end

  public void diaryDelete() {
    try {
    	loopD: while(true) {
      System.out.println("\n[일기장 삭제]");
      System.out.print("삭제할 일기장 날짜를 입력해주세요 : ");
      wdate = sc.nextLine();
      if(wdate == null || wdate.equals("")) { 
    	  System.out.println("제대로 된 날짜를 입력해 주세요");
      continue loopD;
      }
      msg = "select wdate from diary where wdate = '"+wdate+"' ";
      RS = ST.executeQuery(msg);
      if(RS.next()!=true) {
    	  System.out.println("존재하지 않는 일기장 입니다.") ;
    	  continue loopD;
      }//if end
      System.out.print("\n'" + wdate +"의 일기장'을 정말로 삭제하시겠습니까? 1.YES/2.NO > ");
      int check = Integer.parseInt(sc.nextLine());
      if (check != 1) {
        System.out.println("삭제를 취소하였습니다");
        return;
      }
      msg = "delete from diary where wdate = ? ";
      PST = CN.prepareStatement(msg);
      PST.setString(1,wdate);
      int OK = PST.executeUpdate();
      if ( OK > 0 ) {
        System.out.println("'" + wdate + "의 일기장' 삭제 완료");
      }else { 
        System.out.println("'" + wdate + "의 일기장'이 존재하지 않습니다");
      }
      break;
    	}
    }catch (Exception e) {System.out.println(e + " -일기장 삭제를 실패했습니다");}
  }

  public void diaryUpdate() {
	    try {
	    	loopU: while(true) {
	      System.out.println("\n[일기장 수정]");
	      System.out.print("수정할 일기장 날짜를 입력해주세요 : ");
	      wdate = sc.nextLine();
	      if(wdate == null || wdate.equals("")) { 
	    	  System.out.println("제대로 된 날짜를 입력해 주세요");
	    	  continue loopU;
	      }//if end
	      msg = "select wdate from diary where wdate = '"+wdate+"' ";
	      RS = ST.executeQuery(msg);
	      if(RS.next()!=true) {
	    	  System.out.println("존재하지 않는 일기장 입니다.") ;
	    	  continue loopU;
	      }//if end
	        System.out.println(wdate + "의 일기장 수정");
	        System.out.print("1.날씨\t 2.기분\t 3.내용\t 4.반성\t 9.돌아가기 > ");
	        String cel = sc.nextLine();
	        switch(cel) {
	          case "1" :
	            System.out.print("날씨 수정 : ");
	            weather = sc.nextLine();
	            msg = "update diary set weather = '"+weather+"' where wdate = '"+wdate+ "' ";
	            break;
	          case "2" :
	            System.out.print("기분 수정 : ");
	            feel = sc.nextLine();
	            msg = "update diary set feel = '"+feel+"' where wdate = '"+wdate+ "' ";
	            break;
	          case "3" :
	            System.out.print("내용 수정 : ");
	            contents = sc.nextLine();
	            msg = "update diary set contents = '"+contents+"' where wdate = '"+wdate+ "'  ";
	            break;
	          case "4" :
	            System.out.print("반성 수정 : ");
	            wcomment = sc.nextLine();
	            msg = "update diary set wcomment = '"+wcomment+"' where wdate = '"+wdate+ "' ";
	          case "9":
	        	  System.out.println(wdate + "일기장 수정을 취소합니다");
	        	  break loopU;
	          default :
	            System.out.println("잘못된 입력입니다"); break;
	        }//switch end

	        System.out.print("\n'" + wdate +"의 일기장'을 정말로 수정하시겠습니까? 1.YES/2.NO > ");
	        int check = Integer.parseInt(sc.nextLine());
	        if (check != 1) {
	          System.out.println("수정을 취소하였습니다");
	          return;
	        }//if end

	        int OK = ST.executeUpdate(msg);
	        if ( OK > 0 ) {
	          System.out.println("'" + wdate + "의 일기장' 수정 완료"); break;
	        }else { 
	          System.out.println("'" + wdate + "의 일기장'이 존재하지 않습니다"); break;
	        }//else end
	        
	      }//while end

	    }catch (Exception e) {System.out.println(e + " -일기장 수정을 실패하였습니다");}

	  }//diaryUpdate end
}//Class end
  
