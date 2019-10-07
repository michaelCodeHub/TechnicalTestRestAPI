package com.technical.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.technical.test.model.Person;

@Repository

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE LOWER(p.firstName) = LOWER(:searchItem) or LOWER(p.lastName) = LOWER(:searchItem)")
    public List<Person> find(@Param("searchItem") String searchItem);
}