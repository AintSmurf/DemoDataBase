import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textUserName;
	private JPasswordField textPassword;
	protected static String Password = " "; 
	protected static String userName = " ";
	private DemoDataBase d;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		new DemoDataBase();
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 300, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setBounds(199, 11, 97, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUserName = new JLabel("Email");
		lblUserName.setBounds(52, 51, 82, 14);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(52, 110, 82, 14);
		frame.getContentPane().add(lblPassword);
		
		textUserName = new JTextField();
		textUserName.setBounds(227, 48, 158, 20);
		frame.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(227, 107, 158, 20);
		frame.getContentPane().add(textPassword);
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.setPassword(" ");
				Login.setUserName(" ");
				Password = String.valueOf(textPassword.getPassword());;
				userName = textUserName.getText();
				d = new DemoDataBase();	
				try {
					if(d.checkIfItsExists(userName, Password)){
							frame.dispose();
							MainPage m = new MainPage();
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalig Login Details","Login Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException | FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(27, 195, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUserName.setText(null);
				textPassword.setText(null);
			}
		});
		btnReset.setBounds(176, 195, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					frame.dispose();
					RegisterForm r = new RegisterForm();
			}
		});
		btnRegister.setBounds(339, 195, 89, 23);
		frame.getContentPane().add(btnRegister);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 164, 464, 9);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 31, 464, 2);
		frame.getContentPane().add(separator_1);		
	}
	public static String getPassword() {
		return Password;
	}
	public static void setPassword(String password) {
		Password = password;
	}
	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		Login.userName = userName;
	}
	
}
