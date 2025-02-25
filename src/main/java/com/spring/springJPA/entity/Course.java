package com.spring.springJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"students","reviews"})
@JsonIgnoreProperties(value = {"reviews","students"},allowGetters = false)
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private int courseDuration;
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    @ManyToMany(mappedBy = "courses",cascade = {CascadeType.ALL})
    private List<Student> students = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Course(String courseName,int courseDuration){
        this.courseDuration = courseDuration;
        this.courseName = courseName;
    }
    public void addReview(Review review){
        reviews.add(review);
    }

    public void removeReview(Review review){
        reviews.remove(review);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }
}
