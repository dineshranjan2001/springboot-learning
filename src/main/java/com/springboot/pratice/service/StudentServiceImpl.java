package com.springboot.pratice.service;

import com.springboot.pratice.dtos.CreateStudentDTO;
import com.springboot.pratice.dtos.StudentResponseDTO;
import com.springboot.pratice.entity.Student;
import com.springboot.pratice.exception.ResourceExistsException;
import com.springboot.pratice.mapper.StudentMapper;
import com.springboot.pratice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    @Override
    public StudentResponseDTO saveStudent(CreateStudentDTO createStudentDTO){

        // check the student is present with requested roll number
        studentRepository
                .findByRollNumberAndIsDeletedFalse(createStudentDTO.rollNumber())
                .ifPresent(studentInfo->{
                    throw new ResourceExistsException("Student with roll number "+studentInfo.getRollNumber()+" already exists");
                });

        // convert studentRequestDTO to entity
        Student student= StudentMapper.toStudent(createStudentDTO);

        // save the converted entity data
        Student createdStudentInfo=studentRepository.save(student);

        // again convert to entity response to StudentResponseDTO.
        return StudentMapper.toStudentResponseDTO(createdStudentInfo);
    }

    @Override
    public List<Student> getAllStudents() {
       List<Student> getAllStudentInfo=studentRepository.findByIsDeletedFalse();
       return  getAllStudentInfo;
    }
}
