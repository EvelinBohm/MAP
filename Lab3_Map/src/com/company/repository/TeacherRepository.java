package com.company.repository;

import com.company.model.Course;
import com.company.model.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * TeacherRepository extends the InMemoryRepository class
 * @author Bohm Evelin
 * @version  30.10.2021
 * @since 1.0
 */
public class TeacherRepository extends InMemoryRepository<Teacher> {
    public TeacherRepository(List<Teacher> repositoryList) {

        super(repositoryList);
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

        for(Teacher teacher:repositoryList){
            if (teacher.getTeacherID().equals(teacherID))
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
        return repositoryList;
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
        for (Teacher teacher:repositoryList)
        {
            if(teacher.equals(newTeacher))
                return teacher;
        }
        repositoryList.add(newTeacher);
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
            repositoryList.remove(teacher);
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
        for(Teacher teacher:repositoryList)
        {
            if (teacher.getTeacherID().equals(newTeacher.getTeacherID()))
            {
                repositoryList.remove(teacher);
                repositoryList.add(newTeacher);
                return null;
            }
        }
        return newTeacher;
    }


}
