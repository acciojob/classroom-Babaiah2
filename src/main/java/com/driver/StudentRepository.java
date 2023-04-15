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


    public String addStudentTeacherPair(String studentName, String teacherName) {

        if(studentDb.containsKey(studentName)&&teacherDb.containsKey(teacherName)) {
            List<String> list = pairDb.getOrDefault(teacherName, new ArrayList<>());
            if(list.contains(studentName))
                return "Pair already exists";
            else{
                list.add(studentName);
                pairDb.put(teacherName,list);
                return "pair added";
            }
        }
        return "student or Teacher does not exist";
    }

    public Student getStudentByName(String studentName) {
       return studentDb.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName) {
        return teacherDb.get(teacherName);
    }

    public List<String> getStudentByTeacherName(String teacherName) {
        if(!teacherDb.containsKey(teacherName))
            return null;

        return pairDb.get(teacherName);

    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for(String student:studentDb.keySet()){
            studentList.add(student);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacherName) {
        Teacher teacher = teacherDb.get(teacherName);
        teacherDb.remove(teacherName);
        for(String student: pairDb.get(teacher))
        {
            studentDb.remove(student);
        }
        pairDb.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(Teacher teacher: teacherDb.values())
        {
            for(String student: pairDb.get(teacher))
            {
                studentDb.remove(student);
            }
        }
        pairDb.clear();
        teacherDb.clear();
    }
}
