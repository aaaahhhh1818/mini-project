package com.lang.cal;

import java.util.Scanner;

public class App {

  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {

    BoardHandler boardHandler = new BoardHandler("일기장", keyScan);

    MemberHandler memberHandler = new MemberHandler(keyScan);

    ComputeHandler computeHandler = new ComputeHandler(keyScan);

    menuLoop: while (true) {
      System.out.println("--------------[다이어리]---------------");
      System.out.println("           1: 일기장 관리");
      System.out.println("           2: 회원 관리");
      System.out.print("메뉴를 선택하시오.(종료: quit) [1..2] ");
      String menuNo = keyScan.nextLine();

      switch (menuNo) {
        case "1":
          boardHandler.execute();
          break;
        case "2":
          memberHandler.execute();
          break;
        case "quit":
          break menuLoop;
        default:
          System.out.println("메뉴 번호가 옳지 않습니다.");
      }
      System.out.println();
    }

    keyScan.close();

    System.out.println("안녕히 가세요!");
  }


}
