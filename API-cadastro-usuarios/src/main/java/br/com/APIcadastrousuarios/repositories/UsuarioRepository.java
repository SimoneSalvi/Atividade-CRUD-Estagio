package br.com.APIcadastrousuarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.APIcadastrousuarios.models.UsuarioModel;




//cria uma interface que dá acesso a uma infinidade de métodos
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

}
