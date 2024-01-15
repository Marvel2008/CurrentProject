package com.example.projectonsprboot.Service;

import com.example.projectonsprboot.Model.Person;
import com.example.projectonsprboot.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    public void createuser(Person person){
        String encod= encoder().encode(person.getPassword());
        person.setPassword(encod);
        personRepository.save(person);
    }
    public void changepassword(Person person){
        personRepository.getPersonByEmail(person.getEmail()).ifPresent(user -> {
            String encod= encoder().encode(person.getPassword());
            user.setPassword(encod);
            personRepository.save(user);
        });
    }
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


}
