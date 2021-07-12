package com.eomcs;

import java.util.Date;
import java.util.Scanner;

public class BoardHandler implements Handler {

  // 한 개의 게시물을 담을 복합 데이터 변수를 설계 (식판)
  static class Board {
    String title;
    String content;
    String password;
    int viewCount;
    Date createdDate;
  }

  // 게시판을 구분하기 위해 게시판 이름을 저장할 인스턴스 변수를 준비한다.
  String boardName;
  Scanner keyScan;
  ArrayList boardList = new ArrayList();

  // 생성자
  // => 인스턴스를 생성할 떄 반드시 호출해야 하는 메서드
  // => 메서드 명은 클래스 이름과 같아야 한다.
  // => 리턴 타입은 지정하지 말아야 한다.
  // => 인스턴스를 생성할 떄 반드시 설정해야 하는 값은 파라미터로 받는다.
  BoardHandler(String boardName, Scanner keyScan) {
    this.boardName = boardName;
    this.keyScan = keyScan;
  }

  public void execute() {
    loop: while (true) {
      System.out.print(boardName + "/게시글 관리> ");
      String command = keyScan.nextLine();

      //if (command.equals("quit")) { // 문자열을 조건으로 받음
      //  break;
      //}

      //int index = 0; // 반복문이 반복된다고 계속 생성되지 않음
      // while문이 끝난 뒤에는 해당 변수를 사용할 수 없음
      // (반복문 안에 넣어 제약조건을 걸어주는 것이 낫다!)
      switch (command) {
        case "list": this.list(); break;
        case "add": this.add(); break;
        case "update": this.update(); break;
        case "delete": this.delete(); break;
        case "view": this.view(); break;
        case "back":
          break loop; // switch문만 빠져나가기 때문에 while문 앞에 label 붙여야함
        default:
          System.out.println("지원하지 않는 명령입니다.");
      }
      System.out.println();
    }
  }

  void list() {
    System.out.println("[게시글 목록]");

    Object[] arr = this.boardList.toArray();
    int i = 0;
    for(Object item : arr) {
      Board board = (Board) item; // 형변환 해주기
      System.out.printf("%d, %s, %s, %d\n",
          i,
          board.title,
          String.format("%1$tY-%1$tm-%1td", board.createdDate),
          board.viewCount);
      i++;
    }
  }

  void add() {
    System.out.println("[게시글 등록]");

    if (this.boardList.size == ArrayList.MAX_LENGTH) {
      System.out.println("더 이상 게시글을 추가할 수 없습니다.");
      return;
    } // 저장된 게시글 수가 저장할 수 있는 배열의 최대 개수를 넘었을 때

    // 한 개의 게시글 데이터를 저장할 변수를 준비
    Board board = new Board(); // Board 설계도에 따라 변수를 만들고 그 주소를 리턴

    System.out.print("제목: ");
    board.title = this.keyScan.nextLine();

    System.out.print("내용: ");
    board.content = this.keyScan.nextLine();

    System.out.print("비밀번호: ");
    board.password = this.keyScan.nextLine();

    board.createdDate = new Date(); // 현재 날짜와 시간을 생성하여 배열에 저장

    // 배열에 게시글 정보가 담긴 객체(식판)을 넣는다
    boardList.append(board);
    //size++; //게시물 저장 인덱스 증가

    System.out.println("게시글을 등록했습니다.");

  }

  void update() {
    System.out.println("[게시글 변경]");
    // 게시글 변경하기
    System.out.print("번호? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= this.boardList.size) {
      System.out.println("무효한 게시글 번호입니다.");
      return;
    }

    Board board = (Board) this.boardList.retrieve(index);

    // 변경할 내용 작성
    System.out.printf("제목(%s)? ", board.title);
    String title = this.keyScan.nextLine();

    System.out.printf("내용(%s)? ", board.content);
    String content = this.keyScan.nextLine();

    System.out.println("정말 변경하시겠습니까?(y/N) ");
    // String input = keyScan.nextLine();
    // 불필요한 변수를 선언하지말고 임시변수 사용!
    if (!this.keyScan.nextLine().equals("y")) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    board.title = title;
    board.content = content;

    System.out.println("게시글을 변경하였습니다.");
  }

  void delete() {
    System.out.println("[게시글 삭제]");
    System.out.print("번호? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= this.boardList.size) {
      System.out.println("무효한 게시글 번호입니다.");
      return;
    }

    System.out.println("정말 삭제하시겠습니까?(y/N) ");
    if (!this.keyScan.nextLine().equals("y")) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    this.boardList.remove(index);

    //    // 게시물 삭제 인덱스 땡기기(?)
    //    for (int i = index; i < size-1; i++ ) {
    //      boards[i] = boards[i + 1];
    //      //      titles[i] = titles[i + 1];
    //      //      contents[i] = contents[i + 1];
    //      //      viewCounts[i] = viewCounts[i + 1];
    //      //      createdDates[i] = createdDates[i + 1];
    //    }

    // 삭제했기 때문에 size 줄여줘야함
    //size--;

    System.out.println("게시글을 삭제하였습니다.");
  }

  void view() {
    System.out.println("[게시글 조회]");
    System.out.print("번호? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= this.boardList.size) {
      System.out.println("무효한 게시글 번호입니다.");
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
