package com.lang.cal;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

class CalendarForm extends Frame implements ActionListener {
  String[] day = new String[] { "일", "월", "화", "수", "목", "금", "토" };
  Font font;
  Font day_font;
  Panel title_pan; // 년도와 월이 들어갈 패널
  Panel day_pan; // 월~일까지 들어갈 패널
  Panel date_pan; // 1~31일까지 들어갈 패널
  Label year_value;
  Label year_label;
  Label month_value;
  Label month_label;
  Label day_label;
  MyFrame myframe;
  String y_value;
  String m_value;
  Button day_btn;
  Button left_btn;
  Button right_btn;
  int year;
  int month;
  int now_year;
  int now_month;
  int now_day;

  CalendarForm(String Title, MyFrame f) {
    super(Title);
    setLayout(new BorderLayout());
    myframe = f;
    font = new Font("굴림체", Font.BOLD, 21);
    day_font = new Font("굴림체", Font.BOLD, 18);
    title_pan = new Panel();
    day_pan = new Panel();
    day_pan.setLayout(new GridLayout(0, 7));
    date_pan = new Panel();
    date_pan.setLayout(new GridLayout(0, 7));
    year_value = new Label();
    year_label = new Label("년");
    month_value = new Label();
    month_label = new Label("월");
    month_value = new Label();
    left_btn = new Button();
    left_btn.setLabel("◀");
    right_btn = new Button();
    right_btn.setLabel("▶");
    year_value.setFont(font);
    year_label.setFont(font);
    month_value.setFont(font);
    month_label.setFont(font);

    right_btn.addActionListener(this); // 다음달보기 버튼클릭
    left_btn.addActionListener(this); // 이전달보기 버튼클릭

    for (int count = 0; count < 7; count++) {
      day_label = new Label(day[count], Label.CENTER);
      day_label.setFont(font);
      day_pan.add(day_label);
      add(day_pan, "Center");
    }
  }

  void setvalue() {
    y_value = myframe.year_cho.getSelectedItem();
    m_value = myframe.month_cho.getSelectedItem();

    year = Integer.parseInt(y_value);
    month = Integer.parseInt(m_value);
  }

  void setbutton() {
    Calendar calendar = Calendar.getInstance();
    now_year = calendar.get(Calendar.YEAR); // 2015
    now_month = calendar.get(Calendar.MONTH) + 1; // 1
    now_day = calendar.get(Calendar.DATE); // 21

    year_value.setText("  " + year);
    month_value.setText("" + month);
    calendar.set(year, month - 1, 1);
    int last_day = calendar.getActualMaximum(Calendar.DATE);
    System.out.println(month + "월");

    int day = 1;
    int first_day = calendar.get(Calendar.DAY_OF_WEEK);
    int u = first_day - 2;
    for (int a = 1; a < 6; a++) {
      for (int b = 1; b < 8; b++) {
        u++;
        if (u % 7 == 0) {
          if (day_btn != null) {
            day_btn.setFont(day_font);
          }
          System.out.println();
        }
        if (day <= last_day) {
          if (day == 1) {
            for (int t = 1; t < first_day; t++) {
              day_btn = new Button(" ");
              day_btn.setBackground(Color.white);
              date_pan.add(day_btn);
              System.out.print("\t");
            }
          }

          day_btn = new Button("" + day);
          int aaa = Integer.parseInt(day_btn.getActionCommand());
          int bbb = year;
          int ccc = month;
          if (bbb == now_year && ccc == now_month && aaa == now_day) {
            day_btn.setBackground(Color.pink); // 현재날짜표시
          } else {
            day_btn.setBackground(Color.white);
          }
          day_btn.setFont(day_font);

          if (u % 7 == 0) {
            day_btn.setForeground(Color.red);
            day_btn.setFont(day_font);
          }
          date_pan.add(day_btn);
          System.out.print(day + "\t");
          day++;

        } else {
          continue;
        }
      }

    }
  }

  void addComponent() {
    title_pan.add(left_btn);
    title_pan.add(year_value);
    title_pan.add(year_label);
    title_pan.add(month_value);
    title_pan.add(month_label);
    title_pan.add(right_btn);
    add(title_pan, "North");
    date_pan.setLayout(new GridLayout(0, 7));
    date_pan.add(day_btn);
    day_pan.getComponent(0).setForeground(Color.red);
    day_pan.getComponent(6).setForeground(Color.blue);
    int com_num = date_pan.getComponentCount();
    for (int a = 6; a < com_num; a = a + 7) {
      date_pan.getComponent(a).setForeground(Color.blue);
    }
    add(date_pan, "South");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "▶") {
      CalendarForm c = new CalendarForm("달력보기", myframe);
      c.setVisible(true);
      if (month >= 0 && month < 12) {
        c.year = year;
        c.month = month += 1;
      } else {
        c.year = year + 1;
        c.month = 1;
      }
      c.setbutton();
      c.addComponent();
      c.pack();
      setVisible(false);
      c.setResizable(false);
      c.setLocation(getLocation());
      System.out.println("다음달보기");

    } else {
      CalendarForm c = new CalendarForm("달력보기", myframe);
      c.setVisible(true);
      if (month > 1 && month <= 12) {
        c.year = year;
        c.month = month -= 1;
      } else {
        c.year = year - 1;
        c.month = 12;
      }
      c.setbutton();
      c.addComponent();
      c.pack();
      setVisible(false);
      c.setResizable(false);
      c.setLocation(getLocation());
      System.out.println("이전달보기");
    }

  }
}

class MyFrame extends Frame implements ActionListener {
  Choice year_cho;
  Choice month_cho;
  Label year_label, month_label;
  Button view_cal;

  public MyFrame(String Title) {
    super(Title);
    setLayout(new FlowLayout());
    year_cho = new Choice();
    month_cho = new Choice();
    year_label = new Label("년");
    month_label = new Label("월");
    for (int year = 2015; year < 2100; year++) {
      year_cho.add("" + year);
    }
    for (int month = 1; month <= 12; month++) {
      month_cho.add("" + month);
    }
    view_cal = new Button("달력보기");

    view_cal.addActionListener(this);

    add(year_cho);
    add(year_label);
    add(month_cho);
    add(month_label);
    add(view_cal);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "달력보기") {
      CalendarForm c = new CalendarForm("달력보기", this);

      c.setBounds(600, 200, 320, 250);
      c.setVisible(true);
      c.setvalue();
      c.setbutton();
      c.addComponent();
      c.pack();
      setVisible(false);
      c.setResizable(false);

    }
  }
}

public class Test2{
  public static void main(String[] args) {
    MyFrame f = new MyFrame("달력");
    f.setBounds(400, 200, 300, 200);
    f.setVisible(true);

  }
}

