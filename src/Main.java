import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        SalesSystem salesSystem = new SalesSystem();
        
        // Menu loop
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = salesSystem.getValidIntInput();

            switch (choice) {
                case 1:
                    // Admin menu
                    salesSystem.adminMenu();
                    break;
                case 2:
                    // User menu
                    salesSystem.customerMenu();
                    break;
                case 3:
                    // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    Runtime runtime = Runtime.getRuntime();
//            		runtime.gc();
            		long total = runtime.totalMemory();
            		long free = runtime.freeMemory();
            		long usedMemory = total - free ;
            		System.out.println("Total memory " + usedMemory + "bytes");
            		long megabytes = usedMemory / (1024L * 1024L);
            		System.out.println("Total memory " + megabytes + "MB");
                    scanner.close();
                    System.exit(0);
                    
                    
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            
        }
        
   }
}
