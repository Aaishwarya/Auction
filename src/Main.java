/**
 * Created by Samridh on 19-04-2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        //Reading the coursecap file
        //Create array(arrayforCourses) for number of courses in the file. Let's say n courses


        for(int i=0;i<n;i++)
        {
            //Let fcCode store the name of the course
            //Let cap store the cap of the course
            //nameofCourse has name of Course
            arrayforCourses[i]=new Course(fcCode,cap,nameOfCourse);
        }

        //Read the Student Database File
        ////Create array (arrayforStudents) for number of students in the file. Let's say m students.
        int cash=1000;
        for(int i=0;i<m;i++)
        {
            //Let sCode store ID of the student
            //Let sName store name of the student
            //
            arrayForStudents[i]=new Student(sCode,sName,cash);
        }

        //Read the Bids File
    }
}
