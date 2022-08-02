package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	/*
	* Consulta para retornar uma lista contendo:
		- Os elementos limitados pela PROJECTION definida da interface CustomerMinProjection
	* @Query: Anotacao para customizar a consulta
	* nativeQuery = true: Define que a consulta será feita com o SQL padrão 
	* value = "": é onde define o SQL da consulta
	* :state = Define :Nome da variavel para vincular com o nome do atributo passado como argumento para a consulta DEVE ser igual
	*/
	@Query(nativeQuery = true, value = "SELECT name FROM customers WHERE state = :state")
	List<CustomerMinProjection> search1SQL(String state);
}
