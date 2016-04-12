package com.stufin.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.stufin.dao.IGenericDao;
import com.stufin.entity.DomainObject;

public abstract class GenericDaoImpl<T extends DomainObject> implements IGenericDao<T>{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addEntity(T entity) {
		Transaction tr = getTransaction();
		sessionFactory.getCurrentSession().save(entity);
		closeTransaction(tr);
	}
	
	@Override
	public T getById(Class c, long id) {
		T entity = (T) c.cast(sessionFactory.getCurrentSession().get(c, id));
		return entity;
	}
	
	@Override
	public T getByColumn(Class c, String column, Object val) {
		Transaction tr = getTransaction();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(c);
		criteria.add(Restrictions.eq(column, val));
		T entity = (T) c.cast(criteria.uniqueResult());
		closeTransaction(tr);
		return entity;
	}
	
	private Transaction getTransaction() {
		return sessionFactory.getCurrentSession().beginTransaction();
	}
	
	private void closeTransaction(Transaction tr) {
		tr.commit();
	}

}
