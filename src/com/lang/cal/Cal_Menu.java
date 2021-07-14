package com.lang.cal;

public class Cal_Menu {
  Cal cl = new Cal();
  MainCalender MC = new MainCalender();

  void run() throws Exception {
    
  cl.connect();
  cl.add();
  cl.delete();
  cl.AllList();
  cl.view();
  
  MC.Scan_Insert();
  
  }
}
