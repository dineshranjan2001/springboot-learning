package com.springboot.pratice.repository;

import com.springboot.pratice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {
    Optional<Student> findByRollNumberAndIsDeletedFalse(Long rollNumber);

    @Query("""
                SELECT s FROM Student s
                    WHERE
                        s.isDeleted=false
                    AND( 
                        (:studentId IS NOT NULL AND s.id=:studentId)
                       OR (:rollNumber IS NOT NULL AND s.rollNumber=:rollNumber)
                     )
            """)
    Optional<Student> findByIdOrRollNumberAndDeletedFalse(@Param("studentId") String studentId,@Param("rollNumber") Long rollNumber);
    Optional<Student> findByIdAndIsDeletedFalse(String studentId);
    List<Student> findByIsDeletedFalse();
}
