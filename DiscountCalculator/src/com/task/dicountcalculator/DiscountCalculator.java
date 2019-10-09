package com.task.dicountcalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DiscountCalculator {
	public static void main(String[] args) {
		System.out.println("Enter the number items you are to buy : ");
		Scanner inputItem = new Scanner(System.in);
		int itemNumber = inputItem.nextInt();
		System.out.println("Enter the items you are to buy");
		List<ItemsListModel> itemsList = new ArrayList<>();
		while (itemNumber > 0) {
			itemsList.add(calculateDiscount(inputItem));
			itemNumber--;
		}
		displayBill(itemsList);
	}

	private static ItemsListModel calculateDiscount(Scanner inputItem) {
		int count = inputItem.nextInt();
		String itemName = inputItem.nextLine().trim();
		ItemModel itemModel = ItemModel.fromString(itemName);
		int discount = itemModel.getGroup().getDiscount();
		double discountedAmount = count * ((100 - discount) * (itemModel.getPrice())) / 100;
		double actualAmount = count * itemModel.getPrice();
		ItemsListModel itemsListModel = new ItemsListModel();
		itemsListModel.setActualAmount(actualAmount);
		itemsListModel.setDiscountAmount(discountedAmount);
		itemsListModel.setItemName(itemModel);
		itemsListModel.setItemCount(count);
		return itemsListModel;
	}

	private static void displayBill(List<ItemsListModel> itemsList) {
		DecimalFormat roundOff = new DecimalFormat("0.00");
		double total = itemsList.stream().map(item -> {
			System.out.println(
					item.getItemCount() + " " + item.getItemName().getItemName() + "  at "
							+ roundOff.format(item.getDiscountAmount()));
			return item.getDiscountAmount();
		}).collect(Collectors.toList()).stream().reduce(0.0,
				(totalAmount, discountAmount) -> totalAmount + discountAmount);
		double savedAmount = itemsList.stream().map(item -> {
			return item.getActualAmount() - item.getDiscountAmount();
		}).collect(Collectors.toList()).stream().reduce(0.0, (differenceAmount, amount) -> differenceAmount + amount);
		System.out.println("Total : " + roundOff.format(total));
		System.out.println("You Saved : " + roundOff.format(savedAmount));
	}

}
