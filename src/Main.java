/**
 * Created by Samridh on 19-04-2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        //Reading the coursecap file
        //Create array(arrayforCourses) for number of courses in the file. Let's say n courses

        int n=12,cap=1,m=500;
        String fcCode="",nameOfCourse="";
        Main arrayforCourses[]=new Main[n];
        Main arrayForStudents[]=new Main[m];
        for(int i=0;i<n;i++)
        {
            //Let fcCode store the code of the course-eg FC001
            //Let cap store the cap of the course- eg 40
            //nameofCourse has name of Course- eg Indian Civilization

            arrayforCourses[i]=new Course(fcCode,cap,nameOfCourse);
        }

        //Read the Student Database File
        ////Create array (arrayforStudents) for number of students in the file. Let's say m students.
        int cash=1000;
        for(int i=0;i<m;i++)
        {
            //Let sCode store ID of the student
            //Let sName store name of the student
            //cash for student
            arrayForStudents[i]=new Student(sCode,sName,cash);
        }

        //Read the Bids File
        //Second session
