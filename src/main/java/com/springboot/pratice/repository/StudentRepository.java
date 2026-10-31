package com.springboot.pratice.repository;

import com.springboot.pratice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
 Optional<Student> findByRollNumberAndIsDeletedFalse(Long rollNumber);

    List<Student> findByIsDeletedFalse();
}
