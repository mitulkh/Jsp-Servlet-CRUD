package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.MysqlConnection;
import model.Person;

public class PersonDao extends MysqlConnection {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultset = null;

	private static final String INSERT_PERSON = "INSERT INTO person(name, mobile, email) VALUES (?, ?, ?);";
	private static final String SELECT_PERSON_BY_ID = "SELECT id, name, mobile, email FROM person WHERE id = ?;";
	private static final String SELECT_ALL_PERSON = "SELECT * FROM person;";
	private static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?;";
	private static final String UPDATE_PERSON = "UPDATE person SET name = ?, mobile = ?, email = ? WHERE id = ?";

	public void insertPerson(Person person) throws SQLException {

		try (Connection connection = MysqlConnection.createConnect();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSON)) {

			preparedStatement.setString(1, person.getName());
			preparedStatement.setString(2, person.getMobile());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public Person selectPerson(int id) throws SQLException, ClassNotFoundException {
		Person person = null;
		try (Connection connection = MysqlConnection.createConnect();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSON_BY_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String mobile = resultSet.getString("mobile");
				String email = resultSet.getString("email");
				person = new Person(id, name, mobile, email);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return person;
	}

	public List<Person> selectAllPerson() throws ClassNotFoundException, SQLException {
		List<Person> personList = new ArrayList<>();
		try (Connection connection = MysqlConnection.createConnect();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERSON)) {

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String mobile = resultSet.getString("mobile");
				String email = resultSet.getString("email");
				personList.add(new Person(id, name, mobile, email));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return personList;
	}

	public boolean deletePerson(int id) throws ClassNotFoundException, SQLException {
		boolean deleted;
		try (Connection connection = MysqlConnection.createConnect();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERSON)) {

			preparedStatement.setInt(1, id);
			deleted = preparedStatement.executeUpdate() > 0;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return deleted;
	}

	public boolean updatePerson(Person person) throws SQLException, ClassNotFoundException {
		boolean rowUpdated;
		try (Connection connection = MysqlConnection.createConnect();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSON)) {
			preparedStatement.setString(1, person.getName());
			preparedStatement.setString(2, person.getMobile());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setInt(4, person.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return rowUpdated;
	}

}
