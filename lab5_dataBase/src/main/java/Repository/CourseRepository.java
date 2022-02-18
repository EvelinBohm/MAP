package Repository;
import Exception.NullException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Course;
import Model.Student;
import Model.Teacher;

import java.util.List;
import java.sql.*;
/**
 * CourseRepository extends the RepositoryJDBC class
 * the class updates instances of class type Course in the database
 *
 * @author Bohm Evelin
 * @version  07.12.2021
 * @since 1.0
 */

public class CourseRepository extends RepositoryJDBC<Course> {

    public CourseRepository(String url) {
        super(url);
    }
    /**
     * Overrides the findOne methode from the interface
     * searches for the course with the given id
     * @param courseID-must not be null
     * @return Course-if it's found or null -if the course does not exist
     */
    @Override
    public Course findOne(Long courseID) throws NullException {

        if (courseID==null)
            throw new NullException("Null input not allowed");

            String sqlQuery=" SELECT c.ID,c.courseTitle,t.teacherID,t2.firstName,t2.lastName,c.maxEnrollment,c.credits FROM Course c  join teacher_courses t on c.ID = t.courseID join teacher t2 on t.teacherID=t2.ID where c.ID ="+courseID;
            String sqlQueryEnrolledStud=" SELECT s.ID,s.firstName,s.lastName,s.totalCredits FROM student s join enrolled_students se on s.ID=se.studentID  where se.courseID="+courseID;

            List<Student> enrolledStudents = new ArrayList<>();
            Course course=new Course();
            Teacher teacher=new Teacher();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            if (rs.next()) {
                course.setCourseID(rs.getLong("ID"));
                course.setName(rs.getString("courseTitle"));
                teacher.setID(rs.getLong("teacherID"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
                course.setCredits(rs.getInt("credits"));
                course.setMaxEnrollment(rs.getInt("maxEnrollment"));
                course.setTeacher(teacher);
            }
            ResultSet resultSetEnrollment = stmt.executeQuery(sqlQueryEnrolledStud);
            while (resultSetEnrollment.next()) {
                Student student= new Student();
                student.setID(resultSetEnrollment.getLong("ID"));
                student.setFirstName(resultSetEnrollment.getString("firstName"));
                student.setLastName(resultSetEnrollment.getString("lastName"));
                student.setTotalCredits(resultSetEnrollment.getInt("totalCredits"));
                enrolledStudents.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        course.setStudentsEnrolled(enrolledStudents);
        if (course.getCourseID()!= null)
            return course;
        else
            return null;
    }

    /**
     * Overrides the findAll methode from the interface
     * @return  Iterable of courses
     */
    @Override
    public Iterable<Course> findAll()
    {
        List<Course>courseList=new ArrayList<>();
        String sqlQuery=" SELECT c.ID,c.courseTitle,t.teacherID,t2.firstName,t2.lastName,c.maxEnrollment,c.credits FROM Course c  join teacher_courses t on c.ID = t.courseID join teacher t2 on t.teacherID=t2.ID ";

        try {
            Connection conn=connectDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);


            while (rs.next()) {
                Course course=new Course();
                Teacher teacher=new Teacher();
                course.setCourseID(rs.getLong("ID"));
                course.setName(rs.getString("courseTitle"));
                teacher.setID(rs.getLong("teacherID"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
                course.setCredits(rs.getInt("credits"));
                course.setMaxEnrollment(rs.getInt("maxEnrollment"));
                course.setTeacher(teacher);


                String sqlQueryEnrolledStud=" SELECT s.ID,s.firstName,s.lastName,s.totalCredits FROM student s join enrolled_students se on s.ID=se.studentID  where se.courseID="+course.getCourseID();
                List<Student> enrolledStudents = new ArrayList<>();
                Statement stmt2 = con.createStatement();
                ResultSet resultSetEnrollment = stmt2.executeQuery(sqlQueryEnrolledStud);

            while (resultSetEnrollment.next()) {
                Student student= new Student();
                student.setID(resultSetEnrollment.getLong("ID"));
                student.setFirstName(resultSetEnrollment.getString("firstName"));
                student.setLastName(resultSetEnrollment.getString("lastName"));
                student.setTotalCredits(resultSetEnrollment.getInt("totalCredits"));
                enrolledStudents.add(student);
            }
            course.setStudentsEnrolled(enrolledStudents);
            courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    /**
     * Overrides the save methode from the interface
     * adds the course to the list of courses
     * @param newCourse-most be not null
     * @return course -if the course has been already saved or null -if the course was added successfully
     */
    @Override
    public Course save(Course newCourse) throws NullException {
        Course foundCourse=findOne(newCourse.getCourseID());
        if (foundCourse !=null)
        {
            return foundCourse;
        }
        else {
            try{
            String queryTeacher = "SELECT ID, firstName, lastName FROM teacher WHERE ID =" + newCourse.getTeacher().getID();
            Statement statement = con.createStatement();
            ResultSet resultTeacher = statement.executeQuery(queryTeacher);

            //if the Teacher is new
                if (!resultTeacher.next()) {
                    String insertTeacher = "INSERT INTO teacher (ID, firstName, lastName) values ('" + newCourse.getTeacher().getID() + "', '"
                            + newCourse.getTeacher().getFirstName() + "', '"
                            + newCourse.getTeacher().getLastName() + "')";
                    Statement insertStmt = con.createStatement();
                    insertStmt.executeUpdate(insertTeacher);
                }


            try (
                PreparedStatement ps = con.prepareStatement("INSERT INTO course(ID,courseTitle,credits,maxEnrollment) values (?,?,?,?)")

            ) {
                ps.setLong(1,newCourse.getCourseID());
                ps.setString(2, newCourse.getName());
                ps.setInt(3,newCourse.getCredits());
                ps.setInt(4,newCourse.getMaxEnrollment());
                ps.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            try{
                String queryEnrollmentTeacher = "SELECT teacherID,courseID from teacher_courses where teacherID="+newCourse.getTeacher().getID()+" and courseID="+newCourse.getCourseID();
                Statement statement2 = con.createStatement();
                ResultSet resultTeacher2 = statement2.executeQuery(queryEnrollmentTeacher);

                if (!resultTeacher2.next()) {
                    String insertTeacher = "INSERT INTO teacher_courses (teacherID,courseID) values ('" + newCourse.getTeacher().getID() + "', '"
                            + newCourse.getCourseID() + "')";
                    Statement insertStmt = con.createStatement();
                    insertStmt.executeUpdate(insertTeacher);
                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            return null;
    }}

    /**
     * deletes the course with the given ID
     * @param id id must be not null
     * @return course -if it was deleted successfully or null if the course was not found
     */
    @Override
    public Course delete(Long id) throws NullException {
        Course foundCourse=findOne(id);
        if (foundCourse !=null)
        {
            String sqlQuery = "DELETE From Course Where ID="+id;
            String sqlQueryDeletedEnrollment = "Delete FROM enrolled_students Where courseID=" + id;
            String sqlQueryDeleteEnrollmentForTeacher = "Delete FROM teacher_courses Where courseID=" + id;


            try {
                Statement stmt = con.createStatement();
                Statement stmt2 = con.createStatement();
                Statement stmt3 = con.createStatement();
                stmt.executeUpdate(sqlQueryDeletedEnrollment);
                stmt2.executeUpdate(sqlQueryDeleteEnrollmentForTeacher);
                stmt3.executeUpdate(sqlQuery);


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return foundCourse;
        }
        else
        return null;
    }


    /**
     * updates a course
     * @param newCourse-must be not null
     * @return null -if the course was updated successfully or newCourse -if the course was not found
     */
    @Override
    public Course update (Course newCourse) throws NullException {
        Course foundCourse=findOne(newCourse.getCourseID());
        if (foundCourse ==null)
        {
            return null;
        }
        else {
            try (
                    PreparedStatement ps = con.prepareStatement("UPDATE course SET ID=?,courseTitle=?,credits=?,maxEnrollment=? WHERE ID="+newCourse.getCourseID())

            ) {
                ps.setLong(1,newCourse.getCourseID());
                ps.setString(2, newCourse.getName());
                ps.setInt(3,newCourse.getCredits());
                ps.setInt(4,newCourse.getMaxEnrollment());
                ps.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;

    }




}
