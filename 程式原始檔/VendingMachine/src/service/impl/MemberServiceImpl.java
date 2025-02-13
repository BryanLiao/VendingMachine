package service.impl;

import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService {

	public static void main(String[] args) {
		//System.out.println(new MemberServiceImpl().login("Ke", "Bb1234561"));
		//System.out.println(new MemberServiceImpl().isAccountBeenUsed("ke1"));
		//Member member = new Member("John", "jo", "Aa123456", "1", "2", "3");
		//new MemberServiceImpl().addMember(member);
		
	}

	private static MemberDaoImpl memberDaoImpl = new MemberDaoImpl();

	@Override
	public void addMember(Member member) {
		memberDaoImpl.add(member);
	}

	@Override
	public Member login(String account, String password) {

		Member member = memberDaoImpl.selectAccountAndPassword(account, password);
		return member;
	}

	@Override
	public boolean isAccountBeenUsed(String account) {
		Member member = memberDaoImpl.selectByAccount(account);
		return member != null ? true : false;
	}

}
