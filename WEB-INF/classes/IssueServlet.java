import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/IssuePage" })
public class IssueServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        PersonBean user = (PersonBean) session.getAttribute("person");
        String id = (String) session.getAttribute("issuesID");
        IssuesBean issue = new IssuesBean();
        issue.loadIssue(id);
        ArrayList<String> state = new ArrayList<>();
        if (user != null || session == null) {
            if (user.getStatus()) {
                if (user.getRoleInSystem().equalsIgnoreCase("Admin") && (issue.getIssueState().equalsIgnoreCase("new")
                        || issue.getIssueState().equalsIgnoreCase("in progress"))) {
                    state.add("in progress");
                    state.add("completed");
                } else if (user.getRoleInSystem().equalsIgnoreCase("User")
                        && issue.getIssueState().equalsIgnoreCase("completed")) {
                    state.add("Accept");
                    state.add("Reject");
                } else if (user.getRoleInSystem().equalsIgnoreCase("Admin")
                        && issue.getPersonId().equals(user.getPersonID())
                        && issue.getIssueState().equalsIgnoreCase("completed")) {
                    state.add("Accept");
                    state.add("Reject");
                } else {
                    state = null;
                }
                session.setAttribute("state", state);
                session.setAttribute("issue", issue);
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/issue.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("/Group18_FinalProject/MainPage");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        String resDetails = request.getParameter("reslotionDetails");
        String comment = request.getParameter("comment");
        HttpSession session = request.getSession();
        IssuesBean issue = (IssuesBean) session.getAttribute("issue");
        PersonBean user = (PersonBean) session.getAttribute("person");
        CommentsBean tempComment = new CommentsBean();
        if (state != null) {
            if (state.length() > 0) {
                issue.updateState(state);
            }
        }
        if (resDetails != null) {
            if (resDetails.length() > 0) {
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                issue.updateResDetails(resDetails, date.format(formatter));
            }
        }
        if (comment != null) {
            if (comment.length() > 0) {
                tempComment.addComment(comment, issue.getIssueId(), user.getPersonID());
            }
        }
        response.sendRedirect("/Group18_FinalProject/IssuePage");

    }
}
