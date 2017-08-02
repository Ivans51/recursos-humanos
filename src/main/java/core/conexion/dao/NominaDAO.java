package core.conexion.dao;

import core.conexion.vo.Nomina;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class NominaDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public NominaDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Nomina instances from the database.
     *
     * @return the list of all Nomina instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Nomina> selectAll() {
        List<Nomina> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Nomina.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of Nomina from the database.
     *
     * @param id the instance to be persisted.
     */
    public Nomina selectById(int id) {
        Nomina person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Nomina.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Insert an instance of Nomina into the database.
     *
     * @param nomina the instance to be persisted.
     */
    public int insert(Nomina nomina) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Nomina.insert", nomina);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + nomina + ") --> " + nomina.getIdNomina());
        return id;
    }

    /**
     * Update an instance of Nomina into the database.
     *
     * @param nomina the instance to be persisted.
     */
    public void update(Nomina nomina) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Nomina.update", nomina);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + nomina + ") --> updated");
    }

    /**
     * Delete an instance of Nomina from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Nomina.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}