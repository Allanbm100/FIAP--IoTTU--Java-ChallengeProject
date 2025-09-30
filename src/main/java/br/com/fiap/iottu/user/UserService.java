package br.com.fiap.iottu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    public void save(User user) {
        repository.save(user);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
