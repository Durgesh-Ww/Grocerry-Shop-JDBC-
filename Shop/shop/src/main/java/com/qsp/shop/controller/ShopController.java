package com.qsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.shop.model.Product;

public class ShopController {
	Connection connection = null;
	int rowsAffected = 0;

	public int addProduct(int id, String name, int price, int quantity, boolean avaibility) {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?);");
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, avaibility);
			rowsAffected = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
	}

	public void addMultipleProducts(ArrayList<Product> products) {
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?);");
			for (Product product : products) {
				prepareStatement.setInt(1, product.getP_id());
				prepareStatement.setString(2, product.getP_name());
				prepareStatement.setInt(3, product.getP_price());
				prepareStatement.setInt(4, product.getp_quantity());
				prepareStatement.setBoolean(5, product.getP_availability());
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ResultSet fetchProduct(int id) {
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM product WHERE p_id=?");
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}

	public int removeProduct(int id) {
		int idDeleted = 0;
		Connection connection = null;

		Driver driver = new Driver();
		try {
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM product Where p_id=?");
			prepareStatement.setInt(1, id);
			idDeleted = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return idDeleted;
	}
	
	public int updateProductName(int id, String name) {
		int updateVerify = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_name=? WHERE p_id=?");
			prepareStatement.setString(1, name);
			prepareStatement.setInt(2, id);
			updateVerify = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateVerify;
	}
	
	public int updateProductPrice(int id, int price) {
		int updateVerify = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_price=? WHERE p_id=?");
			prepareStatement.setInt(1, price);
			prepareStatement.setInt(2, id);
			updateVerify = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateVerify;
	}
	
	public int updateProductQuantity(int id, int quantity) {
		int updateVerify = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_quantity=? WHERE p_id=?");
			prepareStatement.setInt(1, quantity);
			prepareStatement.setInt(2, id);
			updateVerify = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateVerify;
	}
	public int updateProductAvalibility(int id, int quantity) {
		int updateVerify = 0;
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_availability=? WHERE p_id=?");
			if (quantity>0) {
				prepareStatement.setBoolean(1, true);
			} else {
				prepareStatement.setBoolean(1, false);
			}
			prepareStatement.setInt(2, id);
			updateVerify = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateVerify;
	}
}
	
		
	


