package com.springboot.pratice.service;

import com.springboot.pratice.dtos.CreateStudentDTO;
import com.springboot.pratice.dtos.StudentResponseDTO;
import com.springboot.pratice.dtos.common.PageResponse;
import com.springboot.pratice.entity.Student;

import java.util.List;

public interface StudentService {
    StudentResponseDTO saveStudent(CreateStudentDTO createStudentDTO);
    StudentResponseDTO getStudentInfoByIdOrRollNumber(String identifier);
    StudentResponseDTO getStudentInfoById(String studentId);
    PageResponse<StudentResponseDTO> getAllStudentsInfo(int page, int size, String sortBy, String direction, String search);
}
