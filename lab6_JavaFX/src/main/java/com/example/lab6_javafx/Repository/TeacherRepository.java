package com.example.lab6_javafx.Repository;
import com.example.lab6_javafx.Exception.NullException;
import com.example.lab6_javafx.Model.Course;
import com.example.lab6_javafx.Model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * TeacherRepository extends the RepositoryJDBC class
 * the class updates instances of class type Teacher in the database
 *
 * @author Bohm Evelin
 * @version  07.12.2021
 * @since 1.0
 */
public class TeacherRepository extends RepositoryJDBC<Teacher> {


    public TeacherRepository(String url) {
        super(url);
    }

    /**
     * Overrides the findOne methode from the interface
     * searches for the teacher with the given id
     * @param teacherID-must not be null
     * @return teacher-if the teacher was found or null -if the teacher does not exist
     */
    @Override
    public Teacher findOne(Long teacherID) throws NullException {
        if (teacherID==null)
            throw new NullException("Null input not allowed");

        String sqlQuery=" SELECT Distinct t.ID,t.firstName,t.lastName FROM teacher t where t.ID="+teacherID;
        String sqlQueryTeacherCourses = " SELECT  c.ID,c.courseTitle,t.ID,t.firstName,t.lastName,c.maxEnrollment,c.credits FROM teacher t join teacher_courses tc on t.ID=tc.teacherID join course c on tc.courseID=c.ID where t.ID="+teacherID;
        Teacher teacher=new Teacher();
        List<Course> teacherCourseList = new ArrayList<>();

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            if (rs.next()) {
                teacher.setID(rs.getLong("ID"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
            }

            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sqlQueryTeacherCourses);

            while (rs2.next()) {
                Course course = new Course();
                Teacher newTeacher=new Teacher();
                newTeacher.setID(teacher.getID());
                newTeacher.setFirstName(teacher.getFirstName());
                newTeacher.setLastName(teacher.getLastName());
                course.setCourseID(rs2.getLong("ID"));
                course.setName(rs2.getString("courseTitle"));
                course.setCredits(rs2.getInt("credits"));
                course.setMaxEnrollment(rs2.getInt("maxEnrollment"));
                teacherCourseList.add(course);
            }
            teacher.setCourses(teacherCourseList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (teacher.getID()!= null)
            return teacher;
        else
            return null;
    }
    /**
     * Overrides the findAll methode from the interface
     * @return Iterable of teachers
     */
    @Override
    public Iterable<Teacher> findAll()
    {
        List<Teacher>teacherList=new ArrayList<>();
        String sqlQuery=" SELECT Distinct t.ID,t.firstName,t.lastName FROM teacher t  ";


        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                Teacher teacher=new Teacher();
                teacher.setID(rs.getLong("ID"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));

            List<Course> teacherCourseList = new ArrayList<>();
            String sqlQueryTeacherCourses = " SELECT  c.ID,c.courseTitle,t.ID,t.firstName,t.lastName,c.maxEnrollment,c.credits FROM teacher t join teacher_courses tc on t.ID=tc.teacherID join course c on tc.courseID=c.ID where t.ID="+teacher.getID();
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sqlQueryTeacherCourses);

            while (rs2.next()) {
                Course course = new Course();
                Teacher newTeacher=new Teacher();
                newTeacher.setID(teacher.getID());
                newTeacher.setFirstName(teacher.getFirstName());
                newTeacher.setLastName(teacher.getLastName());
                course.setCourseID(rs2.getLong("ID"));
                course.setName(rs2.getString("courseTitle"));
                course.setTeacher(newTeacher);
                course.setCredits(rs2.getInt("credits"));
                course.setMaxEnrollment(rs2.getInt("maxEnrollment"));
                teacherCourseList.add(course);
            }
            teacher.setCourses(teacherCourseList);
            teacherList.add(teacher);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }
    /**
     * Overrides the save methode from the interface
     * adds the teacher to the list of teachers
     * @param newTeacher-most be not null
     * @return teacher -if the teacher has been already saved or null -if the teacher was added successfully
     */
    @Override
    public Teacher save(Teacher newTeacher) throws NullException {
        Teacher foundTeacher=findOne(newTeacher.getID());
        if (foundTeacher !=null)
        {
            return foundTeacher;
        }
        else {
            try (
                    PreparedStatement ps = con.prepareStatement("INSERT INTO teacher(ID, firstName, lastName) values (?,?,?)")

            ) {
                ps.setLong(1,newTeacher.getID());
                ps.setString(2, newTeacher.getFirstName());
                ps.setString(3,newTeacher.getLastName());
                ps.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }

    /**
     * deletes the teacher with the given ID
     * @param id id must be not null
     * @return teacher -if the teacher was deleted successfully or null -if the teacher couldn't be found
     */
    @Override
    public Teacher delete(Long id) throws NullException {
        Teacher foundTeacher=findOne(id);
        String sqlQuery = "DELETE From teacher Where ID="+id;
        String sqlQueryDeleteEnrollmentForTeacher = "Delete FROM teacher_courses Where teacherID=" + id;
        if (foundTeacher !=null)
        {

            try{
            Statement stmt = con.createStatement();
            int rs=stmt.executeUpdate(sqlQueryDeleteEnrollmentForTeacher);
                if(rs!=0){
                    try {
                        Statement stmt2 = con.createStatement();
                        stmt2.executeUpdate(sqlQuery);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return foundTeacher;
            }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (foundTeacher !=null)
            {
                try
                {
                Statement stmt2 = con.createStatement();
                stmt2.executeUpdate(sqlQuery);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return foundTeacher;
            }
            return null;
    }


    /**
     * updates a teacher
     * @param newTeacher-must be not null
     * @return null -if the teacher was updated successfully or newTeacher -if the teacher was not found
     */
    @Override
    public Teacher update (Teacher newTeacher) throws NullException {
        Teacher foundTeacher=findOne(newTeacher.getID());
        if (foundTeacher ==null)
        {
            return null;
        }
        else {

            try (
                    PreparedStatement ps = con.prepareStatement("UPDATE teacher SET ID=?,firstName=?,lastName=? WHERE ID="+newTeacher.getID())

            ) {
                ps.setLong(1,newTeacher.getID());
                ps.setString(2, newTeacher.getFirstName());
                ps.setString(3,newTeacher.getLastName());
                ps.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }


}
