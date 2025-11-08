package br.com.fiap.iottu.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT t FROM Tag t WHERE t.motorcycles IS EMPTY")
    List<Tag> findAvailableTags();

    @Query("SELECT DISTINCT t FROM Tag t JOIN t.motorcycles m WHERE m.yard.user.id = :userId")
    List<Tag> findByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT DISTINCT t.* FROM tb_tag t " +
           "LEFT JOIN tb_moto_tag mt ON t.id_tag = mt.id_tag " +
           "LEFT JOIN tb_moto m ON mt.id_moto = m.id_moto " +
           "LEFT JOIN tb_patio p ON m.id_patio = p.id_patio " +
           "WHERE NOT EXISTS (SELECT 1 FROM tb_moto_tag mt2 WHERE mt2.id_tag = t.id_tag) " +
           "OR p.id_usuario = :userId",
           nativeQuery = true)
    List<Tag> findOrphanAndUserTags(@Param("userId") Integer userId);

    Optional<Tag> findByRfidCode(String rfidCode);
    
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
           "FROM tb_moto_tag WHERE id_tag = :tagId", nativeQuery = true)
    boolean isTagInUse(@Param("tagId") Integer tagId);
}
