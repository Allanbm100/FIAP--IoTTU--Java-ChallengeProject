package br.com.fiap.iottu.antenna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AntennaRepository extends JpaRepository<Antenna, Integer> {

    List<Antenna> findByYardId(Integer yardId);

}
