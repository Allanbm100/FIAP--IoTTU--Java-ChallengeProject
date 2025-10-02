package br.com.fiap.iottu.motorcycle;

import br.com.fiap.iottu.dto.MotorcycleDataDTO;
import br.com.fiap.iottu.motorcyclestatus.MotorcycleStatus;
import br.com.fiap.iottu.motorcyclestatus.MotorcycleStatusRepository;
import br.com.fiap.iottu.tag.Tag;
import br.com.fiap.iottu.tag.TagService;
import br.com.fiap.iottu.yard.Yard;
import br.com.fiap.iottu.yard.YardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository repository;

    @Autowired
    private MotorcycleStatusRepository motorcycleStatusRepository;
    @Autowired
    private YardRepository yardRepository;
    @Autowired
    private TagService tagService;

    public List<Motorcycle> findAll() {
        return repository.findAll();
    }

    public Optional<Motorcycle> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Motorcycle> findByYardId(Integer yardId) {
        return repository.findByYardId(yardId);
    }

    public void save(Motorcycle motorcycle) {
        repository.save(motorcycle);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public void processMotorcyclesData(List<MotorcycleDataDTO> motorcycleDataDTOs) {
        for (MotorcycleDataDTO dto : motorcycleDataDTOs) {
            // Busca a moto pelo chassi; se não encontrar, cria uma nova instância
            Motorcycle motorcycle = repository.findByChassi(dto.getChassiMoto()).orElse(new Motorcycle());

            // O ID da moto será gerenciado automaticamente pelo banco de dados (se for um novo registro)
            // ou será o ID da moto existente encontrada pelo chassi.
            // Não precisamos mais chamar motorcycle.setId(dto.getIdMoto());

            // Fetch and set MotorcycleStatus
            MotorcycleStatus status = motorcycleStatusRepository.findById(dto.getIdStatus())
                    .orElseThrow(() -> new RuntimeException("MotorcycleStatus not found for ID: " + dto.getIdStatus()));
            motorcycle.setStatus(status);

            // Fetch and set Yard
            Yard yard = yardRepository.findById(dto.getIdPatio())
                    .orElseThrow(() -> new RuntimeException("Yard not found for ID: " + dto.getIdPatio()));
            motorcycle.setYard(yard);

            motorcycle.setLicensePlate(dto.getPlacaMoto());
            motorcycle.setChassi(dto.getChassiMoto());
            motorcycle.setEngineNumber(dto.getNrMotorMoto());
            motorcycle.setModel(dto.getModeloMoto());

            // Handle Tag data
            if (dto.getCodigoRfidTag() != null && !dto.getCodigoRfidTag().isEmpty()) {
                Tag tag = tagService.findOrCreateTag(
                        dto.getCodigoRfidTag(),
                        dto.getSsidWifiTag(),
                        dto.getLatitude(),
                        dto.getLongitude()
                );

                // Ensure the tags list is initialized
                if (motorcycle.getTags() == null) {
                    motorcycle.setTags(new ArrayList<>());
                }

                // Add tag if not already present
                if (!motorcycle.getTags().contains(tag)) {
                    motorcycle.getTags().add(tag);
                }
            }

            repository.save(motorcycle);
        }
    }
}
