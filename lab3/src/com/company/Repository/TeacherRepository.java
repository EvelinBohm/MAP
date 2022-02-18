package com.company.Repository;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * TeacherRepository extends the InFileRepo, the class stores and updates instances of class type Teacher
 * @author Bohm Evelin
 * @version 16.11.2021
 * @since 1.0
 */
public class TeacherRepository extends InFileRepo<Teacher> {

    /**
     * initializes the repository list with the data read from the file
     * @param repoList a teacher list
     * @throws FileNotFoundException if the file couldn't be found
     */
    public TeacherRepository(List<Teacher> repoList) throws FileNotFoundException {
        super(repoList);
    }

    /**
     * Overrides the findOne methode from the interface
     * searches for the teacher with the given id
     * @param teacherID-must not be null
     * @return teacher-if the teacher was found or null -if the teacher does not exist
     */
    @Override
    public Teacher findOne(Long teacherID)
    {

        for(Teacher teacher:repoList){
            if (teacher.getID().equals(teacherID))
                return teacher;
        }
        return null;
    }
    /**
     * Overrides the findAll methode from the interface
     * @return Iterable of teachers
     */
    @Override
    public Iterable<Teacher> findAll()
    {
        return repoList;
    }
    /**
     * Overrides the save methode from the interface
     * adds the teacher to the list of teachers
     * @param newTeacher-most be not null
     * @return teacher -if the teacher has been already saved or null -if the teacher was added successfully
     */
    @Override
    public Teacher save(Teacher newTeacher)
    {
        for (Teacher teacher:repoList)
        {
            if(teacher.equals(newTeacher))
                return teacher;
        }
        repoList.add(newTeacher);
        return null;
    }

    /**
     * deletes the teacher with the given ID
     * @param id id must be not null
     * @return teacher -if the teacher was deleted successfully or null -if the teacher couldn't be found
     */
    @Override
    public Teacher delete(Long id)
    {
        Teacher teacher=findOne(id);
        if (teacher!= null)
        {
            repoList.remove(teacher);
            return teacher;
        }
        return null;
    }


    /**
     * updates a teacher
     * @param newTeacher-must be not null
     * @return null -if the teacher was updated successfully or newTeacher -if the teacher was not found
     */
    @Override
    public Teacher update (Teacher newTeacher)
    {
        for(Teacher teacher:repoList)
        {
            if (teacher.getID().equals(newTeacher.getID()))
            {
                repoList.remove(teacher);
                repoList.add(newTeacher);
                return null;
            }
        }
        return newTeacher;
    }
    /**
     * reads the teachers from a file
     * @return a list of teacher read from a file
     */
    @Override
    public List<Teacher> readDataFromFile() throws FileNotFoundException {
        List<Teacher> teacherList = new ArrayList<>();
        String separator=",";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Bohm Evelin\\Desktop\\teachers.csv"));
            while ((line = reader.readLine()) != null) {

                String[] teacher = line.split(separator);
                Long teacherID = Long.parseLong(teacher[2]);
                String firstName = teacher[0].replace("\"","");
                String lastName = teacher[0].replace("\"","");
                teacher[3] = teacher[3].replace("]", "");
                teacher[3] = teacher[3].replace("[", "");
                String[] courseID = teacher[3].split(";");
                List<Course> courseList = new ArrayList<>();
                if (!courseID[0].equals(""))
                {    for (String course:courseID)
                {
                    course= course.replace("\"","");
                    Long courseIDLong = Long.parseLong(course);
                    Course newCourse = new Course();
                    newCourse.setCourseID(courseIDLong);
                    courseList.add(newCourse);

                }
                }

                Teacher newTeacher = new Teacher(firstName,lastName, teacherID,courseList);
                teacherList.add(newTeacher);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return teacherList;
    }
    /**
     * updates the file
     * @throws IOException if the file couldn't be found
     */
    @Override
    public void writeToFile() throws IOException {
        List<Teacher> teacherList = repoList;
        new PrintWriter("C:\\Users\\Bohm Evelin\\Desktop\\teachers.csv").close();
        for (Teacher teacher:teacherList) {
            String courseIDs="";
            String data= "";
            for (Course course : teacher.getCourses()) {
                courseIDs += (String.valueOf(course.getCourseID())) + ';';
            }
            StringBuffer stringBuffer = new StringBuffer(courseIDs);
            if (courseIDs.length()>0){
                stringBuffer.deleteCharAt(courseIDs.length() - 1);
            }
            data = teacher.getFirstName()+","+teacher.getLastName()+","+teacher.getID()+","+",["+stringBuffer+"]"+"\n";
            System.out.println(data);
            Files.write(Paths.get("C:\\Users\\Bohm Evelin\\Desktop\\teacher.csv"),data.getBytes(), StandardOpenOption.APPEND);
        }
    }
}
