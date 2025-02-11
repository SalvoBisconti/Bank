package com.advancia.stage.Banca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advancia.stage.Banca.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select c from Cliente c where c.email_clnt = :email and c.password_clnt = :password")
	 Cliente verifyUser(String email, String password);
}
