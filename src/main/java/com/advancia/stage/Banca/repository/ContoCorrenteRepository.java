package com.advancia.stage.Banca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advancia.stage.Banca.model.ContoCorrente;

@Repository
public interface ContoCorrenteRepository extends JpaRepository<ContoCorrente, Long> {

}
