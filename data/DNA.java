package data;
import java.util.Random;

public class DNA{

   public DNA(){
   }
   
   public String generateDNA(String rootChar, int length){
      String dna = "";
      int r = 0;

      for(int i = 0; i < length; i++){
         r = (int)(rootChar.length() * Math.random());
         dna += rootChar.charAt(r);
      }

      return dna;
   }
}