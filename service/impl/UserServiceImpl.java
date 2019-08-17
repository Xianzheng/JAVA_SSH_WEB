package mark.fang.platform.service.impl;

import mark.fang.platform.dao.UserDao;
import mark.fang.platform.domain.User;
import mark.fang.platform.service.UserService;

public class UserServiceImpl implements UserService {
	//for spring injection
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		userDao.regist(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
	
}
