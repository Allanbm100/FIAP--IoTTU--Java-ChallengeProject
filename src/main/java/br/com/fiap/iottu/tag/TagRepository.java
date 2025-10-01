package br.com.fiap.iottu.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT t FROM Tag t WHERE t.motorcycles IS EMPTY")
    List<Tag> findAvailableTags();
}
