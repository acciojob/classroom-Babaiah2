package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Component
public class StudentRepository {

    private HashMap<String, Student> studentMap = new HashMap<>() ;
    private HashMap<String, Teacher> teacherMap = new HashMap<>() ;
    private HashMap<String, List<String>> teacherstudentMap = new HashMap<>() ;

    public void addStudent(Student student)
    {
        studentMap.put(student.getName(), student) ;
    }

    public void addTeacher(Teacher teacher)
    {
        teacherMap.put(teacher.getName(), teacher) ;
    }

    public void addStudentTeacherPair(String student, String teacher)
    {
        List<String> studentlist = new ArrayList<>() ;

        if(teacherstudentMap.containsKey(teacher))
            studentlist = teacherstudentMap.get(teacher) ;

        if(!studentlist.contains(student))
            studentlist.add(student) ;

        teacherstudentMap.put(teacher, studentlist) ;
    }

    public Student getStudentByName(String name)
    {
        Student student = studentMap.get(name) ;
        return student ;
    }

    public Teacher getTeacherByName(String name)
    {
        Teacher teacher = teacherMap.get(name) ;
        return teacher ;
    }

    public List<String> getStudentByTeacherName(String name)
    {
        List<String> studentlist = new ArrayList<>() ;
        if(teacherstudentMap.containsKey(name))
            studentlist = teacherstudentMap.get(name) ;

        return studentlist ;
    }

    public List<String> getAllStudents()
    {
        List<String> studentlist = new ArrayList<>() ;
        for(String st : studentMap.keySet())
            studentlist.add(st) ;

        return studentlist ;
    }

    public void deleteTeacherByName(String name)
    {
        List<String> pairlist = new ArrayList<>() ;
        if(teacherstudentMap.containsKey(name)){
            pairlist = teacherstudentMap.get(name) ;
            for(String st : pairlist)
            {
                studentMap.remove(st) ;
            }
            teacherstudentMap.remove(name) ;
        }
        teacherMap.remove(name);

    }

    public void deleteAllTeachers()
    {
        for(String teacher : teacherstudentMap.keySet())
        {
            List<String> pairlist = teacherstudentMap.get(teacher) ;
            for(String st : pairlist)
            {
                if(studentMap.containsKey(st))
                    studentMap.remove(st) ;
            }
        }
        teacherMap.clear();
        teacherstudentMap.clear() ;
    }

}
