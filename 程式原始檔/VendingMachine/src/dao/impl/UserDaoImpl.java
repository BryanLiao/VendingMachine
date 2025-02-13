package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import model.User;
import util.DbConnection;

public class UserDaoImpl implements UserDao {

	public static void main(String[] args) {
		//User user = new User("Bryan", "Aa654321");
		//User user = new User("Kevin", "Aa123456");
		//User user = new User("John", "Aa1234567");
		//new UserDaoImpl().add(user);
		/*
		List<User> users= new UserDaoImpl().selectAll();
		for (User user : users) {
			System.out.println(user.getId() + "\t" + user.getAccount() + "\t" + user.getPassword());
		}
		*/
		/*
		User user= new UserDaoImpl().selectAccountAndPassword("Bryan", "Aa654321");
		System.out.println(user.getId() + "\t" + user.getAccount() + "\t" + user.getPassword());
		*/
		/*
		User user= new UserDaoImpl().selectById(2);
		System.out.println(user.getId() + "\t" + user.getAccount() + "\t" + user.getPassword());
		*/
		/*
		User user= new UserDaoImpl().selectByAccount("John");
		System.out.println(user.getId() + "\t" + user.getAccount() + "\t" + user.getPassword());
		*/
		/*
		User user = new User();
		user.setId(1);
		user.setAccount("Bryan1");
		user.setPassword("Aa123456");
		new UserDaoImpl().update(user);
		*/
		//new UserDaoImpl().delete(3);
	}
	
	private static Connection conn = DbConnection.getDb();
	
	@Override
	public void add(User user) {
		String sql = "insert into User (account, password)"
				+ "values (?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getAccount());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> selectAll() {
		List<User> users = new ArrayList();
		String sql = "select * from user ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setAccount(resultSet.getString("account"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public User selectAccountAndPassword(String account, String password) {
		User user = null;
		String sql = "select * from user where account = ? and password = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setAccount(resultSet.getString("account"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User selectById(int id) {
		User user = null;
		String sql = "select * from user where id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setAccount(resultSet.getString("account"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User selectByAccount(String account) {
		User user = null;
		String sql = "select * from user where account = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, account);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setAccount(resultSet.getString("account"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void update(User user) {
		String sql = "update user set account= ?, password= ? where id = ?";
		try {
			PreparedStatement  preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getAccount());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from user where id = ?";
		try {
			PreparedStatement  preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
