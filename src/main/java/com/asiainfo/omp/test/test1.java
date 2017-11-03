package com.asiainfo.omp.test;

class Wo{
    int m;
    Wo(){
        add(1);
    }
    public void add(int i){
        m+=i;
    }
    public void print(){
        System.out.println(m);
    }
}
class No extends Wo{
    No(){
        add(2);
    }
    public void add(int i){
        m+=i*2;
    }
    public void print(){
        System.out.println(m);
    }
}
class Try{
    public static void add(Wo w){
        w.add(6);
        w.print();
    }
    public static void main(String args[]){
        add(new No());
    }
}
