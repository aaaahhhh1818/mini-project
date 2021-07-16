package com.lang.cal;


public class CalFuntion extends MainCalender {

  public void mainMenu() {
    connect();
    try {
      int sel = 0;
      exit: while(true) {
        System.out.println("\n[일정 메뉴]");
        System.out.println("┌───────────────────┐");  
        System.out.println("   1. 일정 입력");
        System.out.println("   2. 일정 삭제");
        System.out.println("   3. 일정 검색");
        System.out.println("   4. 전체 조회");
        System.out.println("   5. 개별 조회");
        System.out.println("   6. 달력 조회");
        System.out.println("   0. 전체 메뉴");
        System.out.println("└───────────────────┘");
        System.out.print("> ");
        sel = Integer.parseInt(sc.nextLine());
        //                if(sel==0) {
        //                  //          System.out.println("DB프로그램 종료");
        //                  System.exit(0);
        //      }//if end
        switch(sel) {
          case 1 : add(); break;
          case 2 : delete(); break;
          case 3 : dateView(); break;
          case 4 : allList(); break;
          case 5 : singleView(); break;
          case 6 : Scan_Insert(); break;
          case 0 : 
            System.out.println();
            break exit;
          default : System.out.println("메뉴를 다시 입력해주세요");
          break;
        }//switch end

      }//while end
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end
  }//메인메뉴 end

  public void add() throws Exception {

    System.out.println("\n[일정 입력]");
    dateYear();
    dateMonth();
    dateDay();

    String dDate = year+"-"+month+"-"+day;
    System.out.print("일정 제목 : ");
    String title = sc.nextLine();
    System.out.print("일정 내용 : ");
    String contents = sc.nextLine();
    System.out.print("일정 장소 : ");
    String location = sc.nextLine();
    String[] dateArr = dDate.split("-");
    int year = Integer.parseInt(dateArr[0]);
    int month = Integer.parseInt(dateArr[1]);
    int day = Integer.parseInt(dateArr[2]);
    arr[year-1900][month][day-1] = title;

    msg = "insert into cal(Calnumber, Caltitle, Calcontents, Callocation, Caldate) "
        + "values(calnum.nextval, ?, ?, ?, TO_DATE('" + dDate + "','yyyy-MM-dd'))";

    PST = CN.prepareStatement(msg);
    PST.setString(1, title);
    PST.setString(2, contents);
    PST.setString(3, location);


    int OK = PST.executeUpdate();
    //System.out.println(msg);
    if (OK>0) {
      System.out.println(dDate + "일정 저장 성공");
    } else {
      System.out.println(dDate + "일정 저장 실패");
    }
  }//add end

  public void delete() throws Exception {

    System.out.println("\n[일정 삭제]");
    System.out.print("삭제할 일정의 제목을 입력해주세요 : ");
    String title = sc.nextLine();
    if(title.equals("취소") || title==null || title.equals("") || title.equals(" ")) {
      System.out.println("취소 후 메뉴로 돌아가는중....");
      Thread.sleep(1000);
      mainMenu();
    } else{}//if end
    msg = "delete from cal where Caltitle = '" + title +"'" ;
    //System.out.println(msg);
    int OK = ST.executeUpdate(msg);
    if (OK>0) {
      System.out.println(title + "일정 삭제 성공");
      allList();
    } else {
      System.out.println(title + "일정 삭제 실패");
    }
  }//delete end

  public void allList() {
    try {
      msg = "select * from cal ";
      RS = ST.executeQuery(msg);

      System.out.println("\n[전체 조회]");

      System.out.println("\t\t 전체 일정 갯수 : " + total + "개");
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end

    listIn();
  }//allList end

  public void singleView() throws Exception {
    try {
      loopSingle : 
        while(true) {
          System.out.println("\n[개별 조회]");
          System.out.print("검색할 제목을 입력하세요 : ");
          String title = sc.nextLine();
          msg = "select * from cal where Caltitle like "+ "'%"+ title +"%'" ;
          if(title.equals("취소") || title==null || title.equals("") || title.equals(" ")) {
            System.out.println("취소 후 메인 메뉴로 돌아가는중....");
            Thread.sleep(1000);
            mainMenu();
          } if(title.equals(msg)) {
          }
          int OK = ST.executeUpdate(msg);
          if (OK==0) {
            System.out.println(title + "조회 실패");
          } else {
            System.out.println(title + "조회 성공");
            break loopSingle;
          }
        }//while end
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end

    listIn();
    update();
  }//single end

  public void dateView() {
    try {
      loopDate : 
        while(true) {
          System.out.println("\n[일정 검색]"); 

          dateYear();
          dateMonth();
          dateDay();

          msg = "select * from cal where Caldate = "+ "'"+ year +"-"+ month +"-"+ day + "'" ;
          //System.out.println(msg);
          int OK = ST.executeUpdate(msg);
          if (OK==0) {
            System.out.println("일정 검색 실패");
          } else {
            System.out.println("일정 검색 성공");
            break loopDate;
          }
        }//while end
    } catch (Exception ex) { System.out.println("에러이유 " + ex);
    }//try C end

    listIn();
    update();
  }//dateView end

}//C end
