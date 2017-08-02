package core.conexion;

import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;
import sample.hmkcode.dao.PersonDAO;
import sample.hmkcode.vo.Person;

import java.util.List;

public class TextMain {

    public static void main(String args[]) {

        //get jdbcTemplatePersonDAO
        PersonDAO usuarioDAO = new PersonDAO(MyBatisConnection.getSqlSessionFactory());

        //create usuario bean to insert
        Person usuario = new Person();
        usuario.setName("Usuario 1");

        //( 1 ) insert usuario
        //usuarioDAO.insert(usuario);

        //**set name of usuario
        usuario.setName("Usuario 2");
        //** insert another usuario
        int id = usuarioDAO.insert(usuario);

        //( 2 ) select usuarios by id
        usuarioDAO.selectById(id);

        //( 3 ) select all
        List<Person> usuarios = usuarioDAO.selectAll();

        //**set name of all usuarios
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).setName("Usuario Name " + i);
            //( 4 ) update usuario
            usuarioDAO.update(usuarios.get(i));
        }

        //**check update
        usuarios = usuarioDAO.selectAll();

    }
}
