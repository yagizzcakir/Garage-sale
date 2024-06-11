import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class GarageSaleDatabase {

    String url = "jdbc:mysql://127.0.0.1:3306/garage_sale";
    String userName = "root";
    String password = "Qj8Kf6c6";

    String customersTable = "CREATE TABLE IF NOT EXISTS CUSTOMERS ("
            + "customerID INT PRIMARY KEY AUTO_INCREMENT,"
            + "first_name VARCHAR(25),"
            + "last_name VARCHAR(25),"
            + "phone_number VARCHAR(15),"
            + "email VARCHAR(50),"
            + "address VARCHAR(500),"
            + "gender VARCHAR(1),"
            + "age INT);";
    String showroomsTable = "CREATE TABLE IF NOT EXISTS SHOWROOMS ("
            + "showroomID INT PRIMARY KEY AUTO_INCREMENT,"
            + "showroom_status VARCHAR(25),"
            + "address VARCHAR(500),"
            + "phone_number VARCHAR(15));";
    String brandsTable = "CREATE TABLE IF NOT EXISTS BRANDS ("
            + "brandID INT PRIMARY KEY AUTO_INCREMENT,"
            + "brand_name VARCHAR(25) UNIQUE,"
            + "country VARCHAR(15),"
            + "phone_number VARCHAR(15));";
    String dealersTable = "CREATE TABLE IF NOT EXISTS DEALERS ("
            + "dealerID INT PRIMARY KEY AUTO_INCREMENT,"
            + "first_name VARCHAR(25),"
            + "last_name VARCHAR(25),"
            + "phone_number VARCHAR(15),"
            + "showroomID INT,"
            + "FOREIGN KEY(showroomID) REFERENCES SHOWROOMS(showroomID));";
    String vehiclesTable = "CREATE TABLE IF NOT EXISTS VEHICLES ("
            + "vehicleID INT PRIMARY KEY AUTO_INCREMENT,"
            + "damage_status VARCHAR(10),"
            + "km INT,"
            + "vehicle_year INT,"
            + "used_status VARCHAR(10),"
            + "model VARCHAR(15),"
            + "color VARCHAR(15),"
            + "brandID INT,"
            + "dealerID INT,"
            + "FOREIGN KEY(brandID) REFERENCES BRANDS(brandID),"
            + "FOREIGN KEY(dealerID) REFERENCES DEALERS(dealerID));";
    String salesTable = "CREATE TABLE IF NOT EXISTS SALES ("
            + "salesID INT PRIMARY KEY AUTO_INCREMENT,"
            + "cost INT,"
            + "description VARCHAR(500),"
            + "sales_date DATE,"
            + "sold_status VARCHAR(10),"
            + "vehicleID INT,"
            + "customerID INT,"
            + "showroomID INT,"
            + "brandID INT,"
            + "FOREIGN KEY(vehicleID) REFERENCES VEHICLES(vehicleID),"
            + "FOREIGN KEY(customerID) REFERENCES CUSTOMERS(customerID),"
            + "FOREIGN KEY(showroomID) REFERENCES SHOWROOMS(showroomID),"
            + "FOREIGN KEY(brandID) REFERENCES BRANDS(brandID));";

    public void createTable(String tableInput) {
        // creates a mysql table using the given input
        try (Connection connection = DriverManager.getConnection(url, userName, password);
                Statement statement = connection.createStatement()) {
            statement.execute(tableInput);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }

    }

    public void insertShowroomsData(String showroom_status, String address, String phone_number) {
        
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

            String insertIntoSQL = "INSERT INTO SHOWROOMS(showroom_status, address, phone_number) VALUES(?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoSQL)) {

                preparedStatement.setString(1, showroom_status); // one prepared statement per each ?
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, phone_number);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // to understand the given exception
        }

    }

    public void insertBrandsData(String brand_name, String country, String phone_number) {

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

            String insertIntoSQL = "INSERT INTO BRANDS(brand_name, country, phone_number) VALUES(?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoSQL)) {

                preparedStatement.setString(1, brand_name);
                preparedStatement.setString(2, country);
                preparedStatement.setString(3, phone_number);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertIntoDealers(String first_name, String last_name, String phone_number, int showroomID) {
        // for dealer sign in page
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

            String insertIntoSQL = "INSERT INTO DEALERS(first_name, last_name, phone_number, showroomID) VALUES(?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoSQL)) {

                preparedStatement.setString(1, first_name);
                preparedStatement.setString(2, last_name);
                preparedStatement.setString(3, phone_number);
                preparedStatement.setInt(4, showroomID);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoCustomers(String first_name, String last_name, String phone_number, String email,
            String address, String gender, int age) {
         // for customer sign in page
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

            String insertIntoSQL = "INSERT INTO CUSTOMERS(first_name, last_name, phone_number, email, address, gender, age) VALUES(?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoSQL)) {

                preparedStatement.setString(1, first_name);
                preparedStatement.setString(2, last_name);
                preparedStatement.setString(3, phone_number);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, gender);
                preparedStatement.setInt(7, age);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getDataTableModel() { // to visualize the data in our database
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("VEHICLE ID");
        tableModel.addColumn("BRAND");
        tableModel.addColumn("MODEL");
        tableModel.addColumn("YEAR");
        tableModel.addColumn("KM");
        tableModel.addColumn("COLOR");
        tableModel.addColumn("STATUS");
        tableModel.addColumn("PRICE(TL)");

        String selectSQL = "SELECT DISTINCT S.salesID, V.vehicleID, B.brand_name, V.model, V.vehicle_year, V.km, V.color, V.used_status, S.cost FROM BRANDS AS B, SALES AS S, VEHICLES AS V WHERE V.vehicleID = S.vehicleID AND B.brandID = V.brandID;";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                String salesID = resultSet.getString("salesID");
                String brand = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("vehicle_year");
                int km = resultSet.getInt("km");
                String color = resultSet.getString("color");
                String status = resultSet.getString("used_status");
                int price = resultSet.getInt("cost");
                int vehicleID = resultSet.getInt("vehicleID");

                tableModel.addRow(new Object[] { salesID, vehicleID, brand, model, year, km, color, status, price });
            }

        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }

        return tableModel;
    }

    public int fetchBrandID(String brandName) {
        // gets the brand id from the brand name 
        String selectSQL = "SELECT brandID FROM BRANDS WHERE brand_name = ?;";
        int brandID = 0;
    
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
    
            preparedStatement.setString(1, brandName);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    brandID = resultSet.getInt("brandID");
                }
            }
    
        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    
        return brandID;
    }
    public void insertIntoBrands(String brand_name, String country, String phone_number) {
        // to add data to brand table
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

            String insertIntoSQL = "INSERT INTO BRANDS(brand_name, country, phone_number) VALUES(?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoSQL)) {

                preparedStatement.setString(1, brand_name);
                preparedStatement.setString(2, country);
                preparedStatement.setString(3, phone_number);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoVehicles(String damage_status, int km, int vehicle_year, String used_status, String model,
            String color, int brandID, int dealerID) {
                // for import button
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

            String insertIntoSQL = "INSERT INTO VEHICLES(damage_status, km, vehicle_year, used_status , model, color, brandID, dealerID ) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoSQL)) {

                if (dealerID == 0) {

                    preparedStatement.setString(1, damage_status);
                    preparedStatement.setInt(2, km);
                    preparedStatement.setInt(3, vehicle_year);
                    preparedStatement.setString(4, used_status);
                    preparedStatement.setString(5, model);
                    preparedStatement.setString(6, color);
                    preparedStatement.setInt(7, brandID);
                    preparedStatement.setString(8, null);

                }

                else{

                    preparedStatement.setString(1, damage_status);
                    preparedStatement.setInt(2, km);
                    preparedStatement.setInt(3, vehicle_year);
                    preparedStatement.setString(4, used_status);
                    preparedStatement.setString(5, model);
                    preparedStatement.setString(6, color);
                    preparedStatement.setInt(7, brandID);
                    preparedStatement.setInt(8, dealerID);

                }

      
                

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoSales(int cost, String description, String sales_date, String sold_status, int vehicleID,
            int showroomID, int brandID) {
                // for import button
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {

            String insertIntoSQL = "INSERT INTO SALES(cost, description, sales_date, sold_status, vehicleID, showroomID, brandID ) VALUES(?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoSQL)) {

                if (showroomID == 0) {

                    preparedStatement.setInt(1, cost);
                    preparedStatement.setString(2, description);
                    preparedStatement.setString(3, sales_date);
                    preparedStatement.setString(4, sold_status);
                    preparedStatement.setInt(5, vehicleID);
                    preparedStatement.setString(6, null);
                    preparedStatement.setInt(7, brandID);

                }

                else {

                    preparedStatement.setInt(1, cost);
                    preparedStatement.setString(2, description);
                    preparedStatement.setString(3, sales_date);
                    preparedStatement.setString(4, sold_status);
                    preparedStatement.setInt(5, vehicleID);
                    preparedStatement.setInt(6, showroomID);
                    preparedStatement.setInt(7, brandID);

                }

                

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void updateVehicleData(int idToUpdate, String damage, int km, int year, String used, String model,
            String color) {
                // for update button
        String updateSQL = "UPDATE VEHICLES SET damage_status = ?, km  = ? , vehicle_year= ? , used_status = ? , model = ?, color = ? WHERE vehicleID = ?";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            // Set parameters for the prepared statement
            preparedStatement.setString(1, damage);
            preparedStatement.setInt(2, km);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, used);
            preparedStatement.setString(5, model);
            preparedStatement.setString(6, color);
            preparedStatement.setInt(7, idToUpdate);

            // Execute the update statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("No data found for update.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating data: " + e.getMessage());
        }
    }

    public void updateSalesData(int idToUpdate, int cost, String description, String date, String sold_status) {
        String updateSQL = "UPDATE SALES SET cost = ?, description  = ? , sales_date= ? , sold_status = ? WHERE salesID = ?";
        // for update button
        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            // Set parameters for the prepared statement
            preparedStatement.setInt(1, cost);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, sold_status);
            preparedStatement.setInt(5, idToUpdate);

            // Execute the update statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("No data found for update.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating data: " + e.getMessage());
        }
    }

    public void deleteData(int idToDelete, String tablename) {
        // for delete button
        String deleteSQL;
        deleteSQL = " ";

        if (tablename.equalsIgnoreCase("VEHICLES")) {
            deleteSQL = "DELETE FROM VEHICLES WHERE vehicleID = ?";
        } else if (tablename.equalsIgnoreCase("SALES")) {
            deleteSQL = "DELETE FROM SALES WHERE salesID = ?";
        }

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            // Set the parameter for the prepared statement
            preparedStatement.setInt(1, idToDelete);

            // Execute the delete statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("No data found for deletion.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting data: " + e.getMessage());
        }
    }

    public DefaultTableModel getDataTableModelFromBrands() { // for customer brand filter
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("BRAND");
        tableModel.addColumn("MODEL");
        tableModel.addColumn("YEAR");
        tableModel.addColumn("KM");
        tableModel.addColumn("COLOR");
        tableModel.addColumn("STATUS");
        tableModel.addColumn("PRICE(TL)");

        String selectSQL = "SELECT DISTINCT S.salesID, B.brand_name, V.model, V.vehicle_year, V.km, V.color, V.used_status, S.cost FROM BRANDS AS B, SALES AS S, VEHICLES AS V WHERE V.vehicleID = S.vehicleID AND B.brandID = V.brandID AND showroomID is NULL;";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                String salesID = resultSet.getString("salesID");
                String brand = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("vehicle_year");
                int km = resultSet.getInt("km");
                String color = resultSet.getString("color");
                String status = resultSet.getString("used_status");
                int price = resultSet.getInt("cost");

                tableModel.addRow(new Object[] { salesID, brand, model, year, km, color, status, price });
            }

        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }

        return tableModel;
    }

    public DefaultTableModel getDataTableModelFromShowrooms() { // for customer showroom filter
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("BRAND");
        tableModel.addColumn("MODEL");
        tableModel.addColumn("YEAR");
        tableModel.addColumn("KM");
        tableModel.addColumn("COLOR");
        tableModel.addColumn("STATUS");
        tableModel.addColumn("PRICE(TL)");

        String selectSQL = "SELECT DISTINCT S.salesID, B.brand_name, V.model, V.vehicle_year, V.km, V.color, V.used_status, S.cost FROM BRANDS AS B, SALES AS S, VEHICLES AS V WHERE V.vehicleID = S.vehicleID AND B.brandID = V.brandID AND showroomID is not NULL;";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                int salesID = resultSet.getInt("salesID");
                String brand = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("vehicle_year");
                int km = resultSet.getInt("km");
                String color = resultSet.getString("color");
                String status = resultSet.getString("used_status");
                int price = resultSet.getInt("cost");

                tableModel.addRow(new Object[] { salesID, brand, model, year, km, color, status, price });
            }

        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }

        return tableModel;
    }

    public String costFilterString(int low, int high) { // for cost filter 

        String query = "SELECT DISTINCT S.salesID, B.brand_name, V.model, V.vehicle_year, V.km, V.color, V.used_status, S.cost FROM BRANDS AS B, SALES AS S, VEHICLES AS V WHERE V.vehicleID = S.vehicleID AND B.brandID = V.brandID AND S.cost BETWEEN "
                + low + " AND " + high + " ORDER BY cost ASC;";

        return query;
    }

    // Method to update data in the database
    public DefaultTableModel getDataTableModelCostFilter(int low, int high) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("BRAND");
        tableModel.addColumn("MODEL");
        tableModel.addColumn("YEAR");
        tableModel.addColumn("KM");
        tableModel.addColumn("COLOR");
        tableModel.addColumn("STATUS");
        tableModel.addColumn("PRICE(TL)");

        String query = this.costFilterString(low, high);

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                String salesID = resultSet.getString("salesID");
                String brand = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("vehicle_year");
                int km = resultSet.getInt("km");
                String color = resultSet.getString("color");
                String status = resultSet.getString("used_status");
                int price = resultSet.getInt("cost");

                tableModel.addRow(new Object[] { salesID, brand, model, year, km, color, status, price });
            }

        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }

        return tableModel;
    }

    public String BrandFilterString(String brand) { // for brand filter

        String query = "SELECT DISTINCT S.salesID, B.brand_name, V.model, V.vehicle_year, V.km, V.color, V.used_status, S.cost FROM BRANDS AS B, SALES AS S, VEHICLES AS V WHERE V.vehicleID = S.vehicleID AND B.brandID = V.brandID AND B.brand_name LIKE '%" + brand + "%';";
                
        return query;
    }

    // Method to update data in the database
    public DefaultTableModel getDataTableModelBrandFilter(String brandName) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("BRAND");
        tableModel.addColumn("MODEL");
        tableModel.addColumn("YEAR");
        tableModel.addColumn("KM");
        tableModel.addColumn("COLOR");
        tableModel.addColumn("STATUS");
        tableModel.addColumn("PRICE(TL)");

        String query = this.BrandFilterString(brandName);

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                String salesID = resultSet.getString("salesID");
                String brand = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("vehicle_year");
                int km = resultSet.getInt("km");
                String color = resultSet.getString("color");
                String status = resultSet.getString("used_status");
                int price = resultSet.getInt("cost");

                tableModel.addRow(new Object[] { salesID, brand, model, year, km, color, status, price });
            }

        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }

        return tableModel;
    }

    public DefaultTableModel getDataTableModelGeneralInfo() { // for general info button
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("BRAND");
        tableModel.addColumn("AVERAGE PRICE");
        tableModel.addColumn("TOTAL MARKET VALUE");
        tableModel.addColumn("MINIMUM PRICE");
        tableModel.addColumn("MAXIMUM PRICE");
        tableModel.addColumn("NUMBER OF CARS");

        String selectSQL = "SELECT B.brand_name, AVG(S.cost) as avg, SUM(S.cost) as sum, MIN(S.cost) as min, MAX(S.Cost) as max, COUNT(V.VehicleID) as number FROM BRANDS AS B, SALES AS S, VEHICLES AS V WHERE V.vehicleID = S.vehicleID AND B.brandID = V.brandID GROUP BY B.brand_name HAVING COUNT(*) > 0 ORDER BY b.brand_name";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                String brand = resultSet.getString("brand_name");
                int avg = resultSet.getInt("avg");
                int sum = resultSet.getInt("sum");
                int min = resultSet.getInt("min");
                int max = resultSet.getInt("max");
                int number = resultSet.getInt("number");

                tableModel.addRow(new Object[] { brand, avg, sum, min, max, number });
            }

        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }

        return tableModel;
    }
}
