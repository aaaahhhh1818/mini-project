package com.lang.cal;

public class ArrayList {

  static final int MAX_LENGTH = 100;

  Object[] list = new Object[MAX_LENGTH];
  int size = 0;

  void append(Object obj) {
    this.list[this.size++] = obj;
  }

  Object[] toArray() {
    Object[] arr = new Object[this.size];

    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }

  Object retrieve(int index) { // 해당 인덱스에 있는 값을 조회
    return this.list[index];
  }

  void remove(int index) {
    for (int i = index; i < this.size-1; i++ ) {
      this.list[i] = this.list[i + 1];
    }
    this.size--;
  }
}
