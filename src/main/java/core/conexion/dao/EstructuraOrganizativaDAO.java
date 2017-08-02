package core.conexion.dao;

import core.conexion.vo.EstructuraOrganizativa;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class EstructuraOrganizativaDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public EstructuraOrganizativaDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all EstructuraOrganizativa instances from the database.
     *
     * @return the list of all EstructuraOrganizativa instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<EstructuraOrganizativa> selectAll() {
        List<EstructuraOrganizativa> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("EstructuraOrganizativa.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of EstructuraOrganizativa from the database.
     *
     * @param id the instance to be persisted.
     */
    public EstructuraOrganizativa selectById(int id) {
        EstructuraOrganizativa person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("EstructuraOrganizativa.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Insert an instance of EstructuraOrganizativa into the database.
     *
     * @param estructuraOrganizativa the instance to be persisted.
     */
    public int insert(EstructuraOrganizativa estructuraOrganizativa) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("EstructuraOrganizativa.insert", estructuraOrganizativa);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + estructuraOrganizativa + ") --> " + estructuraOrganizativa.getIdEstructura());
        return id;
    }

    /**
     * Update an instance of EstructuraOrganizativa into the database.
     *
     * @param estructuraOrganizativa the instance to be persisted.
     */
    public void update(EstructuraOrganizativa estructuraOrganizativa) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("EstructuraOrganizativa.update", estructuraOrganizativa);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + estructuraOrganizativa + ") --> updated");
    }

    /**
     * Delete an instance of EstructuraOrganizativa from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("EstructuraOrganizativa.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}