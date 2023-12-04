import java.io.IOException;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/CreateAccountServlet" })
public class CreateAccountServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/NewUser.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("email");
		String tempPass = request.getParameter("password");
		String fiestName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		HttpSession session = request.getSession();

		PersonBean person = new PersonBean();
		if (!person.exist(user) && isNumeric(phoneNumber)) {

			String pass = person.hashPassword(tempPass);
			person.addUser(fiestName, lastName, user, pass, phoneNumber);
			session.setAttribute("person", person);
			response.sendRedirect("/Group18_FinalProject/LoginServlet");
		} else {
			if (!isNumeric(phoneNumber)) {
				session.setAttribute("ERROR", "Error the phone number must not contain letters");
			} else {
				session.setAttribute("ERROR", "This account already exists please try again");
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/NewUser.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
