package core.conexion.dao;

import core.conexion.vo.Valores;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ValoresDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public ValoresDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Valores instances from the database.
     *
     * @return the list of all Valores instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<Valores> selectAll() {
        List<Valores> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Valores.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> " + list);
        return list;

    }

    /**
     * Select instance of Valores from the database.
     *
     * @param id the instance to be persisted.
     */
    public Valores selectById(int id) {
        Valores person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Valores.selectById", id);
        } finally {
            session.close();
        }
        System.out.println("selectById(" + id + ") --> " + person);
        return person;
    }
    public Valores selectByIdLastDate() {
        Valores person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Valores.selectByIdLastDate");
        } finally {
            session.close();
        }
        System.out.println("selectById --> " + person);
        return person;
    }

    /**
     * Insert an instance of Valores into the database.
     *
     * @param valores the instance to be persisted.
     */
    public int insert(Valores valores) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("Valores.insert", valores);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert(" + valores + ") --> " + valores.getIdvalores());
        return valores.getIdvalores();
    }

    /**
     * Update an instance of Valores into the database.
     *
     * @param valores the instance to be persisted.
     */
    public void update(Valores valores) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Valores.update", valores);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update(" + valores + ") --> updated");
    }

    /**
     * Delete an instance of Valores from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Valores.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete(" + id + ")");

    }
}