/**
 * 
 */
package com.online.notepad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.notepad.dao.FolderDao;
import com.online.notepad.dao.NotesDao;
import com.online.notepad.model.Folder;

/**
 * @author Syamu
 * 
 */
@Service
public class FolderServiceImpl implements FolderService {

	@Autowired
	FolderDao folderDao;
	
	@Autowired
	NotesDao notesDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.online.notepad.yahoo.service.FolderService#save(com.online.notepad
	 * .yahoo.model.Folder)
	 */
	@Override
	public void save(Folder folder) {
		folderDao.save(folder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.online.notepad.yahoo.service.FolderService#Update(com.online.notepad
	 * .yahoo.model.Folder)
	 */
	@Override
	public void Update(Folder folder) {
		folderDao.Update(folder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.yahoo.service.FolderService#delete(int)
	 */
	@Override
	public void delete(int id) {
		notesDao.deleteNotesByFolder(id);
		folderDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.yahoo.service.FolderService#getById(int)
	 */
	@Override
	public Folder getById(int id) {
		Folder folder = folderDao.getById(id);
		folder.setNotesList(notesDao.list(id));
		return folder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.online.notepad.yahoo.service.FolderService#list()
	 */
	@Override
	public List<Folder> list() {
		return folderDao.list();
	}

}
