package data;

import java.util.ArrayList;
import java.lang.*;

public class Kmer {
   private ArrayList<String> kmer;

   public Kmer(String dna, int k){
      this.kmer = new ArrayList<>();
      String kmer = "";
      int f = 0;
      
      while(k <= dna.length()){
         kmer = dna.substring(f, k);
         this.kmer.add(kmer);
         f++;
         k++;
      }

   }

   public ArrayList<String> getKmer() {
      return kmer;
   }

   public void displayKmer(){
      for(int i = 0; i < this.kmer.size(); i++){
         System.out.println(this.kmer.get(i));
      }
   }

}
