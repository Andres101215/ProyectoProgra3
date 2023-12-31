package BinaryTreeLogic;

import java.util.*;

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

    public void modifyinfo(TreeNode<T> node,T newinfo){
        node.setInfo(newinfo);
    }

    public int heighTree() {
        return height(root);
    }

    public int heightNode(TreeNode<T> node) {
        return height(node);
    }

    private int height(TreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.getLeft());
            int rightHeight = height(node.getRight());
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    public int weightTree() {
        return weight(root);
    }

    private int weight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            int leftWeight = weight(node.getLeft());
            int rightWeight = weight(node.getRight());
            return 1 + leftWeight + rightWeight;
        }
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

    private void presort(TreeNode<T> node) {
        if (node != null) {
            list.add(node.getInfo());
            presort(node.getLeft());
            presort(node.getRight());
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
        ArrayList<T> result = new ArrayList<>();
        ArrayDeque<TreeNode<T>> tail = new ArrayDeque<>();
        tail.add(root);
        TreeNode<T> aux = null;

        while (!tail.isEmpty()) {
            aux = tail.poll();
            if (aux.getRight() != null) {
                tail.add(aux.getRight());
            }
            if (aux.getLeft() != null) {
                tail.add(aux.getLeft());
            }
            result.add(aux.getInfo());
        }

        return result;
    }

    public ArrayList<T> listAmplitudeTop() {
        ArrayList<T> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        Stack<T> stack = new Stack<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            stack.push(current.getInfo());

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public TreeNode<T> findFather(TreeNode<T> node) {
        if (node != root) {
            TreeNode<T> aux = root;
            while (aux != null) {
                if ((aux.getLeft() != null && aux.getLeft() == node) || (aux.getRight() != null && aux.getRight() == node)) {
                    return aux;
                }
                aux = comparator.compare(node.getInfo(), aux.getInfo()) < 0 ? aux.getLeft() : aux.getRight();
            }
        }
        return null;
    }

    public T deleteNode(TreeNode<T> node) {
        switch (gradeNode(node)) {
            case 0:
                return deleteSheet(node);
            case 1:
                return deleteWithSon(node);
            default:
                return deleteSons(node);
        }
    }

    private T deleteSheet(TreeNode<T> node) {
        TreeNode<T> father = findFather(node);
        if (father != null) {
            if (father.getLeft() == node) {
                father.setLeft(null);
            } else {
                father.setRight(null);
            }
        } else {
            root = null;
        }
        return node.getInfo();
    }

    private T deleteWithSon(TreeNode<T> node) {
        TreeNode<T> father = findFather(node);
        TreeNode<T> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();

        if (father != null) {
            if (father.getLeft() == node) {
                father.setLeft(child);
            } else {
                father.setRight(child);
            }
        } else {
            root = child;
        }
        return node.getInfo();
    }

    private T deleteSons(TreeNode<T> node) {
        TreeNode<T> successor = findSuccessor(node);
        T successorValue = successor.getInfo();
        deleteNode(successor);

        node.setInfo(successorValue);
        return successorValue;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> node) {
        TreeNode<T> successor = node.getRight();

        while (successor.getLeft() != null) {
            successor = successor.getLeft();
        }
        return successor;
    }
}
