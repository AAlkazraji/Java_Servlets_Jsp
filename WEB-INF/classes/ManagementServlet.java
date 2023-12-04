import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/UserManagement" })
public class ManagementServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        PersonBean user = (PersonBean) session.getAttribute("person");
        session.removeAttribute("message");
        if (user != null || session == null) {
            if (user.getStatus()) {
                ArrayList<PersonBean> list = new ArrayList<>();
                list = user.getAllUsers();
                session.setAttribute("users", list);
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/management.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/Group18_FinalProject/MainPage");
            }
        }
        response.sendRedirect("/Group18_FinalProject/MainPage");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = request.getParameter("role");
        String update = request.getParameter("update");
        String delete = request.getParameter("delete");
        session.removeAttribute("message");
        PersonBean user = (PersonBean) session.getAttribute("person");
        RequestDispatcher requestDispatcher;
        ArrayList<PersonBean> list = new ArrayList<>();
        if (update != null) {
            user.changeRole(update, role);
            list = user.getAllUsers();
            session.setAttribute("users", list);
            session.setAttribute("message", "The user role has been changed successfully");
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/management.jsp");
            requestDispatcher.forward(request, response);
        } else {
            user.removeUser(delete);
            list = user.getAllUsers();
            session.setAttribute("users", list);
            session.setAttribute("message", "The user has been deleted successfully");
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/management.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
