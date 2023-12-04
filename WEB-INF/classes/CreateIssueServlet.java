import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

@WebServlet(urlPatterns = { "/CreateIssue" })
public class CreateIssueServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        session.removeAttribute("error");
        session.removeAttribute("success");
        ArrayList<String> category = new ArrayList<>();
        category.add("Network");
        category.add("Software");
        category.add("Hardware");
        category.add("Email");
        category.add("Account");
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        PersonBean user = (PersonBean) session.getAttribute("person");
        if (user != null || session == null) {
            if (user.getStatus()) {
                // session.setAttribute("subCat", network);
                session.setAttribute("time", time.format(timeFormatter));
                session.setAttribute("date", date.format(formatter));
                session.setAttribute("selected", "non");
                session.setAttribute("category", category);
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/createIssue.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("/Group18_FinalProject/MainPage");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        session.removeAttribute("error");
        session.removeAttribute("success");
        ArrayList<String> subCat = new ArrayList<>();
        String cat = request.getParameter("category");
        String sub = request.getParameter("subCategory");
        String submit = request.getParameter("sbmt");
        String title = request.getParameter("title");
        String desc = request.getParameter("description");
        if (submit == null) {
            if (cat.equalsIgnoreCase("Network")) {
                subCat.add("Can\'t connect");
                subCat.add("Speed");
                subCat.add("Constant dropouts");
            } else if (cat.equalsIgnoreCase("software")) {
                subCat.add("Slow to load");
                subCat.add("Won\'t load at all");
            } else if (cat.equalsIgnoreCase("hardware")) {
                subCat.add("Computer won\'t turn on");
                subCat.add("Computer \"blue screens\"");
                subCat.add("Disk drive");
                subCat.add("Peripherals");
            } else if (cat.equalsIgnoreCase("email")) {
                subCat.add("Can\'t send");
                subCat.add("Can\'t receive");
                subCat.add("SPAM/Phishing");
            } else if (cat.equalsIgnoreCase("account")) {
                subCat.add("Password reset");
                subCat.add("Wrong details");
            }
            session.setAttribute("subCat", subCat);
            session.setAttribute("selected", cat);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/createIssue.jsp");
            requestDispatcher.forward(request, response);
        } else {
            if (title.length() == 0 || desc.length() == 0) {
                session.setAttribute("error", " Error please fill in all the required fields");
                session.setAttribute("subCat", subCat);
                session.setAttribute("selected", cat);
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/createIssue.jsp");
                requestDispatcher.forward(request, response);
            } else {
                if (sub == null) {
                    sub = "other";
                }
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalTime time = LocalTime.now();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                PersonBean user = (PersonBean) session.getAttribute("person");
                IssuesBean issue = new IssuesBean();
                issue.addNewIssue(user.getPersonID(), "New", cat, sub, title, time.format(timeFormatter),
                        date.format(formatter), desc);
                session.setAttribute("success", "You have successfully submitted an issue");
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/createIssue.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

}
