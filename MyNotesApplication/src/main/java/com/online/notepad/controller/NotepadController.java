package com.online.notepad.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.online.notepad.model.Notes;
import com.online.notepad.service.NotepadService;
import com.online.notepad.validator.NoteFormValidator;

@RestController
public class NotepadController {

	final static Logger logger = Logger.getLogger(NotepadController.class);

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
	 * 	@return
	 */
	@RequestMapping(value = "/allNotes", method = RequestMethod.GET)
	public ModelAndView getAllNotes(@RequestParam("FID") int folderId) {
		logger.info("\n NotepadControllerSample  :: getAllNotes \n");
		logger.info("FOLDER ID  :: " + folderId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("allNotes", notepadService.list(folderId));
		mav.addObject("notes", new Notes());
		mav.setViewName("notesMain");
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView createNote(@ModelAttribute("notes") Notes notes, BindingResult result) {
		logger.info("\n NotepadControllerSample  :: createNote \n" + notes);
		notepadService.saveOrUpdate(notes);
		// return new ResponseEntity<String>("Created Note Successfully
		// !",HttpStatus.OK);
		return new ModelAndView("redirect:allNotes.do?FID=" + notes.getFolder().getId());

	}

	/*
	 * @RequestMapping(value = "/folders/{id}/notes", method =
	 * RequestMethod.POST) public ModelAndView
	 * updateNote(@ModelAttribute("notes") Notes notes) { logger.info(
	 * "\n NotepadControllerSample  :: START CTLR updateNote \n" + notes);
	 * notepadService.saveOrUpdate(notes); logger.info(
	 * "\n NotepadControllerSample  :: END CTLR updateNote \n" + notes); return
	 * new ModelAndView("redirect:notes.do"); }
	 */

	@RequestMapping(value = "/getNotes", method = RequestMethod.GET)
	public ModelAndView getById(@RequestParam("NID") int notesId) {
		logger.info("\n NotepadControllerSample  :: getById \n" + notesId);
		ModelAndView mav = new ModelAndView();
		Notes notes = notepadService.getById(notesId);
		if (notes != null)
			mav.addObject("allNotes", notepadService.list(notes.getFolder().getId()));

		mav.addObject("notes", notes);
		mav.setViewName("notesMain");
		return mav;

	}

	@RequestMapping(value = "/deleteNotes", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("notes") Notes notes, BindingResult result) {
		logger.info("\n NotepadControllerSample  :: delete \n" + notes);
		notepadService.delete(notes.getId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("notes", new Notes());
		modelAndView.addObject("allNotes", notepadService.list(notes.getFolder().getId()));
		modelAndView.setViewName("notepadMain");
		return modelAndView;
	}

}
