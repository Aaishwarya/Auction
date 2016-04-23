/**
 * In this program, we will allocate courses to students based on the value they assign to the respective courses. The one who assigns the most value gets in first.
 * We will have 3 input CSV files -
 *  1)Student Database file-taken from the admin, this file tells us the total number of students and their identification codes
 *  2)Course Details file-taken from the admin, this file tells us the total number of courses offered, the course codes, and their caps
 *  3)Bids file-taken from the students using google forms, this file gives us the value each student attaches to a particular course
 *
 * The program then gives 2 CSV files as output
 *  1)Student list for each subject
 *  2)Redefined Course Details file with subject caps updated after the students have been assigned
 */

import java.util.*;

public class Main {
    //The main method of this class is where the CSV files are read and data is passed to different methods to be computed
    public static void main(String[] args) {
        //We can find the value of numOfCourses while reading the CSV Course Details file and assign it
        //We can find the value of numOfStudents while reading the CSV Student Database file and assign it
        int numOfCourses = 12, numOfStudents = 500;
        //Create array(arrayforCourses) of objects for each of the courses in the CSV Course Details file.
        ArrayList<Course> arrayForCourses = new ArrayList<Course>();
        //Create array(arrayforStudents) of objects for each of the students in the CSV student database file.
        ArrayList<Student> arrayForStudents = new ArrayList<Student>();

        //Let fcCode store the code of the course-eg FC001
        //Let CourseCap store the cap of the course- eg 40
        //nameofCourse has name of Course- eg Indian Civilization
        String fcCode = "", nameOfCourse = "";
        int courseCap = 1;

        //Reading the Course Details CSV file
        for (int i = 0; i < numOfCourses; i++) {
            //The fcCode, nameOfCourse and courseCap will be read from the file, assigned and passed
            arrayForCourses.add(new Course(fcCode, courseCap, nameOfCourse));
        }

        //Let sCode store ID of the student
        //Let sName store name of the student
        //Let sEmailID store the email id of the student
        String sCode = "", sName = "", sEmailID = "";

        //Reading the Student Database File
        for (int i = 0; i < numOfStudents; i++) {

            //The sCode, sName and sEmailID will be read from the file, assigned and passed
            arrayForStudents.add(new Student(sCode, sName, sEmailID));
        }

        //Reading the Bids File course by course (Column wise)
        for (int i = 0; i < numOfCourses; i++) {
            //reads the value attached for the course for each student
            for (int j = 0; j < numOfStudents; j++) {
                //passes data (Student id and corresponding value attached if any to the Course object)
            }
        }
    }
}
