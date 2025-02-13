package dao;
import java.util.List;

import model.User;

public interface UserDao {
	// create
	void add(User user);

	// read
	List<User> selectAll();// select * from user

	User selectAccountAndPassword(String account, String password);// select * from user where account=? and password=?

	User selectById(int id);

	User selectByAccount(String account);

	// update
	void update(User user);

	// delete
	void delete(int id);
}
