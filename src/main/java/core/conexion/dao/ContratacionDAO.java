package core.conexion.dao;

import core.conexion.vo.Contratacion;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ContratacionDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public ContratacionDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Contratacion instances from the database.
     *
     * @return the list of all Contratacion instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Contratacion> selectAll() {
        List<Contratacion> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Contratacion.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }
    @SuppressWarnings("unchecked")
    public List<Contratacion> selectAllCargo() {
        List<Contratacion> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Contratacion.selectAllCargo");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of Contratacion from the database.
     *
     * @param id the instance to be persisted.
     */
    public Contratacion selectById(int id) {
        Contratacion person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Contratacion.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    public Contratacion selectByForeighKey(String id) {
        Contratacion person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Contratacion.selectByForeighKey", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    public Contratacion selectByCargo(String id) {
        Contratacion person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Contratacion.selectByCargo", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Insert an instance of Contratacion into the database.
     *
     * @param contratacion the instance to be persisted.
     */
    public int insertContratacion(Contratacion contratacion) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Contratacion.insertContratacion", contratacion);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + contratacion + ") --> " + contratacion.getIdContratacion());
        return id;
    }

    /**
     * Update an instance of Contratacion into the database.
     *
     * @param contratacion the instance to be persisted.
     */
    public void update(Contratacion contratacion) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Contratacion.update", contratacion);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + contratacion + ") --> updated");
    }

    /**
     * Delete an instance of Contratacion from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Contratacion.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}