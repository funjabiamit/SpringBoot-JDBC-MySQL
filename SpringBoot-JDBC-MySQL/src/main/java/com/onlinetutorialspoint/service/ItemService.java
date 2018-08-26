package com.onlinetutorialspoint.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinetutorialspoint.model.Item;
import com.onlinetutorialspoint.repo.ItemRepository;

@Repository
public class ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	ItemRepository dao;

	/* Getting all Items from table */
	public List<Item> getAllItems() {
		LOGGER.debug(" inside getallitem");
		return dao.getAllItems();
	}

	/* Getting a specific item by item id from table */
	public Item getItem(int itemId) {
		LOGGER.debug(" inside getitem, itemId : " + itemId);
		return dao.getItem(itemId);
	}

	/* Adding an item into database table */
	public int addItem(int itemId, String name, String category) {
		LOGGER.debug(
				" inside addItem, itemId : " + itemId + "name :" + name + "category :" + category);
		return dao.addItem(itemId, name, category);
	}

	/* Updating an item into database table */
	public int updateItem(int itemId, String name, String category) {
		LOGGER.debug(
				" inside updateItem, itemId : " + itemId + "name :" + name + "category :" + category);
		return dao.updateItem(itemId, name, category);

	}

	/* delete an item from database */
	public int deleteItem(int itemId) {
		LOGGER.debug(" inside deleteItem, itemId : " + itemId);
		return dao.deleteItem(itemId);
	}
}
