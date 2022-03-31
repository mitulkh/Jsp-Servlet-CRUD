package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDao;
import model.Person;

@WebServlet("/")
public class PersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PersonDao personDAO;

	public void init() {
		personDAO = new PersonDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/add":
				showNewForm(request, response);
				break;
			case "/insert":
				insertPerson(request, response);
				break;
			case "/delete":
				deletePerson(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updatePerson(request, response);
				break;
			default:
				listPerson(request, response);
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void listPerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		List<Person> personList = personDAO.selectAllPerson();
		request.setAttribute("personList", personList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-person.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("person-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		Person existingPerson = personDAO.selectPerson(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("person-form.jsp");
		request.setAttribute("person", existingPerson);
		dispatcher.forward(request, response);
	}

	private void insertPerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		Person person = new Person(name, mobile, email);
		personDAO.insertPerson(person);
		response.sendRedirect("list");
	}

	private void deletePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		personDAO.deletePerson(id);
		response.sendRedirect("list");
	}

	private void updatePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");

		Person person = new Person(id, name, mobile, email);
		personDAO.updatePerson(person);
		response.sendRedirect("list");
	}
}
