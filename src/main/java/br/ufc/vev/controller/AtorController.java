package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.service.AtorService;


@Controller
@RequestMapping(path= "/ator")
public class AtorController {
	
	@Autowired
	private AtorService atorService;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("ator");
		try {
			List<Ator> atores = getAllAtor();
			
			model.addObject("atores", atores);
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioAtor() {
		ModelAndView model = new ModelAndView("formulario-ator");
		model.addObject("ator", new Ator());
		
		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ModelAndView salvaAtor(Ator ator) {
		ModelAndView model = new ModelAndView("ator");
		try {
			if (this.validaAtor(ator.getNome(), ator.getSobre())) {
				atorService.salvarAtor(ator);
				model.addObject("atorRetorno", ator);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}
	
	public boolean validaAtor(String nome, String sobre) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (sobre.equals(null)) {
			throw new Exception("Sobre não pode ser nulo");
		} else if (sobre.equals("")) {
			throw new Exception("Sobre não pode ser vazio");
		}
		return true;
	}
	
	public boolean validaId(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}

	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaAtor(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("ator");
		try {
			if(this.validaId(id)) {
				if(existsByIdAtor(id)) {
					Ator ator = new Ator();
					
					ator = atorService.buscarAtor(id);
					
					model.addObject("atorRetorno", ator);
				}else {
					//mensagem de erro "id nao existente no banco"
				}
		 	}else {
		 		//msg de id invalido
		 	}
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			return index();
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiAtor(@PathVariable("id") Integer id) {		
		try {
			Ator ator = new Ator();
			if (validaId(id) && existsByIdAtor(id)) {
				ator = atorService.buscarAtor(id);
				atorService.excluirAtor(ator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}

	public List<Ator> getAllAtor() {		
		return atorService.getAllAtor();
	}
	
	//o metodo utilizado para atualizar será o salvar, visto que o spring boot ja atualiza automaticamente o objeto passado.
	//este método só redireciona para a digitação dos novos campos do model
	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaAtor(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-ator");

		try {
			if (existsByIdAtor(id)) {
				Ator ator = atorService.buscarAtor(id);
				
				model.addObject("ator", ator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
	}
	
	public boolean existsByIdAtor(int id) {
		return atorService.buscaAtor(id);
	}
}
