

/**
 * Class containing 3 methods that perform the Euclidean Algorithm, Iteratively and Recursively
 */

public class GCD {

    /**
     * Returns the greatest common divisor of two integers in an iterative form
     * @param m the first argument (number)
     * @param n the second argument (number)
     * @return the greatest common divisor of two integers
     */
    public static int iterativeGcd(int m, int n) {

        int remainder = -1;
        int absM = Math.abs(m);
        int absN = Math.abs(n);

        while(true){
            //while we have not reached a solution
            if (m == 0 && n != 0) {
                return absN;
            }
            //if m is 0 and n is not 0, then re turn the positive value of n

            else if (m != 0 && n == 0) {
                return absM;
            }
            //if m is not 0 and n is 0 then return the positive value of m

            while (m != 0 && n != 0) {
            //while m is not 0 and n is not 0

                if (absM == absN) {
                    return absM;
                }
                //if m is the same value as n, then we return whichever value since they are the same

                else if (absM > absN) {
                    //else if m is greater than n
                    while (remainder != 0) {
                        //while the remainder is not 0
                        remainder = absM % absN;
                        //set the remainder be the modulo of m and n
                        absM = absN;
                        //change the value of m to be n
                        absN = remainder;
                        //change the value of n to be the remainder
                        if (absN == 0) {
                            //if n is 0
                            return absM;
                            //return m
                        }
                    }
                }


                else if (absN > absM) {
                    //else if n is greater than m
                    while (remainder != 0) {
                        //while the remainder is not 0
                        remainder = absN % absM;
                        //set the remainder be the module of n and m
                        absN = absM;
                        //change the value of n to be m
                        absM = remainder;
                        //change the value of m to be the remainder
                        if (absM == 0) {
                            //if m is 0
                            return absN;
                            //return n
                        }
                    }
                }
            }
        }
    }

    /**
     * Return the greatest common divisor of two integers in a recursive way using ternary operators
     * @param m the first argument (number)
     * @param n the second argument (number)
     * @return the greatest common divisor of two integers
     */
    public static int recursiveGcd(int m, int n){
        //create a variable (res "resolution") to hold the gcd (solution) of both integers m and n
        int res = m == 0 ? Math.abs(n) :
                //if m is 0, return the positive value of n
                n == 0 ? Math.abs(m) :
                        //else if n is 0, return the positive value of m
                        recursiveGcd(n, m % n);
                            //else call the method recursiveGcd to loop once more till either m or n is 0
                            //however we change the value of m to n and have n be the remainder of m divided by n within the call of the method recursiveGcd
        return res;
        //return the solution
    }

    /**
     * Edge case testing, makes sure proper input is given in the command line and prints the greatest common divisor
     * @param args command line arguments (both arguments (two integers))
     */

    public static void main(String[] args){

        if (args.length != 2) {
            //if the length of the input is not 2
            System.out.println("Usage: java GCD <integer m> <integer n>");
            //print out an error which lets the user know of the correct input format
            System.exit(1);
            //Fail, Exit
        }

        try{
            Integer.parseInt(args[0]);
            //try if the first input can be parsed into an integer
        }
        catch(NumberFormatException e){
            //if it can't, catch the NumberFormatException
            System.out.println("Error: The first argument is not a valid integer.");
            //Print out that its an error and the first number is not valid
            System.exit(1);
            //Fail, Exit
        }

        try{
            Integer.parseInt(args[1]);
            //Try if the second input can be parsed into an integer
        }
        catch(NumberFormatException e){
            //if it can't then catch the NumberFormatException
            System.out.println("Error: The second argument is not a valid integer.");
            //Print out that its an error and the second number is not valid
            System.exit(1);
            //Fail, Exit
        }

        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        if(m == 0 && n == 0){
            //if m is 0 and n is 0
            System.out.println("gcd(0, 0) = undefined");
            //print out that the GCD of (0,0) is undefined
            System.exit(0);
            //Success, Exit
        }


        System.out.printf("Iterative: gcd(%d, %d) = %d%n",m,n,iterativeGcd(m, n));
        //Else print the iterative solution of m and n
        System.out.printf("Recursive: gcd(%d, %d) = %d%n",m,n,recursiveGcd(m, n));
        //Else print the recursive solution of m and n
        System.exit(0);
        //Success, Exit
    }

}
