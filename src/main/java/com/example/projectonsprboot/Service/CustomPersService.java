package com.example.projectonsprboot.Service;

import com.example.projectonsprboot.Model.Person;
import com.example.projectonsprboot.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomPersService implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.getPersonByEmail(email);
        System.out.println(email);
        return user.map(Person::new).orElseThrow(()->new UsernameNotFoundException("User does not exists"));
    }
}
