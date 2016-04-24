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
        int numberOfCourses=componentsOfEachLine.length-2;
        PreferenceArrayOfStudent=new String[numberOfCourses];
        StudentName=componentsOfEachLine[0];
        StudentID=componentsOfEachLine[1];
        for (int i=0;i<=numberOfCourses;i++)
        {
            PreferenceArrayOfStudent[i]=componentsOfEachLine[i+2];
        }
    }
    Student() {}
    String StudentName;
    String StudentID;
    String[] PreferenceArrayOfStudent;
    /**
     *This List contains the Course objects of the courses allocated to the student.
     */
    List<Course> CoursesAllocatedToStudent= new ArrayList<>(4);
}
