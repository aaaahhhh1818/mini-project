package com.lang.cal;

public class Cal_Menu {
  CalFuntion cl = new CalFuntion();
  MainCalender MC = new MainCalender();

  void run() throws Exception {
    
  cl.connect();
  cl.mainMenu();
  cl.add();
  cl.delete();
  cl.allList();
  cl.singleView();
  
  MC.Scan_Insert();
  
  }
}
