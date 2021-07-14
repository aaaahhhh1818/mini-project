package com.lang.cal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Diary{
	
	Connection CN=null; //DB서버연결정보 서버ip주소 계정id,pwd
	Statement ST=null;  //ST=CN.createStatement()명령어생성 삭제,신규등록,조회하라
	ResultSet RS=null;  //select조회결과값 전체데이터를 기억합니다
	String msg="isud=crud쿼리문기술";
	Scanner sc = new Scanner(System.in);
	
	public void diaryConnect() {
	      
		try {   
			Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클드라이브로드
			String url = "jdbc:oracle:thin:@175.210.92.176:1521:XE" ;
			CN=DriverManager.getConnection("jdbc:oracle:thin:@175.210.92.176:1521:xe","hhwanseung","1234");
			System.out.println("오라클 드라이브및 서버연결성공 ");
			ST = CN.createStatement();

	}catch (Exception e) { }
	}
	
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
			}
		}
	}
	
	public void diaryInsert () {
		try {
		//ST = CN.createStatement();
	       
	       //두번째 키보드에서 데이터 입력
	      System.out.print("\n오늘 날짜>>>"); 
	      String wdate=sc.nextLine();
	      System.out.print("날씨>>>"); 
	      String weather=sc.nextLine();  
	      System.out.print("기분>>>"); 
	      String feel=sc.nextLine();
	      System.out.print("내용>>>"); 
	      String contents=sc.nextLine();
	      System.out.print("반성>>>"); 
	      String wcomment=sc.nextLine();
	      
	      //3번째 쿼리문완성
	      msg="insert into diary(wdate, weather, feel, contents, wcomment) "
	      		+ "values('"+wdate+"','"+weather+"','"+feel+"','"+contents+"','"+wcomment+"')";   
	       System.out.println(msg);
	       
	       //4번째 서버에서 실행 executeUpdate("insert ~~")
	       int OK = ST.executeUpdate(msg);
	       if (OK>0){
	          System.out.println(wdate+"코드 저장성공했습니다");
	       }else{ System.out.println(wdate+"코드 저장실패했습니다");}
		}catch (Exception e) {}
	}
	public void diarySelect () {
		try {
			System.out.println("프로그램 전체데이터 읽어오는중...잠시 기다려 주세요");
//		       Thread.sleep(3000);
		       msg = "select * from  diary " ; //문자열을 명령어 인식해서 실행하도록 Statement
		       RS = ST.executeQuery(msg);
		       
		       System.out.println("날짜\t날씨\t기분\t내용\t반성");
		       while(RS.next()==true) {
		          //필드접근해서 데이터가져올때 getXXX()
		          String udate = RS.getString("wdate");
		          String uweather = RS.getString("weather");
		          String ufeel = RS.getString("feel");
		          String ucontents = RS.getString("contents");
		          String ucomment = RS.getString("wcomment");
		          System.out.println(udate +"\t" + uweather +"\t" + ufeel +"\t" + ucontents +"\t" + ucomment);
		       }
		}catch (Exception e) {}
		
	}
	public void diaryDelete() {
		try {
			System.out.println("삭제할 날짜를 입력해주세요");
			String uwdate = sc.nextLine();
			msg = "delete from diary where wdate = '"+uwdate+"' ";
			int OK = ST.executeUpdate(msg);
			if ( OK > 0 ) {
				System.out.println(uwdate + "데이터 수정 성공");
				}else { 
					System.out.println(uwdate + "데이터 수정 실패");
				}
		}catch (Exception e) {}
	}
	
	public void diaryUpdate() {
		try {
			while(true) {
				System.out.println("수정할 날짜를 입력해주세요");
				String uwdate = sc.nextLine();
				msg="select wdate from diary where wdate = '"+uwdate+"' ";
				RS = ST.executeQuery(msg);
				while(RS.next()==true) {
				String udate = RS.getString("wdate");
				if (!uwdate.equals(udate)){
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
					String uweather = sc.nextLine();
					msg = "update diary set weather = '"+uweather+"' where wdate = '"+uwdate+ "' ";
				break;
				case "2" :
					System.out.println("기분을 수정해주세요");
					String ufeel = sc.nextLine();
					msg = "update diary set feel = '"+ufeel+"' where wdate = '"+uwdate+ "' ";
				break;
				case "3" :
					System.out.println("내용을 수정해주세요");
					String ucontents = sc.nextLine();
					msg = "update diary set contents = '"+ucontents+"' where wdate = '"+uwdate+ "'  ";
				break;
				case "4" :
					System.out.println("반성을 수정해주세요");
					String ucomment = sc.nextLine();
					msg = "update diary set wcomment = '"+ucomment+"' where wdate = '"+uwdate+ "' ";
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
		
	}

   //신규등록,전체출력성공후 메소드생성해서 최대한 메소드활용
}//class END
