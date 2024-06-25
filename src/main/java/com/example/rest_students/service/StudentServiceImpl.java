package com.example.rest_students.service;

import com.example.rest_students.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Map<Integer, Student> STUDENT_REPOSITORY_MAP = new HashMap<>();
    private static final AtomicInteger STUDENT_ID_HANDLER = new AtomicInteger();

    @Override
    public void createStudent(Student student) {
        final int studentId = STUDENT_ID_HANDLER.incrementAndGet();
        student.setId(studentId);
        STUDENT_REPOSITORY_MAP.put(studentId, student);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(STUDENT_REPOSITORY_MAP.values());
    }

    @Override
    public Student getStudentById(int id) {
        return STUDENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean updateStudent(Student student, int id) {
        if (STUDENT_REPOSITORY_MAP.containsKey(id)) {
            student.setId(id);
            STUDENT_REPOSITORY_MAP.put(id, student);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        return STUDENT_REPOSITORY_MAP.remove(id) != null;
    }
}
