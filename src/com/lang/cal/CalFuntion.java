package com.lang.cal;

public class CalFuntion extends SubFuntion{

        public void mainMenu() {
          
          try {
        int sel = 0;
        while(true) {
          System.out.println("\n1.입력  2.삭제  3.검색&수정  4.일정검색"
              + "  5.전체조회  6.개별검색  7.달력조회  0.종료");
          sel = Integer.parseInt(sc.nextLine());
          if(sel==0) {
            System.out.println("DB프로그램 종료");
            System.exit(0);
          }//if end
          switch(sel) {
            case 1 : add(); break;
            case 2 : delete(); break;
            case 3 : update(); break;
            case 4 : dateView(); break;            
            case 5 : allList(); break;
            case 6 : singleView(); break;
            case 7 : Scan_Insert(); break;
            default : System.out.println("올바른 메뉴를 선택해 주세요.");
            break;
          }//switch end
          
        }//while end
        } catch (Exception ex) { System.out.println("에러이유 " + ex);
        }//try C end
  }//메인메뉴 end
        
        public void add() throws Exception {

          System.out.print("일정입력(****.**.**)>>>");
          String dDate = sc.nextLine();
          if(dDate.equals("back")) {
            mainMenu();
          }//if end
      System.out.print("제목입력>>>");
      String title = sc.nextLine();
      System.out.print("내용입력>>>");
      String contents = sc.nextLine();
      System.out.print("장소입력>>>");
      String location = sc.nextLine();
      String[] dateArr = dDate.split("\\.");
      int year = Integer.parseInt(dateArr[0]);
      int month = Integer.parseInt(dateArr[1]);
      int day = Integer.parseInt(dateArr[2]);
      arr[year-1900][month][day-1] = title;
      
      msg = "insert into cal(Caltitle, Calcontents, Callocation, Caldate) "
          + "values(?, ?, ?, TO_DATE('" + dDate + "','yyyy-MM-dd'))";
      
      PST = CN.prepareStatement(msg);
        PST.setString(1, title);
        PST.setString(2, contents);
        PST.setString(3, location);
      
      
      int OK = PST.executeUpdate();
      System.out.println(msg);
      if (OK>0) {
        System.out.println("데이터 저장 성공");
      } else {
        System.out.println("데이터 저장 실패");
      }
        }//add end
        
        public void delete() throws Exception {

          System.out.println("삭제할 일정의 제목을 입력하세요 : ");
          String title = sc.nextLine();
          if(title.equals("back")) {
            mainMenu();
          }//if end
          msg = "delete from cal where Caltitle = " + title ;
          System.out.println(msg);
          int OK = ST.executeUpdate(msg);
          if (OK>0) {
            System.out.println("데이터 삭제 성공");
            allList();
          } else {
            System.out.println("데이터 삭제 실패");
          }
        }//delete end
        
        
        public void update() {
          try {

          System.out.print("제목 수정>>>");
          String settitle = sc.nextLine();
          System.out.print("내용 수정>>>");
          String setcontents = sc.nextLine();
          System.out.print("장소 수정>>>");
          String setlocation = sc.nextLine();
          System.out.print("일정 수정(****.**.**)>>>");
          String setDate = sc.nextLine();
          String[] dateArr = setDate.split("\\.");
          int year = Integer.parseInt(dateArr[0]);
          int month = Integer.parseInt(dateArr[1]);
          int day = Integer.parseInt(dateArr[2]);
          arr[year-1900][month][day-1] = settitle;
          
          msg = "update cal set "
              + "Caltitle = '"+ settitle+"'; "
              + "Calcontents = '"+ setcontents +"'; "
              + "Callocation = '"+ setlocation +"'; "
              + "Caldate = TO_DATE('" + setDate + "','yyyy-MM-dd');"
              +" where Caltitle = '" + settitle + "';" ;
          
          System.out.println(msg);
          
          int OK = ST.executeUpdate(msg);
          if (OK>0) {
            System.out.println("데이터 수정 성공");
          } else {
            System.out.println("데이터 수정 실패");
          }
          } catch (Exception ex) { System.out.println("에러이유 " + ex);
          }//try C end
        }//update end
        
        public void allList() {
          try {
      msg = "select * from cal ";
      RS = ST.executeQuery(msg);
      
      System.out.println("\t\t\t 전체 일정 갯수 : " + total);
          } catch (Exception ex) { System.out.println("에러이유 " + ex);
          }//try C end
          
          listIn();
        }//allList end
        
        public void singleView() throws Exception {
          try {
            loop : 
              while(true) {
            System.out.println("검색할 제목을 입력하세요.");
            String title = sc.nextLine();
            msg = "select * from cal where Caltitle like "+ "'%"+ title +"%'" ;
            if(title.equals("back")) {
              mainMenu();
            } if(title.equals(msg)) {
            }
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
          
          listIn();
        }//single end

        public void dateView() {
          
          try {
            loop : 
              while(true) {
            System.out.println("일정 검색"); 
              while(true) {
                System.out.println("연>>>");
              int year = sc.nextInt();
              int length = (int)(Math.log10(year)+1);
              if(length != 4) {
                System.out.print("오류!\t네 자릿수의 연도를 입력 하세요.");
                } else {break;}
              }
              while(true) {
              System.out.println("월>>>");
              int month = sc.nextInt();
              int length = (int)(Math.log10(month)+1);
              if(month < 1 && month >12 && length !=2) {
                System.out.print("오류!\t두 자릿수의 월을 입력 하세요.");
                } else {
                  String smonth = Integer.toString(month);
                  break;}
              }
//              while(true) {
//                System.out.println("일>>>");
//              day = sc.nextInt();
//              if(day.equals("back")) {
//                mainMenu();
//              } else { a = Integer.parseInt(day);
//              }
//              int length = (int)(Math.log10(a)+1);
//              if(length != 2) {
//                System.out.print("오류!\t두 자릿수의 일을 입력 하세요.");
//                } else {break;}
//              }
            msg = "select * from cal where Caldate = "+ "'"+ year +"-"+ smonth +"-"+  "'" ;
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
          
          listIn();
        }//dateView end

  }//C end
