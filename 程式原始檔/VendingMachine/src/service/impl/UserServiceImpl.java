package service.impl;

import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {

	public static void main(String[] args) {
		//System.out.println(new UserServiceImpl().login("Bryan1", "Aa123456"));
		System.out.println(new UserServiceImpl().isAccountBeenUsed("Bryan4"));
		//User user = new User("aa","Aa123456");
		//new UserServiceImpl().addUser(user);
	}
	
	private static UserDaoImpl userDaoImpl =  new UserDaoImpl();
	
	@Override
	public void addUser(User user) {
		userDaoImpl.add(user);
	}

	@Override
	public User login(String account, String password) {
		User user = userDaoImpl.selectAccountAndPassword(account, password);
		return user;
	}

	@Override
	public boolean isAccountBeenUsed(String account) {
		User user = userDaoImpl.selectByAccount(account);
		return user != null ? true : false;
	}

}
