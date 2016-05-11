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

    /*
    Student(String line)
    {
        String[] componentsOfEachLine=line.split(",");
        int numberOfCourses=componentsOfEachLine.length-5;
        preferenceSetOfStudent=new LinkedHashSet<>();
        studentEmailId=componentsOfEachLine[1];
        studentName=componentsOfEachLine[2];
        studentEnrollmentID=componentsOfEachLine[3];
        numberOfCoursesStudentWants=Integer.parseInt(componentsOfEachLine[4]);
        for (int i=0;i<numberOfCourses;i++)
        {
            preferenceSetOfStudent.add(componentsOfEachLine[i+5]);
        }
    }
    */

    Student(CSVRecord preferenceRecord)
    {
        preferenceSetOfStudent=new LinkedHashSet<>();
        studentEmailId=preferenceRecord.get(1);
        studentName=preferenceRecord.get(2);
        studentEnrollmentID=preferenceRecord.get(3);
        numberOfCoursesStudentWants=Integer.parseInt(preferenceRecord.get(4));
        int numberOfCourses=preferenceRecord.size()-5;
        for(int i=0;i<numberOfCourses;i++)
        {
            preferenceSetOfStudent.add(preferenceRecord.get(i+5));
        }

    }
    Student() {}
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
