package ru.solarev.firstpetproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.solarev.firstpetproject.models.Person;
import ru.solarev.firstpetproject.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> showAllPerson() {
        return peopleRepository.findAll();
    }

    public Person showPerson(int id) {
        Optional<Person> optional = peopleRepository.findById(id);
        return optional.orElse(null);
    }

    public void savePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }

    public void updatePerson(int id, Person person) {
        Person preUpdate = peopleRepository.findById(id).get();
        person.setId(id);
        person.setPassword(preUpdate.getPassword());
        person.setRole(preUpdate.getRole());
        peopleRepository.save(person);
    }

    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }

}
