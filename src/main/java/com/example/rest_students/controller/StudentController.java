package com.example.rest_students.controller;

import com.example.rest_students.model.Student;
import com.example.rest_students.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Список студентов")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    @Operation(summary = "Добавить студента")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/students")
    @Operation(summary = "Получить список студентов")
    public ResponseEntity<List<Student>> getAllStudents() {
        final List<Student> students = studentService.getAllStudents();

        return students != null && !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "students/{id}")
    @Operation(summary = "Получить имя студента по номеру")
    public ResponseEntity<Student> getStudent(@PathVariable(name = "id") int id) {
        final Student student = studentService.getStudentById(id);

        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/students/{id}")
    @Operation(summary = "Изменить имя студента под номером")
    public ResponseEntity<Student> updateStudent(@PathVariable(name = "id") int id, @RequestBody Student student) {
        final boolean isUpdated = studentService.updateStudent(student, id);

        return isUpdated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/students/{id}")
    @Operation(summary = "Удалить студента под номером")
    public ResponseEntity<?> deleteStudent(@PathVariable(name = "id") int id) {
        final boolean isDeleted = studentService.deleteStudent(id);

        return isDeleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
