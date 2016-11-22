/**
 * 
 */
package com.online.notepad.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.online.notepad.model.Folder;

/**
 * @author Syamu
 * 
 */
@Service
public interface FolderService {
	
	public void save(Folder folder);
	
	public void Update(Folder folder);

	public void delete(int id);

	public Folder getById(int id);

	public List<Folder> list();


}
