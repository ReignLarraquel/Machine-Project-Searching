package data;

import java.util.ArrayList;
import java.lang.*;

public class Kmer {
   private ArrayList<String> kmerSequence;

   public Kmer(String dna, int k){
      this.kmerSequence = new ArrayList<>();
      String kmer = "";
      int f = 0;
      
      while(k <= dna.length()){
         kmer = dna.substring(f, k);
         this.kmerSequence.add(kmer);
         f++;
         k++;
      }

   }

   public ArrayList<String> getKmerSequence() {
      return kmerSequence;
   }

   public void displayKmerSequence(){
      for(int i = 0; i < this.kmerSequence.size(); i++){
         System.out.println(this.kmerSequence.get(i));
      }
   }

}
