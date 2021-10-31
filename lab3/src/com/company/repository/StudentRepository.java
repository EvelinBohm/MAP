package com.company.repository;

import com.company.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentRepository implements the ICrudRepository interface for students
 * @author Bohm Evelin
 * @version  %I%, %G%
 * @since 1.0
 */
public class StudentRepository implements ICrudRepository<Student> {
    private final List<Student> studentList=new ArrayList<>();

    /**
     * Overrides the findOne methode from the interface
     * searches for the students with the given id
     * @param studentID-must not be null
     * @return Course-if the student was found or null-if the student does not exist
     */
    @Override
    public Student findOne(Long studentID)
    {

        for(Student student:studentList){
            if (student.getStudentID().equals(studentID))
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
        return studentList;
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
        for (Student student:studentList)
        {
            if(student.equals(newStudent))
                return student;
        }
        studentList.add(newStudent);
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
            studentList.remove(student);
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
        for(Student student:studentList)
        {
            if (student.getStudentID().equals(newStudent.getStudentID()))
            {
                student=newStudent;
                return null;
            }
        }
        return newStudent;
    }



}
