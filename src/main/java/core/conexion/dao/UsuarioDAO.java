package core.conexion.dao;

import core.conexion.vo.Usuario;
import core.util.Myexception;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UsuarioDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public UsuarioDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Usuario instances from the database.
     *
     * @return the list of all Usuario instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Usuario> selectAll() {
        List<Usuario> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Usuario.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of Usuario from the database.
     *
     * @param id the instance to be persisted.
     */
    public Usuario selectById(int id) {
        Usuario person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Usuario.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Select instance of Usuario from the database.
     *
     * @param usuario the instance to be persisted.
     */
    public Usuario login(Usuario usuario) throws Myexception {
        Usuario person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Usuario.login", usuario);
        } finally {
            session.close();
        }
        System.out.println("login(" + usuario.getNombreUsuario() + usuario.getClave());
        if (person == null)
            throw new Myexception("No se encuentra");
        else
            return person;
    }


    /**
     * Select instance of Usuario from the database.
     *
     * @param usuario the instance to be persisted.
     */
    public Usuario nombreUsuario(Usuario usuario) throws Myexception {
        Usuario person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Usuario.nombreUsurio", usuario);
        } finally {
            session.close();
        }
        System.out.println("login(" + usuario.getNombreUsuario());
        if (person == null)
            throw new Myexception("No se encuentra");
        else
            return person;
    }

    /**
     * Insert an instance of Usuario into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public int inserUsuario(Usuario usuario) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.insert("Usuario.inserUsuario", usuario);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + usuario + ") --> " + usuario.getIdUsuario());
        return usuario.getIdUsuario();
    }

    /**
     * Update an instance of Usuario into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public void updatePreguntas(Usuario usuario) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("Usuario.updatePreguntas", usuario);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + usuario + ") --> updated");
    }

    /**
     * Update an instance of Usuario into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public void updateClave(Usuario usuario) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("Usuario.updateClave", usuario);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + usuario + ") --> updated");
    }
    /**
     * Update an instance of Usuario into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public void updateDatosUsuarios(Usuario usuario) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("Usuario.updateDatosUsuarios", usuario);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + usuario + ") --> updated");
    }
    /**
     * Update an instance of Usuario into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public void updateStatus(Usuario usuario) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("Usuario.updateStatus", usuario);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + usuario + ") --> updated");
    }

    /**
     * Delete an instance of Usuario from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Usuario.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}