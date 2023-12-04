import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("ERROR");
		session.removeAttribute("person");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String user = request.getParameter("username");
		String pass = request.getParameter("password");

		PersonBean person = new PersonBean();
		person.login(user, person.hashPassword(pass));

		if (person.getStatus() == true) {
			session.setAttribute("person", person);
			session.removeAttribute("ERROR");
			response.sendRedirect("/Group18_FinalProject/UserMenu");
		}
		// else show error message but we need to change it latter
		else {
			session.setAttribute("ERROR", "The Login has failed, the username or password is Incorrect");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}
