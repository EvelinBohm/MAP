package com.company.Repository;

import com.company.Model.Course;
import com.company.Model.Person;
import com.company.Model.Student;
import com.company.Model.Teacher;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseRepository extends the InFileRepo, the class stores and updates instances of class type Course
 * @author Bohm Evelin
 * @version  16.11.2021
 * @since 1.0
 */

public class CourseRepository extends InFileRepo<Course> {

    /**
     * initializes the repository list with the data read from the file
     * @param repoList a course list
     * @throws FileNotFoundException if the file couldn't be found
     */
    public CourseRepository(List<Course> repoList) throws FileNotFoundException {
        super(repoList);
    }


    /**
     * Overrides the findOne methode from the interface
     * searches for the course with the given id
     * @param courseID-must not be null
     * @return Course-if it's found or null -if the course does not exist
     */
    @Override
    public Course findOne(Long courseID)
    {

        for(Course course:repoList){
            if (course.getCourseID().equals(courseID))
                return course;
        }
        return null;
    }

    /**
     * Overrides the findAll methode from the interface
     * @return  Iterable of courses
     */
    @Override
    public Iterable<Course> findAll()
    {
        return repoList;
    }

    /**
     * Overrides the save methode from the interface
     * adds the course to the list of courses
     * @param newCourse-most be not null
     * @return course -if the course has been already saved or null -if the course was added successfully
     */
    @Override
    public Course save(Course newCourse)
    {
        for (Course course:repoList)
        {
            if (findOne(newCourse.getCourseID())!=null)
            {
                return newCourse;
            }
        }
        repoList.add(newCourse);
        return null;
    }

    /**
     * deletes the course with the given ID
     * @param id id must be not null
     * @return course -if it was deleted successfully or null if the course was not found
     */
    @Override
    public Course delete(Long id)
    {
        Course course=findOne(id);
        if (course!= null)
        {
            repoList.remove(course);
            return course;
        }
        return null;
    }

    /**
     * updates a course
     * @param newCourse-must be not null
     * @return null -if the course was updated successfully or newCourse -if the course was not found
     */
    @Override
    public Course update (Course newCourse)
    {
        for(Course course:repoList)
        {
            if (course.getCourseID().equals(newCourse.getCourseID()))
            {
                repoList.remove(course);
                repoList.add(newCourse);
                return null;
            }
        }
        return newCourse;
    }

    /**
     * reads the courses from a file
     * @return a list of courses read from a file
     */
    @Override
    public List<Course> readDataFromFile() {
        List<Course> courses = new ArrayList<>();
        String separator=",";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Bohm Evelin\\Desktop\\courses.csv"));
            while ((line = reader.readLine()) != null) {
                String[] course = line.split(separator);
                Long courseID = Long.parseLong(course[1]);
                String courseName = course[0].replace("\"","");
                int maxEnrollment = Integer.parseInt(course[2]);
                int credits = Integer.parseInt(course[3]);
                Long teacherID = Long.parseLong(course[4]);
                Teacher teacher = new Teacher();
                teacher.setID(teacherID);
                course[5] = course[5].replace("]", "");
                course[5] = course[5].replace("[", "");
                String[] studentList = course[5].split(";");
                List<Student> studentsEnrolled = new ArrayList<>();
                //if course has no students enrolled
                if (!studentList[0].equals(""))
                {    for (String studentID:studentList)
                {
                    studentID= studentID.replace("\"","");
                    Long studentIDLong = Long.parseLong(studentID);
                    Student student = new Student();
                    student.setID(studentIDLong);
                    studentsEnrolled.add(student);

                }
                }

                Course newCourse = new Course(courseID, courseName, teacher, maxEnrollment, studentsEnrolled, credits);
                courses.add(newCourse);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }

    /**
     * updates the file
     * @throws IOException if the file couldn't be found
     */
    @Override
    public void writeToFile() throws IOException {
        List<Course> courseList = repoList;
        new PrintWriter("C:\\Users\\Bohm Evelin\\Desktop\\courses.csv").close();
        for (Course course:courseList) {
            String studentIDs="";
            String data= "";
            String credits = String.valueOf(course.getCredits());
            for (Student student : course.getStudentsEnrolled()) {
                studentIDs += (String.valueOf(student.getID())) + ';';
            }
            StringBuffer stringBuffer = new StringBuffer(studentIDs);
            if (studentIDs.length()>0){
                stringBuffer.deleteCharAt(studentIDs.length() - 1);
            }
            data = course.getName() + "," + course.getCourseID() + ',' + course.getMaxEnrollment() + ',' + course.getCredits() + ',' +
                    course.getTeacher().getID()+"," + "[" + stringBuffer + "]"+"\n";

            Files.write(Paths.get("C:\\Users\\Bohm Evelin\\Desktop\\courses.csv"),data.getBytes(), StandardOpenOption.APPEND);
        }
    }

}
