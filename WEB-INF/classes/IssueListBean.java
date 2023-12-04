import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssueListBean implements Serializable {
    private ArrayList<IssuesBean> issues = new ArrayList<IssuesBean>();

    public IssueListBean() {

    }

    public void setIssues(ArrayList<IssuesBean> issue) {
        this.issues = issue;
    }

    public ArrayList<IssuesBean> getAllIssues() {
        return issues;
    }

    public ArrayList<IssuesBean> getKIssues() {
        loadKIssues();
        return issues;
    }

    public void getIssuesByUser(String role, String id) {
        int i = 0;
        String query;
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList<IssuesBean> tempList = new ArrayList<IssuesBean>();
        try {
            if (role.equalsIgnoreCase("Admin")) {
                query = "SELECT * FROM Issues WHERE [issueState] = 'New' OR [issueState] = 'in progress'";
                connection = ConfigBean.getConnection();
                statement = connection.prepareStatement(query);
                result = statement.executeQuery();
            } else {
                query = "SELECT * FROM Issues WHERE [issueState] = 'completed' AND [personID] = ? ";
                connection = ConfigBean.getConnection();
                statement = connection.prepareStatement(query);
                statement.setString(1, id);
                result = statement.executeQuery();
            }

            while (result.next()) {

                tempList.add(new IssuesBean());
                tempList.get(i).setIssueId(result.getString("issueID"));
                tempList.get(i).setPersonId(result.getString("personID"));
                tempList.get(i).setIssueState(result.getString("issueState"));
                tempList.get(i).setCategory(result.getString("category"));
                tempList.get(i).setSubCategory(result.getString("subCategory"));
                tempList.get(i).setTitle(result.getString("title"));
                tempList.get(i).setReslotionDetails(result.getString("resolutionDetails"));
                tempList.get(i).setDateReported(result.getString("dateReported"));
                tempList.get(i).setTimeReported(result.getString("timeReported"));
                tempList.get(i).setDateSolved(result.getString("dateSolved"));
                tempList.get(i).setDescription(result.getString("description"));
                i++;
            }
            result.close();
            statement.close();
            connection.close();
            this.setIssues(tempList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public ArrayList<IssuesBean> loadAllIssues(String id, String role, Boolean all) {
        int i = 0;
        String query;
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList<IssuesBean> tempList = new ArrayList<IssuesBean>();
        try {
            if (role.equalsIgnoreCase("Admin")) {
                query = "SELECT * FROM Issues";
                connection = ConfigBean.getConnection();
                statement = connection.prepareStatement(query);
                result = statement.executeQuery();
            } else if (role.equalsIgnoreCase("User") && all) {
                query = "SELECT * FROM Issues WHERE [personID] = ? AND [issueState] = 'complete'";
                connection = ConfigBean.getConnection();
                statement = connection.prepareStatement(query);
                statement.setString(1, id);
                result = statement.executeQuery();
            } else {
                query = "SELECT * FROM Issues WHERE [personID] = ?";
                connection = ConfigBean.getConnection();
                statement = connection.prepareStatement(query);
                statement.setString(1, id);
                result = statement.executeQuery();
            }

            while (result.next()) {

                tempList.add(new IssuesBean());
                tempList.get(i).setIssueId(result.getString("issueID"));
                tempList.get(i).setPersonId(result.getString("personID"));
                tempList.get(i).setIssueState(result.getString("issueState"));
                tempList.get(i).setCategory(result.getString("category"));
                tempList.get(i).setSubCategory(result.getString("subCategory"));
                tempList.get(i).setTitle(result.getString("title"));
                tempList.get(i).setReslotionDetails(result.getString("resolutionDetails"));
                tempList.get(i).setDateReported(result.getString("dateReported"));
                tempList.get(i).setTimeReported(result.getString("timeReported"));
                tempList.get(i).setDateSolved(result.getString("dateSolved"));
                tempList.get(i).setDescription(result.getString("description"));
                i++;
            }
            result.close();
            statement.close();
            connection.close();
            this.setIssues(tempList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
        return issues;
    }

    private void loadKIssues() {
        int i = 0;
        ArrayList<IssuesBean> tempList = new ArrayList<IssuesBean>();
        try {
            String query = "SELECT * FROM Issues WHERE [issueState] = 'resolved'";
            Connection connection = ConfigBean.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {

                tempList.add(new IssuesBean());
                tempList.get(i).setIssueId(result.getString("issueID"));
                tempList.get(i).setPersonId(result.getString("personID"));
                tempList.get(i).setIssueState(result.getString("issueState"));
                tempList.get(i).setCategory(result.getString("category"));
                tempList.get(i).setSubCategory(result.getString("subCategory"));
                tempList.get(i).setTitle(result.getString("title"));
                tempList.get(i).setReslotionDetails(result.getString("resolutionDetails"));
                tempList.get(i).setDateReported(result.getString("dateReported"));
                tempList.get(i).setTimeReported(result.getString("timeReported"));
                tempList.get(i).setDateSolved(result.getString("dateSolved"));
                tempList.get(i).setDescription(result.getString("description"));
                i++;
            }
            result.close();
            statement.close();
            connection.close();
            this.setIssues(tempList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

}
