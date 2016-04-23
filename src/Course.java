/**
 * This class stores details about each course-course id, course name, course cap, students enrolled and seats available
 */
public class Course
{
    String courseName;
    int courseCap;
    String courseID;
    Student [] studentsEnrolled;
    int seatsAvailable;
    public Course(String fcCode, int cap, String nameOfCourse)
    {
        courseID=fcCode;
        courseCap=cap;
        courseName=nameOfCourse;
        studentsEnrolled=new Student[courseCap];
        seatsAvailable=courseCap;
    }

}
