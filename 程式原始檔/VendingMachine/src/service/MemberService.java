package service;

import model.Member;

public interface MemberService {
	//create
	void addMember(Member member);
		
	//read
	Member login(String account, String password);
	boolean isAccountBeenUsed(String account);
		
	//update
		
	//delete
}
