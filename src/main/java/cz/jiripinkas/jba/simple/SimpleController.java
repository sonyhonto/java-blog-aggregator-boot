package cz.jiripinkas.jba.simple;

import java.net.URI;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/simple")
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @RequestMapping("/empty")
    public String empty() {
        return "empty";
    }

    @GetMapping("/all")
    public ResponseEntity<List<SimpleEntity>> all() {
        return ResponseEntity.ok(this.simpleService.all());
    }

    @RequestMapping("/{requestedId}")
    public ResponseEntity<SimpleEntity> getById(@PathVariable("requestedId") Integer id) {
        Optional<SimpleEntity> entity = this.simpleService.findById(id);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/byname/{requestedName}")
    public ResponseEntity<Iterable<SimpleEntity>> notUniqueList(@PathVariable("requestedName") String name) {
        Iterable<SimpleEntity> listEntity = this.simpleService.findAllByName(name);
        if (listEntity != null) {
            return ResponseEntity.ok(listEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleEntity> createEntity(@RequestBody SimpleEntity entity) {
        simpleService.save(entity);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/post")
    public ResponseEntity<SimpleEntity> postEntity(@RequestBody SimpleEntity entity, UriComponentsBuilder ucb) {
        SimpleEntity savedEntity = simpleService.saveAndReturn(entity);
        URI locationOfNewEntity = ucb
                .path("simple/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewEntity).body(savedEntity);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<SimpleEntity> update(@PathVariable Integer id, @RequestBody SimpleEntity entity) {

        if (this.simpleService.findById(id).isPresent()) {
            SimpleEntity entityBase = this.simpleService.findById(id).get();
            entityBase.setName(entity.getName());
            entityBase.setTitle(entity.getTitle());
            SimpleEntity updatedEntity = this.simpleService.saveAndReturn(entityBase);
            return ResponseEntity.ok(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id) {
        Optional<SimpleEntity> optional = this.simpleService.findById(id);
        if (!optional.isPresent()) {
            throw new SimpleNotFoundException("SimpleEntity does not exist with id:" + id);
        }
        SimpleEntity entity = optional.get();
        this.simpleService.delete(entity);
        return ResponseEntity.noContent().build();
    }

}
