package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Formation;
import java.time.LocalDate;


public interface FormationRepository extends JpaRepository<Formation, Integer> {

	List<Formation> findByNomContaining(String nom);
}
