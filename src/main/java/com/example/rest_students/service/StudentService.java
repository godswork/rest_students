package com.example.rest_students.service;

import com.example.rest_students.model.Student;

import java.util.List;

public interface StudentService {
    void createStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(int id);
    boolean updateStudent(Student student, int id);
    boolean deleteStudent(int id);
}
