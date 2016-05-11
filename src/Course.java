import java.util.*;

/**
 * This class contains details of every course
 */
public class Course
{
    /**
     * This constructor splits the line passed by the CSV file to its individual components to be stored in the corresponding field in the object being created
     * @param line This string, which is an individual line in the CourseList CSV file, contains the course code, name, and cap, all separated by commas
     */
    Course(String line)throws NumberFormatException
    {
        String[] componentsOfEachLine=line.split(",");
        courseCode=componentsOfEachLine[0];
        courseName=componentsOfEachLine[1];

        boolean flag1=isNumber(componentsOfEachLine[2]);
        if (flag1)
        {
            courseCap=Integer.parseInt(componentsOfEachLine[2]);
        }
        else
        {
            System.out.println("Invalid values for available number of seats");
            throw new NumberFormatException();
        }

        boolean flag2=isNumber(componentsOfEachLine[3]);
        if (flag1)
        {
            seatsOccupied=Integer.parseInt(componentsOfEachLine[3]);
        }
        else
        {
            System.out.println("Invalid values for available number of seats");
            throw new NumberFormatException();
        }

            seatsAvailable = courseCap - seatsOccupied;

    }
    Course(){}
    String courseName;
    String courseCode;
    int courseCap;
    int seatsAvailable;
    int seatsOccupied;
    /**
     *This List contains the Student objects of the students enrolled in the course.
     */
    List<Student> StudentsEnrolledInCourse=new ArrayList<>();
    public boolean isNumber(String s)
    {
      try
      {
          int i=Integer.parseInt(s);
          return true;
      }
      catch(Exception e)
        {
         return false;
        }

    }
}
