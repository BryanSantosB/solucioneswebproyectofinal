package proyecto.demo.Model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.demo.Model.dao.IMetodoPagoDAO;
import proyecto.demo.Model.entidad.MetodoPago;

@Service
public class MetodoPagoService implements IMetodoPagoService{

    @Autowired
    private IMetodoPagoDAO metodopagoDAO;

    @Override
    public List<MetodoPago> cargarMetodoPago() {
        return (List<MetodoPago>)metodopagoDAO.findAll();
    }

    @Override
    public MetodoPago obtenerMetodoPagoById(Long id) {
        Optional<MetodoPago> metodoPagoOptional = metodopagoDAO.findById(id);
        return metodoPagoOptional.orElse(null); // O manejar de otra manera si es null
    }


}
