package jfx.demo.Presentation;

import Controller.Management;
import BinaryTreeLogic.BinaryTree;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        BinaryTree<Integer> bts = new BinaryTree<>((o1,o2)->Integer.compare( o1, o2));

        bts.addNode(5);
        bts.addNode(1);
        bts.addNode(6);
        bts.addNode(2);
        bts.addNode(9);
        bts.addNode(3);
        bts.addNode(-3);
        bts.addNode(-4);
        bts.addNode(4);



        ArrayList<Integer> aux= new ArrayList<>();
        aux=bts.listPresort();


        for(Integer i: aux){
            System.out.println(i);
        }


    }
}
