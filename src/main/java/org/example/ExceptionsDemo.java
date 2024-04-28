package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //System.out.println("__(a) method 1__");
        //takeDecimalInput();

        // (a) method 2
        //System.out.println("__(a) method 2__");
        //takeDecimalInputSupplier.get();


        //System.out.println("Take date:");
        //takeDate.get();

        //System.out.println("_readTextFile_");
        //readTextFile();

        //writeTextToFile();

        writeTextToFileWithTryResources();
    }


    // (a) method 1
    public static double takeDecimalInput() {
        Scanner scanner = new Scanner(System.in);
        double number;
        while (true) {
            try {
                System.out.println("Enter a Number: ");
                number = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
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
        while (true) {
            try {
                System.out.println("Enter a Number: ");
                number = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Exception: " + e.fillInStackTrace());
                // note 1.
                scanner.next();
            }
        }
        return number;
    };

    public static Supplier<LocalDate> takeDate = () -> {
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        while (true){
        try {
            System.out.println("Enter Date as yyyy-MM-dd");
            String input = scanner.nextLine();
            // we make convert from 'String' to 'LocalDate' before make 'break'
            date = LocalDate.parse(input);
            break;
        } catch (DateTimeParseException e) {
            System.out.println("Wrong: " + e.getMessage());
        }
        }
        return date;
    };

    public static void readTextFile(){
        String absolutePath = "/Users/a2041/Desktop/Exception/Exceptions/dir/skills.txt";
        String relativePath0 = "dir\\skills.txt";
        String relativePath1 = "dir/skills.txt";
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(relativePath1));
            /*
            Stream<String> stringStream = reader.lines();
            List<String> skills = stringStream.collect(Collectors.toList());
            skills.forEach(System.out::println);
             */
            reader
                    .lines()
                    .collect(Collectors.toList())
                    .forEach(System.out::println);

            ////

            List<String> readAll = Files.readAllLines(Paths.get(relativePath1));
            readAll.forEach(System.out::println);

        }catch (NoSuchFileException w){
            System.out.println("File not found: " + w.getFile());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    // Method "Old School": Demonstrates use of 'finally' with 'File' to ensure proper resource handling.
    public static void writeTextToFile(){
        Path path= Paths.get("dir/skills.txt");
        BufferedWriter writeFile = null;
        try {
            writeFile = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            writeFile.append("React");
            writeFile.newLine();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writeFile != null){
                try {
                    writeFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Allocated resources are released ... ");
        }
    }
    // Method "New School": No need for 'finally' and 'close' for 'File'. 'File' is opened and closed within 'try-with-resources'.
    public static void writeTextToFileWithTryResources(){
        Path path = Paths.get("dir/text.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND))
        {
            writer.append("Hi");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
