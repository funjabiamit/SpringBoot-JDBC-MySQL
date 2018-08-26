package com.onlinetutorialspoint.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.onlinetutorialspoint.model.Item;

@Repository
public class ItemRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemRepository.class);

	@Autowired
	JdbcTemplate template;

	/* Getting all Items from table */
	public List<Item> getAllItems() {
		LOGGER.debug(" inside getallitem");
		List<Item> items = template.query("select id, name,category from item", (result,
				rowNum) -> new Item(result.getInt("id"), result.getString("name"), result.getString("category")));
		return items;
	}

	/* Getting a specific item by item id from table */
	public Item getItem(int itemId) {
		LOGGER.debug(" inside getitem, itemId : " + itemId);
		String query = "SELECT * FROM ITEM WHERE ID=?";
		Item item = template.queryForObject(query, new Object[] { itemId }, new BeanPropertyRowMapper<>(Item.class));

		return item;
	}

	/* Adding an item into database table */
	public int addItem(int itemId, String name, String category) {
		LOGGER.debug(
				" inside addItem, itemId : " + itemId + "name :" + name + "category :" + category);
		String query = "INSERT INTO ITEM VALUES(?,?,?)";
		return template.update(query, itemId, name, category);
	}

	/* Updating an item into database table */
	public int updateItem(int itemId, String name, String category) {
		LOGGER.debug(
				" inside updateItem, itemId : " + itemId + "name :" + name + "category :" + category);
		String query = "UPDATE ITEM SET NAME = ? ,CATEGORY = ? WHERE ID = ?";
		return template.update(query, name, category, itemId);
	}

	/* delete an item from database */
	public int deleteItem(int itemId) {
		LOGGER.debug(" inside deleteItem, itemId : " + itemId);
		String query = "DELETE FROM ITEM WHERE ID =?";
		return template.update(query, itemId);
	}
}
