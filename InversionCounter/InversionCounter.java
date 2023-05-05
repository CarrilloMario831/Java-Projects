import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class with two different methods to count inversions in an array of integers.
 */
public class InversionCounter {

    /**
     * Returns the number of inversions in an array of integers.
     * This method uses nested loops to run in Theta(n^2) time.
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long countInversionsSlow(int[] array) {
        // TODO
        long count = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = i + 1; j < array.length; j++){
                if(array[i] > array[j]){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Returns the number of inversions in an array of integers.
     * This method uses mergesort to run in Theta(n lg n) time.
     *
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long countInversionsFast(int[] array) {
        // Make a copy of the array so you don't actually sort the original
        // array.
        int[] arrayCopy = new int[array.length],
              scratch =  new int[array.length];
        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        // TODO - fix return statement

        return mergesortHelper(array, scratch, 0, array.length - 1);
    }

    private static Long mergesortHelper(int[] array, int[] scratch, int low, int high){
        long count = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            count += mergesortHelper(array, scratch, low, mid);
            count += mergesortHelper(array, scratch, mid + 1, high);
            int i = low, j = mid + 1;

            for (int k = low; k <= high; k++) {
                if (i <= mid && (j > high || array[i] <= array[j])){
                    scratch[k] = array[i++];
                }
                else {
                    scratch[k] = array[j++];
                    count += ((mid + 1) - (i));
                }
            }
            for (int k = low; k <= high; k++) {
                array[k] = scratch[k];
            }
        }
        return count;
    }

    /**
     * Reads an array of integers from stdin.
     * @return an array of integers
     * @throws IOException if data cannot be read from stdin
     * @throws NumberFormatException if there is an invalid character in the
     * input stream
     */
    private static int[] readArrayFromStdin() throws IOException,
                                                     NumberFormatException {
        List<Integer> intList = new LinkedList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int value = 0, index = 0, ch;
        boolean valueFound = false;
        while ((ch = reader.read()) != -1) {
            if (ch >= '0' && ch <= '9') {
                valueFound = true;
                value = value * 10 + (ch - 48);
            } else if (ch == ' ' || ch == '\n' || ch == '\r') {
                if (valueFound) {
                    intList.add(value);
                    value = 0;
                }
                valueFound = false;
                if (ch != ' ') {
                    break;
                }
            } else {
                throw new NumberFormatException(
                        "Invalid character '" + (char)ch +
                        "' found at index " + index + " in input stream.");
            }
            index++;
        }

        int[] array = new int[intList.size()];
        Iterator<Integer> iterator = intList.iterator();
        index = 0;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return array;
    }

    public static void main(String[] args) {
        if(args.length > 1){
            System.err.println("Usage: java InversionCounter [slow]");
            System.exit(1);
        }
        else if(args.length == 0){
            System.out.print("Enter sequence of integers, each followed by a space: ");
            try{
                int[] arr = readArrayFromStdin();
                if(arr.length == 0){
                    System.err.println("Error: Sequence of integers not received.");
                    System.exit(1);
                }
                System.out.println("Number of inversions: " + countInversionsFast(arr));
                System.exit(0);
            }
            catch(NumberFormatException | IOException e){
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            }
        }
        else if(args[0].equals("slow")){
            System.out.print("Enter sequence of integers, each followed by a space: ");
            try{
                int[] arr = readArrayFromStdin();
                if(arr.length == 0){
                    System.err.println("Error: Sequence of integers not received.");
                    System.exit(1);
                }
                System.out.println("Number of inversions: " + countInversionsSlow(arr));
                System.exit(0);
            }
            catch(NumberFormatException | IOException e){
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            }
        }
        else{
            System.err.println("Error: Unrecognized option '" + args[0] + "'.");
            System.exit(1);
        }
    }
}
