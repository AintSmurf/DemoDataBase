import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Employee {

	private String ID;
	private String FirstName;
	private String LastName;
	private String Email;
	private int age;
	private String StartYear;
	private String password;

	public Employee() {
	}

	public Employee(String id, String fn, String ln, String Email, int age, String StartYear,String password) {
		this.ID = id;
		this.FirstName = fn;
		this.LastName = ln;
		this.Email = Email;
		this.age = age;
		this.StartYear = StartYear;
		this.password = password;
	}

	public Employee(Employee e) {
		this.ID = e.getID();
		this.FirstName = e.getFirstName();
		this.LastName = e.getLastName();
		this.Email = e.getEmail();
		this.age = e.getAge();
		this.StartYear = e.getStartYear();
	}

	public boolean checkIfHeNeedsTORedo(String y) {
		int a[] = new int[3];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String s = dtf.format(now);
		int month = now.getMonthValue();
		int year = now.getYear();
		a[0] = year;
		a[1] = month;
		if(splitDate(y, a)){
			return false;
		}
		return true;
	}

	public boolean splitDate(String s, int a[]) {
		int month = 0, year = 0;
		for (int i = 0; i < s.length() - 2; i++) {
			if (s.charAt(i) == '/') {
				month = Integer.parseInt((s.substring(i + 1, i + 3)));
				year = Integer.parseInt((s.substring(i + 4, s.length())));
				break;
			}
		}
		if (a[0] - year > 1 || a[1] - month > 6) {
				return false;
		}
		return true;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStartYear() {
		return StartYear;
	}

	public void setStartYear(String startYear) {
		StartYear = startYear;
	}

}
