package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query(nativeQuery = true, value = "SELECT movies.id, movies.name FROM movies "
			+ "INNER JOIN genres ON movies.id_genres = genres.id "
			+ "WHERE genres.description = :genreName")
	List<MovieMinProjection> saerchSQL(String genreName);
	
	//Com JPQL o INNER JOIN é feito implicitamente pois é possível navegar dentro dos objetos buscando a entidade e o atributo desejado = obj.genre.description
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name)"
			+ " FROM Movie obj "
			+ "WHERE obj.genre.description = :genreName")
	List<MovieMinDTO> saerchJPQL(String genreName);
}
