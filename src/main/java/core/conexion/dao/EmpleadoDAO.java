package core.conexion.dao;

import core.conexion.vo.Empleado;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class EmpleadoDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public EmpleadoDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Empleado instances from the database.
     *
     * @return the list of all Empleado instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Empleado> selectAll() {
        List<Empleado> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Empleado.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;
    }
    /**
     * Returns the list of all Empleado instances from the database.
     *
     * @return the list of all Empleado instances from the database.
     */
    @SuppressWarnings("unchecked")
    public Empleado selectByEmpleadoContrato(String id) {
        Empleado list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectOne("Empleado.selectByEmpleadoContrato", id);
        } finally {
            session.close();
        }
        System.out.println("selectByEmpleadoContrato() --> " + list);
        return list;
    }
    /**
     * Returns the list of all Empleado instances from the database.
     *
     * @return the list of all Empleado instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Empleado> selectAllEmpleadoContrato() {
        List<Empleado> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Empleado.selectAllEmpleadoContrato");
        } finally {
            session.close();
        }
        System.out.println("selectByEmpleadoContrato() --> " + list);
        return list;
    }

    /**
     * Select instance of Empleado from the database.
     *
     * @param id the instance to be persisted.
     */
    public Empleado selectById(String id) {
        Empleado person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Empleado.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    public Empleado selectByIdUser(int id) {
        Empleado person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Empleado.selectByIdUser", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Insert an instance of Empleado into the database.
     *
     * @param empleado the instance to be persisted.
     */
    public int insertEmpleado(Empleado empleado) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Empleado.insertEmpleado", empleado);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + empleado + ") --> " + empleado.getCedula());
        return id;
    }

    /**
     * Update an instance of Empleado into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public void update(Empleado usuario) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Empleado.update", usuario);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + usuario + ") --> updated");
    }

    public void updateStatusTrabajo(Empleado empleado) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Empleado.updateStatusTrabajo", empleado);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + empleado + ") --> updated");
    }

    public void updateDatosEmpleado(Empleado empleado) {
        int id = -1;
        Empleado person;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Empleado.updateDatosEmpleado", empleado);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + empleado + ") --> updated");
    }

    /**
     * Delete an instance of Empleado from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Empleado.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}