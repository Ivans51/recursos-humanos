package core.conexion.dao;

import core.conexion.vo.Auditoria;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AuditoriaDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public AuditoriaDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Auditoria instances from the database.
     *
     * @return the list of all Auditoria instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Auditoria> selectAll() {
        List<Auditoria> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Auditoria.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of Auditoria from the database.
     *
     * @param id the instance to be persisted.
     */
    public Auditoria selectById(int id) {
        Auditoria person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Auditoria.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }

    /**
     * Insert an instance of Auditoria into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public int insert(Auditoria usuario) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Auditoria.insert", usuario);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + usuario + ") --> " + usuario.getIdAuditoria());
        return id;
    }

    /**
     * Update an instance of Auditoria into the database.
     *
     * @param usuario the instance to be persisted.
     */
    public void update(Auditoria usuario) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Auditoria.update", usuario);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + usuario + ") --> updated");
    }

    /**
     * Delete an instance of Auditoria from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Auditoria.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}