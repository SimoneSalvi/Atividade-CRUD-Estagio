package br.com.APIcadastrousuarios.controllers;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.APIcadastrousuarios.models.UsuarioModel;
import br.com.APIcadastrousuarios.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")

public class UsuarioController {
	
	// métodos get, post, delete, update
	// aqui estarão os endpoints da aplicação

	/*
	 * Como já foi criado o UsuarioRepository que também é um bean, é preciso criar
	 * um ponto de injeção dentro do controller para que o Spring injete as
	 * dependências de UsuarioRepository quando necessário.
	 */

	@Autowired
	UsuarioRepository usuarioRepository;
	

//	HTTP POST, utiliza-se o método save(S entity) do JPA e caso a persistência aconteça 
//	corretamente no banco, 
//	o retorno deve ser CREATED(202).

	@PostMapping("/save")
	public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody UsuarioModel usuario) {
		return new ResponseEntity<UsuarioModel>(usuarioRepository.save(usuario), HttpStatus.CREATED);
	}
	
//	definir os possíveis retornos utilizando o ResponsityEntity e o HttpStatus

	@GetMapping("/usuariosCadastrados")
	public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
		List<UsuarioModel> usuariosList = usuarioRepository.findAll();
		if (usuariosList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<UsuarioModel>>(usuariosList, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> getOneUsuario(@PathVariable(value = "id") long id) {
		Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
		if (!usuarioO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UsuarioModel>(usuarioO.get(), HttpStatus.OK);
		}
	}

	@DeleteMapping("/deletarUsuario/{id}")
	public ResponseEntity<UsuarioModel> deleteUsuario(@PathVariable(value = "id") long id) {
		Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
		if (!usuarioO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			usuarioRepository.delete(usuarioO.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}


	@PutMapping("/alterarCadastro")
	public ResponseEntity<UsuarioModel> putUsuario(@RequestBody UsuarioModel usuario) {
		Optional<UsuarioModel> usuarioO = usuarioRepository.findById(usuario.getId());
		if (!usuarioO.isPresent()) {
			return new ResponseEntity<UsuarioModel>(HttpStatus.NOT_FOUND);
		} else {
			//produto.setId(produtoO.get().getIdProduto());
			return new ResponseEntity<UsuarioModel>(usuarioRepository.save(usuario), HttpStatus.OK);
		}
	}

	
}
