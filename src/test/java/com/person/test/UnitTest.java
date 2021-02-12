package com.person.test;

import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.person.controller.PersonController;
import com.person.model.Person;
import com.person.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {

	@MockBean(name = "personRepository")
	PersonRepository personRepository;

	@Autowired
	private PersonController personController;

	@Test
	public void personById() {

		Person person = new Person();
		person.setId(1);
		person.setFirst_name("John");
		person.setLast_name("David");
		person.setAge(20);
		person.setFavourite_colour("white");
		Mockito.when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
		Optional<Person> person_service_abc = Optional.ofNullable(personController.show("1"));
		assertThat(person.getFirst_name().equalsIgnoreCase(person_service_abc.get().getFirst_name()));
		assertTrue(person.getLast_name().equalsIgnoreCase("David"));
	}

}
