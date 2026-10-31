package com.springboot.pratice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student_info")
public class Student extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private Long rollNumber;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String studentEmail;
    @Column(nullable = false,length = 10)
    private String studentContact;

    private String studentAddress;
    private Boolean isDeleted=false;
}
