package com.springboot.pratice.controller;

import com.springboot.pratice.dtos.CreateStudentDTO;
import com.springboot.pratice.dtos.StudentResponseDTO;
import com.springboot.pratice.dtos.common.ApiResponse;
import com.springboot.pratice.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> saveStudent(@Valid @RequestBody CreateStudentDTO createStudentDTO) {
        StudentResponseDTO createdStudentInfo = studentService.saveStudent(createStudentDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                HttpStatus.CREATED.value(),
                                "Student created successfully",
                                createdStudentInfo
                        )
                );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> getStudentInfoByIdOrRollNumber(
            @RequestParam("search")
            @NotBlank(message = "Search is required")
            @Pattern(
                    regexp = "^(?:[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|[0-9]+)$",
                    message = "Search must be a valid UUID or numeric roll number"
            )
            String identifier
    ) {
        StudentResponseDTO getStudentInfo = studentService.getStudentInfoByIdOrRollNumber(identifier);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.success(
                                HttpStatus.OK.value(),
                                "Student info fetched successfully",
                                getStudentInfo
                        )
                );
    }

    @GetMapping("{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> getStudentInfoById(
            @NotBlank(message = "Student Id must be required")
            @PathVariable("studentId")
            String studentId
    ){
        StudentResponseDTO getStudentInfo=studentService.getStudentInfoById(studentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.success(
                                HttpStatus.OK.value(),
                                "Student info fetched successfully",
                                getStudentInfo
                        )
                );
    }

//    @GetMapping("/get-all")
//    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getAllStudents(){
//        List<StudentResponseDTO> getAllStudentInfo=studentService.getAllStudents();
//        if(getAllStudentInfo.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body()
//        }
//        return  new ResponseEntity<>(getAllStudentInfo, HttpStatus.OK);
//    }
}
