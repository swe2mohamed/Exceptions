package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

public class ExceptionsDemo {
    public static void main(String[] args) {
        /*
        note 2
        checked-exception:
        Files.newBufferedReader(Paths.get("dir/skills.txt"));
        BufferedReader reader =  Files.newBufferedReader(Paths.get("dir/skills.txt"));
         */

        /*
        note 3
        unchecked-exception:
        int[] numbers = {1,2,3,4};
        System.out.println(numbers[5]);
         */

        // (a) method 1
        System.out.println("__(a) method 1__");
        takeDecimalInput();

        // (a) method 2
        System.out.println("__(a) method 2__");
        takeDecimalInputSupplier.get();
    }


    // (a) method 1
    public static double takeDecimalInput(){
        Scanner scanner = new Scanner(System.in);
        double number;
        while (true){
            try {
                System.out.println("Enter a Number: ");
                number =  scanner.nextDouble();
                break;
            }
            catch (InputMismatchException e ){
                System.out.println("Exception: " + e.fillInStackTrace());
                // note 1.
                scanner.next();
            }
        }
            return number;
    }

    // (a) method 2
    public static Supplier<Double> takeDecimalInputSupplier = () -> {
        Scanner scanner = new Scanner(System.in);
        double number;
        while (true){
            try {
                System.out.println("Enter a Number: ");
                number =  scanner.nextDouble();
                break;
            }
            catch (InputMismatchException e ){
                System.out.println("Exception: " + e.fillInStackTrace());
                // note 1.
                scanner.next();
            }
        }
        return number;
    };

}
