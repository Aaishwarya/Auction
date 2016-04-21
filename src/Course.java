/**
 * Created by Samridh on 19-04-2016.
 */
public class Course
{
    String courseName;
    int courseCap;
    String courseID;
    int [] studentsEnrolled;
    public Course(String fcCode, int cap, String nameOfCourse)
    {
        courseID=fcCode;
        courseCap=cap;
        courseName=nameOfCourse;
        studentsEnrolled=new int[courseCap];
    }

}
