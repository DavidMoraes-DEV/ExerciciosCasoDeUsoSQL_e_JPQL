package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
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
	* Para prevenir que a consulta seja feita com letras minusculas já que no banco de dados esta maiúcula Ex: RS
	 	- Podemos utilizar os comando:
	 		- UPPER() -> Converte tudo para maiúsculo
	 		- DOWN() -> Converte tudo para minúsculo
	 		 
	* Consulta SQL PADRÃO: */
	@Query(nativeQuery = true, value = "SELECT name FROM customers WHERE UPPER(state) = UPPER(:state)")
	List<CustomerMinProjection> searchSQL(String state);
	
	/*
	* Consultas Utilizando JPQL:
	 	- Não precisa utilizar a PROJECTION
	 	- Pode utilizar diretamente o DTO
	 	- Porém na consulta JPQL devemos:
	 		- Ao invés de colocar o nome da TABELA do banco, colocasse o nome da CLASSE = Customer
	 		- Dar um APELIDO para o objeto da consulta = obj
	 		- Devemos colocar um NEW após o SELECT especificando o caminho completo do DTO que desejamos a projeção passando já a referencia do construtor que recebe o nome como argumento
	 	 		- Ficando então: new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name) para dar certo a projecao dos dados sem utilizar uma interface de personalizada de projecao
	 	 	
	 	 	- Nos campos que faz referencia ao objeto devemos colocar o apelido antes do valor desejado como: obj.state para definir a coluna ou obj.name no construtor do DTO
	 		 
	* Consulta JPQL: */
	@Query(value = "SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name)"
			+ " FROM Customer obj "
			+ "WHERE UPPER(obj.state) = UPPER(:state)")
	List<CustomerMinDTO> searchJPQL(String state);
}
