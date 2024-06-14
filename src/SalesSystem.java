import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SalesSystem implements ISalesSystem {
    private ArrayList<Product> products;
    int validCheck = 1 ;
    Scanner scanner = new Scanner(System.in); 
    
    
    //done
    public SalesSystem() {
        this.products = new ArrayList<>();
        String[] productNames = {
        		 "milk", "bread", "egg", "chips", "soda", "candy", "chocolate", "water", "coffee", "tea",
                 "instant noodles", "cigarettes", "beer", "wine", "energy drink", "gum", "ice cream", "sandwich", 
                 "snack bars", "magazines", "newspapers", "batteries", "phone chargers", "lighters", "tissues", 
                 "toilet paper", "shampoo", "conditioner", "soap", "toothpaste", "toothbrush", "deodorant", 
                 "shaving cream", "razors", "hand sanitizer", "band-aids", "medications", "baby food", 
                 "diapers", "trash bags", "dish soap", "cleaning supplies", "laundry detergent", "fabric softener", 
                 "light bulbs", "flashlights", "matches", "pens", "notebooks", "envelopes", "postage stamps", 
                 "scissors", "tape", "glue", "balloons", "party supplies", "plastic utensils", "paper plates", 
                 "paper towels", "napkins", "disposable cups", "aluminum foil", "plastic wrap", "ziploc bags", 
                 "candles", "incense", "air fresheners", "pet food", "cat litter", "collar", "leash", "toys", 
                 "sunglasses", "umbrella", "sunscreen", "insect repellent", "hats", "gloves", "scarves", 
                 "earbuds", "phone cases", "usb cables", "sd cards", "dvds", "video games", "playing cards", 
                 "board games", "earphones", "bluetooth speakers", "portable chargers", "travel adapters", 
                 "travel pillows", "luggage tags", "passport holders", "travel bottles", "travel size toiletries"
        	};


            
            for (int i = 0; i < productNames.length; i++) {
                String name = productNames[i % productNames.length];
                int price = (int) (Math.random() * 100) + 1; 
                int quantity = (int) (Math.random() * 100) + 1;  
                products.add(new Product(name, price, quantity));
                
            }
    }

    //done 
    @Override
    public void addProducts(int numberOfProducts) {
    	
        for (int i = 0; i < numberOfProducts; i++) {
            System.out.print("Enter product name: ");
            String name = getValidStringInput();
            System.out.print("Enter product price: ");
            double price = getValidDoubleInput();
            System.out.print("Enter product stock: ");
            int stock = getValidIntInput();
            int productId = products.size() + 1;
            products.add(new Product(name, price, stock));
            
            
        }
        
        System.out.println(" Items added to the database");
    }

    
    
    //done
    @Override
    public void displayProducts() {
        // Print the table header with a border
        System.out.println("==============================================================");
        System.out.printf("| %-10s | %-15s | %-10s | %-10s |%n", "Product ID", "Name", 
        		"Price", "Stock");
        System.out.println("==============================================================");

        // Iterate over each product and print its details
        for (Product product : products) {
            System.out.printf("| %-10d | %-15s | $%-9.2f | %-10d |%n",
                    product.getProductId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock());
        }

        // Print the bottom border of the table
        System.out.println("==============================================================");
    }

    //done
    public void updateInteraction() {
        System.out.print("Do you want to update stock, price, or delete a product? (yes/no): ");
        scanner.nextLine(); // Consume newline
        String response = scanner.nextLine();
        while (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter the Product ID to update: ");
            int productId = getValidIntInput();
            Product product = searchProductByID(productId);
            if (product == null) {
                System.out.println("Invalid Product ID. Please try again.");
                continue;
            }
            System.out.print("Enter 's' to update stock, 'p' to update price, or 'd' to delete: ");
            char updateType = scanner.next().charAt(0);
            if (updateType == 's') {
                System.out.print("Enter the amount to update "
                		+ "(positive to increase, negative to decrease): ");
                int amount = getValidIntInput();
                updateProductStock(productId, amount);
            } else if (updateType == 'p') {
                System.out.print("Enter the new price: ");
                double newPrice = getValidDoubleInput();
                updateProductPrice(productId, newPrice);
            } else if (updateType == 'd') {
                deleteProduct(productId);
            } else {
                System.out.println("Invalid update type entered.");
            }

            System.out.print("Do you want to update another product? (yes/no): ");
            scanner.nextLine(); // Consume newline
            response = scanner.nextLine();
        }
    }

    //done
    
    public void updateProductStock(int productId, int amount) {
        Product product = searchProductByID(productId);
        if (product != null) {
            product.setStock(product.getStock() + amount);
            System.out.println("Product stock updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
    //done
    public void updateProductPrice(int productId, double newPrice) {
        Product product = searchProductByID(productId);
        if (product != null) {
            product.setPrice(newPrice);
            System.out.println("Product price updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
    
    //done
    public void deleteProduct(int productId) {
        Product product = searchProductByID(productId);
        if (product != null) {
            products.remove(product);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    
    //done
	    public void sortProductsByName() {
	   
	        int n = products.size();
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (products.get(j).getName().compareTo(products.get(j + 1).getName()) > 0) {         
	                    Product temp = products.get(j);
	                    products.set(j, products.get(j + 1));
	                    products.set(j + 1, temp);
	                }
	            }
	        }
	       
	    
	    }

   
    //done
    @Override
    public void sortProductsByPrice() {
        int n = products.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (products.get(j).getPrice() < products.get(j + 1).getPrice()) {
                    Product temp = products.get(j);
                    products.set(j, products.get(j + 1));
                    products.set(j + 1, temp);
                }
            }
        }
       
    }
    
    
    //done
    public void sortProductsByID() {
        int n = products.size();
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - i - 2; j++) {
                if (products.get(j).getProductId() > products.get(j + 1).getProductId()) {
                    Product temp = products.get(j);
                    products.set(j, products.get(j + 1));
                    products.set(j + 1, temp);
                }
            }
        }
     
    }
    
    //done
    public Product searchProductByID(int productId) {
        sortProductsByID(); // Ensure the list is sorted by ID for binary search
      

        int left = 0;
        int right = products.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = products.get(mid);
            if (midProduct.getProductId() == productId) {
                return midProduct; // Product found
            } else if (midProduct.getProductId() < productId) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return null; // Product not found
    }


    
    //done
    public Product searchProductByName(String productName) {
    	
        sortProductsByName(); // Ensure the list is sorted by ID for binary search
        
        int left = 0;
        int right = products.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = products.get(mid);
            int compareResult = midProduct.getName().compareToIgnoreCase(productName);

            if (compareResult == 0) {
                return midProduct; // Product found
            } else if (compareResult < 0) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return null; // Product not found
    }
    
    
    
    
    public void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Products");
            System.out.println("2. Display Products");
            System.out.println("3. Update Product");
            System.out.println("4. Search Product by ID");
            System.out.println("5. Search Product by Name");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    // Add products
                    System.out.print("Enter the number of products to add: ");
                    int numberOfProducts = getValidIntInput();
                    scanner.nextLine();
                    addProducts(numberOfProducts);
                    
                    break;
                case 2:
                    // Display products with sorting options
                    displayProductsMenu();
                    break;
                case 3:
                    // Update interaction
                    updateInteraction();
                    break;
                case 4:
                    // Search product by ID
                    System.out.print("Enter the product ID to search: ");
                    int productId = getValidIntInput();
                    Product searchedProduct = searchProductByID(productId);
                    if (searchedProduct != null) {
                        System.out.printf("Product found: %s (ID: %d, Price: %.2f, Stock: %d)%n",
                                searchedProduct.getName(), searchedProduct.getProductId(),
                                searchedProduct.getPrice(), searchedProduct.getStock());
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 5:
                	  // Search product by Name
                    System.out.print("Enter the product name to search: ");
                    String productName = getValidStringInput();
                   
                    Product searchedName = searchProductByName(productName);
                    if (searchedName != null) {
                        System.out.printf("Product found: %s (ID: %d, Price: %.2f, Stock: %d)%n",
                                searchedName.getName(), searchedName.getProductId(),
                                searchedName.getPrice(), searchedName.getStock());
                    } else {
                        System.out.println("Product not found .");
                    }
                    break;
                case 6:
                    // Back to main menu
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    
    public void customerMenu() {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View and Sort Products");
            System.out.println("2. Purchase Product");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Search Product by Name");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    // Display products with sorting options
                    displayProductsMenu( );
                    break;
                case 2:
                    // Purchase product
                    purchaseProduct();
                    break;
                case 3:
                    // Search product by ID
                    System.out.print("Enter the product ID to search: ");
                    int userProductId = getValidIntInput();
                    Product searchedUserProduct = searchProductByID(userProductId);
                    if (searchedUserProduct != null) {
                        System.out.printf("Product found: %s (ID: %d, Price: %.2f, Stock: %d)%n",
                                searchedUserProduct.getName(), searchedUserProduct.getProductId(),
                                searchedUserProduct.getPrice(), searchedUserProduct.getStock());
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
              	  // Search product by Name
                  System.out.print("Enter the product name to search: ");
                  String productName = getValidStringInput();
                  Product searchedName = searchProductByName(productName);
                  if (searchedName != null) {
                      System.out.printf("Product found: %s (ID: %d, Price: %.2f, Stock: %d)%n",
                              searchedName.getName(), searchedName.getProductId(),
                              searchedName.getPrice(), searchedName.getStock());
                  } else {
                      System.out.println("Product not found.");
                  }
                  break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    
    // done
    public void displayProductsMenu( ) {
        while (true) {
            System.out.println("\nDisplay Products Menu:");
            System.out.println("1. Sort by Name");
            System.out.println("2. Sort by Price (Descending)");
            System.out.println("3. Sort by ID");
            System.out.println("4. Back to Previous Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    // Sort by name
                    sortProductsByName();
                    displayProducts();
                    break;
                case 2:
                    // Sort by price (descending)
                   sortProductsByPrice();
                    displayProducts();
                    break;
                case 3:
                    // Sort by ID
                    sortProductsByID();
                    displayProducts();
                    break;
                case 4:
                    // Back to previous menu
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    //done 
    public void purchaseProduct() {
        double totalCost = 0;
        ArrayList<Purchase> purchasedProducts = new ArrayList<>();

        while (true) {
            System.out.print("Enter the product ID to purchase: ");
            int productId = getValidIntInput();
            Product product = searchProductByID(productId);

            if (product != null) {
                System.out.printf("Selected Product: %s (ID: %d, Price: %.2f, Stock: %d)%n",
                        product.getName(), product.getProductId(), 
                        product.getPrice(), product.getStock());
                System.out.print("Enter the quantity to purchase: ");
                int quantity = getValidIntInput();

                if (quantity > 0 && quantity <= product.getStock()) {
                    totalCost += product.getPrice() * quantity;
                    purchasedProducts.add(new Purchase(product, quantity));
                    System.out.println("Product added to cart.");
                    validCheck = 1;
                } else {
                    System.out.println("Invalid quantity. Please try again.");
                }
            } else {
                System.out.println("Product not found.");
                validCheck = 0 ;
               
            }

            System.out.print("Do you want to purchase another product? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Checkout process
        while(validCheck != 0 ) {
        	checkout(purchasedProducts, totalCost);
        	validCheck = 0 ;
        }
        
    }

    
    //done
    public void checkout(ArrayList<Purchase> purchasedProducts, double totalCost) {
        System.out.println("Purchased products:");
        for (Purchase purchase : purchasedProducts) {
            Product product = purchase.getProduct();
            System.out.printf("ID: %d, Name: %s, Quantity: %d, Subtotal: %.2f%n",
                    product.getProductId(), product.getName(), purchase.getQuantity(),
                    product.getPrice() * purchase.getQuantity());
        }
        System.out.println("\nCheckout:");
        System.out.printf("Total cost: %.2f%n", totalCost);
        System.out.print("Do you want to confirm the purchase? (yes/no): ");

        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            for (Purchase purchase : purchasedProducts) {
                Product purchasedProduct = purchase.getProduct();
                Product product = searchProductByID(purchasedProduct.getProductId());
                if (product != null) {
                    int remainingStock = product.getStock() - purchase.getQuantity();
                    product.setStock(remainingStock);
                }
            }
            System.out.println("Purchase confirmed. Thank you for shopping!");
        } else {
            System.out.println("Purchase canceled.");
        }
    }

    
    
    //done
    public int getValidIntInput() {
        int input = 0;
        boolean valid = false;

        while (!valid) {
            try {
                input = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
                
            }
        }

        return input;
    }

    //done
    public double getValidDoubleInput() {
        double input = 0;
        boolean valid = false;

        while (!valid) {
            try {
                input = scanner.nextDouble();
                
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double.");
                scanner.nextLine(); 
                
            }
        }

        return input;
    }

    //done
    public String getValidStringInput() {
    	 String productName = null;
    	 boolean valid = false;
         while (!valid) {
             try {
                 productName = scanner.next();
                 if (!productName.matches("[a-zA-Z]+")) {
                     throw new InputMismatchException("Invalid input. Please enter "
                     		+ "a valid product name.");
                 }
                
                 break; 
             } catch (InputMismatchException e) {
                 System.out.println(e.getMessage());
             }
         }
        return productName;
    }
}
