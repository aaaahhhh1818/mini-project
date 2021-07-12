package com.eomcs;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Memo extends Frame implements WindowListener, ActionListener {
  private Button btnExit = new Button(" EXIT ");
  private Button btnSend = new Button(" SEND ");
  private TextField tf = new TextField(20); //한줄입력
  private TextArea ta = new TextArea(); //가로row*세로column
  private Font ff = new Font("consolas", Font.BOLD, 20);
  private Panel pan = new Panel(); //레이아웃 받침대=쟁반 역할

  public Memo() {
    //초간단 메모장
    pan.add(tf); pan.add(btnSend); pan.add(btnExit);

    ta.setFont(ff);
    this.add("Center", ta);
    this.add("South", pan); //pan=Panel판넬

    //이벤트연결 리스너추가
    this.addWindowListener(this); //앞this  뒤this
    tf.addActionListener(this);
    btnSend.addActionListener(this);
    btnExit.addActionListener(this);

    //프레임의 메소드값변경
    ta.setBackground(Color.YELLOW);
    this.setSize(400, 500);
    this.setTitle("초간단메모장");
    this.setVisible(true);
  }//end

  @Override public void windowClosing(WindowEvent e) {myExit();}
  @Override public void windowOpened(WindowEvent e) { }
  @Override public void windowClosed(WindowEvent e) { }
  @Override public void windowIconified(WindowEvent e) { }
  @Override public void windowDeiconified(WindowEvent e) { }
  @Override public void windowActivated(WindowEvent e) { }
  @Override public void windowDeactivated(WindowEvent e) { }

  @Override
  public void actionPerformed(ActionEvent ae) {
    //actionPerformed메소드는 버튼클릭, 이미지클릭, 입력란enter
    if(ae.getSource()==tf) { //tf한줄입력란
      myText();
    }else if(ae.getSource()==btnSend) {
      myText();
    }else if(ae.getSource()==btnExit) {
      myExit();
    }else {}
  }//end

  public void myText() {
    //첫번째 tf입력란 데이터가져오기
    //두번째 tf내용을 ta에 추가 후 tf내용삭제, tf입력란에 초점
    String data = tf.getText();
    ta.append(data+"\n");
    tf.setText(""); //내용삭제
    tf.requestFocus();

  }//end

  public void myExit() {
    System.out.println("초간단 메모장 프로그램을 종료합니다 1:16 2:38");
    System.exit(1);
  }//end

  public static void main(String[] args) {
    Memo pad = new Memo(); //생성자호출
    //pad.windowClosing(null);
  }//end

}//class END
