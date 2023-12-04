import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//to do
//if the the user is not signed in then redirect to the main MainPage
//if the user is signed in then show then show then menu jsp based on their type

@WebServlet(urlPatterns = { "/UserMenu", "" })
public class UserViewServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		HttpSession session = request.getSession();
		session.removeAttribute("ERROR");
		PersonBean user = (PersonBean) session.getAttribute("person");
		if (user != null || session == null) {
			if (user.getStatus()) {
				IssueListBean list = new IssueListBean();
				list.getIssuesByUser(user.getRoleInSystem(), user.getPersonID());
				ArrayList<IssuesBean> issues = new ArrayList<IssuesBean>();
				issues = list.getAllIssues();
				session.setAttribute("issues", issues);
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/userView.jsp");
				requestDispatcher.forward(request, response);
			} else {
				response.sendRedirect("/Group18_FinalProject/MainPage");
			}
		}
		response.sendRedirect("/Group18_FinalProject/MainPage");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("issueID");
		HttpSession session = request.getSession();
		RequestDispatcher requestDispatcher;
		if (id == null) {
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/userView.jsp");
			requestDispatcher.forward(request, response);
		} else {
			session.setAttribute("issuesID", id);
			response.sendRedirect("/Group18_FinalProject/IssuePage");
		}

	}
}
