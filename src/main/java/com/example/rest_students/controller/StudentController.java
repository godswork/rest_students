package com.example.rest_students.controller;

import com.example.rest_students.model.Student;
import com.example.rest_students.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class StudentController {
    private StudentRepository studentRepository;

    @Autowired
    public void studentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/getAllStudents")
    @Tag(name = "Получить", description = "список или по id")
    @Operation(summary = "Получить список студентов", description = "Получить данные о всех студентах")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> studentList = new ArrayList<>(studentRepository.findAll());

            if (studentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStudentById/{id}")
    @Tag(name = "Получить")
    @Operation(summary = "Получить данные о студенте", description = "Получить данные студента по его id")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Optional<Student> studentObj = studentRepository.findById(Math.toIntExact(id));
        if (studentObj.isPresent()) {
            return new ResponseEntity<>(studentObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addStudent")
    @Tag(name = "Добавить", description = "или изменить запись")
    @Operation(summary = "Добавить студента", description = "Добавить данные о студенте")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {
            Student studentObj = studentRepository.save(student);
            return new ResponseEntity<>(studentObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateStudentById{id}")
    @Tag(name = "Добавить")
    @Operation(summary = "Редактировать данные о студенте", description = "Редактировать данные о студенте по его id")
    public ResponseEntity<Student> updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        try {
            Optional<Student> studentData = studentRepository.findById(id);
            if (studentData.isPresent()) {
                Student updatedStudentData = studentData.get();
                updatedStudentData.setName(student.getName());
                updatedStudentData.setSurname(student.getSurname());

                Student studentObj = studentRepository.save(updatedStudentData);
                return new ResponseEntity<>(studentObj, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllStudents")
    @Tag(name = "Удалить", description = "список или по id")
    @Operation(summary = "Удалить список студентов", description = "Удалить данные всех студентов")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteStudentById{id}")
    @Tag(name = "Удалить")
    @Operation(summary = "Удалить студента", description = "Удалить данные о студенте по его id")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable Integer id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
