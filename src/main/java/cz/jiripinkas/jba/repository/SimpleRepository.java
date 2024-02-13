package cz.jiripinkas.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.jiripinkas.jba.entity.SimpleEntity;

public interface SimpleRepository extends JpaRepository<SimpleEntity, Integer> {

    SimpleEntity findByName(String name);

    Iterable<SimpleEntity> findAllByName(String name);

}
