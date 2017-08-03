package core.consultas;

import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;
import core.util.Myexception;

public class LoginUser {

    public LoginUser() {

    }

    public Usuario iniciarSession(String nombreUsuario, String clave) throws Myexception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setClave(clave);
        usuario = usuarioDAO.login(usuario);
        if (usuario == null)
            throw new Myexception("No existe el usuario");
        else
            return usuario;
    }
}