package com.lang.cal;

import java.util.Date;

public class SubFuntion extends Cal_connect {

  public void listIn() {
    try {
      System.out.println("번 호\t제 목\t내 용\t장 소\t날 짜\t");
      RS = ST.executeQuery(msg);
      while(RS.next()==true) {

        String unumber = RS.getString("calnumber");
        String ucode = RS.getString("Caltitle");
        String uname = RS.getString("Calcontents");
        String utitle = RS.getNString("Callocation");
        Date udate = RS.getDate("Caldate");

        System.out.println(
            unumber + "\t" + ucode + "\t" + uname + "\t" + utitle + "\t" + udate + "\t");
      }

    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end
  }//listIn end

  public void dateYear() throws Exception {

    loopYear:while(true) {
      System.out.print("년도 입력 : ");
      year = sc.nextLine();
      if(year.equals("") || year==null) {
        System.out.println("오류!\t네 자릿수의 년도를 입력 하세요.");
        continue loopYear;
      } if(year.equals("취소")) {
        System.out.println("취소 후 메뉴로 돌아가는중....");
        Thread.sleep(1000);
        new CalFuntion().mainMenu();
      } else { a = Integer.parseInt(year);
      int length = (int)(Math.log10(a)+1);
      if(length != 4) {
        System.out.println("오류!\t네 자릿수의 년도를 입력 하세요.");
      } else {break;}
      }
    }//year while end
  }//dateYear end

  public void dateMonth() throws Exception {

    loopMonth:while(true) {
      System.out.print("월 입력 : ");
      month = sc.nextLine();
      if(month.equals("") || month==null) {
        System.out.println("오류!\t이 칸은 비울수 없습니다.");
        continue loopMonth;
      } if(month.equals("취소")) {
        System.out.println("취소 후 메뉴로 돌아가는중....");
        Thread.sleep(1000);
        new CalFuntion().mainMenu();
      } else {
        if(month.length()!=2) {
          System.out.println("오류!\t두 자릿수로 월을 입력 하세요.");
          continue loopMonth;
        } else { 
          a = Integer.parseInt(month);}//2else end
      }//1else end
      if(a < 1 || a > 12 ) {
        System.out.println("오류!\t 1~12 월을 입력 하세요.");
      } else {break loopMonth;}//3else end
    } //month while end
  }//dateMonth end

  public void dateDay() throws Exception {

    loopDay:while(true) {
      System.out.print("일 입력 : ");
      day = sc.nextLine();
      if(day.equals("") || day==null) {
        System.out.println("오류!\t이 칸은 비울수 없습니다.");
        continue loopDay;
      } if(day.equals("취소")) {
        System.out.println("취소 후 메뉴로 돌아가는중....");
        Thread.sleep(1000);
        new CalFuntion().mainMenu();
      } else {
        if(day.length()!=2) {
          System.out.println("오류!\t두 자릿수로 일을 입력 하세요.");
          continue loopDay;
        } else { 
          a = Integer.parseInt(day);}//2else end
      }//1else end
      if(a < 1 || a > 31 ) {
        System.out.println("오류!\t 1~31 일을 입력 하세요.");
      } else {break loopDay;}//3else end
    } //day while end
  }//dateDay end

  public void update() {
    try {
      System.out.print("수정할 번호 선택 : ");
      String tempnum = sc.nextLine();

      if(tempnum.equals("취소") || tempnum==null 
          || tempnum.equals("") || tempnum.equals(" ")) {
        System.out.println("취소 후 메뉴로 돌아가는중....");
        Thread.sleep(1000);
        new CalFuntion().mainMenu();
      }
      sc.nextLine();
      msg = "select * from cal where Calnumber =" + tempnum ;

      System.out.print("제목 수정 : ");
      String settitle = sc.nextLine();
      System.out.print("내용 수정 : ");
      String setcontents = sc.nextLine();
      System.out.print("장소 수정 : ");
      String setlocation = sc.nextLine();
      System.out.println("일정 수정");
      dateYear();
      dateMonth();
      dateDay();

      String setDate = year+"-"+month+"-"+day;
      String[] dateArr = setDate.split("-");
      int year = Integer.parseInt(dateArr[0]);
      int month = Integer.parseInt(dateArr[1]);
      int day = Integer.parseInt(dateArr[2]);
      arr[year-1900][month][day-1] = settitle;

      msg = "update cal set "
          + "Caltitle = '"+ settitle+"', "
          + "Calcontents = '"+ setcontents +"', "
          + "Callocation = '"+ setlocation +"', "
          + "Caldate = TO_DATE('" + setDate + "','yyyy-MM-dd')"
          +" where Calnumber = " + tempnum ;

      //System.out.println(msg);

      int OK = ST.executeUpdate(msg);
      if (OK>0) {
        System.out.println("일정 수정 성공");
      } else {
        System.out.println("일정 수정 실패");
      }
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end
  }//update end

}//C end
