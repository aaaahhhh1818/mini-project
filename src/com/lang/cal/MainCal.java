package com.lang.cal;

import java.util.Calendar;
import java.util.Scanner;

public class MainCal {

  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    menuLoop: while (true) {
      System.out.println("\n┌────────────────");  
      System.out.println("│ 1. 일정 관리");
      System.out.println("│ 2. 일기 관리");
      System.out.println("│ 3. 달력 보기");
      System.out.println("│ q. 종료     ");
      System.out.println("└────────────────");
      System.out.print("[선택] ");
      String menuNo = sc.nextLine();

      switch (menuNo) {
        case "1": new Cal_Menu().run(); break;
        case "2": new Diary_Menu().run(); break;
        case "3":  break;
        case "q": break menuLoop;
        default:
          System.out.println("\n메뉴 번호가 옳지 않습니다.\n다시 선택해주세요.");
      }
      System.out.println();
    }
  }

  public void mainCal() {
    System.out.println();
    System.out.print("[년도 입력] ");
    int year = Integer.parseInt(sc.nextLine());
    System.out.print("[월 입력] ");
    int month = Integer.parseInt(sc.nextLine());

    Calendar calStart = Calendar.getInstance();
    Calendar calEnd = Calendar.getInstance();

    calStart.set(year, month-1, 1);
    calEnd.set(year, month, 1);
    calEnd.add(Calendar.DATE, -1);

    int LAST_DAY = calStart.get(Calendar.DAY_OF_WEEK);
    int WEEK = calEnd.get(Calendar.DATE);

    System.out.println();
    System.out.println("\t\t  [ " + year + "년 " + month + "월 ]");
    System.out.println("─────────────────────────────────────────────────────");
    System.out.println("일\t월\t화\t수\t목\t금\t토 ");
    System.out.println("─────────────────────────────────────────────────────");

    for(int i = 1; i < LAST_DAY; i++) {
      System.out.print("\t");
    }

    int date = LAST_DAY - 1;
    for(int i = 1; i <= WEEK; i++) {

      System.out.printf("%2d □\t", i);
      date++;

      if(date == 7) {
        date = 0;
        System.out.println("\n");
      }
    }

  }





}