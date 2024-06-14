public class Product {
	
	
	private static int lastProductId = 0;
    private int productId;
    private String name;
    private double price;
    private int stock;
	
    public Product(String name, double price, int stock) {
        this.productId = ++lastProductId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    
    public void setProductId(int productId) {
		this.productId = productId;
	}
    
	public void setName(String name) {
		this.name = name;
	}

	public void setStock(int stock) {
	    if (stock >= 0) {
	        this.stock = stock;
	    } else {
	        throw new IllegalArgumentException("Stock quantity cannot be negative.");
	    }
	}

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be positive.");
        }
    }
    
    
    
    
}
