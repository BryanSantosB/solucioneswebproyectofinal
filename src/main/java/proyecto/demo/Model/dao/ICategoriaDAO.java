package proyecto.demo.Model.dao;

import org.springframework.data.repository.CrudRepository;

import proyecto.demo.Model.entidad.Categoria;

public interface ICategoriaDAO extends CrudRepository<Categoria, Long>{
    
}
