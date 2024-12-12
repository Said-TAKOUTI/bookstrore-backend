package com.takouti.bookstore.dao;

import com.takouti.bookstore.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "provinces", path = "provinces")
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findByCountryCode(@Param("code") String code);
}
