package com.curso.fullstack.back.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.fullstack.back.models.entity.Region;

public interface IRegionDao extends JpaRepository<Region,Long> {

}
