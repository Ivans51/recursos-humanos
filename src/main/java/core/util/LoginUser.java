package core.util;

import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;

public class LoginUser {

    public LoginUser() {

    }

    public Usuario iniciarSession(String nombreUsuario, String clave) throws Myexception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setClave(clave);
        return usuarioDAO.login(usuario);
    }
}