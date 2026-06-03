import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * File: CourseScheduler.java
 * Description: This is the entry to the application. It populates a course schedule based on a course file name and
 * amount of subjects to be taken concurrently provided by user input. It handles reading in a text file containing
 * a course's subjects and prerequisites. Course schedule will be printed to screen and written to current working
 * directory as "CourseSchedule.txt".
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

public class CourseScheduler {

    /**
     * Gets course code from user.
     * @param userInput Scanner user input
     * @return Scanner file reader containing the requested course file
     */
    public static String courseCode(Scanner userInput) {

        // Get user input for course file name - case-insensitive by default
        System.out.println("Please enter course file name. Eg: XBIT.txt");
        return userInput.nextLine().trim();
    }

    /**
     * Imports course data from a text file. File cannot be empty.
     * @param courseCode Course file name
     * @return Scanner file reader containing the requested course file
     */
    public static Scanner course(String courseCode) throws FileNotFoundException, EmptyFileException {

        Scanner fileScanner = new Scanner(new File(courseCode));

        // Empty file check
        if (!fileScanner.hasNextLine()) {
            fileScanner.close();
            throw new EmptyFileException("File is empty.\n");
        }
        return fileScanner;
    }

    /**
     * Gets the amount of subjects a user would like to study concurrently in a single study period. Maximum is 4
     * in consideration of full-time in person, minimum is 1.
     * @param userInput Scanner user input
     * @return int The number of subjects to be studied per study period
     */
    public static int concurrentCourses(Scanner userInput){

        int concurrent = 0;
        int minLoad = 1;
        int maxLoad = 4;

        // Loop used to ensure valid integer (1-4) is entered
        while (true) {

            try {
                // Get and save user input for concurrent courses
                System.out.println("Enter the amount of concurrent subjects you wish to take per study period, between 1 and 4.");
                concurrent = userInput.nextInt();

                // Check user input is in range
                if (concurrent < minLoad || concurrent > maxLoad) {
                    throw new IllegalArgumentException("Number must be between 1 and 4.\n");
                }

                System.out.println();

                break; // When an in range integer is entered

            } catch (InputMismatchException e) { // Error if input is not integer type
                System.err.println("Error: Please enter a valid number between 1 and 4.\n");
                userInput.nextLine();

            } catch (IllegalArgumentException e) { // Error if integer input is not in range
                System.err.println("Error: " + e.getMessage());
                userInput.nextLine();

            } catch (IllegalStateException e) {
                System.err.println("Scanner is closed unexpectedly.\n");
                userInput.nextLine();
            }
        }
        return concurrent;
    }
    /*
    Exception handling code inspired by:

    Khayalian, S. (2024, June 17). Mastering Java Exception Handling: A Comprehensive Guide for Developers.
    https://medium.com/@ShantKhayalian/mastering-java-exception-handling-a-comprehensive-guide-for-developers-c31cef921d75

    Geeksforgeeks. (2025, July 23). Types of Exception in Java with Examples.
    https://www.geeksforgeeks.org/java/types-of-exception-in-java-with-examples/

    w3resource. (2025, May 23). Java Program: File reading and empty file exception handling.
    https://www.w3resource.com/java-exercises/exception/java-exception-exercise-5.php
    */

}
