package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Note;
import core.NoteLabel;

public class NoteDAO {

	private DbConnection dbConnection;

	public NoteDAO(DbConnection dbConnection) {

		this.dbConnection = dbConnection;
	}

	public List<Note> GetNotes() throws SQLException {
		List<Note> notesList = new ArrayList<Note>();
		String query = "SELECT * FROM  Notes";

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next())
			notesList.add(new Note(resultSet.getInt("ID"), resultSet.getString("Title"),
					NoteLabel.fromText(resultSet.getString("OrderStatus")), resultSet.getString("Data"),
					resultSet.getInt("TextAreaSize_X"), resultSet.getInt("TextAreaSize_Y"),
					resultSet.getString("Content")));

		preparedStatement.close();
		return notesList;
	}

	public Note GetNoteById(int id) throws SQLException {
		String query = "SELECT * FROM Notes WHERE ID=?";

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		resultSet.next();
		Note note = (new Note(resultSet.getInt("ID"), resultSet.getString("Title"),
				NoteLabel.fromText(resultSet.getString("OrderStatus")), resultSet.getString("Data"),
				resultSet.getInt("TextAreaSize_X"), resultSet.getInt("TextAreaSize_Y"),
				resultSet.getString("Content")));

		return note;
	}

	public Note SaveNote(Note entity) throws SQLException {
		String query = "INSERT INTO Notes (Title, Label, Data, TextAreaSize_X, textAreaSize_Y, Content) VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

		preparedStatement.setInt(1, entity.getID());
		SetPreparedStatements(preparedStatement, entity);
		preparedStatement.execute();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next())
			entity.setID(resultSet.getInt(1));
		else
			entity = null;

		preparedStatement.close();
		return entity;
	}

	public Note AddNote(Note entity) throws SQLException {
		String query = "INSERT INTO Notes (Title, Label, Data, TextAreaSize_X, textAreaSize_Y, Content) VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

		preparedStatement.setInt(1, entity.getID());
		SetPreparedStatements(preparedStatement, entity);
		preparedStatement.execute();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next())
			entity.setID(resultSet.getInt(1));
		else
			entity = null;

		preparedStatement.close();
		return entity;
	}

	public int getCount() throws SQLException {
		String query = "SELECT COUNT(*) FROM Notes";

		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
		preparedStatement.execute();
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		int rowCount = resultSet.getInt(1);
		System.out.println("SELECT COUNT(*) FROM Notes: " + rowCount);
		preparedStatement.close();
		return rowCount;
	}

	public Boolean EditNoteByID(Note entity) throws SQLException {
		String query = "UPDATE Notes SET 'Title'=?, 'Label'=?, 'TextAreaSize_X'=?, 'textAreaSize_Y'=?, 'OrderStatus'=? WHERE 'OrdersID'=?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
		SetPreparedStatements(preparedStatement, entity);
		Boolean update = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return update;
	}

	private PreparedStatement SetPreparedStatements(PreparedStatement statement, Note entity) throws SQLException {
		statement.setString(1, entity.getTitle());
		statement.setString(2, entity.getLabel().getValue());
		statement.setString(3, entity.getDate());
		statement.setInt(4, entity.getTextAreaSize_X());
		statement.setInt(5, entity.getTextAreaSize_Y());
		statement.setString(6, entity.getNoteContent());

		return statement;
	}

	public Boolean DeleteNotesById(int id) throws SQLException {
		String query = "DELETE FROM Notes WHERE OrderID=?";
		PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
		preparedStatement.setInt(1, id);
		Boolean methodSucceeded = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return methodSucceeded;
	}

	/*
	 * TEST
	 */
	public static void main(String[] args) {
		DbConnection dbConnection;
		try {
			dbConnection = new DbConnection();
			NoteDAO noteDAO = new NoteDAO(dbConnection);

			System.out.println("DZIA£A   " + noteDAO.getCount());

			dbConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
