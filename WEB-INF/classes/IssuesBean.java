import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssuesBean implements Serializable {

	private String issueId;
	private String personId;
	private String issueState;
	private String category;
	private String subCategory;
	private String title;
	private String description;
	private String reslotionDetails;
	private String dateReported;
	private String timeReported;
	private String dateSolved;
	private ArrayList<CommentsBean> comments = new ArrayList<CommentsBean>();
	private String personName;

	public IssuesBean() {
	}

	public void setIssueId(String id) {
		this.issueId = id;
	}

	public void setPersonId(String id) {
		this.personId = id;
	}

	public void setIssueState(String i) {

		issueState = i;
	}

	public void setDescription(String d) {
		this.description = d;
	}

	public void setCategory(String c) {

		category = c;
	}

	public void setSubCategory(String s) {

		subCategory = s;
	}

	public void setTitle(String t) {

		title = t;
	}

	public void setReslotionDetails(String r) {

		reslotionDetails = r;
	}

	public void setDateReported(String dr) {

		dateReported = dr;
	}

	public void setTimeReported(String tr) {

		timeReported = tr;
	}

	public void setDateSolved(String ds) {

		dateSolved = ds;
	}

	public void setPersonName(String name) {
		this.personName = name;
	}

	public void setComments(ArrayList<CommentsBean> comments) {
		this.comments = comments;
	}

	public String getIssueId() {
		return issueId;
	}

	public String getPersonId() {
		return personId;
	}

	public String getDescription() {
		return description;
	}

	public String getIssueState() {

		return issueState;
	}

	public String getCategory() {

		return category;
	}

	public String getSubCategory() {

		return subCategory;
	}

	public String getTitle() {

		return title;
	}

	public String getReslotionDetails() {

		return reslotionDetails;
	}

	public String getDateReported() {

		return dateReported;
	}

	public String getTimeReported() {

		return timeReported;
	}

	public String getDateSolved() {

		return dateSolved;
	}

	public ArrayList<CommentsBean> getComments() {
		return this.comments;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void LoadPersonName() {
		PersonBean person = new PersonBean();
		String name = person.getNameById(this.personId);
		this.setPersonName(name);
	}

	public void addNewIssue(String personID, String state, String cat, String sub, String title, String time,
			String date, String desc) {
		String query = "INSERT INTO Issues(issueID, personID, issueState, category, subCategory, title, timeReported, dateReported, [description]) ";
		query += "VALUES(NEWID(), ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, personID);
			statement.setString(2, state);
			statement.setString(3, cat);
			statement.setString(4, sub);
			statement.setString(5, title);
			statement.setString(6, time);
			statement.setString(7, date);
			statement.setString(8, desc);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	public void updateState(String state) {
		String query = "UPDATE Issues SET [issueState] = ? WHERE [issueID]=?";
		try {
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			String st = "";
			if (state.equalsIgnoreCase("accept")) {
				st = "resolved";
			} else if (state.equalsIgnoreCase("reject")) {
				st = "in progress";
			} else {
				st = state;
			}
			statement.setString(1, st);
			statement.setString(2, this.issueId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	public void updateResDetails(String details, String date) {
		String query = "UPDATE Issues SET [resolutionDetails] = ?, [dateSolved]=? WHERE [issueID]=?";
		try {
			Connection connection = ConfigBean.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, details);
			statement.setString(2, date);
			statement.setString(3, this.issueId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	public void loadIssue(String id) {
		String query;
		Connection connection;
		PreparedStatement statement;
		ResultSet result;

		try {
			query = "SELECT * FROM Issues WHERE [issueID] = ?";
			connection = ConfigBean.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				this.setIssueId(result.getString("issueID"));
				this.setPersonId(result.getString("personID"));
				this.setIssueState(result.getString("issueState"));
				this.setCategory(result.getString("category"));
				this.setSubCategory(result.getString("subCategory"));
				this.setTitle(result.getString("title"));
				this.setReslotionDetails(result.getString("resolutionDetails"));
				this.setDateReported(result.getString("dateReported"));
				this.setTimeReported(result.getString("timeReported"));
				this.setDateSolved(result.getString("dateSolved"));
				this.setDescription(result.getString("description"));
			}
			this.LoadPersonName();
			this.loadComments();
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	private void loadComments() {
		int i = 0;
		String query;
		Connection connection;
		PreparedStatement statement;
		ResultSet result;
		ArrayList<CommentsBean> tempList = new ArrayList<CommentsBean>();
		try {
			query = "SELECT * FROM Comments WHERE [issueID] = ?";
			connection = ConfigBean.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, this.getIssueId());
			result = statement.executeQuery();
			while (result.next()) {
				tempList.add(new CommentsBean());
				tempList.get(i).setCommentsID(result.getString("commentsId"));
				tempList.get(i).setPersonId(result.getString("personID"));
				tempList.get(i).setDescription(result.getString("description"));
				tempList.get(i).setIssueID(result.getString("issueID"));
				tempList.get(i).LoadPersonName();
				i++;
			}
			this.setComments(tempList);
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

}
