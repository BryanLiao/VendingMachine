package dao;
import java.util.List;

import model.Member;


public interface MemberDao {
		// create
		void add(Member member);

		// read
		List<Member> selectAll();// select * from member

		Member selectAccountAndPassword(String account, String password);// select * from member where account=? and password=?

		Member selectById(int id);

		Member selectByAccount(String account);

		// update
		void update(Member member);

		// delete
		void delete(int id);
}
