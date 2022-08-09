import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DemoDataBase {

	public DemoDataBase() {
	}

	public static String ClearCurrentUserPath(String temp1) {
		char c = '\\';
		String s = "";
		System.out.println(temp1);
		for (int i = 0; i < temp1.length(); i++) {
			if (temp1.charAt(i) == c) {
				s += '/';
			} else {
				s += temp1.charAt(i);
			}
			if (temp1.charAt(i) == ':') {
				s += '/';
			}
			if (temp1.charAt(i) == '>') {
				s = s.substring(0, i + 1);
				s += '/';
				break;
			}
		}
		s += "DataBase.txt";
		return s;
	}

	public String CurrentUserPath() {
		String s = null;
		String[] cmd = new String[] { "resources/path.cmd" };
		ProcessBuilder pb = new ProcessBuilder(cmd);
		try {
			Process p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if (reader.readLine() != null) {
				String temp = ClearCurrentUserPath(reader.readLine().toString());
				return temp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public String getThePath() {
		String s = null;
		String[] cmd = new String[] { "resources/path.cmd" };
		ProcessBuilder pb = new ProcessBuilder(cmd);
		try {
			Process p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if (reader.readLine() != null) {
				String temp = clearThePath(reader.readLine().toString());
				return temp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public String clearThePath(String temp1) {
		char c = '\\';
		String s = "";
		System.out.println(temp1);
		for (int i = 0; i < temp1.length(); i++) {
			if (temp1.charAt(i) == c) {
				s += '/';
			} else {
				s += temp1.charAt(i);
			}
			if (temp1.charAt(i) == ':') {
				s += '/';
			}
			if (temp1.charAt(i) == '>') {
				s = s.substring(0, i + 1);
				s += '/';
				break;
			}
		}
		s += "UpdatedDataBase.txt";
		return s;
	}

	public boolean checkIfItsExists(String Email, String password) throws FileNotFoundException {
		String s = getThePath();
		File file = new File(s);
		if (!file.exists()) {
			File file1 = new File("UpdatedDataBase.txt");
			JOptionPane.showMessageDialog(null, "You Need To Register", "Register Error",
					JOptionPane.INFORMATION_MESSAGE);
			try {
				if (file1.createNewFile()) {
					return false;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		int count = 0;
		Scanner scan = new Scanner(file);
		String email = " ";
		String pass = password;
		String resetCount = "-----------------------";
		while (scan.hasNextLine()) {
			String temp = scan.nextLine();
			if (temp.indexOf(Email) != -1) {
				email = Email;
			}
			if (temp.indexOf(password) != -1 && password.length() == pass.length()) {
				pass = password;
				if (email.equals(Email)) {
					break;
				}
			}
			if (temp.indexOf(resetCount) != -1) {
				count = 0;
			}
			count++;
		}
		if (email.equals("") && pass.equals("")) {
			return false;
		}
		if (email.equals(Email) && pass.equals(password) && count < 7) {
			return true;
		}
		return false;
	}

	public String getTheDate(File f, String Email, String pw) throws FileNotFoundException {
		int count = 0;
		String date = " ";
		String resetCount = "-----------------------";
		String email = " ";
		String pass = " ";
		Scanner scan = new Scanner(f);
		while (scan.hasNextLine()) {
			String temp = scan.nextLine();
			if (temp.indexOf(Email) != -1) {
				email = Email;
			}
			if (temp.indexOf(pw) != -1) {
				pass = pw;
			}
			if (temp.indexOf(resetCount) != -1) {
				count = 0;
			}
			try {
				if (temp.indexOf("Date") != -1) {
					date = temp.substring(6, temp.length());
					if (email != " " && pass != " ") {
						break;
					} else {
						email = " ";
						pass = " ";
					}

				}
			} catch (StringIndexOutOfBoundsException e1) {
				return " ";
			}
			count++;
		}
		if (count < 8 && email.equals(Email) && pass.equals(pw)) {
			return date;
		}
		return " ";
	}

	public void EntireDateBase(String id, String fn, String ln, String Email, int age, String StartYear,
			String password) throws IOException {
		int count = 0;
		try (FileWriter fw = new FileWriter("UpdatedDataBase.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw)) {
			pw.println("-----------------------");
			pw.println("ID: " + id);
			pw.println("First Name: " + fn);
			pw.println("Last Name: " + ln);
			pw.println("Age: " + age);
			pw.println("Email: " + Email);
			pw.println("Password: " + password);
			pw.println("Date: " + StartYear);
			pw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		if (checkIfEmailAlreadyExists(Email)) {
		}
		;
	}

	public void currentUser(String userName, String Password, String date) throws IOException {
		File file = new File("DataBase.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println("ID: " + userName);
		pw.println("Password: " + Password);
		pw.println("Date: " + date);
		pw.close();
	}

	public boolean checkIfEmailAlreadyExists(String Email) throws FileNotFoundException {
		try {
			String s = getThePath();
			File file = new File(s);

			Scanner scan = new Scanner(file);
			String email = " ";
			while (scan.hasNextLine()) {
				String temp = scan.nextLine();
				if (temp.indexOf(Email) != -1) {
					email = Email;
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			File file = new File("UpdatedDataBase.txt");
			e.printStackTrace();
		}
		return false;
	}
}
