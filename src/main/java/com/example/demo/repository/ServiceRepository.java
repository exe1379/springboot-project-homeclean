package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

}
