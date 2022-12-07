package data;
import java.util.Random;

public class DNA{
   private String dna;

   public DNA(String rootChar, int length){
      String dna = "";
      int r = 0;

      for(int i = 0; i < length; i++){
         r = (int)(rootChar.length() * Math.random());
         dna += rootChar.charAt(r);
      }

      this.dna = dna;
   }

   
   public String getDna() {
      return dna;
   }
}