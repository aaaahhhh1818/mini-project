package com.lang.cal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DiaryTest {
	
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
			Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클드라이브로드
			String url = "jdbc:oracle:thin:@175.210.92.176:1521:XE" ;
			CN=DriverManager.getConnection(url,"hhwanseung","1234");
			System.out.println("오라클 드라이브및 서버연결성공 ");
			ST = CN.createStatement();

	}catch (Exception e) { }
	}//diaryConnect end
	
	public void diaryMenu() {
		
		System.out.println("<다이어리 메뉴>");
		
		loop: while(true) {
			System.out.println("1.일기생성 2.일기삭제 3.일기수정 4.일기조회 9.종료");
			String sel = sc.nextLine();
			switch (sel) {
			case "1" :
				diaryInsert();
			break;
			case "2" :
				diaryDelete();
			break;
			case "3" :
				diaryUpdate();	
			break;
			case "4" :
				diarySelect();
			break;
			
			case "9" :
				System.out.println("일기장 종료");
				break loop;
			default :
				System.out.println("잘못된 입력입니다.");
			break;
			}//switch end
		}//while end
	}//diaryMenu end

public void diaryInsert () {
	try {
       
       //두번째 키보드에서 데이터 입력
      System.out.print("\n오늘 날짜>>>"); 
      wdate=sc.nextLine();
      System.out.print("날씨>>>"); 
      weather=sc.nextLine();  
      System.out.print("기분>>>"); 
      feel=sc.nextLine();
      System.out.print("내용>>>"); 
      contents=sc.nextLine();
      System.out.print("반성>>>"); 
      wcomment=sc.nextLine();
      
      //3번째 쿼리문완성
      msg = "insert into diary(wdate, weather, feel, contents, wcomment) "
      		+ "values(?,?,?,?,?)";   
      PST = CN.prepareStatement(msg);
      PST.setString(1,wdate);
      PST.setString(2,weather);
      PST.setString(3,feel);
      PST.setString(4,contents);
      PST.setString(5,wcomment);
       
       //4번째 서버에서 실행 executeUpdate("insert ~~")
       int OK = PST.executeUpdate();
       if (OK>0){
          System.out.println(wdate+"코드 저장성공했습니다");
       }else{ System.out.println(wdate+"코드 저장실패했습니다");}
	}catch (Exception e) {}
}//diaryInsert end

public void diarySelect () {
	try {
		System.out.println("프로그램 전체데이터 읽어오는중...잠시 기다려 주세요");
//	       Thread.sleep(3000);
	       msg = "select * from  diary " ; //문자열을 명령어 인식해서 실행하도록 Statement
	       RS = ST.executeQuery(msg);
	       
	       System.out.println("날짜\t날씨\t기분\t내용\t반성");
	       while(RS.next()==true) {
	          //필드접근해서 데이터가져올때 getXXX()
	          wdate = RS.getString("wdate");
	          weather = RS.getString("weather");
	          feel = RS.getString("feel");
	          contents = RS.getString("contents");
	          wcomment = RS.getString("wcomment");
	          System.out.println(wdate +"\t" + weather +"\t" + feel +"\t" + contents +"\t" + wcomment);
	       }
	}catch (Exception e) {}
	
}//diarySelect end

public void diaryDelete() {
	try {
		System.out.println("삭제할 날짜를 입력해주세요");
		wdate = sc.nextLine();
		System.out.println(wdate+"를 정말로 삭제하시겠습니까? 1.예 2.아니요");
		int check = sc.nextInt();
		if (check != 1) {
			System.out.println("삭제를 취소하였습니다");
			return;
		}
		msg = "delete from diary where wdate = ? ";
		PST = CN.prepareStatement(msg);
		PST.setString(1,wdate);
		int OK = PST.executeUpdate();
		if ( OK > 0 ) {
			System.out.println(wdate + "일기 삭제를 성공");
			}else { 
				System.out.println(wdate + "삭제할 일기가 없습니다");
			}
	}catch (Exception e) {}
}

public void diaryUpdate() {
	try {
		while(true) {
			System.out.println("수정할 날짜를 입력해주세요");
			wdate = sc.nextLine();
			msg="select wdate from diary where wdate = ? ";
			PST = CN.prepareStatement(msg);
			RS = PST.executeQuery(msg);
			PST.setString(1, wdate);
			
			while(RS.next()==true) {
			wdate = RS.getString("wdate");
			if (!wdate.equals(wdate)){
				System.out.println("다시 입력해주세요");
			}
			}
			System.out.println("1.날씨\t 2.기분\t 3.내용\t 4.반성 9.돌아가기");
			String cel = sc.nextLine();
			if (cel.equals("9")) {
				System.exit(1);
			}else {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
			switch(cel) {
			case "1" :
				System.out.println("날씨를 수정해주세요");
				weather = sc.nextLine();
				msg = "update diary set weather = '"+weather+"' where wdate = '"+wdate+ "' ";
			break;
			case "2" :
				System.out.println("기분을 수정해주세요");
				feel = sc.nextLine();
				msg = "update diary set feel = '"+feel+"' where wdate = '"+wdate+ "' ";
			break;
			case "3" :
				System.out.println("내용을 수정해주세요");
				contents = sc.nextLine();
				msg = "update diary set contents = '"+contents+"' where wdate = '"+wdate+ "'  ";
			break;
			case "4" :
				System.out.println("반성을 수정해주세요");
				wcomment = sc.nextLine();
				msg = "update diary set wcomment = '"+wcomment+"' where wdate = '"+wdate+ "' ";
			default :
				System.out.println("잘못된 입력입니다"); break;
			}//switch end
			int OK = ST.executeUpdate(msg);
			if ( OK > 0 ) {
				System.out.println("데이터 수정 성공"); break;
			}else { 
				System.out.println("데이터 수정 실패"); break;
			}
		}//while end
		
	}catch (Exception e) {System.out.println(e);}
	
}//diaryUpdate end


	public static void main(String[] args) {
		DiaryTest dt = new DiaryTest();
		dt.diaryConnect();
		dt.diaryMenu();
	}//main end

}//Class end
