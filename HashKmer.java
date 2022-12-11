import java.util.Objects;
import java.util.*;
import java.util.zip.*;
public class HashKmer {
    private int kmer;
    private String input;
    private String[] temp1;
    private int[] temp2;
    public HashKmer(){
    }
    public void setKmer(int kmer){
        this.kmer = kmer;
    }

    public int getKmer() {
        return kmer;
    }

    public void kmerDistri(HashCRC crc , String input){
        this.temp1 = new String[input.length()];
        this.temp2 = new int[input.length()];
        for(int i = 0; i < input.length() - kmer + 1; i++) {
            temp1[i] = input.substring(i, i + kmer);
            temp2[i] = 1;
        }
        for(int i = 0; i < input.length() - kmer - 1; i++) {
            for(int j = i+1; j < input.length() - kmer + 1; j++) {
                if(Objects.equals(temp1[i], temp1[j])) {
                    temp2[i]++;
                }
            }
            crc.insert(temp1[i], temp2[i]);
        }
        System.out.println("Number of collisions: "+ crc.getCollisions());
    }

    public void kmerDistri(HashMD5 md5 , String input){
        this.temp1 = new String[input.length()];
        this.temp2 = new int[input.length()];
        for(int i = 0; i < input.length() - kmer + 1; i++) {
            temp1[i] = input.substring(i, i + kmer);
            temp2[i] = 1;
        }
        for(int i = 0; i < input.length() - kmer - 1; i++) {
            for(int j = i+1; j < input.length() - kmer + 1; j++) {
                if(Objects.equals(temp1[i], temp1[j])) {
                    temp2[i]++;
                }
            }
            md5.insert(temp1[i], temp2[i]);
        }

        System.out.println("Number of collisions: "+ md5.getCollisions());
    }

    public String randomstringgen(int n) {
        String characters = "zuioedklj";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++)
        {
            sb.append(characters.charAt(rnd.nextInt(characters.length())));
        }

        return sb.toString();
    }

}
