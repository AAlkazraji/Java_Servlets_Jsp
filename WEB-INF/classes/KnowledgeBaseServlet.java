import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/KBase" })
public class KnowledgeBaseServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        PersonBean user = (PersonBean) session.getAttribute("person");
        IssueListBean list = new IssueListBean();
        ArrayList<IssuesBean> issues = list.getKIssues();
        if (user != null || session == null) {
            if (user.getStatus()) {
                session.setAttribute("category", "non");
                session.setAttribute("issues", issues);
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/knowledgeBase.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/Group18_FinalProject/MainPage");
            }
        }
        response.sendRedirect("/Group18_FinalProject/MainPage");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String id = request.getParameter("issueID");
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        if (id == null) {
            session.setAttribute("category", category);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/knowledgeBase.jsp");
            requestDispatcher.forward(request, response);
        } else {
            session.setAttribute("issuesID", id);
            response.sendRedirect("/Group18_FinalProject/IssuePage");
        }
    }

}
