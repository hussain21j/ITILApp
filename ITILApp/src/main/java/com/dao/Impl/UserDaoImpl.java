package com.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory session;
	
	public void addUser(User user) {
		session.getCurrentSession().save(user);
	}

	public void editUser(User user) {
		session.getCurrentSession().update(user);
	}

	public void deleteUser(int userId) {
		session.getCurrentSession().delete(findUser(userId));
	}

	public User findUser(int userId) {
		return (User) session.getCurrentSession().get(User.class, userId);
	}

	public User findUserByName(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));		
		return (User) criteria.uniqueResult();
	}

	public List<User> getAllUsers() {
		return session.getCurrentSession().createQuery("from User").list();
	}
	
	public User getUserRoles(int userId){
		return (User) session.getCurrentSession().get(User.class, userId);
	}

	public User findUser(String userId) {
		return (User) session.getCurrentSession().get(User.class, userId);
	}

	public User getUserDetails(String userId) {
		return (User) session.getCurrentSession().get(User.class, userId) ;
	}
		
}