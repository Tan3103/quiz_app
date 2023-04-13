package kz.spring.quiz_app.services;

import kz.spring.quiz_app.model.User;
import kz.spring.quiz_app.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll(){
        return usersRepository.findAll();
    }

    public User findOne(int id){
        Optional<User> foundPerson = usersRepository.findById(id);

        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(User user){
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser){
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id){
        usersRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username){
        return usersRepository.findByUsername(username);
    }
}
