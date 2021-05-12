package in.nit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.dao.IUserDao;
import in.nit.model.User;
import in.nit.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao dao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	@Transactional
	public Integer saveUser(User user) {
		//Read password entered in UI
		String pwd=user.getUserPwd();
		//Encode PWd
		pwd=encoder.encode(pwd);
		//sent back to Model object
		user.setUserPwd(pwd);
		
		return dao.saveUser(user);
	}

}



