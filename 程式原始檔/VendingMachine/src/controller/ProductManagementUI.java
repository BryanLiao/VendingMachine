package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Product;
import service.impl.ProductServiceImpl;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class ProductManagementUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField productId;
	private JTextField name;
	private JTextField price;
	private JTextField stockQty;
	private DefaultTableModel model;
	private static ProductServiceImpl productServiceImpl = new ProductServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductManagementUI frame = new ProductManagementUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductManagementUI() {
		setTitle("產品管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 列名
		String[] columnNames = { "產品編號", "產品名稱", "價格", "庫存量" };

		// 創建 DefaultTableModel
		model = new DefaultTableModel(columnNames, 0);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(117, 91, 460, 154);
		contentPane.add(scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		

		
		
		
		
		JLabel lblNewLabel = new JLabel("產品編號");
		lblNewLabel.setBounds(117, 304, 72, 15);
		contentPane.add(lblNewLabel);
		
		productId = new JTextField();
		productId.setEditable(false);
		productId.setBounds(206, 301, 96, 21);
		contentPane.add(productId);
		productId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("產品名稱");
		lblNewLabel_1.setBounds(312, 304, 72, 15);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(406, 301, 96, 21);
		contentPane.add(name);
		
		JLabel lblNewLabel_2 = new JLabel("價格");
		lblNewLabel_2.setBounds(117, 332, 72, 15);
		contentPane.add(lblNewLabel_2);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(206, 329, 96, 21);
		contentPane.add(price);
		
		JLabel lblNewLabel_1_1 = new JLabel("庫存量");
		lblNewLabel_1_1.setBounds(317, 332, 72, 15);
		contentPane.add(lblNewLabel_1_1);
		
		stockQty = new JTextField();
		stockQty.setColumns(10);
		stockQty.setBounds(406, 329, 96, 21);
		contentPane.add(stockQty);
		
		JLabel lblNewLabel_3 = new JLabel("產品修改");
		lblNewLabel_3.setFont(new Font("細明體", Font.BOLD, 14));
		lblNewLabel_3.setBounds(117, 265, 72, 15);
		contentPane.add(lblNewLabel_3);
		
		// JTable 的鼠標事件處理
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // 獲取選中行的資料並顯示到文字方塊
                    productId.setText(model.getValueAt(selectedRow, 0).toString());
                    name.setText(model.getValueAt(selectedRow, 1).toString());
                    price.setText(model.getValueAt(selectedRow, 2).toString());
                    stockQty.setText(model.getValueAt(selectedRow, 3).toString());
                }
            }
        });
        
        JButton udpate = new JButton("修改");
		udpate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int paraId = Integer.parseInt(productId.getText());
				int paraPrice = Integer.parseInt(price.getText());
				int paraStockQty = Integer.parseInt(stockQty.getText());
				Product product = new Product();
				product.setId(paraId);
				product.setName(name.getText());
				product.setPrice(paraPrice);
				product.setStockQty(paraStockQty);
				productServiceImpl.updateProduct(product);
				queryProduct();
				JOptionPane.showMessageDialog(null, "修改成功");
			}
		});
		udpate.setBounds(117, 376, 87, 23);
		contentPane.add(udpate);
		
		JButton query = new JButton("查詢");
		query.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				queryProduct();
			}
		});
		
		query.setBounds(117, 47, 87, 23);
		contentPane.add(query);
		
		JButton returnManagementUI = new JButton("回後台管理");
		returnManagementUI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagementUI managementUI = new ManagementUI();
				managementUI.setVisible(true);
				dispose();
			}
		});
		returnManagementUI.setBounds(440, 47, 137, 23);
		contentPane.add(returnManagementUI);
	}
	
	private void queryProduct() {
		// 清除原有資料
		model.setRowCount(0);
		// 創建一個 List
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		List<Product> products = productServiceImpl.findAllProducts();

		for (Product p : products) {
			model.addRow(new String[] { String.valueOf(p.getId()), p.getName(), String.valueOf(p.getPrice()),
					String.valueOf(p.getStockQty()) });
		}
	}
	
}
