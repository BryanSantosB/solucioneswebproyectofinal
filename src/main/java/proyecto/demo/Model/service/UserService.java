package proyecto.demo.Model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto.demo.Model.dao.IUsuarioDAO;
import proyecto.demo.Model.entidad.Roles;
import proyecto.demo.Model.entidad.Usuario;

@Service("UserService")
public class UserService implements UserDetailsService{

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByCorreo(correo);
        List<GrantedAuthority> listaRoles = new ArrayList<>();
        for(Roles item : usuario.getRoles()){
            listaRoles.add(new SimpleGrantedAuthority(item.getAuthority()));
        }
        return new User(usuario.getCorreo(), usuario.getPassword(), listaRoles);
    }

    public Usuario encontrarUsuarioPorNombre(String correo){
        return usuarioDAO.findByCorreo(correo);
    }

    public void registrarUsuario(Usuario usuario){
        usuarioDAO.save(usuario);
    }
    
}
