package Linear.Stack;

// 배열을 이용한 구현

import java.util.Arrays;

class MyStack2 {
    int[] arr;
    int top = -1;

    MyStack2(int size) {
        arr = new int[size];
    }

    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (this.top == this.arr.length - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void push(int data) {
        if (this.isFull()) {
            System.out.println("Stack is Full!");
            return;
        }

        this.top++;
        this.arr[this.top] = data;
    }

    public Integer pop() {
        if (this.isEmpty()) {
            System.out.println("Stack is Empty!");
            return null;
        }

        int data = arr[top];
        this.top--;

        return data;
    }

    public Integer peek() {
        if (this.isEmpty()) {
            System.out.println("Stack is Empty!");
            return null;
        }

        return this.arr[this.top];
    }

    public void printStack() {
//        for (int i = 0; i < this.top + 1; i++) {
//            System.out.print(this.arr[i] + " ");
//        }
//        System.out.println();
        System.out.println(Arrays.toString(this.arr));
    }
}

public class prac2 {
    public static void main(String[] args) {
        // Test code
        MyStack2 myStack = new MyStack2(5);
        myStack.isEmpty();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.printStack();               // 1, 2, 3, 4, 5

        System.out.println(myStack.peek()); // 5
        myStack.printStack();               // 1, 2, 3, 4, 5

        System.out.println(myStack.pop());  // 5
        System.out.println(myStack.pop());  // 4
        myStack.printStack();               // 1, 2, 3

        System.out.println(myStack.pop());  // 3
        System.out.println(myStack.pop());  // 2
        System.out.println(myStack.pop());  // 1
        myStack.printStack();
    }
}
