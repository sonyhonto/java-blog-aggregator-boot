package cz.jiripinkas.jba.simple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<SimpleEntity> selectTheName(@PathVariable("requestedId") Integer id) {

        Optional<SimpleEntity> entity = this.simpleService.findById(id);

        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/create")
    public ResponseEntity<SimpleEntity> createEntity(@RequestBody SimpleEntity entity) {
        simpleService.save(entity);
        return ResponseEntity.ok(entity);
    }

}
