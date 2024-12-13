import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {

    // Fields
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private Map<String, Integer> sales = new HashMap<>();
    private String name;
    private String branch;

    // Constructor
    public Shop(String name, String branch) {
        this.name = name;
        this.branch = branch;
    }

    // Product-related methods
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product.getName());
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
        System.out.println("Product removed: " + productId);
    }

    public void searchProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.showDetails();
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available products:");
            for (Product product : products) {
                product.showDetails();
            }
        }
    }

    // Customer-related methods
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer.getName());
    }

    public void removeCustomer(String customerId) {
        customers.removeIf(customer -> customer.getCustomerId().equals(customerId));
        System.out.println("Customer removed: " + customerId);
    }

    public void searchCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.showDetails();
                return;
            }
        }
        System.out.println("Customer not found.");
    }

    public void showAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            for (Customer customer : customers) {
                customer.showDetails();
            }
        }
    }

    // Shop details
    public void showShopDetails() {
        System.out.println("Super Shop: " + name + ", Branch: " + branch);
    }

    // Sales Management
    public void sellProduct(String productId, int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    sales.put(productId, sales.getOrDefault(productId, 0) + quantity);
                    System.out.println("Product sold: " + product.getName());
                    return;
                } else {
                    System.out.println("Insufficient stock.");
                    return;
                }
            }
        }
        System.out.println("Product not found.");
    }

    public void showSalesReport() {
        if (sales.isEmpty()) {
            System.out.println("No sales made.");
        } else {
            for (Map.Entry<String, Integer> sale : sales.entrySet()) {
                Product product = findProductById(sale.getKey());
                System.out.println("Product: " + (product != null ? product.getName() : "Unknown") + ", Quantity Sold: " + sale.getValue());
            }
        }
    }

    // Helper method
    private Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
}