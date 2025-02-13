package dao;
import java.util.List;

import model.OrderDetail;
public interface OrderDetailDao {
	// create
	void add(OrderDetail orderDetail);

	// read
	List<OrderDetail> selectAll();// select * from order_detail

	List<OrderDetail> selectByOrderId(int order_id);// select * from order_detail where order_id = ?
	
	OrderDetail selectById(int id);
	
	// update
	void update(OrderDetail orderDetail);

	// delete
	void delete(int id);
	
}
