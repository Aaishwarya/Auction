import org.apache.commons.csv.CSVRecord;

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
    Student(CSVRecord preferenceRecord)
    {
        //using LinkedHashSet to store Student Preferences to make sure that no preferences are repeated
        preferenceSetOfStudent=new LinkedHashSet<>();
        studentEmailId=preferenceRecord.get(1); //auto-generated from the Google Form
        studentName=preferenceRecord.get(2); //Mandatory in the google form
        studentEnrollmentID=preferenceRecord.get(3); //mandatory in the google form
        numberOfCoursesStudentWants=Integer.parseInt(preferenceRecord.get(4)); //just 2 options given ({2,3})
        int numberOfCourses=preferenceRecord.size()-5;

        //storing the student preferences in the set
        for(int i=0;i<numberOfCourses;i++)
        {
            preferenceSetOfStudent.add(preferenceRecord.get(i+5));
        }

    }
    Student() {}

    //class variables declaration
    String studentEmailId;
    String studentName;
    String studentEnrollmentID;
    int numberOfCoursesStudentWants;
    LinkedHashSet<String> preferenceSetOfStudent;

    /**
     *This List contains the Course objects of the courses allocated to the student.
     */
    List<Course> CoursesAllocatedToStudent= new ArrayList<>();
}
