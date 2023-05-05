import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Class for finding the most common words in a txt file
 */

public class CommonWordFinder<K extends Comparable<K>, V>{

    /**
     * HashMap approach to receiving the count of words within a
     * text file. Parses through the text file which is passed, and puts
     * distinct words (keys) in the HashMap. Increments the times a word
     * is seen by one each time its seen.
     * @param File
     * @return an array of Keys(String) Values(Integer)
     * Where the keys are the words in a text file and the
     * values are the count of the words
     * @throws IOException
     */
    private static Entry[] hashMapWord(String File) throws IOException{
        MyMap<String, Integer> map = new MyHashMap<>();
        //Instantiation of MyHashMap of String, Integer referencing to MyMap
        int character;
        //Ascii Variable Character
        String builder = "";
        //empty string to build words
        //https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
            BufferedReader br = new BufferedReader(new FileReader(File));
            //Instantiation of a buffered reader which reads the passed file.
            while((br.ready())){
                //while the file can be read
                character = br.read();
                //character variable is equal to the character but it is in ASCII values
                //https://www.asciitable.com/
                if(builder.length() == 0 && character == 45){
                    //if the builder string is empty and we see a hyphen continue, dont add
                    continue;
                }
                else if(((character <= 90 && character >= 65) || (character >= 97 && character <= 122)) || character == 39 || character == 45){
                    //else if we see a letter, or single quote, or hyphen
                    builder += Character.toLowerCase((char)character);
                    //add it to the builder
                }
                else if((char)character == ' ' || (char)character == '\n' || (char)character == '\r'){
                    //if we see a space or new line
                    if(map.get(builder) == null && builder != ""){
                        //if there is no value to word in the HashMap
                        map.put(builder, 1);
                        //add it to the hashmap and set a value of 1
                    }
                    else if(map.get(builder) != null && builder != ""){
                        //if there is a vlaue to the word in the hashmap
                        map.put(builder, map.get(builder) + 1);
                        //replace the value by adding one
                    }
                    builder = "";
                    //set the builder to empty
                }
            }
            if(builder.length() != 0){
                //if we make it out of reading the file but we still have a word
                if(map.get(builder) == null && builder != ""){
                    //if there is no value in the hashmap
                    map.put(builder, 1);
                    //put it in the hashmap and set a value of 1
                }
                else if(map.get(builder) != null && builder != ""){
                    //else it exists in the hashmap
                    map.put(builder, map.get(builder) + 1);
                    //increment the value by one
                }
                builder = "";
                //set builder to empty
            }
            br.close();
            //close the buffered reader
            int i = 0;
            //create an indexing variable for the while loop
            Entry<String, Integer>[] arr = new Entry[map.size()];
            //create a new Entry array the size of the hashmap
            Entry<String, Integer>[] scratchArray = new Entry[map.size()];
            //create a scratch entry array the size of the hashmap
            String[] newArrKey = new String[map.size()];
            //create a string array to put the string keys in
            Iterator<Entry<String, Integer>> iter = map.iterator();
            //create an iterator to iterate through the keys in the hashmap
            while (iter.hasNext()) {
                //while we can iterate through the hashmap
                arr[i] = iter.next();
                //set the keys in values into the entry array
                i++;
                //increment i
            }
            for(int a = 0; a < arr.length; a++){
                //copy the keys (strings/words) into the string array
                newArrKey[a] = arr[a].key;
            }
            Arrays.sort(newArrKey);
            //sort the words
            for(int b = 0; b < arr.length; b++){
                //replace the entry array with the sorted words
                arr[b] = new Entry(newArrKey[b], map.get(newArrKey[b]));
            }
            //sort the values using merge sort
            arr = mergesortHelper(arr, scratchArray, 0, arr.length - 1);
            //return the sorted keys and values array
            return arr;
        }

    /**
     * BST approach to receiving the count of words within a
     * text file. Parses through the text file which is passed, and puts
     * distinct words (keys) in the BST tree. Increments the times a word
     * is seen by one each time its seen.
     * @param File
     * @return an array of Keys(String) Values(Integer)
     * Where the keys are the words in a text file and the
     * values are the count of the words
     * @throws IOException
     */

    private static Entry[] binarySearchWord(String File) throws IOException{
        MyMap<String, Integer> map = new BSTMap<>();
        //Instantiation of BSTMap of String, Integer referencing to MyMap

        //Everything below this is very similar to the HashMap method
        //However the only difference is that I do not sort the
        //keys since they are sorted by the BST

        int character;
        String builder = "";

        BufferedReader br = new BufferedReader(new FileReader(File));
        while((br.ready())){
            character = br.read();
            if(builder.length() == 0 && character == 45){
                continue;
            }
            else if(((character <= 90 && character >= 65) || (character >= 97 && character <= 122)) || character == 39 || character == 45){
                builder += Character.toLowerCase((char)character);
            }
            else if((char)character == ' ' || (char)character == '\n' || (char)character == '\r'){
                if(map.get(builder) == null && builder != ""){
                    map.put(builder, 1);
                }
                else if(map.get(builder) != null && builder != ""){
                    map.put(builder, map.get(builder) + 1);
                }
                builder = "";
            }
        }
        if(builder.length() != 0){
            if(map.get(builder) == null && builder != ""){
                map.put(builder, 1);
            }
            else if(map.get(builder) != null && builder != ""){
                map.put(builder, map.get(builder) + 1);
            }
            builder = "";
        }
        br.close();
        int i = 0;
        Entry<String, Integer>[] arr = new Entry[map.size()];
        Entry<String, Integer>[] scratchArray = new Entry[map.size()];
        Iterator<Entry<String, Integer>> iter = map.iterator();
        while (iter.hasNext()) {
            arr[i] = iter.next();
            i++;
        }
        arr = mergesortHelper(arr, scratchArray, 0, arr.length - 1);
        return arr;
    }

    /**
     * AVL approach to receiving the count of words within a
     * text file. Parses through the text file which is passed, and puts
     * distinct words (keys) in the AVL tree. Increments the times a word
     * is seen by one each time its seen.
     * @param File File to read
     * @return an array of Keys(String) Values(Integer)
     * Where the keys are the words in a text file and the
     * values are the count of the words
     * @throws IOException
     */

    private static Entry[] avlTreeWord(String File) throws IOException{
        MyMap<String, Integer> map = new AVLTreeMap<>();
        //Instantiation of AvlTreeMap of String, Integer referencing to MyMap

        //Everything below this is very similar to the HashMap and BST method
        //However the only difference is that I do not sort the
        //keys since they are sorted by the AVL tree

        int character;
        String builder = "";

        //https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
        BufferedReader br = new BufferedReader(new FileReader(File));
        while((br.ready())){
            character = br.read();
            if(builder.length() == 0 && character == 45){
                continue;
            }
            else if(((character <= 90 && character >= 65) || (character >= 97 && character <= 122)) || character == 39 || character == 45){
                builder += Character.toLowerCase((char)character);
            }
            else if((char)character == ' ' || (char)character == '\n' || (char)character == '\r'){
                if(map.get(builder) == null && builder != ""){
                    map.put(builder, 1);
                }
                else if(map.get(builder) != null && builder != ""){
                    map.put(builder, map.get(builder) + 1);
                }
                builder = "";
            }
        }
        if(builder.length() != 0){
            if(map.get(builder) == null && builder != ""){
                map.put(builder, 1);
            }
            else if(map.get(builder) != null && builder != ""){
                map.put(builder, map.get(builder) + 1);
            }
            builder = "";
        }
        br.close();
        int i = 0;
        Entry<String, Integer>[] arr = new Entry[map.size()];
        Entry<String, Integer>[] scratchArray = new Entry[map.size()];
        Iterator<Entry<String, Integer>> iter = map.iterator();
        while (iter.hasNext()) {
            arr[i] = iter.next();
            i++;
        }
        arr = mergesortHelper(arr, scratchArray, 0, arr.length - 1);
        return arr;
    }

    /**
     * Merge sort helper method to sort the values of the Entry[]
     * array. Minimum modifications.
     * @param array Array w/ values to be sorted
     * @param scratch Empty array to copy values into
     * @param low low index
     * @param high high index
     * @return an Array with sorted Values
     */
    private static Entry[] mergesortHelper(Entry[] array, Entry[] scratch, int low, int high){
        //merge sort helper method
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergesortHelper(array, scratch, low, mid);
            mergesortHelper(array, scratch, mid + 1, high);
            int i = low, j = mid + 1;
            for (int k = low; k <= high; k++) {
                if (i <= mid && (j > high || (int) array[i].value >= (int) array[j].value)){

                    //Change the if statement in order to sort the values by descending order

                    scratch[k] = array[i++];
                }
                else {
                    scratch[k] = array[j++];
                }
            }
            for (int k = low; k <= high; k++) {
                array[k] = scratch[k];
            }
        }
        return array;
    }

    /**
     * Main method of CommonWordFinder.java. Handles the printing out
     * the correct output with spacing, in addition handles multiple errors.
     * Such as if the command line arguments are correct and catches
     * IO Exceptions, NumberFormatExceptions.
     * @param args - Command Line Input
     */

    public static void main(String[] args){

        int limit = 10;
        //limit
        //make args[0] into a file to check if it exists later on

        if(args.length != 2 && args.length != 3){
            //if there are less than 2 arguments or more than 3 arguments, it is an error
            System.err.println("Usage: java CommonWordFinder <filename> <bst|avl|hash> [limit]");
            System.exit(1);
        }

        if(args.length == 2) {
            File file = new File(args[0]);
            //if there are two arguments
            if (!file.exists()) {
                //check if the file exists, if not, then there is an error
                System.err.println("Error: Cannot open file '" + args[0] + "' for input.");
                System.exit(1);
            }
            if(args[1].equals("hash")){
                //if the second argument is hash, do the HashMap method
                try{
                    int len = 0;
                    Entry<String, Integer>[] resArr = hashMapWord(args[0]);
                    //call the hashmap method, and copy the array into a new arr
                    double space = Math.floor(Math.log10(Math.min(resArr.length, 10)));
                    //find out how much spacing you need by flooring the logarithm of base 10 of the amount of unique words
                    int spaceFin = (int) space;

                    System.out.println("Total unique words: " + Math.min(resArr.length, 10));
                    //print out the amount of unique words

                    for(int k = 0; k < Math.min(resArr.length, 10); k++){
                        //find the largest word when it comes to length and save the length
                        len = Math.max(len, resArr[k].key.length());
                    }

                    for(int i = 0; i < Math.min(resArr.length, 10); i++){
                        //iterate through the Entry Array we made earlier copying the hashmap array
                        if(i + 1 < Math.pow(10.0, space)) {
                            //if the number we are looking at is equal to 10 to the power of the spacing we need
                            for (int j = 0; j < spaceFin; j++) {
                                System.out.print(" ");
                                //add the spacing - however, since this only takes two inputs, meaning
                                //the text file and the method hash/avl/bst
                                //the max limit is 10 and the min limit is 1. Therefore only one space
                                //is added to the numbers until 10. Which no space is added.
                            }
                        }
                        System.out.print((i + 1) + ". " + resArr[i].key);
                        //add the number and the key to the print
                        for(int a = resArr[i].key.length(); a < len + 1; a++){
                            //starting at the length of the key, use the length of the largest word and add empty spaces
                            System.out.print(" ");
                        }
                        System.out.print(resArr[i].value);
                        //append the values to the end
                        System.out.print(System.lineSeparator());
                        //line separator
                    }
                    System.exit(0);
                }
                catch (IOException e){
                    //catch IO Exception
                    System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                    //error
                    System.exit(1);
                }
            }
            else if(args[1].equals("bst")){
                //if the argument is bst, do the BST method
                try{
                    //everything else is the same as the HashMap method
                    int len = 0;
                    Entry<String, Integer>[] resArr = binarySearchWord(args[0]);
                    double space = Math.floor(Math.log10(Math.min(resArr.length, 10)));
                    int spaceFin = (int) space;
                    System.out.println("Total unique words: " + Math.min(resArr.length, 10));

                    for(int k = 0; k < Math.min(resArr.length, 10); k++){
                        len = Math.max(len, resArr[k].key.length());
                    }

                    for(int i = 0; i < Math.min(resArr.length, 10); i++){
                        if(i + 1 < Math.pow(10.0, space)) {
                            for (int j = 0; j < spaceFin; j++) {
                                System.out.print(" ");
                            }
                        }
                        System.out.print((i + 1) + ". " + resArr[i].key);
                        for(int a = resArr[i].key.length(); a < len + 1; a++){
                            System.out.print(" ");
                        }
                        System.out.print(resArr[i].value);
                        System.out.print(System.lineSeparator());
                    }
                    System.exit(0);
                }
                catch (IOException e){
                    System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                    System.exit(1);
                }
            }
            else if(args[1].equals("avl")){
                //if the argument is avl, do the AVL method
                try{
                    //everything else is the same as the HashMap and BST method
                    int len = 0;
                    Entry<String, Integer>[] resArr = avlTreeWord(args[0]);
                    double space = Math.floor(Math.log10(Math.min(resArr.length, 10)));
                    int spaceFin = (int) space;
                    System.out.println("Total unique words: " + Math.min(resArr.length, 10));

                    for(int k = 0; k < Math.min(resArr.length, 10); k++){
                        len = Math.max(len, resArr[k].key.length());
                    }

                    for(int i = 0; i < Math.min(resArr.length, 10); i++){
                        if(i + 1 < Math.pow(10.0, space)) {
                            for (int j = 0; j < spaceFin; j++) {
                                System.out.print(" ");
                            }
                        }
                        System.out.print((i + 1) + ". " + resArr[i].key);
                        for(int a = resArr[i].key.length(); a < len + 1; a++){
                            System.out.print(" ");
                        }
                        System.out.print(resArr[i].value);
                        System.out.print(System.lineSeparator());
                    }
                    System.exit(0);
                }
                catch (IOException e){
                    System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                    System.exit(1);
                }
            }
            else{
                //else if no valid argument is given, print out the error
                System.err.println("Error: Invalid data structure '" + args[1] + "' received.");
                System.exit(1);
            }
        }

        else if(args.length == 3){
            File file = new File(args[0]);
            //if there are three arguments given, meaning there is a limit
            if (!file.exists()) {
                //check if the file exists.
                System.err.println("Error: Cannot open file '" + args[0] + "' for input.");
                System.exit(1);
            }
            if(!args[1].equals("bst") && !args[1].equals("avl") && !args[1].equals("hash")){
                //if args 1 is not bst and avl and hash
                System.err.println("Error: Invalid data structure '" + args[1] + "' received.");
                System.exit(1);
            }
            try{
                //check if the third argument is a valid integer
                Integer.parseInt(args[2]);
            }
            catch(NumberFormatException e){
                //else its not a valid integer
                System.err.println("Error: Invalid limit '" + args[2] + "' received.");
                System.exit(1);
            }
            limit = Integer.parseInt(args[2]);
            //set the limit variable to the given limit
            if(limit < 1){
                //if the limit is a non positive integer, then print the error.
                System.err.println("Error: Invalid limit '" + args[2] + "' received.");
                System.exit(1);
            }
            if(args[1].equals("hash")){
                //if the first argument is hash, then do the hash method
                try{
                    //everything else is the same as the previous methods for when we are given two arguments
                    int move = 1;
                    int len = 0;
                    Entry<String, Integer>[] resArr = hashMapWord(args[0]);
                    double space = Math.floor(Math.log10(Math.min(Integer.parseInt(args[2]), resArr.length)));
                    int spaceFin = (int) space;
                    System.out.println("Total unique words: " + Math.min(Integer.parseInt(args[2]), resArr.length));

                    for(int k = 0; k < Math.min(Integer.parseInt(args[2]), resArr.length); k++){
                        len = Math.max(len, resArr[k].key.length());
                    }

                    for(int i = 0; i < Math.min(Integer.parseInt(args[2]), resArr.length); i++){
                        if(i + 1 == Math.pow(10.0, move)){
                            spaceFin--;
                            move++;
                        }
                        for (int j = 0; j < spaceFin; j++) {
                            System.out.print(" ");
                        }
                        System.out.print((i + 1) + ". " + resArr[i].key);
                        for(int a = resArr[i].key.length(); a < len + 1; a++){
                            System.out.print(" ");
                        }
                        System.out.print(resArr[i].value);
                        System.out.print(System.lineSeparator());
                    }
                    System.exit(0);
                }
                catch (IOException e){
                    System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                    System.exit(1);
                }
            }
            else if(args[1].equals("bst")){
                //if the second argument is bst then do the BST method
                try{
                    //everything else is the same as the previous methods as well
                    int move = 1;
                    int len = 0;
                    Entry<String, Integer>[] resArr = binarySearchWord(args[0]);
                    double space = Math.floor(Math.log10(Math.min(Integer.parseInt(args[2]), resArr.length)));
                    int spaceFin = (int) space;
                    System.out.println("Total unique words: " + Math.min(Integer.parseInt(args[2]), resArr.length));

                    for(int k = 0; k < Math.min(Integer.parseInt(args[2]), resArr.length); k++){
                        len = Math.max(len, resArr[k].key.length());
                    }

                    for(int i = 0; i < Math.min(Integer.parseInt(args[2]), resArr.length); i++){
                        if(i + 1 == Math.pow(10.0, move)){
                            spaceFin--;
                            move++;
                        }
                        for (int j = 0; j < spaceFin; j++) {
                            System.out.print(" ");
                        }
                        System.out.print((i + 1) + ". " + resArr[i].key);
                        for(int a = resArr[i].key.length(); a < len + 1; a++){
                            System.out.print(" ");
                        }
                        System.out.print(resArr[i].value);
                        System.out.print(System.lineSeparator());
                    }
                    System.exit(0);
                }
                catch (IOException e){
                    System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                    System.exit(1);
                }
            }
            else if(args[1].equals("avl")){
                //if the second argument is avl then do the AVL method
                try{
                    //everything else is the same as the previous methods
                    int move = 1;
                    int len = 0;
                    Entry<String, Integer>[] resArr = avlTreeWord(args[0]);
                    double space = Math.floor(Math.log10(Math.min(Integer.parseInt(args[2]), resArr.length)));
                    int spaceFin = (int) space;
                    System.out.println("Total unique words: " + Math.min(Integer.parseInt(args[2]), resArr.length));

                    for(int k = 0; k < Math.min(Integer.parseInt(args[2]), resArr.length); k++){
                        len = Math.max(len, resArr[k].key.length());
                    }

                    for(int i = 0; i < Math.min(Integer.parseInt(args[2]), resArr.length); i++){
                        if(i + 1 == Math.pow(10.0, move)){
                            spaceFin--;
                            move++;
                        }
                        for (int j = 0; j < spaceFin; j++) {
                            System.out.print(" ");
                        }
                        System.out.print((i + 1) + ". " + resArr[i].key);
                        for(int a = resArr[i].key.length(); a < len + 1; a++){
                            System.out.print(" ");
                        }
                        System.out.print(resArr[i].value);
                        System.out.print(System.lineSeparator());
                    }
                    System.exit(0);
                }
                catch (IOException e){
                    System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                    System.exit(1);
                }
            }
            else{
                //if no valid data structure is given, then print out the error
                System.err.println("Error: Invalid data structure '" + args[1] + "' received.");
                System.exit(1);
            }
        }
    }
}
