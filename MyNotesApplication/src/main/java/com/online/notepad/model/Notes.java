/**
 * 
 */
package com.online.notepad.model;

import java.sql.Date;

/**
 * @author Syamu
 * 
 */
public class Notes {

	private Integer id;
	private String name;
	private String type;
	private String content;
	private Date createdDate;
	private Date updatedDate;
	private String status;
	private String location;
	private Folder folder;
	private User user;
	private String searchString;

	public Notes() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param id
	 * @param name
	 * @param type
	 * @param content
	 * @param createdDate
	 * @param updatedDate
	 * @param status
	 * @param location
	 * @param folder
	 * @param user
	 */
	public Notes(Integer id, String name, String type, String content,
			Date createdDate, Date updatedDate, String status, String location,
			Folder folder, User user) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.content = content;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.location = location;
		this.folder = folder;
		this.user = user;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * @param folder
	 *            the folder to set
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notes [id=" + id + ", name=" + name + ", type=" + type
				+ ", content=" + content + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", status=" + status
				+ ", location=" + location + ", folder=" + folder + ", user="
				+ user + "]";
	}

}
