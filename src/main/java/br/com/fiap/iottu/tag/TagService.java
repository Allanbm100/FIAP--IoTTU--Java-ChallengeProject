package br.com.fiap.iottu.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public List<Tag> findAll() {
        return repository.findAll();
    }

    public Optional<Tag> findById(Integer id) {
        return repository.findById(id);
    }

    public void save(Tag tag) {
        repository.save(tag);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Tag> findAvailableTags() {
        return repository.findAvailableTags();
    }

}
