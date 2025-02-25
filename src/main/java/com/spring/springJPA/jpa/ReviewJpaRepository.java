package com.spring.springJPA.jpa;

import com.spring.springJPA.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewJpaRepository extends JpaRepository<Review,Integer> {
}
