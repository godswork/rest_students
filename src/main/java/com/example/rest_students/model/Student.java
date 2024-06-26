package com.example.rest_students.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Students")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surname;

}
