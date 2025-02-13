package service;

import model.Member;
import model.User;

public interface UserService {
	//create
	void addUser(User user);
		
	//read
	User login(String account, String password);
	boolean isAccountBeenUsed(String account);
		
	//update
		
	//delete
}
