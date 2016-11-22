/**
 * 
 */
package com.online.notepad.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.online.notepad.model.User;

/**
 * @author Syamu
 * 
 */
@Repository
public class UserDaoImpl implements UserDao {

	final static Logger logger = Logger.getLogger(UserDaoImpl.class);

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

	@Override
	public User login(User user) {
		logger.info("\n  UserDaoImpl  :: login \n" + user.toString());
		String sql = "SELECT * FROM Users WHERE username= '"
				+ user.getUserName() + "' AND password='" + user.getPassword()
				+ "'";
		logger.info("SQL ::::::::::::" + sql);
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserName(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setSsn(rs.getString("ssn"));
					user.setCreatedDate(rs.getDate("created_date"));
					user.setStatus(rs.getString("status"));

					return user;
				}

				return null;
			}

		});
	}

	/* (non-Javadoc)
	 * @see com.online.notepad.dao.UserDao#save(com.online.notepad.model.User)
	 */
	@Override
	public User save(User user) {
		logger.info("Save User ::::::::::::" + user.toString());
		
		final String sql = "insert into users (username,password,ssn,created_date) values(?,?,?,?)";
		logger.info("SQL ::::::::::::" + sql);
		
    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, new String[] {"id"});
    	            pst.setString(1, user.getUserName());
    	            pst.setString(2, user.getPassword());
    	            pst.setString(3, user.getSsn());
    	            pst.setDate(4,new Date(0));
    	            return pst;
    	        }
    	    },
    	    keyHolder);
    	user.setId(keyHolder.getKey().intValue());
    	logger.info("After Saving User ::::::::::::" + user.toString());
    	
		return user;
	}

}
