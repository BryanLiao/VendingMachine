package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import model.User;
import service.impl.MemberServiceImpl;
import service.impl.UserServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagementLoginUI extends JFrame {

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
					ManagementLoginUI frame = new ManagementLoginUI();
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
	public ManagementLoginUI() {
		setTitle("後台登入");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("後台登入");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel.setBounds(164, 31, 111, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setBounds(128, 92, 46, 15);
		contentPane.add(lblNewLabel_1);

		account = new JTextField();
		account.setColumns(10);
		account.setBounds(187, 89, 118, 21);
		contentPane.add(account);

		JLabel lblNewLabel_1_1 = new JLabel("密碼");
		lblNewLabel_1_1.setBounds(128, 135, 46, 15);
		contentPane.add(lblNewLabel_1_1);

		password = new JPasswordField();
		password.setBounds(187, 132, 118, 21);
		contentPane.add(password);

		JButton register = new JButton("註冊");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddUserUI addUserUI = new AddUserUI();
				addUserUI.setVisible(true);
				dispose();
			}
		});
		register.setBounds(81, 198, 87, 23);
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

				User user = new UserServiceImpl().login(account.getText(), pwd);
				if (user != null) {
					ManagementUI managementUI = new ManagementUI();
					managementUI.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "帳號或密碼不正確");
					return;
				}
			}
		});
		login.setBounds(187, 198, 87, 23);
		contentPane.add(login);

		JButton returnLogin = new JButton("回前台登入");
		returnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI loginUI = new LoginUI();
				loginUI.setVisible(true);
				dispose();
			}
		});
		returnLogin.setBounds(293, 198, 111, 23);
		contentPane.add(returnLogin);
	}

}
