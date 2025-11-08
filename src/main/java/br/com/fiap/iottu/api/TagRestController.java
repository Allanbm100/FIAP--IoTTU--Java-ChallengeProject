package br.com.fiap.iottu.api;

import br.com.fiap.iottu.tag.Tag;
import br.com.fiap.iottu.tag.TagService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tags")
@CacheConfig(cacheNames = "tags")
public class TagRestController {

    @Autowired
    private TagService tagService;
    
    @Autowired
    private br.com.fiap.iottu.tag.TagRepository tagRepository;

    public static class TagResponse {
        @JsonProperty("id_tag")
        public Integer id;
        
        @JsonProperty("codigo_rfid_tag")
        public String rfidCode;
        
        @JsonProperty("ssid_wifi_tag")
        public String wifiSsid;
        
        @JsonProperty("latitude_tag")
        public BigDecimal latitude;
        
        @JsonProperty("longitude_tag")
        public BigDecimal longitude;
        
        @JsonProperty("data_hora_tag")
        public LocalDateTime timestamp;
        
        @JsonProperty("em_uso")
        public boolean inUse;

        public static TagResponse fromTag(Tag tag, br.com.fiap.iottu.tag.TagRepository repository) {
            TagResponse response = new TagResponse();
            response.id = tag.getId();
            response.rfidCode = tag.getRfidCode();
            response.wifiSsid = tag.getWifiSsid();
            response.latitude = tag.getLatitude();
            response.longitude = tag.getLongitude();
            response.timestamp = tag.getTimestamp();
            response.inUse = repository.isTagInUse(tag.getId());
            return response;
        }
    }

    @GetMapping
    @Cacheable
    public List<TagResponse> list(@RequestParam(required = false) Integer userId) {
        List<Tag> tags;
        if (userId != null) {
            tags = tagService.findOrphanAndUserTags(userId);
        } else {
            tags = tagService.findAll();
        }
        return tags.stream().map(tag -> TagResponse.fromTag(tag, tagRepository)).collect(Collectors.toList());
    }

    @GetMapping("/available")
    @Cacheable
    public List<Tag> listAvailable() {
        return tagService.findAvailableTags();
    }

    @GetMapping("/{id}")
    public Tag getById(@PathVariable Integer id) {
        return tagService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag não encontrada, id=" + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    public Tag create(@Valid @RequestBody Tag tag) {
        tagService.validateDuplicate(tag);
        tagService.save(tag);
        return tag;
    }

    @PutMapping("/{id}")
    @CacheEvict(allEntries = true)
    public Tag update(@PathVariable Integer id, @Valid @RequestBody Tag tag) {
        tagService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag não encontrada, id=" + id));
        tag.setId(id);
        tagService.validateDuplicate(tag);
        tagService.save(tag);
        return tag;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void delete(@PathVariable Integer id) {
        tagService.deleteById(id);
    }
}
