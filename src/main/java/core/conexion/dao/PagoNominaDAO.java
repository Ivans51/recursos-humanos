package core.conexion.dao;

import core.conexion.vo.PagoNomina;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PagoNominaDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public PagoNominaDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all PagoNomina instances from the database.
     *
     * @return the list of all PagoNomina instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<PagoNomina> selectAll() {
        List<PagoNomina> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("PagoNomina.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of PagoNomina from the database.
     *
     * @param id the instance to be persisted.
     */
    public PagoNomina selectById(int id) {
        PagoNomina person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("PagoNomina.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }/**
     * Select instance of PagoNomina from the database.
     *
     * @param id the instance to be persisted.
     */
    public PagoNomina selectForeighKey(String id) {
        PagoNomina person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("PagoNomina.selectForeighKey", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Insert an instance of PagoNomina into the database.
     *
     * @param nomina the instance to be persisted.
     */
    public int insert(PagoNomina nomina) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("PagoNomina.insert", nomina);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + nomina + ") --> " + nomina.getIdNominaPago());
        return id;
    }

    /**
     * Update an instance of PagoNomina into the database.
     *
     * @param nomina the instance to be persisted.
     */
    public void update(PagoNomina nomina) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("PagoNomina.update", nomina);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + nomina + ") --> updated");
    }
    public void updateDeduciones(PagoNomina nomina) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("PagoNomina.updateDeduciones", nomina);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + nomina + ") --> updated");
    }
    public void updateAsignaciones(PagoNomina nomina) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("PagoNomina.updateAsignaciones", nomina);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + nomina + ") --> updated");
    }

    /**
     * Delete an instance of PagoNomina from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("PagoNomina.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}