/**
 * This class is where most of the accepting of data is going to take place and the data will be stored
 * We will have 3 input CSV files -
 *  1)Student Database file-taken from the admin, this file tells us the total number of students and their identification codes
 *  2)Course Details file-taken from the admin, this file tells us the total number of courses offered, the course codes, and their caps
 *  3)Bids file-taken from the students using google forms, this file gives us the value each student attaches to a particular course
 */
public class Main
{
    public static void main(String[] args) {
        //Reading the coursedetails file
        //Create array(arrayforCourses) for number of courses in the file. Let's say n courses

        int n = 12, cap = 1, m = 500;
        String fcCode = "", nameOfCourse = "";
        Main arrayforCourses[] = new Main[n];
        Main arrayForStudents[] = new Main[m];
        for (int i = 0; i < n; i++) {
            //Let fcCode store the code of the course-eg FC001
            //Let cap store the cap of the course- eg 40
            //nameofCourse has name of Course- eg Indian Civilization

            arrayforCourses[i] = new Course(fcCode, cap, nameOfCourse);
        }

        //Read the Student Database File
        ////Create array (arrayforStudents) for number of students in the file. Let's say m students.
        int cash = 1000;
        for (int i = 0; i < m; i++) {
            //Let sCode store ID of the student
            //Let sName store name of the student
            //cash for student
            arrayForStudents[i] = new Student(sCode, sName, cash);
        }

        //Read the Bids File
        //Second session
        for(int i=0;i<144;i++)
        {
            arrayForBids[i]=new Bids(BidValue,StudentCode,courseCode)
        }
    }
