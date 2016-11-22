package com.online.notepad.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.online.notepad.model.Notes;

/**
 * Defines DAO operations for the contact model.
 * 
 * @author www.codejava.net
 *
 */
@Repository
public interface NotesDao {

	public void saveOrUpdate(Notes notes);

	public void delete(int id);

	public Notes getById(int id);

	public List<Notes> list(int folderId);

	public List<Notes> search(String searchString);

	public int deleteNotesByFolder(int folderId);
}
