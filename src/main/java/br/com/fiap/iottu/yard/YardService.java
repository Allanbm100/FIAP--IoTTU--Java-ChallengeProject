package br.com.fiap.iottu.yard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YardService {

    @Autowired
    private YardRepository repository;

    public List<Yard> findAll() {
        return repository.findAll();
    }

    public Optional<Yard> findById(Integer id) {
        return repository.findById(id);
    }

    public void save(Yard yard) {
        repository.save(yard);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
