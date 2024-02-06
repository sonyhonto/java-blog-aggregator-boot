package cz.jiripinkas.jba.simple;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleRepository extends JpaRepository<SimpleEntity, Integer> {

    SimpleEntity findByName(String name);

    Iterable<SimpleEntity> findAllByName(String name);

}
