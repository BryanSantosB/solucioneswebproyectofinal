package proyecto.demo.Model.service;

import java.util.List;

import proyecto.demo.Model.entidad.Categoria;

public interface ICategoriaService {
    public void guardarCategoria(Categoria categoria);
    public List<Categoria> cargarCategorias();
    public void eliminarCategoria(Long id);
}
