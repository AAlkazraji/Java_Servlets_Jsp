import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentsBean implements Serializable {

  private String commentID;
  private String personID;
  private String description;
  private String issueID;
  private String personName;

  public CommentsBean() {
  }

  public void setCommentsID(String id) {
    this.commentID = id;
  }

  public void setPersonId(String pd) {

    personID = pd;
  }

  public void setDescription(String nd) {

    description = nd;
  }

  public void setIssueID(String id) {
    this.issueID = id;
  }

  public void setPersonName(String name) {
    this.personName = name;
  }

  public String getCommentID() {
    return this.commentID;
  }

  public String getPersonId() {
    return personID;
  }

  public String getDescription() {
    return description;
  }

  public String getIssueID() {
    return this.issueID;
  }

  public String getPersonName() {
    return this.personName;
  }

  public void LoadPersonName() {
    PersonBean person = new PersonBean();
    String name = person.getNameById(this.personID);
    this.setPersonName(name);
  }

  public void addComment(String des, String issueID, String personId) {
    try {
      String query = "INSERT INTO Comments VALUES (NEWID(), ?, ?, ?)";
      Connection connection = ConfigBean.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, personId);
      statement.setString(2, des);
      statement.setString(3, issueID);
      statement.executeUpdate();
      statement.close();
      connection.close();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
    }
  }
}