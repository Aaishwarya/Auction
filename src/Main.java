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
         * The courseList List will contain objects corresponding to each course offered
         */
        List<Course> courseList=new ArrayList<>();

        /**
         * The studentList List will contain objects corresponding to each student, with his/her preferences
         */
        List<Student> studentList=new ArrayList<>();
        try
        {
            //Read Course Details File from the specified directory
            File courseDetailsFile=new File("."+File.separator+"Course Details.csv");
            FileReader fr=new FileReader(courseDetailsFile);
            LineNumberReader lnr=new LineNumberReader(fr);
            String coursesHeaderLine=lnr.readLine(); //reads the first line of the file which is the header file
            String coursesInput=lnr.readLine(); //reads each line of the Course Details File.

            //Reading every line of the Course Details file, and adding an object corresponding to each course to the courseList
            while(coursesInput!=null)
            {
                courseList.add(new Course(coursesInput));
                coursesInput=lnr.readLine();
            }

            //Reads the Preferences File from the specified Directory
            File preferencesFile=new File("."+File.separator+"Preferences.csv");
            FileReader fr2=new FileReader(preferencesFile);
            LineNumberReader lnr2=new LineNumberReader(fr2);
            String PreferencesHeaderLine=lnr2.readLine(); //reads the first line of the file which is the header file
            String PreferencesInput=lnr2.readLine(); //reads each line of the Preferences File.

            //Reading every line of the CSV file, and adding an object corresponding to each student, and his/her preferences
            while(PreferencesInput!=null)
            {
                studentList.add(new Student(PreferencesInput));
                PreferencesInput=lnr2.readLine();
            }


        //catches an IO Exception if found
        }catch (IOException e)
        {
            System.out.println("Found Exception: "+e);
        }
        Main obj=new Main();
        obj.allocateCourses(courseList,studentList); //passes the two lists to allocate students as per preferences
        obj.createOutputFiles(); //once the course allocations have been done, creates output CSV files
    }

    /**
     * This function will allocate courses based on an algorithm.
     */
    public void allocateCourses(List<Course> courseList, List<Student> studentList)
    {
        int numberOfCourses=courseList.size();
        int numberOfStudents=studentList.size();

        //creates a new clone list of the original student list
        List<Student> cloneStudentList=new ArrayList<>(numberOfStudents);
        for(Iterator it=studentList.iterator();it.hasNext();)
        {
            cloneStudentList.add((Student) it.next());
        }

        //iterates for as many times as the maximum preference a student can have, i.e. the total number of courses
        PRIME:
        for(int i=0;i<numberOfCourses;i++)
        {

            cloneStudentList=RemoveRedundancies(cloneStudentList,i); //returns a redundancy-free modified cloneStudentList
            Collections.shuffle(cloneStudentList); //shuffles the cloneStudentList to achieve a random order
            int flag=1;//serves as a indicator to move on to the next student once the previous student has been assigned a course(flag=1) else repeat(flag=0)

            //iterates on the shuffled list to assign courses student by student
            for(ListIterator it = (ListIterator) cloneStudentList.listIterator(); it.hasNext();)
            {
              Student s= (Student) it.next();

                //repeats allocation for same student if (s)he hasn't been assigned one in the current PRIME iteration
                if(flag==0&& it.hasPrevious())
                {
                    s=(Student) it.previous();
                    flag=1;
                }

                String preferenceArrayOfStudent[]=(String[]) s.preferenceSetOfStudent.toArray(new String[s.preferenceSetOfStudent.size()]);
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
        List<Student> cloneStudentsList=new ArrayList<>(studentList.size());
        for(Iterator it=studentList.iterator();it.hasNext();)
        {
            cloneStudentsList.add((Student) it.next());
        }
        for(Iterator it=cloneStudentsList.iterator();it.hasNext();)
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

