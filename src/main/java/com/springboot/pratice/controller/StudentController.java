package com.springboot.pratice.controller;

import com.springboot.pratice.dtos.CreateStudentDTO;
import com.springboot.pratice.dtos.StudentResponseDTO;
import com.springboot.pratice.dtos.common.ApiResponse;
import com.springboot.pratice.entity.Student;
import com.springboot.pratice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> saveStudent(@Valid @RequestBody CreateStudentDTO createStudentDTO){
        StudentResponseDTO createdStudentInfo=studentService.saveStudent(createStudentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        HttpStatus.CREATED.value(),
                        "Student created successfully",
                        createdStudentInfo
                ));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> getAllStudentInfo=studentService.getAllStudents();
        if(getAllStudentInfo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(getAllStudentInfo, HttpStatus.OK);
    }
}
