package mark.fang.platform.service;

import mark.fang.platform.domain.User;

public interface UserService {

	void regist(User user);

	User login(User user);

}
