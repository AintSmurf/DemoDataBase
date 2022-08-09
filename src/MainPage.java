import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainPage extends Login {

	private static boolean ranOnce = false;
	private JFrame frame;
	private JTextField Result;
	private JLabel userMessage;
	private String date;
	private JTextField CurrentUserEmail;
	private File file;
	private String s;
	private Employee emp;
	private String temp;
	private DemoDataBase d;

	public MainPage() {
		date = null;
		if (!ranOnce) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ranOnce = true;
						MainPage window2 = new MainPage();
						window2.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		this.file = null;
		this.s = " ";
		emp = new Employee();
		d = new DemoDataBase();
		this.temp = " ";
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(500, 100, 800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome To Your Profile");
		lblNewLabel.setBounds(286, 11, 194, 30);
		frame.getContentPane().add(lblNewLabel);

		Result = new JTextField();
		Result.setEnabled(false);
		Result.setBounds(332, 109, 232, 20);
		frame.getContentPane().add(Result);
		Result.setColumns(10);
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s = d.getThePath();
				file = new File(s);
				userMessage.setEnabled(true);
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					temp = currentUserId();
					CurrentUserEmail.setText(temp);
					date = d.getTheDate(file, userName, Password);
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
				;
				System.out.println(date);
				if (emp.checkIfHeNeedsTORedo(date)) {
					Result.setText("You need to renew !");
					Result.setForeground(Color.BLACK);
					Result.setBackground(Color.red);
				} else {
					Result.setText("Your'e all good");
					Result.setForeground(Color.BLACK);
					Result.setBackground(Color.green);
				}

			}
		});
		btnShow.setBounds(133, 108, 154, 23);
		frame.getContentPane().add(btnShow);

		userMessage = new JLabel("Proccessing Deatils wait few seconds  user..");
		userMessage.setEnabled(false);
		userMessage.setBounds(165, 49, 255, 20);
		frame.getContentPane().add(userMessage);

		JLabel lblNewLabel_1 = new JLabel("Current User:");
		lblNewLabel_1.setBounds(75, 177, 109, 14);
		frame.getContentPane().add(lblNewLabel_1);

		CurrentUserEmail = new JTextField();
		CurrentUserEmail.setBounds(165, 174, 435, 20);
		frame.getContentPane().add(CurrentUserEmail);
		CurrentUserEmail.setColumns(10);
	}

	public String currentUserId() {
		String s1 = d.CurrentUserPath();

		try {
			File file1 = new File(s1);
			Scanner scan = new Scanner(file1);
			return scan.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return userName;
	}
}
