import java.io.*;   //Required since we will read and output .CSV files
import java.nio.charset.Charset;
import java.util.*;  //Required since we will use ArrayLists

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


/**
 * The Main Class contains the main function, and here we will take the input files for the Course Lists, and the Preferences of the Students.
 * The main function will then allocate courses to students, and will subsequently print the final allocations for every course being offered.
 */
public class Main
{
    /**
     * This method reads 2 CSV files as input:
     * 1) Course Details file ("Course Details.csv"): This file, provided by the Office of Academic Affairs, will store the total number of courses offered,
     *    the corresponding course codes, and the number of seats available for each course.
     * 2) Preferences file ("Preferences.csv"): This file, procured from the students using a Google Form, gives us the preference order of courses of each student.
     *
     * Based on these inputs, the program allocates courses to the students in the best manner possible.
     *
     * The program outputs the following CSV files:
     * 1) Student List (for every course): A File containing the names and IDs of the students enrolled in the various courses
     * 2) [Updated] Course Details file: This file contains the number of seats still available in every course, to be fed as input to
     *    another batch of students (if required).
     * 3) Course List : A File containing the names of all students, along with the courses allocated to them.
     */
    static int f=0;
    public static void main(String[] args) {
        /**
         * The courseList List will contain objects corresponding to each course offered
         */
        List<Course> courseList = new ArrayList<>();
        /**
         * The studentList List will contain objects corresponding to each student, with his/her preferences
         */
        List<Student> studentList = new ArrayList<>();
        try {
            CSVParser parser = CSVParser.parse(new File("."+File.separator+"Course Details.csv"), Charset.forName("UTF-8"), CSVFormat.EXCEL.withHeader());
            List<CSVRecord> courseDetails = parser.getRecords();    //parsing CSV file
            for(CSVRecord courseRecord : courseDetails)             //iterating over each courseRecord
            {
                courseList.add(new Course(courseRecord));           //making a new object for each course
            }

            CSVParser parser2 = CSVParser.parse(new File("."+File.separator+"Preferences.csv"), Charset.forName("UTF-8"), CSVFormat.EXCEL.withHeader());
            List<CSVRecord> preferencesDetails = parser2.getRecords();
            for(CSVRecord preferenceRecord : preferencesDetails)
            {
                studentList.add(new Student(preferenceRecord));     //making a new object for each student
            }

        Main obj = new Main();
        obj.allocateCourses(courseList, studentList); //passes the two lists to allocate students as per preferences
        obj.printStatistics (courseList,studentList); //prints statistics for internal use by the Office of Academic Affairs
        obj.createOutputFiles(courseList, studentList); //once the course allocations have been done, creates output CSV files
    }
        //catches an IO Exception if found
        catch (IOException e)
        {
            System.out.println("Found Exception: "+e);
        }
    }

    /**
     * This function will allocate courses based on an algorithm which tries to give every student his/her most preferred course.
     * @param courseList This List contains objects of every course being offered, which in turn contain the details of the corresponding course
     *                   as their fields.
     * @param studentList This List contains objects of every student who has to be allocated courses, which further contain details of the students.
     */
    public void allocateCourses(List<Course> courseList, List<Student> studentList)
    {
        int numberOfCourses=courseList.size();
        int numberOfStudents=studentList.size();

        /**
         * This clone list of the original student list will be manipulated during allocation, to leave the original list undisturbed for future use.
         */
        List<Student> cloneStudentList=new ArrayList<>(numberOfStudents);
        for(Iterator it=studentList.iterator();it.hasNext();)
        {
            cloneStudentList.add((Student) it.next());
        }

        //iterates for as many times as the maximum preference a student can have, i.e. the total number of courses
        PRIME:
        for(int i=0;i<numberOfCourses;i++)
        {
            //returns a modified cloneStudentList after removing the students whose listed priorities have been exhausted.
            cloneStudentList= RemoveRedundancies(cloneStudentList,i);
            Collections.shuffle(cloneStudentList);  //shuffles the cloneStudentList to achieve a random order
            Collections.shuffle(cloneStudentList);
            Collections.shuffle(cloneStudentList);
            Collections.shuffle(cloneStudentList);
            int flag=1;     //serves as a indicator to move on to the next student once the previous student has been assigned a course(flag=1) else repeat(flag=0)

            //iterates on the shuffled list to assign courses student by student
            for(ListIterator it = (ListIterator) cloneStudentList.listIterator(); it.hasNext();)
            {
              Student s= (Student) it.next();

                //repeats allocation for same student if (s)he hasn't been assigned one in the current PRIME iteration
                if(flag==0&& it.hasPrevious())
                {
                    flag=1;
                }

                //Actual course allocation done here
                String preferenceArrayOfStudent[]=s.preferenceSetOfStudent.toArray(new String[s.preferenceSetOfStudent.size()]);

                for(Iterator courseIt=courseList.iterator();courseIt.hasNext();)
                {
                    Course c=(Course) courseIt.next();
                    if(c.courseName.equalsIgnoreCase(preferenceArrayOfStudent[i]))
                    {
                        if(c.courseCap!=0)
                        {
                            c.StudentsEnrolledInCourse.add(s);
                            s.CoursesAllocatedToStudent.add(c);
                            c.courseCap--;
                        }
                        else
                        {
                          s.preferenceSetOfStudent.remove(c);
                            flag=0;
                            break;
                        }
                    }
                }
            }
        }
    }


    /**
     * This function will create the output files pertaining to individual courses as well as the Student allocation data
     * @param courseList This List contains objects of every course being offered, which in turn contain the details of the corresponding course
     *                   as their fields.
     * @param studentList This List contains objects of every student who has to be allocated courses, which further contain details of the students.
     */
    private void createOutputFiles(List<Course> courseList, List<Student> studentList) throws IOException
    {
        //Stores the output files in a directory
        String dirname = "OutputFiles";
        File outputDirectory = new File(dirname);
        outputDirectory.mkdirs();

        //creates files for each course with the list of students enrolled in it
        for(Iterator courseIt=courseList.iterator();courseIt.hasNext();)
        {
            Course c = (Course) courseIt.next ( );
            String fileName = c.courseName;
            File file = new File ("." + File.separator + outputDirectory+ File.separator+ fileName + ".csv");
            CSVPrinter printer = new CSVPrinter (new BufferedWriter (new FileWriter (file)), CSVFormat.EXCEL);
            printer.printRecord ("Student Name", "Student Enrollment ID", "Student Email id");
            List<Student> enrolled = c.StudentsEnrolledInCourse;

            for (Iterator eachCourse = enrolled.iterator ( ); eachCourse.hasNext ( ); )
            {
                Student s = (Student) eachCourse.next ( );
                printer.printRecord (s.studentName, s.studentEnrollmentID, s.studentEmailId);
            }

            printer.close ( );

        }

        //Outputs a CSV file which consist of details of all students and their corresponding allocated subjects
        File file2 = new File ("." + File.separator+ outputDirectory+ File.separator + "StudentCourseList.csv");
        CSVPrinter printer2 = new CSVPrinter (new BufferedWriter (new FileWriter (file2)), CSVFormat.EXCEL);
        printer2.printRecord ("Student Enrollment ID", "Student Email ID", "Student Name", "Course1", "Course2", "Course3");
        for(Iterator eachStudent=studentList.iterator();eachStudent.hasNext();)
        {
            Student s=(Student) eachStudent.next();
            printer2.print(s.studentEnrollmentID);
            printer2.print(s.studentEmailId);
            printer2.print(s.studentName);
            for(Iterator eachStudentCourses=s.CoursesAllocatedToStudent.iterator();eachStudentCourses.hasNext();)
            {
                Course c=(Course) eachStudentCourses.next ();
                printer2.print(c.courseName);
            }
            printer2.println();

        }
        printer2.close();

        //Outputs the altered Course Details file with the redefined course caps so that it can be reused
        File file3 = new File ("." + File.separator+ outputDirectory+ File.separator + "Altered Course Details.csv");
        CSVPrinter printer3 = new CSVPrinter (new BufferedWriter (new FileWriter (file3)), CSVFormat.EXCEL);
        printer3.printRecord("Course Code", "Course Name", "Seats Available");

        for(Iterator courseIt=courseList.iterator();courseIt.hasNext();)
        {
            Course c = (Course) courseIt.next ( );
            printer3.printRecord (c.courseCode, c.courseName, c.courseCap);
        }
        printer3.close();
    }

    /**
     * This method will print statistics like how many students got their 1st/2nd preferred course, etc., ehich can be used to
     * check the efficiency of the system.
     * @param courseList This List contains objects of every course being offered, which in turn contain the details of the corresponding course
     *                   as their fields.
     * @param studentList This List contains objects of every student who has to be allocated courses, which further contain details of the students.
     */
    public void printStatistics(List<Course> courseList, List<Student> studentList)
    {

    }

    /**
     * This method removes the students who have run out of preferences from the clone list.
     * @param studentList This List contains objects of every student who has to be allocated courses, which further contain details of the students.
     * @param currentPreferenceNo Indicates which preference number course is being currently alloated by the program.
     * @return Returns a list after removing the redundant students (i.e. the ones who have run out of their listed choices)
     */
    public List RemoveRedundancies(List studentList, int currentPreferenceNo)
    {
        List<Student> cloneStudentsList=new ArrayList<>(studentList.size());
        for(Iterator it=studentList.iterator();it.hasNext();)
        {
            cloneStudentsList.add((Student) it.next());
        }
        for(Iterator it=cloneStudentsList.iterator();it.hasNext();)
        {

            Student s= (Student) it.next();
            if ((s.numberOfCoursesStudentWants==s.CoursesAllocatedToStudent.size())||(s.preferenceSetOfStudent.size()<=currentPreferenceNo))
            {
                studentList.remove(s);
            }

        }
        return studentList;
    }
}