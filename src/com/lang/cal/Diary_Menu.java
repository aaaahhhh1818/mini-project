package com.lang.cal;

public class Diary_Menu {
  Diary da = new Diary();

  void run() throws Exception {
    da.diaryConnect();
    da.diaryMenu();
    //    da.diaryInsert();
    //    da.diaryList();
    //    da.diaryView();
    //    da.diaryDelete();
    //    da.diaryUpdate();

  }
}
