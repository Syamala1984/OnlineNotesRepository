package com.online.notepad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.online.notepad.model.Folder;
import com.online.notepad.model.Notes;

/**
 * An implementation of the ContactDAO interface.
 * 
 * @author www.codejava.net
 * 
 */
@Repository
public class NotesDaoImpl implements NotesDao {

	final static Logger logger = Logger.getLogger(NotesDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Default Constructor
	 */
	public NotesDaoImpl() {
	}

	/**
	 * 
	 * @param dataSource
	 */
	public NotesDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("\n DataSource  :: " + dataSource + "\n jdbcTemplate   ::  " + jdbcTemplate);
	}

	/**
	 * saveOrUpdate
	 */
	@Override
	public void saveOrUpdate(Notes notes) {
		if (notes.getId() != null && notes.getId() > 0) {
			// update
			String sql = "UPDATE notes SET name=?,type=?,updated_date=?,location=?,content=?,status=? WHERE id=?";

			logger.info("\n______________ OnlineNotesService  ::  UPDATE NOTE _____________");
			logger.info("\n______________ NOTE ID_____________" + notes.getId());

			jdbcTemplate.update(sql, notes.getName(), notes.getType(), notes.getUpdatedDate(), notes.getLocation(),
					notes.getContent(), notes.getStatus(), notes.getId());

		} else {
			logger.info("\n______________  OnlineNotesService  :: INSERT NOTE _____________");
			// insert
			String sql = "INSERT INTO notes (name, content, type, created_date,folder_id,user_id,status,location)"
					+ " VALUES (?,?,?,?,?,?,?,?)";
			logger.info("\n===SQL  ::  " + sql + "\n===" + notes.getName() + "\n" + notes.getContent() + "\n"
					+ notes.getType() + "\n" + notes.getFolder().getId() + "\n" + notes.getUser().getId());
			jdbcTemplate.update(sql, notes.getName(), notes.getContent(), notes.getType(), notes.getCreatedDate(),
					notes.getFolder().getId(), notes.getUser().getId(), notes.getStatus(), notes.getLocation());

		}

	}

	/**
	 * delete
	 */
	@Override
	public void delete(int id) {
		logger.info("\n  OnlineNotesService  :: delete \n" + id);
		String sql = "DELETE FROM notes WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	/**
	 * list
	 */
	@Override
	public List<Notes> list(int folderId) {
		logger.info("\n  OnlineNotesService  :: list \n");
		String sql = "SELECT * FROM notes Where folder_id=?";
		logger.info("\nlist  jdbcTemplate:  " + jdbcTemplate);
		List<Notes> listContact = jdbcTemplate.query(sql, new RowMapper<Notes>() {

			@Override
			public Notes mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notes notes = new Notes();

				notes.setId(rs.getInt("id"));
				notes.setName(rs.getString("name"));
				notes.setContent(rs.getString("content"));
				notes.setType(rs.getString("type"));
				notes.setStatus(rs.getString("status"));
				notes.setFolder(new Folder());
				notes.getFolder().setId(rs.getInt("folder_id"));
				return notes;
			}

		}, folderId);

		return listContact;
	}

	/**
	 * getById
	 */
	@Override
	public Notes getById(int id) {
		logger.info("\n  OnlineNotesService  :: getById \n" + id);
		String sql = "SELECT * FROM notes WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Notes>() {

			@Override
			public Notes extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Notes notes = new Notes();
					notes.setId(rs.getInt("id"));
					notes.setName(rs.getString("name"));
					notes.setContent(rs.getString("content"));
					notes.setType(rs.getString("type"));
					notes.setStatus(rs.getString("status"));
					notes.setLocation(rs.getString("location"));
					notes.setCreatedDate(rs.getDate("created_date"));
					notes.setUpdatedDate(rs.getDate("updated_date"));
					notes.setFolder(new Folder());
					notes.getFolder().setId(rs.getInt("folder_id"));
					return notes;
				}

				return null;
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.dao.NotesDAO#search(java.lang.String)
	 */
	@Override
	public List<Notes> search(String searchString) {
		Pattern pattern = null;
		Matcher matcher = null;
		List<Notes> searchResultList = null;
		logger.info("RegExValidation :: Search  ::-----------------::" + searchString);

		if (searchString != null)
			pattern = Pattern.compile(".*?\\b" + searchString + "\\b.*?", Pattern.CASE_INSENSITIVE);

		logger.info("\n PATTERN  ::" + pattern.toString());

		/** need to update ***/
		for (Notes note : this.list(1)) {
			logger.info("\n INSIDE SEARCH LOOP  ::" + note);
			matcher = pattern.matcher(note.toString());
			if (matcher.matches()) {
				logger.info("\n MATCHER SEARCH LOOP  ::" + matcher);
				searchResultList = new ArrayList<Notes>();
				searchResultList.add(note);
			}
		}
		logger.info("\nresultList  ::" + searchResultList);
		return searchResultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.dao.NotesDao#deleteNotesByFolder(int)
	 */
	@Override
	public int deleteNotesByFolder(int folderId) {
		logger.info("\n  OnlineNotesService  :: deleteNotesByFolder \n" + folderId);
		String sql = "DELETE FROM notes WHERE folder_id=?";
		int result = jdbcTemplate.update(sql, folderId);
		return result;
	}

}
