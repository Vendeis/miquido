package miquido.controller;

import miquido.model.Person;
import miquido.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/import/{id}")
    public Person importPerson(@PathVariable Integer id) {
        return personService.importCharacter(id);
    }

    @GetMapping("/get-single/{name}")
    public Person getPerson(@PathVariable String name) {
        return personService.getbyName(name);
    }

    @GetMapping("get-multiple/{name}")
    public List<Person> getPersons(@PathVariable String name) {
        return personService.getbyNameMatching(name);
    }
}
