package in.nit.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.nit.dao.IUserDao;
import in.nit.model.User;

@Repository
public class UserDaoImpl implements IUserDao {
	@Autowired
	private HibernateTemplate ht;
	
	@Override
	public Integer saveUser(User user) {
		return (Integer) ht.save(user);
	}
	
	@Override
	public User getUserByEmail(String email) {
		
		User user=ht.execute(new HibernateCallback<User>() {
				@Override
				public User doInHibernate(Session ses)
						throws HibernateException {
					
					String hql=" select u "
							+ " from in.nit.model.User u "
							+ " where u.userEmail=:email ";
					
					return (User)ses.createQuery(hql)
					.setParameter("email", email)
					.uniqueResult();
				}
		});
		
		return user;
	}

}
