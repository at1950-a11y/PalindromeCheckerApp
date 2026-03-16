import java.util.*;
import java.util.stream.Collectors;

public class PalindromeSystem {

    public static void main(String[] args) {

        header("UC1 - Application Entry");
        System.out.println("Palindrome Checker Application Started\n");

        /* UC2 */
        header("UC2 - Hardcoded Palindrome Check");
        String hard = "madam";
        System.out.println("Input : " + hard);
        System.out.println("Is Palindrome : " + PointerCheck.verify(hard));
        System.out.println();

        /* UC3 */
        header("UC3 - Reverse String Method");
        String w1 = "radar";
        System.out.println("Input : " + w1);
        System.out.println("Is Palindrome : " + ReverseCheck.verify(w1));
        System.out.println();

        /* UC4 */
        header("UC4 - Character Array Method");
        String w2 = "level";
        System.out.println("Input : " + w2);
        System.out.println("Is Palindrome : " + ArrayCheck.verify(w2));
        System.out.println();

        /* UC5 */
        header("UC5 - Stack Based Palindrome");
        String w3 = "noon";
        System.out.println("Input : " + w3);
        System.out.println("Is Palindrome : " + StackCheck.verify(w3));
        System.out.println();

        /* UC6 */
        header("UC6 - Queue + Stack Method");
        String w4 = "civic";
        System.out.println("Input : " + w4);
        System.out.println("Is Palindrome : " + QueueStackCheck.verify(w4));
        System.out.println();

        /* UC7 */
        header("UC7 - Deque Based Method");
        String w5 = "refer";
        System.out.println("Input : " + w5);
        System.out.println("Is Palindrome : " + DequeCheck.verify(w5));
        System.out.println();

        /* UC8 */
        header("UC8 - LinkedList Based Method");
        String w6 = "level";
        System.out.println("Input : " + w6);
        System.out.println("Is Palindrome : " + LinkedListCheck.verify(w6));
        System.out.println();

        /* UC9 */
        header("UC9 - Recursive Palindrome Check");
        String w7 = "racecar";
        System.out.println("Input : " + w7);
        System.out.println("Is Palindrome : " + RecursiveCheck.verify(w7));
        System.out.println();

        /* UC10 */
        header("UC10 - Case Insensitive Palindrome");
        String sentence = "A man a plan a canal Panama";

        String clean = sentence.chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(c -> String.valueOf((char)c))
                .collect(Collectors.joining())
                .toLowerCase();

        System.out.println("Original : " + sentence);
        System.out.println("Normalized : " + clean);
        System.out.println("Is Palindrome : " + PointerCheck.verify(clean));
        System.out.println();

        /* UC11 */
        header("UC11 - OOP Service Class");

        PalindromeManager manager = new PalindromeManager();
        String w8 = "madam";

        System.out.println("Input : " + w8);
        System.out.println("Is Palindrome : " + manager.isPalindrome(w8));
        System.out.println();

        /* UC12 */
        header("UC12 - Strategy Pattern");

        Strategy strat = new StackStrategyAlt();
        String w9 = "noon";

        System.out.println("Input : " + w9);
        System.out.println("Strategy Used : StackStrategy");
        System.out.println("Is Palindrome : " + strat.check(w9));
        System.out.println();

        /* UC13 */
        header("UC13 - Performance Comparison");

        String test = "amanaplanacanalpanama";

        long start = System.nanoTime();
        boolean res = PointerCheck.verify(test);
        long end = System.nanoTime();

        System.out.println("Input : " + test);
        System.out.println("Result : " + res);
        System.out.println("Execution Time (nanoseconds) : " + (end - start));
    }


    public static void header(String uc){

        System.out.println("=================================================");
        System.out.println("Welcome to the Palindrome Checker Management System");
        System.out.println("Version : 1.0");
        System.out.println("System initialized successfully.");
        System.out.println("Running : " + uc);
        System.out.println("=================================================");
    }
}


/* Two Pointer */
class PointerCheck {

    public static boolean verify(String s){

        for(int i=0;i<s.length()/2;i++){

            if(s.charAt(i) != s.charAt(s.length()-1-i)){
                return false;
            }
        }

        return true;
    }
}


/* Reverse using StringBuilder */
class ReverseCheck {

    public static boolean verify(String s){

        String rev = new StringBuilder(s).reverse().toString();
        return s.equals(rev);
    }
}


/* Char Array */
class ArrayCheck {

    public static boolean verify(String s){

        char[] arr = s.toCharArray();

        for(int i=0;i<arr.length/2;i++){

            if(arr[i] != arr[arr.length-1-i]){
                return false;
            }
        }

        return true;
    }
}


/* Stack */
class StackCheck {

    public static boolean verify(String s){

        Deque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            stack.push(c);
        }

        for(char c : s.toCharArray()){

            if(c != stack.pop()){
                return false;
            }
        }

        return true;
    }
}


/* Queue + Stack */
class QueueStackCheck {

    public static boolean verify(String s){

        Queue<Character> q = new ArrayDeque<>();
        Deque<Character> st = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            q.offer(c);
            st.push(c);
        }

        while(!q.isEmpty()){

            if(!q.poll().equals(st.pop())){
                return false;
            }
        }

        return true;
    }
}


/* Deque */
class DequeCheck {

    public static boolean verify(String s){

        ArrayDeque<Character> dq = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            dq.add(c);
        }

        while(dq.size() > 1){

            if(!dq.pollFirst().equals(dq.pollLast())){
                return false;
            }
        }

        return true;
    }
}


/* LinkedList */
class LinkedListCheck {

    public static boolean verify(String s){

        LinkedList<Character> list = new LinkedList<>();

        for(char c : s.toCharArray()){
            list.add(c);
        }

        while(list.size() > 1){

            if(!list.removeFirst().equals(list.removeLast())){
                return false;
            }
        }

        return true;
    }
}


/* Recursion */
class RecursiveCheck {

    public static boolean verify(String s){

        return recursive(s,0,s.length()-1);
    }

    private static boolean recursive(String s,int a,int b){

        if(a >= b) return true;

        if(s.charAt(a) != s.charAt(b)) return false;

        return recursive(s,a+1,b-1);
    }
}


/* Service Class */
class PalindromeManager {

    public boolean isPalindrome(String s){

        String rev = new StringBuilder(s).reverse().toString();
        return s.equals(rev);
    }
}


/* Strategy Pattern */
interface Strategy{

    boolean check(String s);
}

class StackStrategyAlt implements Strategy{

    public boolean check(String s){

        Stack<Character> st = new Stack<>();

        for(char c : s.toCharArray()){
            st.push(c);
        }

        for(int i=0;i<s.length();i++){

            if(s.charAt(i) != st.pop()){
                return false;
            }
        }

        return true;
    }
}
