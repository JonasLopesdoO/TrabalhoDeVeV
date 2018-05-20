package br.ufc.vev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.vev.bean.Sala;
import br.ufc.vev.repositorio.SalaRepositorio;

@Service	
public class SalaService {
	@Autowired
	SalaRepositorio salaRepositorio;

	public Sala salvarSala(Sala sala) {
		return salaRepositorio.save(sala);
	}

	public Sala buscarSala(Integer id) {
		return salaRepositorio.getOne(id);
	}

	public void excluirSala(Sala sala) {
		salaRepositorio.delete(sala);
	}

	public Sala atualizaSala(Sala sala) {
		return salaRepositorio.save(sala);
		
	}
	
	
	
	
}
