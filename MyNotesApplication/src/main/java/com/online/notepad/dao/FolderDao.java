/**
 * 
 */
package com.online.notepad.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.online.notepad.model.Folder;

/**
 * @author Syamu
 * 
 */
@Repository
public interface FolderDao {
	
	public void save(Folder folder);

	public void Update(Folder folder);

	public void delete(int id);

	public Folder getById(int id);

	public List<Folder> list();

}
