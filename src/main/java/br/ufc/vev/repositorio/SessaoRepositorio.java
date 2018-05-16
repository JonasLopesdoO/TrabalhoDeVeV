package br.ufc.vev.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.vev.bean.Sessao;

@Repository
public interface SessaoRepositorio extends JpaRepository<Sessao, Integer>{

	
	
	/*
	Sessao findOne(Integer id);

	@Query("from Sessao s where s.startDate between ?1 and ?2")
	List<Sessao> findByStartDateBetween(LocalDate dataInicial, LocalDate dataFinal);

	@Query("from Sessao s, Filme f where f.id = s.id_filme and f.cidade = ?1")
	List<Sessao> findByFirstNameCidade(String cidade);

	@Query("from Sessao s, Filme f where s.id_filme = f.id and f.nome = ?1")
	List<Sessao> findByFirstNameFilme(String filme);
	
	@Query("from Sessao s, Filme f, Genero g where s.id_filme = f.id and f.id_genero = g.id and g.nome = ?1")
	List<Sessao> findByFirstNameGenero(String genero);
	

	@Query("select * from Sessao s where s.ci between ?1 and ?2")
	List<Sessao> findByFirstname(String cidade);

*/
}
