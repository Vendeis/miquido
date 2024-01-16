package miquido.service;

import miquido.exception.PersonNotFoundException;
import miquido.exception.SwapiPersonNotFoundException;
import miquido.model.Person;
import miquido.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {
    @Value("${person.url}")
    String url;

    @Value("${person.height}")
    Double height;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RestTemplate restTemplate;

    public Person importCharacter(Integer id) {
        try {
            Person person = restTemplate.getForObject(url + id, Person.class);
            personRepository.save(person);
            return person;
        } catch (HttpClientErrorException e) {
            throw new SwapiPersonNotFoundException(id);
        }
    }

    public Person getbyName(String name) {
        return personRepository.findByName(name)
                .filter(p -> p.getHeight() > height)
                .orElseThrow(() -> new PersonNotFoundException(name));
    }

    public List<Person> getbyNameMatching(String name) {
        List<Person> personList = personRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .filter(p -> p.getHeight() > height)
                .collect(Collectors.toList());

        if (personList.isEmpty())
            throw new PersonNotFoundException(name);

        return personList;
    }
}
