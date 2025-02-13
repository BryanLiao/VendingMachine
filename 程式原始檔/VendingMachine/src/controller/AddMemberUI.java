package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import service.impl.MemberServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

public class AddMemberUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;
	private JPasswordField confirmPassword;
	private JTextField name;
	private JTextField phone;
	private JTextField mobile;
	private JTextField address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberUI frame = new AddMemberUI();
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
	public AddMemberUI() {
		setTitle("新增會員");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(36, 10, 388, 376);
		contentPane.add(contentPane_1);
		
		JLabel lblManagement = new JLabel("新增會員");
		lblManagement.setFont(new Font("新細明體", Font.BOLD, 18));
		lblManagement.setBounds(128, 33, 141, 32);
		contentPane_1.add(lblManagement);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setBounds(91, 129, 46, 15);
		contentPane_1.add(lblNewLabel_1);
		
		account = new JTextField();
		account.setColumns(10);
		account.setBounds(150, 126, 118, 21);
		contentPane_1.add(account);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼");
		lblNewLabel_1_1.setBounds(91, 171, 46, 15);
		contentPane_1.add(lblNewLabel_1_1);
		
		password = new JPasswordField();
		password.setBounds(150, 168, 118, 21);
		contentPane_1.add(password);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("確認密碼");
		lblNewLabel_1_1_1.setBounds(70, 215, 67, 15);
		contentPane_1.add(lblNewLabel_1_1_1);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(150, 212, 118, 21);
		contentPane_1.add(confirmPassword);
		
		JLabel lblNewLabel_1_2 = new JLabel("姓名");
		lblNewLabel_1_2.setBounds(91, 89, 46, 15);
		contentPane_1.add(lblNewLabel_1_2);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(150, 86, 118, 21);
		contentPane_1.add(name);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("電話");
		lblNewLabel_1_1_2.setBounds(91, 262, 46, 15);
		contentPane_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("行動電話");
		lblNewLabel_1_1_1_1_1.setBounds(70, 297, 67, 15);
		contentPane_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("地址");
		lblNewLabel_1_1_2_1.setBounds(91, 340, 46, 15);
		contentPane_1.add(lblNewLabel_1_1_2_1);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(150, 259, 118, 21);
		contentPane_1.add(phone);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(151, 294, 118, 21);
		contentPane_1.add(mobile);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(150, 337, 228, 21);
		contentPane_1.add(address);
		
		JButton returnLogin = new JButton("回登入畫面");
		returnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI loginUI = new LoginUI();
				loginUI.setVisible(true);
				dispose();
			}
		});
		
		returnLogin.setBounds(104, 396, 106, 23);
		contentPane.add(returnLogin);
		
		JButton register = new JButton("註冊");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (new MemberServiceImpl().isAccountBeenUsed(account.getText())) {
					JOptionPane.showMessageDialog(null, "帳號已有人使用");
					return;
				} else {
					String pwd = new String(password.getPassword());
					String confirmPwd = new String(confirmPassword.getPassword());
					
					if (name.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入姓名");
						return;
					}
					
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
					
					if (phone.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入電話");
						return;
					}
					
					if (mobile.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入行動電話");
						return;
					}
					
					if (address.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "請輸入地址");
						return;
					}
					
					Member member = new Member(name.getText(), account.getText(), pwd, phone.getText(), mobile.getText(), address.getText());
					new MemberServiceImpl().addMember(member);
					JOptionPane.showMessageDialog(null, "新增會員成功");
					return;
				}
			}
		});
		register.setBounds(243, 396, 87, 23);
		contentPane.add(register);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("密碼必須8碼以上且包含英文字母大小寫及數字");
		lblNewLabel_1_1_2_1_1.setBounds(104, 450, 264, 15);
		contentPane.add(lblNewLabel_1_1_2_1_1);
	}
	
	public boolean isValidPassword(String password) {
		// 正規表示式
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(password).matches();
	}
}
