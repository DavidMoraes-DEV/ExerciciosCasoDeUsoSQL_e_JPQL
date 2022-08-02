package com.devsuperior.uri2602.projections;
/*
* Para gerar um consulta customizado que retorna apenas a coluna NAME da tabela CUSTOMERS será necessário:
	- Criar uma PROJEÇÃO dessa tabela definindo apenas o campo desejado NAME
	- No Spring Data JPA(Sub Framework do Spring) que estamos utilizando:
		- Para retornar uma PROJEÇÃO da tabela (Sendo PROJEÇÃO a limitação dos campos que será retornado na consulta):
			- É necessário implementar uma interface que define apenas os campos necessarios para a consulta.	

* Essa então será a interface que representa a projeção dos dados necessarios para a consulta do exercicio atual:
*/
public interface CustomerMinProjection {

	//Colocar os nomes necessários para a projecao na forma de METODOS GET
	String getName();
}
