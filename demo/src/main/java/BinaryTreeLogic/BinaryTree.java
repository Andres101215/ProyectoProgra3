package BinaryTreeLogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

public class BinaryTree <T> {
    private Comparator<T> comparator;
    private int aux;
    private TreeNode root;

    private ArrayList<T> list;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
        root=null;
    }

    public boolean isEmpty() {
        return root==null;
    }

    public void addNode(T info) {
        if(isEmpty()){
            root=new TreeNode(info);
        }else{
            TreeNode<T> newNode=new TreeNode<>(info);
            TreeNode<T> actually=root;
            TreeNode<T> previous=root;
            while(actually != null){
                previous= actually;
                actually= comparator.compare(actually.getInfo(),info) <0 ? actually.getRight():actually.getLeft();
            }
            if(comparator.compare(info,previous.getInfo())<0){
                previous.setLeft(newNode);
            }else{
                previous.setRight(newNode);
            }
        }
    }

    public TreeNode<T> findNode(T info) {
        TreeNode<T> aux=root;
        while(aux!=null &&  comparator.compare(info,aux.getInfo()) !=0){
            aux= comparator.compare(info,aux.getInfo())<0 ? aux.getLeft():aux.getRight();
        }
        return aux;
    }

    public int heighTree() {
        return 0;
    }

    public int heightNode(TreeNode<T> node) {
        return 0;
    }

    private int height(TreeNode<T> node, int aux) {
        return 0;
    }

    public int weightTree() {
        return 0;
    }

    private int weight(TreeNode<T> node) {
        return 0;
    }

    public int levelNode(TreeNode<T> node) {
        return (node==root) ? 0:levelNode(findFather(node))+1;
    }

    public boolean isLeaf(TreeNode node) {
        return (node.getRight()==null && node.getLeft()==null);
    }

    public byte gradeNode(TreeNode node) {
        if(node.getRight()!=null && node.getLeft()!= null){
            return 2;
        }else if(node.getRight()!=null || node.getLeft()!= null){
            return 1;
        }
        return 0;
    }

    public ArrayList<T> listPresort() {
        list= new ArrayList<>();
        presort(root);
        return list;
    }

    private void presort(TreeNode node) {
        if(root != null){
            list.add((T) root.getInfo());
            presort(root.getLeft());
            presort(root.getRight());
        }
    }

    public ArrayList<T> listInsort() {
        list = new ArrayList<>();
        insort( root );

        return list;
    }
    private void insort(TreeNode<T> node) {
        if( node != null ){
            insort( node.getLeft());
            list.add(node.getInfo());
            insort( node.getRight());
        }
    }
    public ArrayList<T> listPosort() {
        list = new ArrayList<>();
        posort( root );

        return list;
    }
    private void posort(TreeNode<T> node) {
        if( node != null ){
            posort( node.getLeft());
            posort( node.getRight());
            list.add(node.getInfo());
        }
    }
    public ArrayList<T> listAmplitudeDown() {
        list = new ArrayList<T>();
        ArrayDeque<TreeNode<T>> tail = new ArrayDeque<>();
        tail.add( root );
        TreeNode<T> aux = null;
        while( !tail.isEmpty( ) ){
            aux = tail.poll();
            if( aux.getLeft() != null ){
                tail.add( aux.getLeft());
            }
            if( aux.getRight() != null ){
                tail.add( aux.getRight());
            }
            list.add( aux.getInfo());
        }

        return list;
    }
    public ArrayList<T> listAmplitudeTop() {
        return null;
    }
    public TreeNode<T> findFather(TreeNode<T> node) {
        if(node!= root ){
            TreeNode<T> aux=root;
            while(aux.getLeft()!=node && aux.getRight()!=node){
                aux=comparator.compare(node.getInfo(),aux.getInfo())<0 ? aux.getLeft():aux.getRight();
            }
        }
        return null;
    }
    public T deleteNode(TreeNode node) {
        switch (gradeNode(node)){
            case 0:
                return deleteSheet(node);
            case 1:
                return  deleteWithSon(node);
            default:
                return deleteSons(node);
        }
    }
    private T deleteSheet(TreeNode node) {
        return null;
    }
    private T deleteWithSon(TreeNode node) {
        return null;
    }
    private T deleteSons(TreeNode node) {
        return null;
    }
}
