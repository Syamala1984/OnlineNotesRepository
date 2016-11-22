package com.online.notepad.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.online.notepad.model.Notes;
import com.online.notepad.service.NotepadService;
import com.online.notepad.validator.NoteFormValidator;

public class CopyOfNotepadController {

	final static Logger logger = Logger
			.getLogger(CopyOfNotepadController.class);

	@Autowired
	NotepadService notepadService;

	@Autowired
	NoteFormValidator noteFormValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(noteFormValidator);
	}
	
	/**
	 * @
	 * 
	 * @return
	 */
	@RequestMapping(value = "/folders/{id}/notes", method = RequestMethod.GET, produces = { "application/json" })
	public List<Notes> getAllNotes(@PathVariable("id") int folderId) {
		logger.info("\n NotepadControllerSample  :: getAllNotes \n");
		return notepadService.list(folderId);
	}

	@RequestMapping(value = "/folders/{id}/notes", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> createNote(@RequestBody Notes notes) {
		logger.info("\n NotepadControllerSample  :: createNote \n" + notes);
		notepadService.saveOrUpdate(notes);
		return new ResponseEntity<String>("Created Note Successfully !",
				HttpStatus.OK);
	}

	@RequestMapping(value = "/folders/{id}/notes/{id}", method = RequestMethod.PUT, consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> updateNote(@RequestBody Notes notes) {
		logger.info("\n NotepadControllerSample  :: START CTLR updateNote \n"
				+ notes);
		notepadService.saveOrUpdate(notes);
		logger.info("\n NotepadControllerSample  :: END CTLR updateNote \n"
				+ notes);
		return new ResponseEntity<String>("Notes updated Successfully !",
				HttpStatus.OK);
	}

	@RequestMapping(value = "/folders/{id}/notes/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Notes> getById(@PathVariable("id") int noteId) {
		logger.info("\n NotepadControllerSample  :: getById \n" + noteId);
		Notes notes = notepadService.getById(noteId);
		if (notes == null) {
			return new ResponseEntity<Notes>(notes,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Notes>(notes, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/folders/{id}/notes/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<String> delete(@PathVariable("id") int noteId) {
		logger.info("\n NotepadControllerSample  :: delete \n" + noteId);
		notepadService.delete(noteId);
		return new ResponseEntity<String>("Deleted Note Successfully !",
				HttpStatus.OK);
	}

}
