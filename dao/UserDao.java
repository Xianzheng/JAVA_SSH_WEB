package mark.fang.platform.dao;

import mark.fang.platform.domain.User;

public interface UserDao {

	void regist(User user);

	User login(User user);

}
