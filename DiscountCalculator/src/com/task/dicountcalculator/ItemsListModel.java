package com.task.dicountcalculator;

import lombok.Data;

@Data
public class ItemsListModel {

	private ItemModel itemName;
	private double discountAmount;
	private double actualAmount;
	private int itemCount;

}
