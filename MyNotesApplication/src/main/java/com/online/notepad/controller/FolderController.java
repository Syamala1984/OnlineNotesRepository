/**
 * 
 */
package com.online.notepad.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.online.notepad.model.Folder;
import com.online.notepad.model.User;
import com.online.notepad.service.FolderService;

/**
 * @author Syamu
 * 
 */
@RestController
public class FolderController {

	final static Logger logger = Logger.getLogger(FolderController.class);

	@Autowired
	private FolderService folderService;

	/*
	 * Default Constructor
	 */
	public FolderController() {
		logger.info("&&&&&&&&&&&&&&&&&&  FolderController  &&&&&&&&&&&&&&&&&&&");
	}

	/**
	 * 
	 * @return AllFolders
	 */
	@RequestMapping(value = "/folders", method = RequestMethod.GET)
	public ModelAndView getAllFolders(@ModelAttribute("LOGGEDIN_USER") User user) {
		logger.info("\n FolderController  :: listFolders \n");
		logger.info("\n FolderController  :: LOGGEDIN_USER \n" + user);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("folder", new Folder());
		modelAndView.addObject("allFolders", folderService.list());
		modelAndView.setViewName("notepadMain");
		return modelAndView;
	}

	/**
	 * 
	 * @param folder
	 * @return Status
	 */
	@RequestMapping(value = "/addFolder", method = RequestMethod.POST)
	public ModelAndView createFolder(@ModelAttribute("folder") Folder folder, BindingResult result) {
		logger.info("\n FolderController &&&&&&&&&&&&&&& createFolder \n" + folder.toString());
		if (folder.getId() == null)
			folderService.save(folder);
		else
			folderService.Update(folder);

		// return new ModelAndView("addFolder","folder",folder);
		return new ModelAndView("redirect:folders.do");
	}

	/**
	 * 
	 * @param folderId
	 * @return Folder
	 */
	@RequestMapping(value = "/getFolder", method = RequestMethod.GET)
	public ModelAndView getFolder(@ModelAttribute("folder") Folder folder, BindingResult result) {
		logger.info("\n FolderController  :: getFolder \n" + folder.getId());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("folder", folderService.getById(folder.getId()));
		modelAndView.addObject("allFolders", folderService.list());
		modelAndView.setViewName("notepadMain");

		logger.info("\n FolderController  ::getFolder  ::   modelAndView \n" + modelAndView);
		return modelAndView;
	}

	/**
	 * 
	 * @param folder
	 * @return Updated Status
	 * 
	 *         NOTE: UnUsed Method
	 */
	@RequestMapping(value = "updateFolder/{id}", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public ModelAndView updateFolder(@RequestBody Folder folder) {
		logger.info("\n FolderController  :: START CTLR updateFolder \n" + folder);
		folderService.Update(folder);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addFolder");
		modelAndView.addObject("folder", folder);
		logger.info("\n FolderController  :: END CTLR updateFolder \n" + folder);
		return modelAndView;
	}

	/**
	 * 
	 * @param folderId
	 * @return Deleted Folder Status
	 */
	@RequestMapping(value = "/deleteFolder", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("folder") Folder folder) {
		logger.info("\n FolderController  :: delete \n" + folder.getId());

		folderService.delete(folder.getId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("folder", new Folder());
		modelAndView.addObject("allFolders", folderService.list());
		modelAndView.setViewName("notepadMain");
		return modelAndView;

	}

}
