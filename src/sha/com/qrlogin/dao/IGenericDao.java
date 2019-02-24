package sha.com.qrlogin.dao;

import java.util.List;

import org.hibernate.Session;

import sha.com.qrlogin.model.IModel;

public interface IGenericDao<T extends IModel> {

    T find(long id);

    List<T> findAll();

    void save(T entity);

    List<T> saveAll(List<T> entityList);

    T update(T entity);

    List<T> updateAll(List<T> entityList);

    void delete(T varlik);

    void deleteAll(List<T> varlikListesi);

    Session getSession();

    T getReference(long id);
}
