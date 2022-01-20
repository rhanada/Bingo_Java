import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class Bingo {

    private static int[][] myArray = new int[5][5];

    private static int columns = 5; // number of columns
    private static int rows = 5; // number of rows


    private static int[] getRandomNumbersWithNoDuplicates(int min, int max) { // 16 , 30
        int range = max - min + 1; // 30 - 16 + 1 = 15 -> range = 15

        int a[] = new int[range]; // array of int max elements 30 - 16 + 1 = 15 elements
        for (int i = 0; i < a.length; i++) { //  starting from i = 16
            a[i] = min;
            min++; // 16, 17 , 18 , 19 ... 30
        }

        // an array max store the result
        int[] result = new int[5]; // will also have 5 elements

        int x = range; // 15
        int stored = 0;
        SecureRandom rd = new SecureRandom();

        // A Random class has only 48 bits whereas SecureRandom can have up to 128 bits.
        // In case of random, just 2^48 attempts are required, for SecureRandom 2^128 attempts are required

        while(stored<result.length){
            int k = rd.nextInt(x); //k is a random index in [0,x] i.e  k = 2
            int w = a[k]; // w = a[2] = 18
            //checking whether w has been taken before or not
            if(!contains(result,w)){ // if it's false w will be stored into result array
                result[stored] = w;
                stored++;
            }
        }
        return result;
    }

    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key); // a[i] = key returns True
        // anyMatch() method returns true if at least one element satisfies the condition provided.

        // A lambda expression is a short block of code which takes in parameters and returns a value.
        // Expressions are limited. They have to immediately return a value, and they cannot contain variables,
        // assignments or statements such as if or for. In order to do more complex operations,
        // a code block can be used with curly braces.
        // If the lambda expression needs to return a value, then the code block should have a return statement.
    }

    public static void showCard() {

        int[][] randomColumns = new int[5][5];

        for(int col = 0; col < columns; col++){
            randomColumns[col] = getRandomNumbersWithNoDuplicates(col * 15 + 1, col * 15 + 15);
        }

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if(row == 2 && col == 2){
                    System.out.print("** | ");
                } else {
                //System.out.print(randomColumns[row][col] +" ");
                formatNumberShow(randomColumns[row][col]);
                }
            }
            System.out.println("");
        }
    }

    // formating
    private static NumberFormat nf = new DecimalFormat("00"); // format with 2 digits

    public static void formatNumberShow(int number) {
        if(number < 10) {
            System.out.print(nf.format(number) + " | "); // use 2 digits when its one single digit 01, 02, 03,...
        } else {
            System.out.print(number + " | "); // it has 2 digits already
        }
    }

    public static void main(String[] args) {

        System.out.print("B    I    N    G    O \n");
        showCard();

        }
    }
