import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonBean implements Serializable {

	private String personID;
	private String fname;
	private String lname;
	private String email;
	private String userPassword;
	private String phoneNo;
	private String roleInSystem;
	private boolean status = false;

	public PersonBean() {
	}

	public PersonBean(String nFname, String nLname, String nemail, String nuserPassword, String nphoneNo,
			String nroleInSystem) {

		this.fname = nFname;
		this.lname = nLname;
		this.email = nemail;
		this.userPassword = nuserPassword;
		this.phoneNo = nphoneNo;
		this.roleInSystem = nroleInSystem;
		this.status = false;
	}

	public void setStatus(boolean v) {

		this.status = v;
	}

	public void setPersonId(String id) {

		this.personID = id;
	}

	public void setFname(String f) {

		this.fname = f;
	}

	public void setLname(String l) {

		this.lname = l;
	}

	public void setEmail(String e) {

		this.email = e;
	}

	public void setUserPassword(String u) {

		this.userPassword = u;
	}

	public void setPhoneNo(String p) {
		this.phoneNo = p;
	}

	public void setRoleInSystem(String r) {

		this.roleInSystem = r;
	}

	public boolean getStatus() {

		return status;
	}

	public String getPersonID() {

		return personID;
	}

	public String getFname() {

		return fname;
	}

	public String getLname() {

		return lname;
	}

	public String getEmail() {

		return email;
	}

	public String getUserPassword() {

		return userPassword;
	}

	public String getPhoneNo() {

		return phoneNo;
	}

	public String getRoleInSystem() {

		return roleInSystem;
	}

	public void changeRole(String id, String role) {
		String query = "UPDATE person SET [roleInSystem] = ? WHERE [personID]=?";
		try {
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, role);
			statement.setString(2, id);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	public void removeUser(String id) {
		String query = "DELETE FROM person WHERE [personID]=?";
		try {
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	public ArrayList<PersonBean> getAllUsers() {
		ArrayList<PersonBean> list = new ArrayList<>();
		int count = 0;
		try {
			String query = "SELECT * FROM person";
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				list.add(new PersonBean());
				list.get(count).setPersonId(result.getString("personID"));
				list.get(count).setFname(result.getString("Fname"));
				list.get(count).setLname(result.getString("Lname"));
				list.get(count).setEmail(result.getString("email"));
				list.get(count).setPhoneNo(result.getString("phoneNo"));
				list.get(count).setUserPassword(result.getString("userPassword"));
				list.get(count).setRoleInSystem(result.getString("roleInSystem"));
				count++;
			}
			result.close();
			statement.close();
			connection.close();
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		return list;
	}

	public void login(String userName, String password) {
		try {
			String query = "SELECT * FROM person Where [email]=? AND [userPassword]=? ";
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				status = true;
				this.setPersonId(result.getString("personID"));
				this.setFname(result.getString("Fname"));
				this.setLname(result.getString("Lname"));
				this.setEmail(result.getString("email"));
				this.setPhoneNo(result.getString("phoneNo"));
				this.setUserPassword(result.getString("userPassword"));
				this.setRoleInSystem(result.getString("roleInSystem"));
			}

			result.close();
			statement.close();
			connection.close();
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}

	}

	public void addUser(String fn, String ln, String em, String pass, String pn) {

		try {
			String query = "INSERT INTO person VALUES (NEWID(), ?, ?, ?, ?, ?, ?)";
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, fn);
			statement.setString(2, ln);
			statement.setString(3, em);
			statement.setString(4, pass);
			statement.setString(5, pn);
			statement.setString(6, "User");

			statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	public boolean exist(String userName) {
		boolean check = false;
		try {
			String query = "SELECT * FROM person Where [email]=? ";
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				result.close();
				statement.close();
				connection.close();
				check = true;
			}

			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		;
		return check;
	}

	public String getNameById(String id) {
		String name = "";
		try {
			String query = "SELECT * FROM person Where [personID]=?";
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				name += result.getString("Fname");
				name += " ";
				name += result.getString("Lname");
			}

			result.close();
			statement.close();
			connection.close();
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		return name;
	}

	// hashing algorithm SHA-224
	public String hashPassword(String needtohash) {
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("SHA-224");
			byte[] messageDigest = messagedigest.digest(needtohash.getBytes());
			BigInteger intNum = new BigInteger(1, messageDigest);
			String hashedText = intNum.toString(16);

			while (hashedText.length() < 32) {
				hashedText = "0" + hashedText;
			}
			return hashedText;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
