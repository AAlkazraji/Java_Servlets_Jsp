import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/MainPage" })
public class Main extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("person");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/MainPage.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String choise = request.getParameter("button");
		RequestDispatcher requestDispatcher;
		if (choise.equals("login")) {
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			requestDispatcher.forward(request, response);
		}
		if (choise.equals("newaccount")) {
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/NewUser.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
