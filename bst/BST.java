package bst;

import data.Kmer;

public class BST {
   public class Node {
      private String data;
      private int count;

      private Node left;
      private Node right;

   
      public Node(String data){
         this.data = data;
         count = 1;
      }
   }

   private Node root;

   public BST(){
      root = null;
   }

   public void insert(String data){
      root = insertRecursion(root, data);
   }

   public Node insertRecursion(Node root, String data){
      if(root == null){
         root = new Node(data);
         return root;
      }
      else if(data.compareToIgnoreCase(root.data) > 0){
         root.right = insertRecursion(root.right, data);
      }
      else if(data.compareToIgnoreCase(root.data) < 0){
         root.left = insertRecursion(root.left, data);
      }
      else if(data.compareToIgnoreCase(root.data) == 0){
         root.count++;
      }

      return root;
   }

   public boolean search(String data){
      root = searchRecursion(root, data);

      if(root == null){
         return false;
      }
      else{
         return true;
      }

   }

   public Node searchRecursion(Node root, String data){
      if(root == null){
         return null;
      }
      else if(data.compareToIgnoreCase(root.data) > 0){
         root.right = searchRecursion(root.right, data);
      }
      else if(data.compareToIgnoreCase(root.data) < 0){
         root.left = searchRecursion(root.left, data);
      }
      else if(data.compareToIgnoreCase(root.data) == 0){
         return root;
      }

      return root;
   }

   public void destroy(Node root){
      if(root.left != null){
         destroy(root.left);
      }

      if(root.right != null){
         destroy(root.right);
      }

      root = null;
   }

   public void displayRecursion(Node root){
      if(root != null){
         System.out.println(root.data + " - " + root.count);
         displayRecursion(root.left);
         displayRecursion(root.right);
      }
   }

   public void displayBST(Node root){
      if(root != null){
         System.out.println("k-mer - no. of occ");
         displayRecursion(root);
      }
      else{
         System.out.println("Tree is empty.");
      }
   }

   public Node generateTree(Kmer kmer){
      for(int i = 0; i < kmer.getKmerSize(); i++){
         insert(kmer.getKmerSequence().get(i));
      }

      return root;
   }

   public Node getRoot(){
      return root;
   }

}
