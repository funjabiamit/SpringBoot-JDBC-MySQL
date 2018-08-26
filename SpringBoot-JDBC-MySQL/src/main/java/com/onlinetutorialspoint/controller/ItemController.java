package com.onlinetutorialspoint.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinetutorialspoint.model.Item;
import com.onlinetutorialspoint.service.ItemService;
import com.onlonetutorialspoint.constants.ApplicationConstant;

@RestController
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	ItemService itemService;

	@GetMapping(value = ApplicationConstant.GET_ALL_ITEMS_URI)
	@ResponseBody
	public List<Item> getAllItems() {
		// LOGGER.debug("inside getallitem");
		LOGGER.debug("inside getallitem");
		return itemService.getAllItems();
	}

	@GetMapping(value = ApplicationConstant.GET_ITEM_URI)
	@ResponseBody
	public Item getItem(@RequestParam("itemId") int itemId) {
		LOGGER.debug("inside getitem, itemId : " + itemId);
		return itemService.getItem(itemId);
	}

	@PostMapping(value = ApplicationConstant.ADD_ITEM_URI)
	@ResponseBody
	public Object addItem(@RequestParam("itemId") int itemId, @RequestParam("name") String name,
			@RequestParam("category") String category) {
		LOGGER.debug("inside addItem, itemId : " + itemId + "name :" + name + "category :" + category);
		if (itemService.addItem(itemId, name, category) >= 1) {
			Item item = new Item(itemId, name, category);
			return item;
			// return ApplicationConstant.ITEM_ADDED_SUCCESS_MESSAGE;
		} else {
			return ApplicationConstant.ITEM_ERROR_MESSAGE;
		}
	}

	// adding update method below
	@PutMapping(value = ApplicationConstant.UPDATE_ITEM_URI)
	@ResponseBody
	public Object updateItem(@RequestParam("itemId") int itemId, @RequestParam("name") String name,
			@RequestParam("category") String category) {
		LOGGER.debug("inside updateItem, itemId : " + itemId + "name :" + name + "category :" + category);
		if (itemService.updateItem(itemId, name, category) >= 1) {
			Item item = new Item(itemId, name, category);
			return item;
		} else {
			return ApplicationConstant.ITEM_ERROR_MESSAGE;
		}
	}

	@DeleteMapping(value = ApplicationConstant.DELETE_ITEM_URI)
	@ResponseBody
	public String deleteItem(@RequestParam("itemId") int itemId) {
		LOGGER.debug("inside deleteItem, itemId : " + itemId);
		if (itemService.deleteItem(itemId) >= 1) {
			return ApplicationConstant.ITEM_DELETED_SUCCESS_MESSAGE;
		} else {
			return ApplicationConstant.ITEM_ERROR_MESSAGE;
		}
	}
}
