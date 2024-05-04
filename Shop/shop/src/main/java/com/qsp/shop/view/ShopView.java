package com.qsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class ShopView {

	static Scanner myInput = new Scanner(System.in);
	Product product = new Product();
	static ShopController shopController = new ShopController();

	public static void main(String[] args) throws SQLException {
		do {
			System.out.println("Select operation to perfrom: ");
			System.out.println("1.Add product\n2.Remove product\n3.Update product details\n4.Fetch product\n0.Exit");
			System.out.print("Enter digit respective to desired option: ");
			int userInput = myInput.nextInt();
			myInput.nextLine();
			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println("----------------------------EXITED----------------------------");
				System.exit(0);

				break;
			case 1:
				System.out.println("How many product you want to add ? \n1. Single product \n2.Multiple products");
				int productsCount = myInput.nextInt();
				myInput.nextLine();
				if (productsCount == 1) {
					System.out.print("Enter product id: ");
					int i_p_id = myInput.nextInt(); // i_p_id--- input p id
					myInput.nextLine();
					System.out.print("Enter product name: ");
					String i_p_name = myInput.nextLine();
					System.out.print("Enter price: ");
					int i_p_price = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter quantity: ");
					int i_p_quantity = myInput.nextInt();
					myInput.nextLine();
					boolean i_p_availability = false;
					if (i_p_quantity > 0) {
						i_p_availability = true;
					}
					if ((shopController.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability)) != 0) {
						System.out.println("-------------Product Added--------------");
					} else {
						System.out.println("----------------Product not Added-------------------------");
					}
				} else {
					boolean toContinue = true;
					ArrayList<Product> products = new ArrayList<Product>();
					do {
						Product product = new Product();
						System.out.println("enter id : ");
						product.setP_id(myInput.nextInt());
						myInput.nextLine();
						System.out.println("enter name : ");
						product.setP_name(myInput.nextLine());
						System.out.println("enter Price : ");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
						System.out.println("enter quantity : ");
						int quantity = myInput.nextInt();
						product.setp_quantity(quantity);
						myInput.nextLine();
						boolean i_p_availability = false;
						if (quantity > 0) {
							i_p_availability = true;
						}
						product.setP_availability(i_p_availability);
						products.add(product);
						System.out.println("press 1 to continue adding product, Press 0 to stop adding products : ");
						int toAdd = myInput.nextInt();
						if (toAdd == 0) {
							toContinue = false;
						}

					} while (toContinue);
					shopController.addMultipleProducts(products);
				}
				break;

			case 2:
				System.out.println("Enter product id to remove : ");
				int ptoductToRemove = myInput.nextInt();
				myInput.nextLine();
				if (shopController.removeProduct(ptoductToRemove) != 0) {
					System.out.println("------------Deleted successfully------------");
				} else {
					System.out.println("-----------------------Product with given id does not exist.--------------------");
				}
				break;

			case 3:
				System.out.println("Enter product id to update : ");
				int productIdToUpdate = myInput.nextInt();
				myInput.nextLine();
				ResultSet product = shopController.fetchProduct(productIdToUpdate);
				if (product.next()) {
					System.out.println("What you want to update ?");
					System.out.println("1.Name\n2.Price\n3.Quantity");
					System.out.println("Enter number respective to desired option : ");
					byte updateOption = myInput.nextByte();
					myInput.nextLine();
					switch (updateOption) {
					case 1:
						System.out.print("Enetr name to update : ");
						String nameToUpdate = myInput.nextLine();
						if (shopController.updateProductName(productIdToUpdate, nameToUpdate)!= 0) {
							System.out.println("----------------PRoduct Name Updated Successfully--------------------");
						} else {
							System.out.println("-----------------PRoduct Name not Updated-------------------");
						}
						System.out.println();
						
						break;
					case 2:
						System.out.println("Enter price to update : ");
						int priceToUpdate = myInput.nextInt();
						if (shopController.updateProductPrice(productIdToUpdate, priceToUpdate)!=0) {
							System.out.println("---------------Price Updated-------------");
						} else {
							System.out.println("---------------Price not Updated------------------");
						}
						break;
					case 3:
						System.out.println("Enter quantity to update : ");
						int quantityToUpdate = myInput.nextInt();
						if (shopController.updateProductPrice(productIdToUpdate, quantityToUpdate)!=0) {
							System.out.println("---------------Quantity Updated----------------");
						} else {
							System.out.println("Quantity not Updated");
						}
						if (shopController.updateProductAvalibility(productIdToUpdate, quantityToUpdate)>0) {
							System.out.println("Product availability : Available");
						} else {
							System.out.println("Product availability : Unavailable");
						}
						break;

					default:
						System.out.println("INVALID SELECTION");
						break;
					}
				} else {
					System.out.println("Product with given id does not exist, Update operation can not be perform.");
				}
				break;
			case 4:
				System.out.println("Enter product id to fetch : ");
				int productIdToFind = myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchProduct = shopController.fetchProduct(productIdToFind);
				boolean next = fetchProduct.next();
				if (next) {
					System.out.println("PRODUCT DETAILS");
					System.out.println("Id : " + fetchProduct.getInt(1));
					System.out.println("Name : " + fetchProduct.getString(2));
					System.out.println("Price : " + fetchProduct.getInt(3));
					System.out.println("Quantity : " + fetchProduct.getInt(4));
					if (fetchProduct.getBoolean(5)) {
						System.out.println("Availabilty : Available");
					} else {
						System.out.println("Availabilty : Not Available");
					}
					System.out.println();
				} else {
					System.out.println("Product with id : " + productIdToFind + " does not exit.");
				}

				break;

			default:
				System.out.println("--------------------------INVALID SECTION----------------------------");
				break;
			}
		} while (true);
	}

}
