package Practical1;

/*Jinesh Jain
This is a program to implement an AVL tree.
It handles all types of rotations and has basic functions of traversal,insertion,search and update.
*/

import java.util.Scanner;
class Node1 {
    int key;
    int height;
    int balancefactor;
    String keycust;
    Node1 left;
    Node1 right;

    Node1() {
        this.key = 0;
        this.height = 0;
        this.balancefactor = 0;
        this.keycust = " ";
        this.left = null;
        this.right = null;
    }

    Node1(int key, String keycust) {
        this.key = key;
        this.keycust = keycust;
    }
}

public class AVL {
    private static Node1 root;
    static int premium_cust = 0, ordinary_cust = 0;
    static boolean flag = false;

    void updateHeight(Node1 n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    void updateBalance(Node1 n) {
        n.balancefactor = height(n.left) - height(n.right);
    }

    int height(Node1 n) {
        if (n == null) {
            return -1;
        } else {
            return n.height;
        }
    }

    int getBalance(Node1 n) {
        if (n == null) {
            return 0;
        } else {
            return (height(n.right) - height(n.left));
        }
    }

    Node1 rotateRight(Node1 y) {
        Node1 x = y.left;
        Node1 z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        updateBalance(y);
        updateBalance(x);
        return x;
    }

    Node1 rotateLeft(Node1 y) {
        Node1 x = y.right;
        Node1 z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        updateBalance(y);
        updateBalance(x);
        return x;
    }

    Node1 rebalanceNode1(Node1 z) {
        updateHeight(z);
        updateBalance(z);
        int balance = z.balancefactor;
        if ((balance == 0) || (balance == 1) || (balance == -1)) {
        } else {
            if (balance > 1) {
                if (height(z.left.left) > height(z.left.right)) {
                    z = rotateRight(z);
                } else {
                    z.left = rotateLeft(z.left);
                    z = rotateRight(z);
                }
            }
            if (balance < -1) {
                if (height(z.right.right) > height(z.right.left)) {
                    z = rotateLeft(z);
                } else {
                    z.right = rotateRight(z.right);
                    z = rotateLeft(z);
                }
            }
        }
        return (z);
    }

    Node1 insert(int key, Node1 node, String keycust) {
        if (node == null) {
            return new Node1(key, keycust);
        } else if (node.key > key) {
            node.left = insert(key, node.left, keycust);
            node = rebalanceNode1(node);
        } else if (node.key < key) {
            node.right = insert(key, node.right, keycust);
            node = rebalanceNode1(node);
        } else {
            throw new RuntimeException("Keys with same value not allowed");
        }
        return (node);
    }
    void update(Node1 root,int num){
        Node1 temp=root;
        Scanner sc=new Scanner(System.in);
        if(temp.key==num){
            System.out.println("FOUND");
            System.out.println("Update value: ");
            String value=sc.next();
            String type=temp.keycust;
            temp.keycust=value;
            if(value.equals("Y") && !value.equals(type)){
                premium_cust++;
                ordinary_cust--;
            }
            if(value.equals("N") && !value.equals(type)){
                premium_cust--;
                ordinary_cust++;
            }
        }
        else if(temp.key<num){
            update(temp.right,num);
        }
        else if(temp.key>num){
            update(temp.left,num);
        }
    }

    private void inorderTraversal(Node1 root) {
        Node1 temp = root;
        if (temp != null) {
            inorderTraversal(temp.left);
            System.out.print("( " + temp.key + "," + temp.keycust + "," + temp.height + " , " + temp.balancefactor + " )");
            inorderTraversal(temp.right);
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVL obj = new AVL();
        for (int i = 0; i < 8; i++) {
         System.out.println("Please enter an element to insert in AVL Tree");
         int num=sc.nextInt();
         String type=sc.next();
         root = obj.insert(num, root, type);
         if(type.equals("Y")){
             premium_cust++;
         }
         if(type.equals("N")){
             ordinary_cust++;
         }
         }
        obj.inorderTraversal(root);
        System.out.println("\nSearch: ");
        int num=sc.nextInt();
        obj.update(root,num);
        obj.inorderTraversal(root);
        System.out.println("\nP: "+premium_cust+"\nO: "+ordinary_cust);
    }
}
