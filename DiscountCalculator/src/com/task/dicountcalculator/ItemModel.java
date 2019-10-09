package com.task.dicountcalculator;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public enum ItemModel {
	
	/*
	 * Enum to categorise the different items groups and its discounts
	 */
	/* BFD books foods drinks */
	BOOK(14.99, "book", Group.BFD),
	WINE(18.99, "wine", Group.BFD), CHOCOLATE_BOX(11.25, "chocolate box", Group.BFD),
	CHOCOLATE_BAR(1, "chocolate bar", Group.BFD),
	/* CLOTHS */
	SHIRT(19.99, "shirt", Group.CLOTHS), DRESS(9.75, "dress", Group.CLOTHS),
	/* Others */
	PERFUME(27.99, "perfume", Group.OTHERS),
	/* clearance sale */
	CLEARANCE_CHOCOLATE_BAR(2, "clearance chocolate bar", Group.CLEARANCE);

	private final double price;
	private final String itemName;
	private Group group;

	private static final Map<Double, ItemModel> STATUS_MAP = Arrays.stream(ItemModel.values())
			.collect(Collectors.toMap(acoount -> acoount.getPrice(), Function.identity()));

	ItemModel(double price, String itemName, Group group) {
		this.price = price;
		this.itemName = itemName;
		this.group = group;
	}

	public double getPrice() {
		return price;
	}

	public String getItemName() {
		return itemName;
	}

	public Group getGroup() {
		return group;
	}

	public static ItemModel getByCode(double code) {
		return STATUS_MAP.get(code);
	}

	public static ItemModel fromString(String text) {
		for (ItemModel itemModel : ItemModel.values()) {
			if (itemModel.itemName.equalsIgnoreCase(text)) {
				return itemModel;
			}
		}
		return null;
	}

	/**
	 * The group enum to give the discount rate for the different group of items
	 */
	public enum Group {
		BFD(5), CLOTHS(20), OTHERS(3), CLEARANCE(20);

		private final int discount;

		Group(int discount) {
			this.discount = discount;
		}

		public int getDiscount() {
			return discount;
		}
	}

}
