/**
 * 
 */
package com.online.notepad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.online.notepad.model.Folder;

/**
 * @author Syamu
 * 
 */
@Repository
public class FolderDaoImpl implements FolderDao {

	final static Logger logger = Logger.getLogger(FolderDaoImpl.class);

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

	public FolderDaoImpl() {
	}

	public FolderDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("\n DataSource  :: " + dataSource + "\n jdbcTemplate   ::  " + jdbcTemplate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.online.notepad.yahoo.dao.FolderDao#save(com.online.notepad.yahoo.
	 * model.Folder)
	 */
	@Override
	public void save(Folder folder) {
		// insert
		String sql = "INSERT INTO folder (name, location, created_date,status)" + " VALUES (?,?,?,?)";
		logger.info("\n===SQL  ::  " + sql + "\n===" + folder.getName() + "\n" + folder.getLocation() + "\n"
				+ folder.getCreatedDate() + "\n");
		jdbcTemplate.update(sql, folder.getName(), folder.getLocation(), folder.getCreatedDate(), "Active");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.online.notepad.yahoo.dao.FolderDao#Update(com.online.notepad.yahoo
	 * .model.Folder)
	 */
	@Override
	public void Update(Folder folder) {
		// update
		logger.info("\n______________ FolderDaoImpl  ::  UPDATE Folder _____________");
		logger.info("\n______________ NOTE ID_____________" + folder.getId());

		String sql = "UPDATE folder SET name=?, location=?,status=? WHERE id=?";
		jdbcTemplate.update(sql, folder.getName(), folder.getLocation(), folder.getStatus(), folder.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.yahoo.dao.FolderDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		logger.info("\n  FolderDaoImpl  :: delete \n" + id);
		String sql = "DELETE FROM folder WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.yahoo.dao.FolderDao#getById(int)
	 */
	@Override
	public Folder getById(int id) {
		logger.info("\n  OnlineNotesService  :: getById \n" + id);
		String sql = "SELECT * FROM folder WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Folder>() {

			@Override
			public Folder extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Folder folder = new Folder();
					folder.setId(rs.getInt("id"));
					folder.setName(rs.getString("name"));
					folder.setLocation(rs.getString("location"));
					folder.setCreatedDate(rs.getDate("created_date"));
					folder.setStatus(rs.getString("status"));

					return folder;
				}

				return null;
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.yahoo.dao.FolderDao#list()
	 */
	@Override
	public List<Folder> list() {
		String sql = "SELECT * FROM folder";

		logger.info("\n  FolderDaoImpl  :: list \n");
		logger.info("\nlist  jdbcTemplate:  " + jdbcTemplate);
		List<Folder> listFolder = jdbcTemplate.query(sql, new RowMapper<Folder>() {

			@Override
			public Folder mapRow(ResultSet rs, int rowNum) throws SQLException {
				Folder folder = new Folder();

				folder.setId(rs.getInt("id"));
				folder.setName(rs.getString("name"));
				folder.setLocation(rs.getString("location"));
				folder.setCreatedDate(rs.getDate("created_date"));
				folder.setStatus(rs.getString("status"));

				return folder;
			}

		});

		return listFolder;

	}

}
