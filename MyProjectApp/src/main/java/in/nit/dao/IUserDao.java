package in.nit.dao;

import in.nit.model.User;

public interface IUserDao {

	public Integer saveUser(User user);
	public User getUserByEmail(String email);
}
