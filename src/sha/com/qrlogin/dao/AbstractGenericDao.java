package sha.com.qrlogin.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import sha.com.qrlogin.model.IModel;


public abstract class AbstractGenericDao<T extends IModel> implements IGenericDao<T> {
	
    @PersistenceContext
    protected EntityManager em;

    @SuppressWarnings("unchecked")
    protected Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(final long id) {
        try {
            return this.em.find(this.entityClass, id);
        }
        catch(final RuntimeException e) {
            e.printStackTrace();
        }
		return null;
    }

    @Override
    public T getReference(final long id) {
        try {
            return this.em.getReference(this.entityClass, id);
        }
        catch(final RuntimeException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<T> findAll() {
        try {

            @SuppressWarnings("unchecked") final List<T> sonucListesi = (this.em.createQuery("SELECT v FROM " + this.entityClass.getCanonicalName() + " v").getResultList());
            return sonucListesi;
        }
        catch(final RuntimeException e) {
        	e.printStackTrace();
        }
		return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(final T entity) {
        try {
            this.em.persist(entity);
        }
        catch(final RuntimeException e) {
        	e.printStackTrace();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<T> saveAll(final List<T> entityList) {
        try {
            for(int i=0;i<entityList.size(); i++) {
                this.em.persist(entityList.get(i));
                if ( i % 20 == 0 ) {
                    this.em.flush();
                    this.em.clear();
                }
            }
        }
        catch(final RuntimeException e) {
        	e.printStackTrace();
        }
        return entityList;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public T update(final T entity) {
        try {
            return this.em.merge(entity);
        }
        catch(final RuntimeException e) {
        	e.printStackTrace();
        }
		return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<T> updateAll(final List<T> entityList) {
        try {
            List<T> updatedEntityList=new ArrayList<>();
            for(T entity : entityList) {
                updatedEntityList.add(this.em.merge(entity));
            }
            return updatedEntityList;
        }
        catch(final RuntimeException e) {
        	e.printStackTrace();
        }
		return entityList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(final T entity) {
        try {
            this.em.remove(entity);
        }
        catch(final RuntimeException e) {
        	e.printStackTrace();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll(final List<T> entityList) {
        try {
            for(T entity : entityList) {
                this.em.remove(entity);
            }
        }
        catch(final RuntimeException e) {
        	e.printStackTrace();
        }
    }


    @Override
    public Session getSession() {
        return (Session) this.em.getDelegate();
    }

}
