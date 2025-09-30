package br.com.fiap.iottu.motorcyclestatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleStatusService {

    @Autowired
    private MotorcycleStatusRepository repository;

    public List<MotorcycleStatus> findAll() {
        return repository.findAll();
    }

    public Optional<MotorcycleStatus> findById(Integer id) {
        return repository.findById(id);
    }

    public void save(MotorcycleStatus status) {
        repository.save(status);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
