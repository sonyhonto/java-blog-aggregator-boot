package cz.jiripinkas.jba.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/create")
    public ResponseEntity<SimpleEntity> createEntity(@RequestBody SimpleEntity entity) {
        simpleService.save(entity);
        return ResponseEntity.ok(entity);
    }

}