package ru.solarev.firstpetproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.solarev.firstpetproject.models.Person;
import ru.solarev.firstpetproject.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> showAllPerson() {
        return peopleRepository.findAll();
    }

    public Person showPerson(int id) {
        Optional<Person> optional = peopleRepository.findById(id);
        return optional.orElse(null);
    }

    public void savePerson(Person person) {
        peopleRepository.save(person);
    }

    public void updatePerson(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }

}
