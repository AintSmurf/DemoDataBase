import java.awt.EventQueue;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RegisterForm {
	private static boolean ranOnce = false;
	private static boolean flag = true;
	private DemoDataBase d;
	private JFrame frame;
	private JTextField nameText;
	private JTextField emailText;
	private JTextField ageText;
	private JTextField startyearText;
	private JPasswordField passwordField;
	private JTextField lastText;
	private String id;
	private String ln;
	private String fn;
	private String startYear;
	private int age;
	private String email;
	private String Password;
	private UUID uuid ;

	public RegisterForm() {
		if (!ranOnce) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ranOnce = true;
						RegisterForm window = new RegisterForm();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		this.uuid= null;
		this.d = new DemoDataBase();
		this.id = " ";
		this.ln = " ";
		this.fn = " ";
		this.startYear = " ";
		this.age = 0;
		this.email = " ";
		this.Password = " ";
		initialize();

	}

	private void initialize() {
		Random rand = new Random();
		frame = new JFrame();
		frame.setBounds(500, 300, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnApply = new JButton("Apply");
		btnApply.setEnabled(false);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Password = String.valueOf(passwordField.getPassword());
				email = emailText.getText();
				age = Integer.parseInt(ageText.getText());
				startYear = startyearText.getText();
				fn = nameText.getText();
				ln = lastText.getText();
				UUID uuid = UUID.randomUUID();
				id = uuid.toString();;
				try {
					d.EntireDateBase(id, fn, ln, email, age, startYear, Password);
					d.currentUser(id, Password, startYear);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
				Login.main(null);
			}
		});
		btnApply.setBounds(54, 202, 89, 23);
		frame.getContentPane().add(btnApply);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emailText.setText(null);
				ageText.setText(null);
				startyearText.setText(null);
				passwordField.setText(null);
				nameText.setText(null);
				lastText.setText(null);
			}
		});
		btnReset.setBounds(363, 202, 89, 23);
		frame.getContentPane().add(btnReset);

		JLabel lblNewLabel = new JLabel("FirstName");
		lblNewLabel.setBounds(30, 11, 89, 14);
		frame.getContentPane().add(lblNewLabel);

		nameText = new JTextField();
		nameText.setBounds(198, 8, 122, 20);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(30, 72, 89, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(30, 150, 89, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Age");
		lblNewLabel_3.setBounds(30, 97, 89, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("StartYear");
		lblNewLabel_4.setBounds(30, 122, 89, 14);
		frame.getContentPane().add(lblNewLabel_4);

		emailText = new JTextField();
		emailText.setBounds(198, 147, 122, 20);
		frame.getContentPane().add(emailText);
		emailText.setColumns(10);

		ageText = new JTextField();
		ageText.setBounds(198, 97, 122, 20);
		frame.getContentPane().add(ageText);
		ageText.setColumns(10);

		startyearText = new JTextField();
		startyearText.setText("dd/MM/yyyy");
		startyearText.setBounds(198, 122, 122, 20);
		frame.getContentPane().add(startyearText);
		startyearText.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(198, 69, 122, 20);
		frame.getContentPane().add(passwordField);

		JLabel lblNewLabel_5 = new JLabel("LastName");
		lblNewLabel_5.setBounds(30, 47, 89, 14);
		frame.getContentPane().add(lblNewLabel_5);

		lastText = new JTextField();
		lastText.setBounds(198, 39, 122, 20);
		frame.getContentPane().add(lastText);
		lastText.setColumns(10);

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				Password = String.valueOf(passwordField.getPassword());
				email = emailText.getText();
				age = Integer.parseInt(ageText.getText());
				startYear = startyearText.getText();
				fn = nameText.getText();
				ln = lastText.getText();
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, "you missed a Field", "Error", JOptionPane.ERROR_MESSAGE);
			}
				while (flag) {
					flag = security(email, age, startYear, Password, fn, ln);
					RegisterForm r = new RegisterForm();
					break;
				}
				if(flag == false) {
				btnApply.setEnabled(true);
				}
			}
		});
		btnNewButton.setBounds(178, 202, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

	public boolean security(String email, int age, String startYear, String password, String fn, String ln) {
		DemoDataBase d = new DemoDataBase();
		String temp= " ";
		String s = d.getThePath();
		File file = new File(s);
		if(!file.exists()) {
		File file1 = new File("UpdatedDataBase.txt");
		JOptionPane.showMessageDialog(null, "Please Click again on Confirm", "Processing Details", JOptionPane.INFORMATION_MESSAGE);
		try {
			if(file1.createNewFile()){
				return true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		if (email == null || ((age <= 0) && age < 18) || startYear == null || password == null || fn == null
				|| ln == null) {
			JOptionPane.showMessageDialog(null, "Age must be over 18, you missed a Field", "Error", JOptionPane.ERROR_MESSAGE);
			this.emailText.setText(null);
			this.ageText.setText(null);
			this.startyearText.setText(null);
			this.passwordField.setText(null);
			this.nameText.setText(null);
			this.lastText.setText(null);
			return true;
		}
		if(startYear.length() != 10){
			try {
				temp = d.getTheDate(file, email, password);
				if(temp.equals(" ")){
					JOptionPane.showMessageDialog(null, "Enter the 'StartYear' in format: dd/MM/yyyy", "Syntax ERROR", JOptionPane.ERROR_MESSAGE);
					return true;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			if (d.checkIfEmailAlreadyExists(email)) {
				JOptionPane.showMessageDialog(null, "Email Already exists", "Login Error", JOptionPane.ERROR_MESSAGE);
				this.emailText.setText(null);
				this.ageText.setText(null);
				this.startyearText.setText(null);
				this.passwordField.setText(null);
				this.nameText.setText(null);
				this.lastText.setText(null);
				return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;

	}
}
