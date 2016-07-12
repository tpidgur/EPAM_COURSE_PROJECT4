package DAO;

import java.util.List;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public interface GenericDao<E> {
    void createTable();

    void insert(E e);

    boolean update(E e,int id);

    boolean delete(int id);

    E find(int id);

    List<E> findAll();


}
