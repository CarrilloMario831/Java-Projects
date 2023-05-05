public class Recursion {

    /**
     * Returns the value of x * y, computed via recursive addition.
     * x is added y times.
     * @param x  integer multiplicand 1
     * @param y  integer multiplicand 2
     * @return   x * y
     */
    public static int recursiveMultiplication(int x, int y) {
        //if y is greater than 0
        if(y > 0){
            //if y is equal to one
            if(y == 1){
                //return x
                return x;
            }
            //if y does not equal to one, call recursiveMultiplication but decrease y and add x
            return recursiveMultiplication(x, y - 1) + x;
        }
        //else if y is less than 0
        else if(y < 0){
            //if y is equal to negative 1
            if(y == -1){
                //return negative x
                return -x;
            }
            //if y does not equal to negative 1, call recursiveMultiplication but increase y and subtract x
            return recursiveMultiplication(x, y + 1) - x;
        }
        //return 0 if y is not greater than or less than y - meaning y is 0 and anything multiplied by 0 is 0
        return 0;
    }

/******************************************************************************/
    /**
     * Reverses a string via recursion.
     * @param s  the non-null string to reverse
     * @return   a new string with the characters in reverse order
     */
    public static String reverse(String s) {
        //if the length of the string is 0
        if(s.length() == 0){
            //return the reversed string
            return "";
        }
        //call reverse to keep stacking the letters
        return  reverse(s.substring(1)) + s.charAt(0);
    }

    /******************************************************************************/
    private static int maxHelper(int[] array, int index, int max) {
        //if index of the array reaches the end
        if(index == array.length){
            //return max
            return max;
        }
        //if max is less than a value at the index
        if(max < array[index]){
            //set max to be that value
            max = array[index];
        }
        //repeat process but increase the index by 1
        return maxHelper(array, index + 1, max);
    }

    /**
     * Returns the maximum value in the array.
     * Uses a helper method to do the recursion.
     * @param array  the array of integers to traverse
     * @return       the maximum value in the array
     */
    public static int max(int[] array) {
        return maxHelper(array, 0, Integer.MIN_VALUE);
    }

/******************************************************************************/

    /**
     * Returns whether or not a string is a palindrome, a string that is
     * the same both forward and backward.
     * @param s  the string to process
     * @return   a boolean indicating if the string is a palindrome
     */
    public static boolean isPalindrome(String s) {
        //if the length of the string is either 0 or 1 return true since it's a palindrome
        if(s.length() == 0 || s.length() == 1){
            return true;
        }
        //if the character at index 0 is the same character at the last index of the string
        if(s.charAt(0) == s.charAt(s.length() - 1)){
            //take a substring between the similar characters and repeat the same process of checking the outer characters if they are the same
            return isPalindrome(s.substring(1, s.length() - 1));
        }
        //return false if it is not a palindrome
        return false;
    }

    /******************************************************************************/
    private static boolean memberHelper(int key, int[] array, int index) {
        //if we reached the end of the array and the key is not found
        if(index == array.length){
            //return false
            return false;
        }
        //if we find a value within the array equal to the key
        if(array[index] == key){
            //return true
            return true;
        }
        //call itself again but increasing the index to check through the array
        return memberHelper(key, array, index + 1);
    }

    /**
     * Returns whether or not the integer key is in the array of integers.
     * Uses a helper method to do the recursion.
     * @param key    the value to seek
     * @param array  the array to traverse
     * @return       a boolean indicating if the key is found in the array
     */
    public static boolean isMember(int key, int[] array) {
        return memberHelper(key, array, 0);
    }

/******************************************************************************/
    private static String separateIdenticalHelper(String s, int index, String res){
        //if the length of the string is either 0 or 1
        if(s.length() == 0 || s.length() == 1){
            //return the given string
            return s;
        }
        //else if we reached the end of the string
        else if(index == s.length() - 1){
            //return the new array with the appended values
            return res + s.charAt(index);
        }
        //else if the character at the index does not equal to the character ahead
        else if(s.charAt(index) != s.charAt(index + 1)){
            //append the character to the new string
            res += s.charAt(index);
            //call the helper once more to continue through the string, increase index by one
            return separateIdenticalHelper(s, index + 1, res);
        }
        //else if the character at the index DOES equal to the character ahead
        else if(s.charAt(index) == s.charAt(index + 1)){
            //append the character with a tilde
            res += s.charAt(index) + "~";
            //call the helper once more to continue through the string, increase index by one
            return separateIdenticalHelper(s, index + 1, res);
        }
        //have the helper call itself, and increase index by one
        return separateIdenticalHelper(s, index + 1, res);
    }

    /**
     * Returns a new string where identical chars that are adjacent
     * in the original string are separated from each other by a tilde '~'.
     * @param s  the string to process
     * @return   a new string where identical adjacent characters are separated
     *           by a tilde
     */
    public static String separateIdentical(String s) {
        return separateIdenticalHelper(s,0, "");
    }

}
