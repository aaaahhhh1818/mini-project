package com.lang.cal;

import java.util.Scanner;

public class MainCal {

  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    menuLoop: while (true) {
      System.out.println("\n[다이어리 메뉴]");
      System.out.println("┌─────────────────┐");  
      System.out.println("   1. 일정 관리");
      System.out.println("   2. 일기 관리");
      System.out.println("   q. 종료     ");
      System.out.println("└─────────────────┘");
      System.out.print("> ");
      String menuNo = sc.nextLine();

      switch (menuNo) {
        case "1": new Cal_Menu().run(); break;
        case "2": new Diary_Menu().run(); break;
        case "q": 
          System.out.println("다이어리 프로그램 종료");
          break menuLoop;
        default:
          System.out.println("\n-메뉴 번호가 옳지 않습니다");
      }
    }
  }
}