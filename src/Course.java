import java.util.*;
import org.apache.commons.csv.CSVRecord;

/**
 * This class contains details of every course being offered: Course Code, Name, and course Cap.
 */
public class Course
{
    /**
     *  This constructor is used to store the details of every course in the corresponding object fields.
     * @param courseRecord This CSV Record, which is an individual line in the CourseList CSV file, contains the course code, name,
     *                     and cap, all separated by commas
     */
    Course(CSVRecord courseRecord)
    {
        courseCode=courseRecord.get(0);
        courseName=courseRecord.get(1);
        courseCap=Integer.parseInt(courseRecord.get(2));
    }
    //class variable declarations
    String courseName;
    String courseCode;
    int courseCap;
    int preferenceCount[]=new int[9];

    /**
     *This List contains the Student objects of the students enrolled in the course.
     */
    List<Student> StudentsEnrolledInCourse=new ArrayList<>();
}
