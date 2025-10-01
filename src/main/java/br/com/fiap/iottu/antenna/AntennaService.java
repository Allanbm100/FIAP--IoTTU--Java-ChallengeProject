package br.com.fiap.iottu.antenna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AntennaService {

    @Autowired
    private AntennaRepository repository;

    public List<Antenna> findAll() {
        return repository.findAll();
    }

    public Optional<Antenna> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Antenna> findByYardId(Integer yardId) {
        return repository.findByYardId(yardId);
    }

    public void save(Antenna antenna) {
        repository.save(antenna);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
