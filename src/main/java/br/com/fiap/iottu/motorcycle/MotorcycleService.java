package br.com.fiap.iottu.motorcycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository repository;

    public List<Motorcycle> findAll() {
        return repository.findAll();
    }

    public Optional<Motorcycle> findById(Integer id) {
        return repository.findById(id);
    }

    public void save(Motorcycle motorcycle) {
        repository.save(motorcycle);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
