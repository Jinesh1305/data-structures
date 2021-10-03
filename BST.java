package Practical1;

/* Jinesh Jain
This is a program to implement Binary Search Tree.
It performs the basic functions of Insertion,Searching and Traversal
*/

import java.util.*;

class Node {
    int key;
    Node left, right;
    String name;
    int m1,m2,m3,avg;
    Node(int roll, String name, int m1, int m2, int m3,int avg) {
        key = roll;
        left = right = null;
        this.name=name;
        this.m1=m1;
        this.m2=m2;
        this.m3=m3;
        this.avg=avg;
    }
}

class BST {
    static Node root;
    BST(){
        root=null;
    }
    
    public static void insertion(int roll, String name, int m1, int m2, int m3,int avg) {
        Node newNode = new Node(roll,name,m1,m2,m3,avg);
        if(root==null){
            root=newNode;
        }
        else{
            Node current=root;
            Node parent=null;
            while(true){
                parent=current;
                if(roll<current.key){
                    current=current.left;
                    if(current==null){
                        parent.left=newNode;
                        return;
                    }
                }
                else{
                    current=current.right;
                    if(current==null){
                        parent.right=newNode;
                        return;
                    }
                }
            }
        }
    }
    
    public static void traverse(Node node){
        if(root==null){
            System.out.println("Tree is empty");
        }
        else{
            if(node.left!=null){
                traverse(node.left);
            }
            System.out.print(node.key+" ");
            if(node.right!=null){
                traverse(node.right);
            }
        }
    }
    
    public static void first(Node node){
        if(node.left!=null){
            first(node.left);
        }
        else{
            System.out.println("First Roll No.: "+node.key);
        }
    }
    
    public static void last(Node node){
        if(node.right!=null){
            last(node.right);
        }
        else{
            System.out.println("Last Roll No.: "+node.key);
        }
    }
    
    public static void search(Node node,int find){
        if(node!=null){
            if(node.key==find){
                System.out.println("Search Success");
                System.out.println("Name: "+node.name);
                System.out.println("Roll No.: "+node.key);
                System.out.println("Average: "+node.avg);
            }
            else if(node.key<find){
                search(node.right,find);
            }
            else if(node.key>find){
                search(node.left,find);
            }
        }
        else{
            System.out.println("Search Not Success");
        }
    }

    public static void main(String[] args) {
        BST tree=new BST();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int num = input.nextInt();
        System.out.println("Name\tRoll\tM1\tM2\tM3");
        for (int i = 0; i < num; i++) {
            System.out.println("Student "+(1+i)+" details: ");
            String name = input.next();
            int roll = input.nextInt();
            int m1 = input.nextInt();
            int m2 = input.nextInt();
            int m3 = input.nextInt();
            int avg = (m1 + m2 + m3) / 3;
            tree.insertion(roll, name, m1, m2, m3,avg);
        }
        traverse(tree.root);
        System.out.print("\n");
        first(tree.root);
        last(tree.root);
        System.out.print("Search Roll: ");
        int find=input.nextInt();
        search(tree.root,find);
    }
}
