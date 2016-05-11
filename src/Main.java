import java.io.*;   //Required since we will read and output .CSV files
import java.util.*;  //Required since we will use ArrayLists

/**
 * The Main Class contains the main function, and here we will take the input files for the Course Lists, and the Preferences of the Students
 */
public class Main
{
    /**
     * This method reads 2 CSV files as input:
     * 1) Course Details file: This file, provided by the Office of Academic Affairs, will store the total number of courses offered,
     *    the corresponding course codes, and the number of seats available for each course.
     * 2) Preferences file: This file, procured from the students using a Google Form, gives us the preference order of courses of each student.
     *
     * Based on these inputs, the program allocates courses to the students in the best manner possible.
     *
     * The program outputs 2 CSV files:
     * 1) Student List: A File containing the names and IDs of the students enrolled in the various courses
     * 2) [Updated] Course Details file: This file contains the number of seats still available in every course, to be fed as input to
     *    another batch of students (if required).
     */
    public static void main(String[] args)
    {
        /**
         * The CourseList List will contain objects corresponding to each course offered
         */
        List<Course> courseList=new ArrayList<>();

        /**
         * The PreferencesList List will contain objects corresponding to each student, with his/her preferences
         */
        List<Student> studentList=new ArrayList<>();
        try
        {
            //Read Course Details File
            File courseDetailsFile=new File("."+File.separator+"Course Details.csv");
            FileReader fr=new FileReader(courseDetailsFile);
            LineNumberReader lnr=new LineNumberReader(fr);
            String input=lnr.readLine();
            input=lnr.readLine();

            //Reading every line of the CSV file, and adding an object corresponding to each course
            while(input!=null)
            {
                courseList.add(new Course(input));
                input=lnr.readLine();
            }

            //Read Preferences File
            File preferencesFile=new File("."+File.separator+"Preferences.csv");
            FileReader fr2=new FileReader(preferencesFile);
            LineNumberReader lnr2=new LineNumberReader(fr2);
            String input2=lnr2.readLine();
            input2=lnr2.readLine();

            //Reading every line of the CSV file, and adding an object corresponding to each student, and his/her preferences
            while(input2!=null)
            {
                studentList.add(new Student(input2));
                input2=lnr2.readLine();
            }



        }catch (IOException e)
        {
            System.out.println("Found Exception: "+e);
        }
        Main obj=new Main();
        Student studentObject = new Student();
        Course courseObject = new Course();
        obj.allocateCourses(courseList,studentList);
        obj.createOutputFiles();
    }

    /**
     * This function will allocate courses based on an algorithm.
     */
    public void allocateCourses(List<Course> courseList, List<Student> studentList)
    {
        try
        {
        int numberOfCourses=courseList.size();
        int numberOfStudents=studentList.size();
        List<Student> cloneStudentList=new ArrayList<>(numberOfStudents);
        for(Iterator it=studentList.iterator();it.hasNext();)
        {
            cloneStudentList.add((Student) it.next());
        }
        for(int i=0;i<numberOfCourses;i++)
        {

            cloneStudentList=RemoveRedundancies(cloneStudentList,i);
            Collections.shuffle(cloneStudentList);
            int flag=1;
            for(ListIterator it = (ListIterator) cloneStudentList.iterator(); it.hasNext();)
            {
              Student s= (Student) it.next();
                if(flag==0&& it.hasPrevious())
                {
                    s=(Student) it.previous();
                    flag=1;
                }

                if(s.preferenceSetOfStudent.size()<=i)
                {
                    continue;
                }
                String preferenceArrayOfStudent[]=(String[]) s.preferenceSetOfStudent.toArray();
                for(Iterator courseIt=courseList.iterator();courseIt.hasNext();)
                {
                    Course c=(Course) courseIt.next();
                    if(c.courseName.equalsIgnoreCase(preferenceArrayOfStudent[i]))
                    {
                        if(c.seatsAvailable!=0)
                        {
                            c.StudentsEnrolledInCourse.add(s);
                            s.CoursesAllocatedToStudent.add(c);
                            c.seatsAvailable--;
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
        catch(NumberFormatException e)
        {
         System.out.println("Invalid format of data entered. Expected format was integer.");
        }

    /**
     * This function will create the output files.
     */
    public void createOutputFiles()
    {
        //Create Output Files
    }

    /**
     * To remove redundancies in the StudentList
     */
    public List RemoveRedundancies(List studentList, int prefNo)
    {
        for(Iterator it=studentList.iterator();it.hasNext();)
        {
            Student s= (Student) it.next();
            if ((s.numberOfCoursesStudentWants==s.CoursesAllocatedToStudent.size())||(s.preferenceSetOfStudent.size()<=prefNo))
            {
                studentList.remove(s);
            }

        }
        return studentList;
    }
}