package com.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
