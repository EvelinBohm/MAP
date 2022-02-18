package com.company.Repository;


import com.company.Model.Course;
import com.company.Model.Student;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentRepository extends the InFileRepo, the class stores and updates instances of class type Student
 *
 * @author Bohm Evelin
 * @version  16.11.2021
 * @since 1.0
 */
public class StudentRepository extends InFileRepo<Student> {

    /**
     * initializes the repository list with the data read from the file
     * @param repoList a student list
     * @throws FileNotFoundException if the file couldn't be found
     */
    public StudentRepository(List<Student> repoList) throws FileNotFoundException {
        super(repoList);

    }

    /**
     * gets the student list stored in the repository
     * @return a student list
     */
    public List<Student> getStudentList() {
        return repoList;
    }

    /**
     * Overrides the findOne methode from the interface
     * searches for the students with the given id
     * @param studentID-must not be null
     * @return Course-if the student was found or null-if the student does not exist
     */
    @Override
    public Student findOne(Long studentID)
    {

        for(Student student:repoList){
            if (student.getID().equals(studentID))
                return student;
        }
        return null;
    }
    /**
     * Overrides the findAll methode from the interface
     * @return Iterable of students
     */
    @Override
    public Iterable<Student> findAll()
    {
        return repoList;
    }
    /**
     * Overrides the save methode from the interface
     * adds the student to the list of student(=studentList)
     * @param newStudent-most be not null
     * @return student -if the student has been already saved or null -if the student was added successfully
     */
    @Override
    public Student save(Student newStudent)
    {
        for (Student student:repoList)
        {
            if(student.getID().equals(newStudent.getID()))
                return student;
        }
        repoList.add(newStudent);
        return null;
    }
    /**
     * deletes the student with the given ID
     * @param id- id must be not null
     * @return student -if it was deleted successfully or null -if the student was not found
     */
    @Override
    public Student delete(Long id)
    {
        Student student=findOne(id);
        if (student!= null)
        {
            repoList.remove(student);
            return student;
        }
        return null;
    }


    /**
     * updates a student
     * @param newStudent-must be not null
     * @return null -if the student was updated successfully or newStudent -if the student was not found
     */
    @Override
    public Student update (Student newStudent)
    {
        for(Student student:repoList)
        {
            if (student.getID().equals(newStudent.getID()))
            {
                student=newStudent;
                return null;
            }
        }
        return newStudent;
    }
    /**
     * reads the students from a file
     * @return a list of students read from a file
     */
    @Override
    public List<Student> readDataFromFile() throws FileNotFoundException {
        List<Student> studentList = new ArrayList<>();
        String separator=",";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Bohm Evelin\\Desktop\\students.csv"));
            while ((line = reader.readLine()) != null) {
                String[] student = line.split(separator);
                String firstName = student[0].replace("\"","");
                String lastName = student[1].replace("\"","");
                Long studentID = Long.parseLong(student[2]);
                int credits = Integer.parseInt(student[3]);
                student[4] = student[4].replace("]", "");
                student[4] = student[4].replace("[", "");
                String[] courseList = student[4].split(";");
                List<Course> coursesOfStudent = new ArrayList<>();
                //if course has no students enrolled
                if (!courseList[0].equals(""))
                {    for (String course:courseList)
                {
                    course= course.replace("\"","");
                    Long courseID = Long.parseLong(course);
                    Course newCourse = new Course();
                    newCourse.setCourseID(courseID);
                    coursesOfStudent.add(newCourse);

                }
                }

                Student newStudent = new Student(firstName,lastName,studentID,credits,coursesOfStudent);
                //System.out.println(Arrays.toString(studensEnrolled.toArray()));
                studentList.add(newStudent);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentList;
    }
    /**
     * updates the file
     * @throws IOException if the file couldn't be found
     */
    @Override
    public void writeToFile() throws IOException {
        List<Student> studentList = repoList;
        new PrintWriter("C:\\Users\\Bohm Evelin\\Desktop\\students.csv").close();
        for (Student student:studentList) {
            String courseIDs="";
            String data= "";
            for (Course course : student.getEnrolledCourses()) {
                courseIDs += (String.valueOf(course.getCourseID())) + ';';
            }
            //StringBuffer stringBuffer = new StringBuffer(courseIDs);
            if (courseIDs.length()>0){
                courseIDs.substring(0, courseIDs.length() - 1);
            }
            String list="["+courseIDs+"]";
            data = student.getFirstName()+","+student.getLastName()+","+student.getID()+","+student.getTotalCredits()+","+list+"\n";
            Files.write(Paths.get("C:\\Users\\Bohm Evelin\\Desktop\\students.csv"),data.getBytes(), StandardOpenOption.APPEND);
        }


    }
}
