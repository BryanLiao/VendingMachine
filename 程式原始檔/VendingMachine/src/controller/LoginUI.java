package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import service.impl.MemberServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setTitle("前台登入");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("前台登入");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel.setBounds(166, 20, 111, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setBounds(130, 81, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼");
		lblNewLabel_1_1.setBounds(130, 124, 46, 15);
		contentPane.add(lblNewLabel_1_1);
		
		account = new JTextField();
		account.setBounds(189, 78, 118, 21);
		contentPane.add(account);
		account.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(189, 121, 118, 21);
		contentPane.add(password);
		
		JButton register = new JButton("註冊");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMemberUI addMemberUI = new  AddMemberUI();
				addMemberUI.setVisible(true);
				dispose();
			}
		});
		register.setBounds(74, 187, 87, 23);
		contentPane.add(register);
		
		JButton login = new JButton("登入");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pwd = new String(password.getPassword());
				if (account.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "請輸入帳號");
					return;
				}

				if (pwd.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "請輸入密碼");
					return;
				}
				
				Member member = new MemberServiceImpl().login(account.getText(), pwd);
				if(member!=null)
				{
					Tool.save(member, "member.txt");
					VendingMachineUI vendingMachineUI=new VendingMachineUI();
					vendingMachineUI.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "帳號或密碼不正確");
					return;
				}
				
				
			}
		});
		login.setBounds(189, 187, 87, 23);
		contentPane.add(login);
		
		JButton managementLogin = new JButton("後台管理");
		managementLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagementLoginUI managementLoginUI = new ManagementLoginUI();
				managementLoginUI.setVisible(true);
				dispose();
			}
		});
		managementLogin.setBounds(301, 187, 87, 23);
		contentPane.add(managementLogin);
		
		
	}
}
