import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop("Super Mart", "Main Street");

        while (true) {
            try {
                System.out.println("\nChoose an option:");
                System.out.println("1. Product Management\n2. Customer Management\n3. Sales\n4. Shop Info\n5. Exit");
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.println("\n1. Add Product\n2. Remove Product\n3. Search Product\n4. Show All Products\n5. Back");
                        int productChoice = Integer.parseInt(scanner.nextLine().trim());
                        switch (productChoice) {
                            case 1:
                                System.out.println("Enter Product ID, Name, Price, Quantity:");
                                shop.addProduct(new Product(scanner.nextLine(), scanner.nextLine(),
                                        Double.parseDouble(scanner.nextLine().trim()), Integer.parseInt(scanner.nextLine().trim())));
                                break;
                            case 2:
                                System.out.println("Enter Product ID to Remove:");
                                shop.removeProduct(scanner.nextLine());
                                break;
                            case 3:
                                System.out.println("Enter Product Name to Search:");
                                shop.searchProduct(scanner.nextLine());
                                break;
                            case 4:
                                shop.displayProducts();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;
                    case 2:

                        System.out.println("\n1. Add Customer\n2. Remove Customer\n3. Search Customer\n4. Show All Customers\n5. Back");
                        int customerChoice = Integer.parseInt(scanner.nextLine().trim());
                        switch (customerChoice) {
                            case 1:
                                System.out.println("Enter Customer ID, Name, Phone:");
                                shop.addCustomer(new Customer(scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                                break;
                            case 2:
                                System.out.println("Enter Customer ID to Remove:");
                                shop.removeCustomer(scanner.nextLine());
                                break;
                            case 3:
                                System.out.println("Enter Customer ID to Search:");
                                shop.searchCustomer(scanner.nextLine());
                                break;
                            case 4:
                                shop.showAllCustomers();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;
                    case 3:
                        System.out.println("\n1. Sell Product\n2. Show Sales Report\n3. Back");
                        int salesChoice = Integer.parseInt(scanner.nextLine().trim());
                        switch (salesChoice) {
                            case 1:
                                System.out.println("Enter Product ID and Quantity:");
                                shop.sellProduct(scanner.nextLine(), Integer.parseInt(scanner.nextLine().trim()));
                                break;
                            case 2:
                                shop.showSalesReport();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;
                    case 4:
                        shop.showShopDetails();
                        break;
                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }
}