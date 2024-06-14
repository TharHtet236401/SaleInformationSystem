import java.util.ArrayList;
import java.util.Scanner;

public interface ISalesSystem {
	
    public void addProducts(int numberOfProducts);
    public void displayProducts();
    public void displayProductsMenu( );
    public void sortProductsByName();
    public void sortProductsByPrice();
    public void sortProductsByID();
    public void updateInteraction();
    public Product searchProductByID(int productId);
    public Product searchProductByName(String productName);
    public void adminMenu();
    public void customerMenu();
    public void purchaseProduct();
    public void checkout( ArrayList<Purchase> purchasedProducts, double totalCost);
    
    public int getValidIntInput();
    public double getValidDoubleInput();
    public String getValidStringInput();
    
}


