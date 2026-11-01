package com.springboot.pratice.service;

import com.springboot.pratice.dtos.CreateStudentDTO;
import com.springboot.pratice.dtos.StudentResponseDTO;
import com.springboot.pratice.entity.Student;

import java.util.List;

public interface StudentService {
    StudentResponseDTO saveStudent(CreateStudentDTO createStudentDTO);
    StudentResponseDTO getStudentInfoByIdOrRollNumber(String identifier);
    StudentResponseDTO getStudentInfoById(String studentId);
    List<StudentResponseDTO> getAllStudents();

}
