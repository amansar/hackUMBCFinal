package com.stufin.dao.impl;

import org.springframework.stereotype.Repository;

import com.stufin.dao.UserDao;
import com.stufin.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

}
