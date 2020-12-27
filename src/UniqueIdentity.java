import java.util.Scanner;

public class UniqueIdentity {
    public static void main(String[] args) {
        System.out.println(isAllLettersAreUnique(new Scanner(System.in).nextLine()));

    }

    public static boolean isPermutation(String s1, String s2) {
        if(s1.length()==s2.length()) {
            for (int i = 0; i < s1.length(); i++) {

            }
        }
        return false;
    }

    public static boolean isAllLettersAreUnique(String s) {
        for (int i = 0; i < s.length() ; i++) {
            if(s.indexOf(s.substring(i,i+1))!=s.lastIndexOf(s.substring(i,i+1))) return false;
        }
        /*for (int i = 0; i <s.length() -1; i++) {
            for (int j = i+1; j < s.length(); j++) {
                if(s.substring(i,i+1).equalsIgnoreCase(s.substring(j,j+1)))return  false;
            }
        }*/
        return true;
    }
}
