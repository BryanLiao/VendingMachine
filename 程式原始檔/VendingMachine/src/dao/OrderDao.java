package dao;

import java.util.List;
import model.Order;
import model.Product;

public interface OrderDao {
	// create
	void add(Order order);

	// read
	List<Order> selectAll();// select * from orders

	Order selectById(int id);
	
	Order selectLastOrderByMemberId(int member_id); //依會員id取得最後一筆訂單
	
	// update
	void update(Order order);

	// delete
	void delete(int id);
}
