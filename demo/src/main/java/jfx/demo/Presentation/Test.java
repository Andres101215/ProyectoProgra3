package jfx.demo.Presentation;

import Controller.Management;

public class Test {
    public static void main(String[] args) {
        Management management = new Management();
        System.out.println(management.generateAscciCode("AA"));
        System.out.println(management.generatePosition("Zndres"));
    }
}
