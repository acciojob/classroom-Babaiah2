package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentDb = new HashMap<>();
    HashMap<String, Teacher> teacherDb = new HashMap<>();
    HashMap<String, List<String>> pairDb = new HashMap<>();


    public void addStudent(Student student) {
        studentDb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }


    public String addStudentTeacherPair(String student, String teacher) {

        if(studentDb.containsKey(student)&&teacherDb.containsKey(teacher)) {
            List<String> list = pairDb.getOrDefault(teacher, new ArrayList<>());
            if(list.contains(student))
                return "Pair already exists";
            else{
                list.add(student);
                pairDb.put(teacher,list);
                return "pair added";
            }
        }
        return "student or Teacher does not exist";
    }

    public Student getStudentByName(String name) {
       return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.get(name);
    }

    public List<String> getStudentByTeacherName(String teacher) {
        if(!teacherDb.containsKey(teacher))
            return null;

        return pairDb.get(teacher);

    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for(String student:studentDb.keySet()){
            studentList.add(student);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher) {
        List<String>studentlist=new ArrayList<>();
        if(pairDb.containsKey(teacher))
        {
            studentlist=pairDb.get(teacher);
        }
        for(String movie:studentlist)
        {
            if(studentDb.containsKey(movie))
            {
                studentDb.remove(movie);
            }
        }
        pairDb.remove(teacher);
        if(teacherDb.containsKey(teacher))
        {
            teacherDb.remove(teacher);
        }

    }

    public void deleteAllTeachers() {
        for(String teacher:pairDb.keySet())
        {
            List<String> list=new ArrayList<>();
            list=pairDb.get(teacher);
            for(String student:list)
            {
                if(teacherDb.containsKey(student))
                {
                    studentDb.remove(student);
                }
            }
            pairDb.remove(teacher);
        }
        for(String teacher:teacherDb.keySet())
        {
            teacherDb.remove(teacher);
        }
    }
}
