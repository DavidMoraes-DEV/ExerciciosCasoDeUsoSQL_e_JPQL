package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

//Para não precisar implementar interface WEB será rodado no console utilizando o CommandLineRunner aqui na classe principal
@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {
	
	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}
	//O que for colocado no metodo RUM será executado no inicio da aplicação
	@Override
	public void run(String... args) throws Exception {
		
		//Verificando se realmente esta retornando a consulta do banco como o exercicio pede
		List<CustomerMinProjection> list = repository.searchSQL("rs");
		
		List<CustomerMinDTO> result1 = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());
		
		System.out.printf("\nResultado SQL padrão: \n");
		for(CustomerMinDTO obj : result1) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<CustomerMinDTO> result2 = repository.searchJPQL("rs");
		
		System.out.printf("\nResultado JPQL: \n");
		for(CustomerMinDTO obj : result2) {
			System.out.println(obj);
		}
	}
}
