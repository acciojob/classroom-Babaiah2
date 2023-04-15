package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepository.addTeacher(teacher);
    }

    public String addStudentTeacherPair(String student, String teacher) {
      return  studentRepository.addStudentTeacherPair(student,teacher);
    }

    public Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name);
    }

    public Teacher getTeacherByName(String name) {
        return  studentRepository.getTeacherByName(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentRepository.getStudentByTeacherName(teacher);
    }

    public List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        studentRepository.deleteTeacherByName(teacher);
    }

    public void deleteAllTeacehrs() {
        studentRepository.deleteAllTeachers();
    }
}
