package com.lang.cal;

import java.util.Scanner;

public class MainCalender {
  
  static char[] weeks = {'일', '월', '화', '수', '목', '금', '토'};
  static int [][] days = {{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, // 평년
                          { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}}; // 윤년
  static String [][] cals = {{ "\t, \t, \t, \t, \t, \t, \t, \t, \t, \t, \t, \t"}, // 평년
                          { "\t, \t, \t, \t, \t, \t, \t, \t, \t, \t, \t, \t"}}; // 윤년
  
  static int isLeap(int year) {
    return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0 ;
  }//e
  
  static int fristDayWeek(int year, int month) {
    int weeks = 0;
    int leapSum = 0;
    int yridx = isLeap(year);
    int date = 0;
    
    for (int i = 1900 ; i <year ; i++) {
      leapSum += isLeap(i);
    } weeks = (year - 1900 + leapSum) % 7;
    
    for(int i = 0; i < month - 1; i++) {
      date += days[yridx][i];
    } weeks += (date + 1) % 7;
    
    return weeks % 7;
  }//e
  
  static void Show_Calender(int year, int month) {
    int yridx = isLeap(year);
    int lastday = days[yridx][month - 1];
    int Firstweek = fristDayWeek(year,month);
    
    System.out.printf(
        "년도 : %d   월 : %d\t %s\t 첫 요일 위치 : %d (일:0 월:1 화:2 수:3 목:4 금:5 토:6) \n",
        year, month, isLeap(year)==1 ?"<윤년>":"<평년>", Firstweek);
    
    for (char Value : weeks) {
      System.out.printf("%1$c\t", Value);
    } System.out.println();
    
    for (int i = 0; i < Firstweek; i++) {
      System.out.printf("\t");
    }
    
    for(int i = 1; i<= lastday; i++) {
      System.out.printf("%d\t", i);
      if((Firstweek + i -1) % 7 == 6)
        System.out.println();
    }
  }//e
  
  static void Scan_ERROR() {
    System.out.printf("\n 올바른 값을 입력하세요");
    System.exit(0);
  }//e
  
  public static void main(String[] args) {
    int year = 0;
    int month = 0;
    
    Scanner sc = new Scanner(System.in);
    System.out.print("년도를 입력(1900년 이후) : ");
    year = sc.nextInt();
    if(year < 1900) {
      Scan_ERROR();
    }
    
    System.out.print("월을 입력 (1 ~ 12월) : ");
    month = sc.nextInt();
    if (month < 1 || month > 12) {
      Scan_ERROR();
    }
    
    Show_Calender(year, month);
  }//M end
  
}//C end
