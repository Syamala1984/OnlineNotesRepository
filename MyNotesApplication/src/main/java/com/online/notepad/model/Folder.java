/**
 * 
 */
package com.online.notepad.model;

import java.sql.Date;
import java.util.List;

/**
 * @author Syamu
 *
 */
public class Folder {
	
	private Integer id;
	private String name;
	private String location;
	private Date createdDate;
	private String status;
	private List<Notes> notesList;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the notesList
	 */
	public List<Notes> getNotesList() {
		return notesList;
	}
	/**
	 * @param notesList the notesList to set
	 */
	public void setNotesList(List<Notes> notesList) {
		this.notesList = notesList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Folder [id=" + id + ", name=" + name + ", location=" + location + ", createdDate=" + createdDate
				+ ", status=" + status + ", notesList=" + notesList + "]";
	}
	
	

}
