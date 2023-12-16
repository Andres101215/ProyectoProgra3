package Controller;

import BinaryTreeLogic.BinaryTree;
import Logic.Word;


public class Management{
    private BinaryTree<Word> binaryTree;
    private BinaryTree<Word> [] list= new BinaryTree[26];


    public String generateAscciCode(String word){
        int aux1=0;
        for (int i = 0; i < word.length(); i++) {
            char caracter = word.charAt(i);
            aux1 += (int) caracter;
        }
        return String.valueOf(aux1);
    }
    public int generatePosition(String word){
      String aux=word.substring(0,1);
      int aux1=0;
        for (int i = 0; i < aux.length(); i++) {
            char caracter = aux.charAt(i);
            aux1 += (int) caracter;
        }
        return aux1-65;
    }
    public String ConvertFirstToUppercase(String word){
        return null;
    }


}
