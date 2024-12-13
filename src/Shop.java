import java.io.*;
import java.util.*;

class Shop {
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private Map<String, Integer> sales = new HashMap<>();
    private String name;
    private String branch;

    public Shop(String name, String branch) {
        this.name = name;
        this.branch = branch;


        loadProductsFromFile("products.csv");
        loadCustomersFromFile("customers.csv");
    }


    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product.getName());
        saveProductsToFile("products.csv"); // Save to file
    }


    public void removeProduct(String productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
        System.out.println("Product removed with ID: " + productId);
        saveProductsToFile("products.csv"); // Save to file
    }


    public void searchProduct(String name) {
        products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresentOrElse(Product::showDetails, () -> System.out.println("Product not found."));
    }


    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available products:");
            products.forEach(Product::showDetails);
        }
    }


    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer.getName());
        saveCustomersToFile("customers.csv"); // Save to file
    }

    public void removeCustomer(String customerId) {
        customers.removeIf(customer -> customer.getCustomerId().equals(customerId));
        System.out.println("Customer removed with ID: " + customerId);
        saveCustomersToFile("customers.csv"); // Save to file
    }


    public void searchCustomer(String customerId) {
        customers.stream()
                .filter(customer -> customer.getCustomerId().equalsIgnoreCase(customerId))
                .findFirst()
                .ifPresentOrElse(Customer::showDetails, () -> System.out.println("Customer not found."));
    }

    // Show All Customers
    public void showAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("Customers:");
            customers.forEach(Customer::showDetails);
        }
    }

    public void sellProduct(String productId, int quantity) {
        products.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .ifPresentOrElse(product -> {
                    if (product.getQuantity() >= quantity) {
                        product.setQuantity(product.getQuantity() - quantity);
                        sales.put(productId, sales.getOrDefault(productId, 0) + quantity);
                        System.out.println("Sale Successful! Sold: " + quantity + " of " + product.getName());
                        saveProductsToFile("products.csv");
                    } else {
                        System.out.println("Insufficient stock for product " + product.getName());
                    }
                }, () -> System.out.println("Product not found."));
    }

    public void showSalesReport() {
        System.out.println("Sales Report:");
        if (sales.isEmpty()) {
            System.out.println("No sales recorded yet.");
        } else {
            sales.forEach((productId, quantitySold) -> {
                products.stream()
                        .filter(product -> product.getProductId().equals(productId))
                        .findFirst()
                        .ifPresent(product -> System.out.println("Product: " + product.getName() + ", Quantity Sold: " + quantitySold));
            });
        }
    }

    public void showShopDetails() {
        System.out.println("Shop Name: " + name + ", Branch: " + branch);
    }

    public void saveProductsToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                writer.write(product.getProductId() + "," +
                        product.getName() + "," +
                        product.getPrice() + "," +
                        product.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProductsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                products.add(new Product(data[0], data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing products file found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCustomersToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customers) {
                writer.write(customer.getCustomerId() + "," +
                        customer.getName() + "," +
                        customer.getPhoneNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomersFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                customers.add(new Customer(data[0], data[1], data[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing customers file found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}