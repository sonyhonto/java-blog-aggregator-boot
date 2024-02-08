package cz.jiripinkas.jba.simple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    @Autowired
    private SimpleRepository simpleRepository;

    public SimpleEntity findByName(String name) {
        return this.simpleRepository.findByName(name);
    }

    public Iterable<SimpleEntity> findAllByName(String name) {
        return this.simpleRepository.findAllByName(name);
    }

    public Optional<SimpleEntity> findById(Integer id) {
        return this.simpleRepository.findById(id);
    }

    public void save(SimpleEntity entity) {
        this.simpleRepository.save(entity);
    }

    public SimpleEntity saveAndReturn(SimpleEntity entity){
        return this.simpleRepository.save(entity);
    }

    public List<SimpleEntity> all() {
        return this.simpleRepository.findAll();
    }

}
