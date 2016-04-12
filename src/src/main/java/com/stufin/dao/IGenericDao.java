package com.stufin.dao;

import com.stufin.entity.DomainObject;

public interface IGenericDao<T extends DomainObject> {
	
	public void addEntity(T entity);
	
	public T getById(Class c, long id);
	
	public T getByColumn(Class c, String column, Object val);

}
