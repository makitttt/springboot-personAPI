package com.person.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.person.model.Person;
import com.person.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    public PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository){this.personRepository = personRepository;}

    @Transactional(readOnly = true)
    public List<Person> getAllPersons(final int count){
        return this.personRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Person> getPerson(final int id){return this.personRepository.findById(id);}
}
