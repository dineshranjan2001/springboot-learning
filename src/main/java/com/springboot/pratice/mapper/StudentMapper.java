package com.springboot.pratice.mapper;

import com.springboot.pratice.dtos.CreateStudentDTO;
import com.springboot.pratice.dtos.StudentResponseDTO;
import com.springboot.pratice.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static Student toStudent(CreateStudentDTO createStudentDTO){
        return  Student.builder()
                .firstName(createStudentDTO.firstName().trim())
                .lastName(createStudentDTO.lastName().trim())
                .rollNumber(createStudentDTO.rollNumber())
                .studentEmail(createStudentDTO.studentEmail().trim())
                .studentContact(createStudentDTO.studentContact().trim())
                .studentAddress(createStudentDTO.studentAddress().trim())
                .isDeleted(false)
                .build();
    }

    public static StudentResponseDTO  toStudentResponseDTO(Student student){
        return  StudentResponseDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .rollNumber(student.getRollNumber())
                .studentEmail(student.getStudentEmail())
                .studentContact(student.getStudentContact())
                .studentAddress(student.getStudentAddress())
                .isDeleted(student.getIsDeleted())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
