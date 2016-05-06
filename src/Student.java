import java.util.*;  //Required since we will use split method

/**
 * This class contains details of every student
 */
public class Student
{
    /**
     * This constructor splits the line passed by the CSV file to its individual components to be stored in the corresponding field in the object being created
     * @param line This string, which is an individual line in the Preferences CSV file, contains the name, ID, and the course preferences order
     *             of the student, all separated by commas.
     */
    Student(String line)
    {
        String[] componentsOfEachLine=line.split(",");
        int numberOfCourses=componentsOfEachLine.length-5;
        preferenceArrayOfStudent=new String[numberOfCourses];
        studentEmailId=componentsOfEachLine[1];
        studentName=componentsOfEachLine[2];
        studentEnrollmentID=componentsOfEachLine[3];
        numberOfCoursesStudentWants=componentsOfEachLine[4];
        for (int i=0;i<=numberOfCourses;i++)
        {
            preferenceArrayOfStudent[i]=componentsOfEachLine[i+2];
        }
    }
    Student() {}
    String studentEmailId;
    String studentName;
    String studentEnrollmentID;
    String numberOfCoursesStudentWants;
    String[] preferenceArrayOfStudent;
    /**
     *This List contains the Course objects of the courses allocated to the student.
     */
    List<Course> CoursesAllocatedToStudent= new ArrayList<>(4);
}
