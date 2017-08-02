package core.conexion.dao;

import core.conexion.vo.Capacitacion;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CapacitacionDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public CapacitacionDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Capacitacion instances from the database.
     *
     * @return the list of all Capacitacion instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Capacitacion> selectAll() {
        List<Capacitacion> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Capacitacion.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of Capacitacion from the database.
     *
     * @param id the instance to be persisted.
     */
    public Capacitacion selectById(int id) {
        Capacitacion person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Capacitacion.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Insert an instance of Capacitacion into the database.
     *
     * @param capacitacion the instance to be persisted.
     */
    public int insert(Capacitacion capacitacion) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Capacitacion.insert", capacitacion);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + capacitacion + ") --> " + capacitacion.getIdCapacitacion());
        return id;
    }

    /**
     * Update an instance of Capacitacion into the database.
     *
     * @param capacitacion the instance to be persisted.
     */
    public void update(Capacitacion capacitacion) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Capacitacion.update", capacitacion);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + capacitacion + ") --> updated");
    }

    /**
     * Delete an instance of Capacitacion from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Capacitacion.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}