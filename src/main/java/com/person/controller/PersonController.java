package com.person.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import com.person.model.Person;
import com.person.repository.PersonRepository;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Person", description = "Person controller")
public class PersonController {

	public PersonRepository personRepository;

	public PersonController(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Operation(summary = "Find all persons", description = "All persons", tags = { "person" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))) })
	@GetMapping("/person")
	public List<Person> index() {
		return personRepository.findAll();
	}

	@Operation(summary = "Find person by id", description = "Find person by id", tags = { "person" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Person.class))) })
	@GetMapping("/person/{id}")
	public Person show(@PathVariable String id) {
		int personId = Integer.parseInt(id);
		return personRepository.findById(personId).orElse(new Person());
	}

	@Operation(summary = "Create a new person", description = "Create a new person", tags = { "person" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Person.class))) })
	@PostMapping("/person")
	public Person create(@RequestBody Map<String, String> body) {
		String first_name = body.get("first_name");
		String last_name = body.get("last_name");
		int age = Integer.valueOf(body.get("age"));
		String favourite_colour = body.get("favourite_colour");
		return personRepository.save(new Person(first_name, last_name, age, favourite_colour));
	}

	@Operation(summary = "Update a new person", description = "Update a new person", tags = { "person" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Person.class))) })
	@PutMapping("/person/{id}")
	public Person update(@PathVariable String id, @RequestBody Map<String, String> body) {
		int personId = Integer.parseInt(id);
		// getting person
		Person person = personRepository.findById(personId).orElse(new Person());
		person.setFirst_name(body.get("first_name"));
		person.setLast_name(body.get("last_name"));
		person.setAge(Integer.valueOf(body.get("age")));
		person.setFavourite_colour(body.get("favourite_colour"));
		return personRepository.save(person);
	}

	@Operation(summary = "Delete a  person", description = "Delete a person", tags = { "person" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Boolean.class))) })
	@DeleteMapping("person/{id}")
	public boolean delete(@PathVariable String id) {
		int personId = Integer.parseInt(id);
		personRepository.deleteById(personId);
		return true;
	}

}
