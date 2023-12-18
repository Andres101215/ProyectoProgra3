package Controller;
import BinaryTreeLogic.BinaryTree;
import Logic.Word;

import java.util.ArrayList;


public class Management{
    private Word word;
    private BinaryTree<Word> [] list;

    public Management(){
        word=new Word();
        list= new BinaryTree[26];
        initialize();
    }

    public void initialize(){
        for(int i=0;i<list.length;i++){
            list[i]=new BinaryTree<>((word1, word2) -> Integer.compare(word1.getId(), word2.getId()));
        }
    }

    public int generateAscciCode(String word){
        int aux1=0;
        for (int i = 0; i < word.length(); i++) {
            char caracter = word.charAt(i);
            aux1 += (int) caracter;
        }
        return aux1;
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
        char primeraLetra = Character.toUpperCase(word.charAt(0));
        return primeraLetra + word.substring(1);
    }
    public boolean containCharacterSpecial(String str) {
        int x = 0;
        for (int i = 0; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).equals("1")
                    || String.valueOf(str.charAt(i)).equals("2")
                    || String.valueOf(str.charAt(i)).equals("3")
                    || String.valueOf(str.charAt(i)).equals("4")
                    || String.valueOf(str.charAt(i)).equals("5")
                    || String.valueOf(str.charAt(i)).equals("6")
                    || String.valueOf(str.charAt(i)).equals("7")
                    || String.valueOf(str.charAt(i)).equals("8")
                    || String.valueOf(str.charAt(i)).equals("9")
                    || String.valueOf(str.charAt(i)).equals("0")) {
                x++;
            }
        }
        return str.matches("^[a-zA-Z0-9\\s]+$") && x == 0;
    }

    public Word createWord(int id, String word1, String definition, String translate){
        word=new Word(id,word1,definition,translate);
        return word;
    }
    public boolean addBinaryTreeWord(int pos,Word wordaux){
        list[pos].addNode(wordaux);
        return true;
    }
    public boolean deleteBinaryTreeWord(int pos,int id){
       list[pos].deleteNode(list[pos].findNode(findInfoById(pos,id)));
        return true;
    }
    public Word findInfoById(int pos,int id){
        for (Word  aux: list[pos].listAmplitudeDown()) {
            if(aux.getId()==id){
                return aux;
            }
        }
        return null;
    }
    public ArrayList<Word> returnlistByletter(int pos){
        return list[pos].listInsort();
    }
    public boolean modifyword(int pos,int id,Word word1){
        list[pos].modifyinfo(list[pos].findNode(findInfoById(pos,id)),word1);
        return true;
    }
    public ArrayList<Word> returnlist(){
        ArrayList<Word> newlistWords = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            newlistWords.addAll(list[i].listInsort());
        }
        return newlistWords;
    }
}
