import org.apache.commons.csv.CSVRecord;    //Required since we will be parsing CSV file records

import java.util.*;  //Required since we will use LinkedHash

/**
 * This class contains details of every student: name, email ID, enrollment ID, number of courses the student wants, and his preference list.
 */
public class Student
{
    /**
     * This constructor is used to store the details of every student in the corresponding object fields.

     * @param preferenceRecord This CSVRecord, which is an individual line in the Preferences CSV file, contains the name,Enrollment ID,
     *                         email ID, number of courses he/she wants, and the course preferences order of the student, all separated by commas.
     */
    Student(CSVRecord preferenceRecord)
    {

        preferenceSetOfStudent=new LinkedHashSet<>();
        studentEmailId=preferenceRecord.get(1); //auto-generated from the Google Form
        studentName=preferenceRecord.get(2); //Mandatory in the google form
        studentEnrollmentID=preferenceRecord.get(3); //mandatory in the google form
        numberOfCoursesStudentWants=Integer.parseInt(preferenceRecord.get(4)); //just 3 options given ({1,2,3})
        int numberOfCourses=preferenceRecord.size()-5; //subtracting 5 to account for the non-preference columns in the CSV file

        //storing the student preferences in the set
        for(int i=0;i<numberOfCourses;i++)
        {
            preferenceSetOfStudent.add(preferenceRecord.get(i+5));
        }

    }

    //class variables declarations
    String studentEmailId;
    String studentName;
    String studentEnrollmentID;
    int numberOfCoursesStudentWants;
    /**
     * Using LinkedHashSet to store Student Preferences to make sure that no preferences are repeated
     */
    LinkedHashSet<String> preferenceSetOfStudent;

    /**
     *This List contains the Course objects of the courses allocated to the student.
     */
    List<Course> CoursesAllocatedToStudent= new ArrayList<>();
}