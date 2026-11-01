package com.springboot.pratice.service;

import com.springboot.pratice.dtos.CreateStudentDTO;
import com.springboot.pratice.dtos.StudentResponseDTO;
import com.springboot.pratice.entity.Student;
import com.springboot.pratice.exception.ResourceExistsException;
import com.springboot.pratice.exception.ResourceNotFoundException;
import com.springboot.pratice.mapper.StudentMapper;
import com.springboot.pratice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDTO saveStudent(CreateStudentDTO createStudentDTO) {

        // check the student is present with requested roll number
        studentRepository
                .findByRollNumberAndIsDeletedFalse(createStudentDTO.rollNumber())
                .ifPresent(studentInfo -> {
                    throw new ResourceExistsException("Student with roll number " + studentInfo.getRollNumber() + " already exists");
                });

        // convert studentRequestDTO to entity
        Student student = StudentMapper.toStudent(createStudentDTO);

        // save the converted entity data
        Student createdStudentInfo = studentRepository.save(student);

        // again convert to entity response to StudentResponseDTO.
        return StudentMapper.toStudentResponseDTO(createdStudentInfo);
    }

    @Override
    public StudentResponseDTO getStudentInfoByIdOrRollNumber(String identifier) {
        String studentId = null;
        Long rollNumber = null;
        if (identifier.matches("\\d+")) {
            System.out.println("Enter into if block and identifier is " + identifier);
            rollNumber = Long.parseLong(identifier);
        } else {
            System.out.println("Enter into else block and identifier is " + identifier);
            studentId = identifier;
        }
        Student getStudentInfo = studentRepository
                .findByIdOrRollNumberAndDeletedFalse(studentId, rollNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with identifier " + identifier));
        return StudentMapper.toStudentResponseDTO(getStudentInfo);
    }

    @Override
    public StudentResponseDTO getStudentInfoById(String studentId) {
        Student getStudentInfo= studentRepository
                .findByIdAndIsDeletedFalse(studentId)
                .orElseThrow(()->new ResourceNotFoundException("Student not found with id "+studentId));
        return  StudentMapper.toStudentResponseDTO(getStudentInfo);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> getAllStudentInfo = studentRepository.findByIsDeletedFalse();
        return getAllStudentInfo.stream().map(StudentMapper::toStudentResponseDTO).collect(Collectors.toList());
    }
}
