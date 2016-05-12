import java.util.*;
import org.apache.commons.csv.CSVRecord;

/**
 * This class contains details of every course
 */
public class Course
{
    /**
     * This constructor splits the line passed by the CSV file to its individual components to be stored in the corresponding field in the object being created
     * @param line This string, which is an individual line in the CourseList CSV file, contains the course code, name, and cap, all separated by commas
     */
    /*
    Course(String line)
    {
        String[] componentsOfEachLine=line.split(",");
        courseCode=componentsOfEachLine[0];
        courseName=componentsOfEachLine[1];
        courseCap=Integer.parseInt(componentsOfEachLine[2]);

    }
    */
    Course(CSVRecord courseRecord)
    {
        courseCode=courseRecord.get(0);
        courseName=courseRecord.get(1);
        courseCap=Integer.parseInt(courseRecord.get(2));
    }
    Course(){}
    String courseName;
    String courseCode;
    int courseCap;
    int noOfStudentswithCourseAsPriority[9];

    /**
     *This List contains the Student objects of the students enrolled in the course.
     */
    List<Student> StudentsEnrolledInCourse=new ArrayList<>();
}
