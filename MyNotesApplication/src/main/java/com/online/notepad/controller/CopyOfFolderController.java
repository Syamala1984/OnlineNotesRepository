/**
 * 
 */
package com.online.notepad.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.online.notepad.model.Folder;
import com.online.notepad.service.FolderService;

/**
 * @author Syamu
 * 
 */
public class CopyOfFolderController {

	final static Logger logger = Logger.getLogger(CopyOfFolderController.class);

	@Autowired
	private FolderService folderService;

	/*
	 * Default Constructor
	 */
	public CopyOfFolderController() {
		logger.info("---------FolderController----------");
	}

	/**
	 * 
	 * @return AllFolders
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Folder> getAllFolders() {
		logger.info("\n FolderController  :: listFolders \n");
		return folderService.list();
	}

	/**
	 * 
	 * @param folder
	 * @return Status
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> createFolder(@RequestBody Folder folder) {
		logger.info("\n FolderController  :: createFolder \n" + folder);
		folderService.save(folder);
		return new ResponseEntity<String>("Created Folder Successfully !",
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param folderId
	 * @return Folder
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Folder> getFolder(@PathVariable("id") int folderId) {
		logger.info("\n FolderController  :: getFolder \n" + folderId);
		Folder folder = folderService.getById(folderId);
		if(folder != null)
			return new ResponseEntity<Folder>(folder, HttpStatus.OK);
		else
			return new ResponseEntity<Folder>(folder, HttpStatus.NO_CONTENT);
	
	}

	/**
	 * 
	 * @param folder
	 * @return Updated Status
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> updateFolder(@RequestBody Folder folder) {
		logger.info("\n FolderController  :: START CTLR updateFolder \n"
				+ folder);
		folderService.Update(folder);

		logger.info("\n FolderController  :: END CTLR updateFolder \n" + folder);
		return new ResponseEntity<String>("Notes updated Successfully !",
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param folderId
	 * @return Deleted Folder Status
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<String> delete(@PathVariable("id") int folderId) {
		logger.info("\n FolderController  :: delete \n" + folderId);
		folderService.delete(folderId);
		return new ResponseEntity<String>("Deleted Folder Successfully !",
				HttpStatus.OK);
	}

}
