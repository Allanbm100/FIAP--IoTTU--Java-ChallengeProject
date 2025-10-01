package br.com.fiap.iottu.motorcycle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

    List<Motorcycle> findByYardId(Integer yardId);

}
