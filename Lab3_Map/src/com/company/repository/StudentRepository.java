package com.company.repository;


import com.company.model.Course;
import com.company.model.Student;

import java.util.ArrayList;
import java.util.List;


/**
 * StudentRepository extends the InMemoryRepository class
 * @author Bohm Evelin
 * @version  30.10.2021
 * @since 1.0
 */
public class StudentRepository extends InMemoryRepository<Student> {
    public StudentRepository(List<Student> repositoryList) {

        super(repositoryList);
    }


    public List<Student> getStudentList() {
        return repositoryList;
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

        for(Student student:repositoryList){
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
        return repositoryList;
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
        for (Student student:repositoryList)
        {
            if(student.equals(newStudent))
                return student;
        }
        repositoryList.add(newStudent);
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
            repositoryList.remove(student);
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
        for(Student student:repositoryList)
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
