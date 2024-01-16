package com.example.demo;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @BeforeEach
    void setUp() {
        personRepository.save(new Person("Owen Lars", 178.0, 120.0));
        personRepository.save(new Person("Beru Whitesun lars", 175.0, 75.0));
        personRepository.save(new Person("Darth Vader", 202.0, 136.0));
    }

    @Test
    void shouldGetPersonByName() {
        // given
        String personName = "Owen Lars";

        // when
        Person person = personService.getbyName(personName);

        // then
        assertThat(person.getName().equals(personName)).isTrue();
    }

    @Test
    void shouldGetMatchingPersonsByName() {
        // given
        String personName = "Lars";

        // when
        List<Person> personList = personService.getbyNameMatching(personName);

        // then
        assertThat(personList.size() == 2).isTrue();
    }
}
