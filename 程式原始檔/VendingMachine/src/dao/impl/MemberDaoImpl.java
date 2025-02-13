package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import model.Member;
import util.DbConnection;

public class MemberDaoImpl implements MemberDao {
	
	public static void main(String[] args) {
		//Member member = new Member("Bryan", "br", "Aa123456", "1", "2", "3");
		//Member member = new Member("Kevin", "ke", "Bb123456", "11", "22", "33");
		//Member member = new Member("John", "jo", "Cc123456", "12", "13", "14");
		//new MemberDaoImpl().add(member);
		//List<Member> members=new MemberDaoImpl().selectAll();
		
		
		/*
		for (Member member : members) {
			System.out.println(member.getId() + "\t" + member.getName() + "\t" + member.getAccount() + "\t"
					+ member.getPassword() + "\t" + member.getPhone() +"\t" + member.getMobile() + "\t" + member.getAddress());
		}
		*/
		/*
		Member member = new MemberDaoImpl().selectById(6);
		System.out.println(member.getId() + "\t" + member.getName() + "\t" + member.getAccount() + "\t"
				+ member.getPassword() + "\t" + member.getPhone() +"\t" + member.getMobile() + "\t" + member.getAddress());
		*/
		/*
		Member member = new MemberDaoImpl().selectAccountAndPassword("br", "Aa123456");
		System.out.println(member.getId() + "\t" + member.getName() + "\t" + member.getAccount() + "\t"
				+ member.getPassword() + "\t" + member.getPhone() +"\t" + member.getMobile() + "\t" + member.getAddress());
		*/
		/*
		Member member = new MemberDaoImpl().selectByAccount("br");
		System.out.println(member.getId() + "\t" + member.getName() + "\t" + member.getAccount() + "\t"
				+ member.getPassword() + "\t" + member.getPhone() +"\t" + member.getMobile() + "\t" + member.getAddress());
		*/
		/*
		Member member= new Member("Bryan1", "br1", "Aa6543211", "a", "b", "c");
		member.setId(5);
		new MemberDaoImpl().update(member);
		*/
		//new MemberDaoImpl().delete(7);
	}
	
	private static Connection conn = DbConnection.getDb();
	
	@Override
	public void add(Member member) {
		String sql = "insert into member (name, account, password,  phone, mobile ,address)"
				+ "values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, member.getName());
			preparedStatement.setString(2, member.getAccount());
			preparedStatement.setString(3, member.getPassword());
			preparedStatement.setString(4, member.getPhone());
			preparedStatement.setString(5, member.getMobile());
			preparedStatement.setString(6, member.getAddress());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Member> selectAll() {
		List<Member> members = new ArrayList();
		String sql = "select * from member ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Member member = new Member();
				member.setId(resultSet.getInt("id"));
				member.setName(resultSet.getString("name"));
				member.setAccount(resultSet.getString("account"));
				member.setPassword(resultSet.getString("password"));
				member.setPhone(resultSet.getString("phone"));
				member.setMobile(resultSet.getString("mobile"));
				member.setAddress(resultSet.getString("address"));
				members.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return members;
	}

	@Override
	public Member selectAccountAndPassword(String account, String password) {
		Member member = null;
		String sql = "select * from member where account = ? and password = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setId(resultSet.getInt("id"));
				member.setName(resultSet.getString("name"));
				member.setAccount(resultSet.getString("account"));
				member.setPassword(resultSet.getString("password"));
				member.setAddress(resultSet.getString("address"));
				member.setPhone(resultSet.getString("phone"));
				member.setMobile(resultSet.getString("mobile"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public Member selectById(int id) {
		Member member = null;
		String sql = "select * from member where id = ? ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setId(resultSet.getInt("id"));
				member.setName(resultSet.getString("name"));
				member.setAccount(resultSet.getString("account"));
				member.setPassword(resultSet.getString("password"));
				member.setAddress(resultSet.getString("address"));
				member.setPhone(resultSet.getString("phone"));
				member.setMobile(resultSet.getString("mobile"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public Member selectByAccount(String account) {
		Member member = null;
		String sql = "select * from member where account = ? ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, account);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setId(resultSet.getInt("id"));
				member.setName(resultSet.getString("name"));
				member.setAccount(resultSet.getString("account"));
				member.setPassword(resultSet.getString("password"));
				member.setAddress(resultSet.getString("address"));
				member.setPhone(resultSet.getString("phone"));
				member.setMobile(resultSet.getString("mobile"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public void update(Member member) {
		String sql = "update member set name=?, account = ?, password=?, phone = ?, mobile = ?, address = ? where id = ?";
		try {
			PreparedStatement  preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, member.getName());
			preparedStatement.setString(2, member.getAccount());
			preparedStatement.setString(3, member.getPassword());
			preparedStatement.setString(4, member.getPhone());
			preparedStatement.setString(5, member.getMobile());
			preparedStatement.setString(6, member.getAddress());
			preparedStatement.setInt(7, member.getId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from member where id = ?";
		try {
			PreparedStatement  preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
