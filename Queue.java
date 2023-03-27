package com.company;

public class Queue {
    private int dimension = 13;
    String[] Name;
    int[] Number;
    private int front;
    private int rear;

    public Queue() {
        this.Name = new String[this.dimension];
        this.Number = new int[this.dimension];
        this.front = 0;
        this.rear = 0;
    }

    public boolean isFull() {
        if (this.front == 1 && this.rear == this.dimension) {
            return true;
        } else {
            return this.front == this.rear;
        }
    }

    public boolean isEmpty() {
        return this.front == 0;
    }

    public void enQueueString(String name) {
        if (this.isFull()) {
            System.out.println("The Queue is full");
        } else {
            if (this.front == 0) {
                this.front = 1;
            }

            this.rear %= this.dimension;
            this.Name[this.rear] = name;
        }

    }

    public void enQueueNumber(int num) {
        if (this.isFull()) {
            System.out.println("The Queue is full");
        } else {
            if (this.front == 0) {
                this.front = 1;
            }

            this.rear %= this.dimension;
            this.Number[this.rear] = num;
        }

    }

    public String deQueueString() {
        String name = "e";
        if (this.isEmpty()) {
            System.out.println("The Queue is empty");
        } else {
            name = this.Name[this.front];
            this.Name[this.front] = null;
            this.front %= this.Name.length;
        }

        return name;
    }

    public int deQueueNumber() {
        int num = 1;
        if (this.isEmpty()) {
            System.out.println("The Queue is empty");
        } else {
            num = this.Number[this.front];
            this.Number[this.front] = 1;
            this.front %= this.Number.length;
        }

        return num;
    }
}
