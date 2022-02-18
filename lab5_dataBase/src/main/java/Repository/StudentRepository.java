package Repository;

import Model.Course;
import Model.Student;
import Exception.NullException;
import Model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * StudentRepository extends the RepositoryJDBC class
 * the class updates instances of class type Student in the database
 *
 * @author Bohm Evelin
 * @version  07.12.2021
 * @since 1.0
 */
public class StudentRepository extends RepositoryJDBC<Student> {


    public StudentRepository(String url) {
        super( url);
    };

    /**
     * Overrides the findOne methode from the interface
     * searches for the students with the given id
     * @param studentID-must not be null
     * @return Course-if the student was found or null-if the student does not exist
     */
    @Override
    public Student findOne(Long studentID) throws NullException {
        if (studentID==null)
            throw new NullException("Null");

        String sqlQuery=" SELECT distinct s.ID,s.firstName,s.lastName,s.totalCredits FROM student s where s.ID="+studentID;
        String sqlQueryStudCourses = "SELECT c.ID,c.courseTitle,t.teacherID,t2.firstName,t2.lastName,c.maxEnrollment,c.credits FROM student s join enrolled_students se on s.ID=se.studentID join course c on c.ID=se.courseID join teacher_courses t on c.ID = t.courseID join teacher t2 on t2.ID=t.teacherID where s.ID="+studentID;
        Student student=new Student();
        List<Course> studentCourses = new ArrayList<>();


        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            if (rs.next()) {
                student.setID(rs.getLong("ID"));
                student.setFirstName(rs.getString("firstName"));
                student.setLastName(rs.getString("lastName"));
                student.setTotalCredits(rs.getInt("totalCredits"));
            }

            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sqlQueryStudCourses);

            while (rs2.next()) {
                Course course=new Course();
                Teacher teacher=new Teacher();
                course.setCourseID(rs2.getLong("ID"));
                course.setName(rs2.getString("courseTitle"));
                teacher.setID(rs2.getLong("teacherID"));
                teacher.setFirstName(rs2.getString("firstName"));
                teacher.setLastName(rs2.getString("lastName"));
                course.setCredits(rs2.getInt("credits"));
                course.setMaxEnrollment(rs2.getInt("maxEnrollment"));
                course.setTeacher(teacher);
                studentCourses.add(course);
            }
            student.setEnrolledCourses(studentCourses);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (student.getID()!= null)
            return student;
        else
            return null;

    }
    /**
     * Overrides the findAll methode from the interface
     * @return Iterable of students
     */
    @Override
    public Iterable<Student> findAll()
    {
        String sqlQuery=" SELECT distinct s.ID,s.firstName,s.lastName,s.totalCredits FROM student s";
        // s join enrolled_students se on s.ID=se.studentID";
        List<Student>studentList=new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);


            while (rs.next()) {
                Student student = new Student();
                student.setID(rs.getLong("ID"));
                student.setFirstName(rs.getString("firstName"));
                student.setLastName(rs.getString("lastName"));
                student.setTotalCredits(rs.getInt("totalCredits"));

                String sqlQueryStudCourses = "SELECT c.ID,c.courseTitle,t.teacherID,t2.firstName,t2.lastName,c.maxEnrollment,c.credits FROM enrolled_students se join course c on c.ID=se.courseID join teacher_courses t on c.ID = t.courseID join teacher t2 on t2.ID=t.teacherID where se.studentID="+student.getID();
                List<Course> studCourses = new ArrayList<>();
                Statement stmt2 = con.createStatement();
                ResultSet resultSetCourses = stmt2.executeQuery(sqlQueryStudCourses);

                while (resultSetCourses.next()) {
                    Course course=new Course();
                    Teacher teacher=new Teacher();
                    course.setCourseID(resultSetCourses.getLong("ID"));
                    course.setName(resultSetCourses.getString("courseTitle"));
                    teacher.setID(resultSetCourses.getLong("teacherID"));
                    teacher.setFirstName(resultSetCourses.getString("firstName"));
                    teacher.setLastName(resultSetCourses.getString("lastName"));
                    course.setCredits(resultSetCourses.getInt("credits"));
                    course.setMaxEnrollment(resultSetCourses.getInt("maxEnrollment"));
                    course.setTeacher(teacher);
                    studCourses.add(course);
                }
                student.setEnrolledCourses(studCourses);
                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;



    }
    /**
     * Overrides the save methode from the interface
     * adds the student to the list of student(=studentList)
     * @param newStudent-most be not null
     * @return student -if the student has been already saved or null -if the student was added successfully
     */
    @Override
    public Student save(Student newStudent) throws NullException {
        Student foundStudent=findOne(newStudent.getID());
        if (foundStudent !=null)
        {
            return foundStudent;
        }
        else {

            try (
                    PreparedStatement ps = con.prepareStatement("INSERT INTO student(ID, firstName, lastName, totalCredits) values (?,?,?,?)");

            ) {
                ps.setLong(1,newStudent.getID());
                ps.setString(2, newStudent.getFirstName());
                ps.setString(3,newStudent.getLastName());
                ps.setInt(4, newStudent.getTotalCredits());
                ps.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }
    /**
     * deletes the student with the given ID and the enrollments of the student
     * @param id- id must be not null
     * @return student -if it was deleted successfully or null -if the student was not found
     */
    @Override
    public Student delete(Long id) throws NullException {
        Student foundStudent=findOne(id);
        String sqlQuery = "DELETE From student Where ID="+id;
        String sqlQueryDeletedEnrollment = "Delete FROM enrolled_students Where studentID=" + id;
        if (foundStudent !=null)
        {

            try{
                Statement stmt = con.createStatement();
                int rs=stmt.executeUpdate(sqlQueryDeletedEnrollment);
                if(rs!=0){
                    try {
                        Statement stmt2 = con.createStatement();
                        stmt2.executeUpdate(sqlQuery);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return foundStudent;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (foundStudent !=null)
        {
            try
            {
                Statement stmt2 = con.createStatement();
                stmt2.executeUpdate(sqlQuery);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return foundStudent;
        }
        return null;
    }


    /**
     * updates a student
     * @param newStudent-must be not null
     * @return null -if the student was updated successfully or newStudent -if the student was not found
     */
    @Override
    public Student update (Student newStudent) throws NullException {
        Student foundStudent=findOne(newStudent.getID());
        if (foundStudent ==null)
        {
            return null;
        }
        else {
            try (
                    PreparedStatement ps = con.prepareStatement("UPDATE student SET ID=?,firstName=?,lastName=?,totalCredits=? WHERE ID="+newStudent.getID());

            ) {
                ps.setLong(1,newStudent.getID());
                ps.setString(2, newStudent.getFirstName());
                ps.setString(3,newStudent.getLastName());
                ps.setInt(4,newStudent.getTotalCredits());
                ps.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }
    /**
     * @param student-student to be registered for a course , student must not be null
     * @param course-course to which the student wants to register, course must not be null
     */
    public void saveRegistration(Student student, Course course){
        try (
                PreparedStatement ps = con.prepareStatement("INSERT INTO enrolled_students(studentID,courseID) values (?,?)")

        ) {
            ps.setLong(1, student.getID());
            ps.setLong(2, course.getCourseID());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}
