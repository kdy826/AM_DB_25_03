package org.example;

public class Main {
    public static void main(String[] args) {
    try{
        new App().run();

    }catch(Exception e){

        System.out.println(e.getMessage());
        e.getOrigin().printStackTrace();
    }
    }

    }