package mark.fang.platform.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import mark.fang.platform.dao.UserDao;
import mark.fang.platform.domain.User;

@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		 this.getHibernateTemplate().save(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		List<User> list = (List<User>)this.getHibernateTemplate().find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
