package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import service.impl.UserServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

public class AddUserUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;
	private JPasswordField confirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserUI frame = new AddUserUI();
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
	public AddUserUI() {
		setTitle("新增後台人員");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManagement = new JLabel("新增後台人員");
		lblManagement.setFont(new Font("新細明體", Font.BOLD, 18));
		lblManagement.setBounds(128, 33, 141, 32);
		contentPane.add(lblManagement);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setBounds(92, 94, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		account = new JTextField();
		account.setColumns(10);
		account.setBounds(151, 91, 118, 21);
		contentPane.add(account);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼");
		lblNewLabel_1_1.setBounds(92, 137, 46, 15);
		contentPane.add(lblNewLabel_1_1);
		
		password = new JPasswordField();
		password.setBounds(151, 134, 118, 21);
		contentPane.add(password);
		
		JButton register = new JButton("註冊");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (new UserServiceImpl().isAccountBeenUsed(account.getText().trim())) {
					JOptionPane.showMessageDialog(null, "帳號已有人使用");
					return;
				} else {
					System.out.println("add");
					String pwd = new String(password.getPassword());
					String confirmPwd = new String(confirmPassword.getPassword());
					
					if (account.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入帳號");
						return;
					}

					if (pwd.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入密碼");
						return;
					}
					
					if (!isValidPassword(pwd)) {
						JOptionPane.showMessageDialog(null, "密碼必須8碼以上且包含英文字母大小寫及數字");
						return;
					}
					
					if (!pwd.equals(confirmPwd)) {
						JOptionPane.showMessageDialog(null, "密碼與確認密碼不相符");
						return;
					}
					
					User user = new User(account.getText(), pwd);
					new UserServiceImpl().addUser(user);
					JOptionPane.showMessageDialog(null, "新增後台人員成功");
					return;
				}
			}
		});
		register.setBounds(182, 227, 87, 23);
		contentPane.add(register);
		
		JButton managementLogin = new JButton("回後台管理");
		managementLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagementLoginUI managementLoginUI = new ManagementLoginUI();
				managementLoginUI.setVisible(true);
				dispose();
			}
		});
		managementLogin.setBounds(43, 227, 106, 23);
		contentPane.add(managementLogin);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("確認密碼");
		lblNewLabel_1_1_1.setBounds(71, 181, 67, 15);
		contentPane.add(lblNewLabel_1_1_1);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(151, 178, 118, 21);
		contentPane.add(confirmPassword);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("密碼必須8碼以上且包含英文字母大小寫及數字");
		lblNewLabel_1_1_2_1_1.setBounds(71, 271, 264, 15);
		contentPane.add(lblNewLabel_1_1_2_1_1);
	}
	
	public boolean isValidPassword(String password) {
		// 正規表示式
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(password).matches();
	}
}
