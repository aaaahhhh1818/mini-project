package com.lang.cal;

import java.util.Date;
import java.util.Scanner;

public class BoardHandler implements Handler {

  static class Board {
    String title;
    String content;
    String password;
    int viewCount;
    Date createdDate;
  }

  String boardName;
  Scanner keyScan;
  ArrayList boardList = new ArrayList();

  BoardHandler(String boardName, Scanner keyScan) {
    this.boardName = boardName;
    this.keyScan = keyScan;
  }

  public void execute() {
    loop: while (true) {
      System.out.print(boardName + " 작성> [list, add, update, delete, view, back] ");
      String command = keyScan.nextLine();

      switch (command) {
        case "list": this.list(); break;
        case "add": this.add(); break;
        case "update": this.update(); break;
        case "delete": this.delete(); break;
        case "view": this.view(); break;
        case "back":
          break loop;
        default:
          System.out.println("지원하지 않는 명령입니다.");
      }
      System.out.println();
    }
  }

  void list() {
    System.out.println("[일기장 목록]");

    Object[] arr = this.boardList.toArray();
    int i = 0;
    for(Object item : arr) {
      Board board = (Board) item;
      System.out.printf("%d, %s, %s, %d\n",
          i,
          board.title,
          String.format("%1$tY-%1$tm-%1td", board.createdDate),
          board.viewCount);
      i++;
    }
  }

  void add() {
    System.out.println("[일기장 등록]");

    if (this.boardList.size == ArrayList.MAX_LENGTH) {
      System.out.println("더 이상 일기장을 추가할 수 없습니다.");
      return;
    } 

    Board board = new Board(); 

    System.out.print("제목: ");
    board.title = this.keyScan.nextLine();

    System.out.print("내용: ");
    board.content = this.keyScan.nextLine();

    System.out.print("비밀번호: ");
    board.password = this.keyScan.nextLine();

    board.createdDate = new Date(); 

    boardList.append(board);

    System.out.println("일기장을 등록했습니다.");

  }

  void update() {
    System.out.println("[일기장 변경]");
    System.out.print("번호? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= this.boardList.size) {
      System.out.println("무효한 일기장 번호입니다.");
      return;
    }

    Board board = (Board) this.boardList.retrieve(index);

    System.out.printf("제목(%s)? ", board.title);
    String title = this.keyScan.nextLine();

    System.out.printf("내용(%s)? ", board.content);
    String content = this.keyScan.nextLine();

    System.out.println("정말 변경하시겠습니까?(y/N) ");
    
    if (!this.keyScan.nextLine().equals("y")) {
      System.out.println("일기장 변경을 취소하였습니다.");
      return;
    }

    board.title = title;
    board.content = content;

    System.out.println("일기장을 변경하였습니다.");
  }

  void delete() {
    System.out.println("[일기장 삭제]");
    System.out.print("번호? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= this.boardList.size) {
      System.out.println("무효한 일기장 번호입니다.");
      return;
    }

    System.out.println("정말 삭제하시겠습니까?(y/N) ");
    if (!this.keyScan.nextLine().equals("y")) {
      System.out.println("일기장 삭제를 취소하였습니다.");
      return;
    }

    this.boardList.remove(index);

    System.out.println("일기장을 삭제하였습니다.");
  }

  void view() {
    System.out.println("[일기장 조회]");
    System.out.print("번호? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= this.boardList.size) {
      System.out.println("무효한 일기장 번호입니다.");
      return;
    }

    Board board = (Board) this.boardList.retrieve(index);

    board.viewCount++;

    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("조회수: %s\n", board.viewCount);
    System.out.printf("등록일: %1$tY-%1$tm-%1td %1$tH:%1$tM:%1$tS\n", board.createdDate);
  }
}
