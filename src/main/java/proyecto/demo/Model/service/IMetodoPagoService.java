package proyecto.demo.Model.service;

import java.util.List;

import proyecto.demo.Model.entidad.MetodoPago;

public interface IMetodoPagoService{
    public List<MetodoPago> cargarMetodoPago();
    public MetodoPago obtenerMetodoPagoById(Long id);
}
