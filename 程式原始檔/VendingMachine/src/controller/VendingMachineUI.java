package controller;
import javax.swing.*;

import model.Member;
import model.Order;
import model.OrderDetail;
import model.Product;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import util.Tool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.Timer;

public class VendingMachineUI extends JFrame {
    /**
	 * 自動販賣機
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Product> inventory;
    private JTextField amountField;
    private JTextArea salesArea;
    private JTextField inputField;
    private double totalInserted = 0.0;
    private static Member member=(Member)Tool.read("member.txt");
    
    public VendingMachineUI() {
        setTitle("自動販賣機");
        setSize(1100, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inventory = new HashMap<>();
        initializeInventory();

        amountField = new JTextField("已投入金額: 0元");
        amountField.setBounds(20, 20, 540, 30);
        amountField.setEditable(false);
        add(amountField);

        inputField = new JTextField();
        inputField.setBounds(20, 60, 200, 30);
        add(inputField);

        JButton insertButton = new JButton("投入");
        insertButton.setBounds(230, 60, 100, 30);
        insertButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(inputField.getText());
                totalInserted += amount;
                amountField.setText("已投入金額: " + totalInserted + "元");
                updateButtonColors();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "請輸入有效的金額");
            }
        });
        add(insertButton);

        createButtons();

        salesArea = new JTextArea();
        salesArea.setBounds(20, 300, 540, 200);
        salesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(salesArea);
        scrollPane.setBounds(20, 300, 540, 200);
        add(scrollPane);

        JButton changeButton = new JButton("找零");
        changeButton.setBounds(20, 260, 100, 30);
        changeButton.addActionListener(e -> showChange());
        add(changeButton);

        JButton printButton = new JButton("列印");
        printButton.setBounds(140, 260, 100, 30);
        printButton.addActionListener(e -> print());
        add(printButton);
        
        JButton exportButton = new JButton("匯出");
        exportButton.setBounds(260, 260, 100, 30);
        exportButton.addActionListener(e -> exportToCsv());
        add(exportButton);
        
        JButton exitButton = new JButton("離開");
        exitButton.setBounds(380, 260, 100, 30);
        exitButton.addActionListener(e -> exit());
        add(exitButton);
       
        JLabel userGreetingMessage = new JLabel(member.getName()+"  歡迎你!");
        userGreetingMessage.setForeground(Color.YELLOW);
        userGreetingMessage.setFont(new Font("新細明體", Font.BOLD, 20));
        userGreetingMessage.setBounds(651, 20, 197, 23);
		add(userGreetingMessage);
        
        
        JLabel currentDateTimeText = new JLabel("");
		currentDateTimeText.setForeground(new Color(238, 119, 0));
		currentDateTimeText.setFont(new Font("新細明體", Font.BOLD, 20));
		currentDateTimeText.setBounds(851, 20, 197, 23);
		add(currentDateTimeText);
		// 第一次設定
 		currentDateTimeText.setText(getCurrentDateTime());
 		
 		// 每秒更新目前日期時間
 		Timer timer = new Timer(1000, new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				currentDateTimeText.setText(getCurrentDateTime());
 			}
 		});
 		timer.start();
 		
 		Image bgImage = new ImageIcon(getClass().getResource("/images/bg.png")).getImage();

		JLabel bgLabel = new JLabel("");
		//bgLabel.setBackground(new Color(0, 0, 0, 0));
		bgLabel.setBounds(0, 0, 1360, 768);
		bgLabel.setIcon(new ImageIcon(bgImage));
		add(bgLabel);
    }
    
    /**
	 * 回傳目前日期時間
	 */
	private String getCurrentDateTime() {
		String currentDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		return currentDateTime;
	}

    private void initializeInventory() {
    	ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		List<Product> products = productServiceImpl.findAllProducts();

		for (Product p : products) {
			inventory.put(String.valueOf(p.getId()), new Product(p.getId(), p.getName(), p.getPrice(), p.getStockQty()));
		}
	}

    private void createButtons() {
        int x = 20, y = 100;
        int count = 0;

        for (String id : inventory.keySet()) {
            JButton button = new JButton(inventory.get(id).getName() + " - " + inventory.get(id).getPrice() + "元");
            button.setBounds(x, y, 200, 30);
            button.setBackground(Color.LIGHT_GRAY);
            button.addActionListener(new ProductButtonListener(id));
            button.setName(id);
            add(button);
            count++;

            if (count % 5 == 0) { // 每列 5 個商品
                x = 20;
                y += 40;
            } else {
                x += 210; // 每個按鈕寬度 + 間距
            }
        }
    }

    private void updateButtonColors() {
        for (String name : inventory.keySet()) {
        	System.out.println(name);
            JButton button = getButtonByName(name);
            if (button != null) {
            	Product product = inventory.get(name);
            	System.out.println(product.getId());
                
            	if (product.getStockQty() > 0 && totalInserted >= product.getPrice()) {
                    button.setBackground(Color.YELLOW);
                } else {
                    button.setBackground(product.getStockQty() > 0 ? Color.LIGHT_GRAY : Color.GRAY);
                }
            }
        }
    }

    private JButton getButtonByName(String name) {
        for (Component comp : getContentPane().getComponents()) {
            //System.out.println("comp:" + comp.getName());
            if (comp instanceof JButton && ((JButton) comp).getName() == name) {
                return (JButton) comp;
            }
            
        }
        return null;
    }

    private class ProductButtonListener implements ActionListener {
        private String productId;

        public ProductButtonListener(String productId) {
            this.productId = productId;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = inventory.get(productId);
            if (product.getStockQty() > 0 && totalInserted >= product.getPrice()) {
                product.setStockQty(product.getStockQty() - 1);
                // 開始設定訂單
                Order order = new Order();
                order.setMemberId(member.getId());
                int orderId = 0; //先設定為0,之後OrderServiceImpl會依據memberId會取得正確的訂單編號
                // 開始設定訂單明細
                List<OrderDetail> orderDetails = new ArrayList();
                OrderDetail orderDetail = new OrderDetail(orderId, product.getId() , product.getName(), product.getPrice(), 1);
                orderDetails.add(orderDetail);
                
                // 寫入訂單
                new OrderServiceImpl().addOrder(order, orderDetails);
                
                // 依購買量更新庫存
                new ProductServiceImpl().updateQtyById(product.getId(), 1);
                totalInserted -= product.getPrice();
                amountField.setText("已投入金額: " + totalInserted + "元");
                salesArea.append("購買: " + product.getName() + "，價格: " + product.getPrice() + "元，剩餘庫存: " + product.getStockQty() + "\n");
                updateButtonColors();
            }
        }
    }

    private void showChange() {
        int change = (int) totalInserted;
        StringBuilder changeMessage = new StringBuilder("找零金額: " + change + "元\n");
        int[] coins = {1000, 500, 100, 50, 10, 5, 1};
        for (int coin : coins) {
            int count = change / coin;
            if (count > 0) {
                changeMessage.append(coin + "元 x " + count + "\n");
                change -= coin * count;
            }
        }
        JOptionPane.showMessageDialog(this, changeMessage.toString());
        totalInserted = 0;
        amountField.setText("已投入金額: 0元");
        updateButtonColors();
    }
    
    private void print() {
    	try {
    		salesArea.print();;
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
    	
    }
    
    private void exit() {
		System.exit(0);
	}
    
    private void exportToCsv() {
    	String fileName = "output.csv";
        // UTF-8 BOM 的字元
        byte[] bom = new byte[] {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        try (FileOutputStream fos = new FileOutputStream(fileName);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(osw);) {
        	// 寫入 UTF-8 BOM(避免亂碼)
        	fos.write(bom);
        	String text = salesArea.getText();
        	String[] lines = text.split("\n");
            for (String line : lines) {
            	bufferedWriter.write(line);
            	bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            JOptionPane.showMessageDialog(null, "output.csv文件已成功匯出，稍候將開啟Excel檢視！");
            // 開啟 CSV 文件
            Desktop.getDesktop().open(new File(fileName));
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "寫入檔案發生錯誤");
        	//e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VendingMachineUI machine = new VendingMachineUI();
            machine.setVisible(true);
        });
    }
}
