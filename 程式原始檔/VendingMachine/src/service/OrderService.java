package service;
import java.util.List;

import model.Order;
import model.OrderDetail;

public interface OrderService {
	// create
	void addOrder(Order order);
	
	void addOrder(Order order, List<OrderDetail> orderDetails);

	// read-->列印報表
	List<Order> findAllOrders();

	Order findLastOrderByMemberId(int member_id); //依會員id取得最後一筆訂單
	
	Order findById(int id);

	

	// update

	// delete
}
