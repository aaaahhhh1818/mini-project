package com.lang.cal;

import java.util.Calendar;
import java.util.Scanner;

public class MainCal {

  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    MainCal cal = new MainCal();

    menuLoop: while (true) {
      System.out.println("[메뉴선택]");
      System.out.println("1. 일정");
      System.out.println("2. 일기");
      System.out.println("3. 달력");
      System.out.print("메뉴를 선택하시오.(종료: q) [1..3] ");
      String menuNo = sc.nextLine();

      switch (menuNo) {
        case "1":
          //일정();
          break;
        case "2":
          //일기();
          break;
        case "3":
          cal.mainCal();
        case "q":
          break menuLoop;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다.");
      }
      System.out.println();
    }
  }

  public void mainCal() {

    System.out.print("년도 입력>> ");
    int year = Integer.parseInt(sc.nextLine());
    System.out.print("월 입력>> ");
    int month = Integer.parseInt(sc.nextLine());

    Calendar calStart = Calendar.getInstance();
    Calendar calEnd = Calendar.getInstance();

    calStart.set(year, month-1, 1);
    calEnd.set(year, month,1);
    calEnd.add(Calendar.DATE, -1);

    int LAST_DAY = calStart.get(Calendar.DAY_OF_WEEK);
    int WEEK = calEnd.get(Calendar.DATE);

    System.out.println("[ " + year + "년 " + month + "월 ]");
    System.out.println(" 일\t월\t화\t수\t목\t금\t토 ");
    System.out.println("---------------------------------------------------");

    for(int i = 1; i < LAST_DAY; i++) {
      System.out.print("\t");
    }

    int date = LAST_DAY - 1;
    for(int i = 1; i <= WEEK; i++) {
      System.out.print(i + "\t");
      date++;

      if(date == 7) {
        date = 0;
        System.out.println("\n");
      }
    }
    sc.close();
  }




}
