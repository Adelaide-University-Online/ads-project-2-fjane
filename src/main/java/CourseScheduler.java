import java.io.File;
import java.io.FileNotFoundException;
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
}
