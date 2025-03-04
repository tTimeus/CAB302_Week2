public class Main {
    // Static list of users, acting as a database
    private static ArrayList<User> users = new ArrayList<>();

    // Mock authentication service that always returns the first user when log in, and does nothing when sign up
    private static IAuthenticationService authService = new IAuthenticationService() {
        @Override
        public User signUp(String username, String password) {
            return null;
        }

        @Override
        public User logIn(String username, String password) {
            return users.get(0);
        }
    };
    private static boolean isRunning = true;

    /**
     * The entry point of the application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        users.add(new User("test", "test"));
        while (isRunning) {
            showMenu();
        }
    }

    /**
     * Displays the main menu to the user.
     */
    public static void showMenu() {
        System.out.println("Welcome to the To-Do List Application!");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        // Ask for user choice
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        handleMenu(choice);
    }

    /**
     * Handles the user's choice, mapping the menu options to the corresponding methods.
     * @param choice The user's choice.
     */
    public static void handleMenu(int choice) {
        switch (choice) {
            case 1:
                onLogIn();
                break;
            case 2:
                onSignUp();
                break;
            case 3:
                onExit();
                break;
            default:
                System.out.println("Invalid choice!");
                showMenu();
        }
    }

    /**
     * Handles the log-in process, and the post-login operations.
     */
    public static void onLogIn() {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = authService.logIn(username, password);
        System.out.println("Welcome, " + user.getUsername() + "!");
        // TODO Later: Add the to-do list operations
    }

    /**
     * Handles the sign-up process.
     */
    public static void onSignUp() {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = authService.signUp(username, password);
        // TODO Later: Shows a message based on the result
    }

    /**
     * Exits the application by setting the `isRunning` flag to false.
     */
    public static void onExit() {
        isRunning = false;
    }
}

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username of the user.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

public interface IAuthenticationService {
    User signUp(String username, String password);
    User logIn(String username, String password);
}

public class ToDoItem {
    private String description;
    private boolean isDone;

    // TODO Now:
    // Add a constructor to initialize the item with the description, and isDone as false, with a single parameter for the description
    // Add getters and setters for each field
}

public class User {
    // ... (other fields and methods)

    private ArrayList<ToDoItem> toDoItems;

    public User(String username, String password) {
        // ... (other initializations)
        this.toDoItems = new ArrayList<>();
    }

    /**
     * Gets the to-do items of the user.
     * @return A reference to the list of to-do items, which can be modified from outside the class.
     */
    public ArrayList<ToDoItem> getToDoItems() {
        return toDoItems;
    }
}
public class ToDoList {
    private User user;
    private boolean isRunning;

    /**
     * Initializes the to-do list with the specified user.
     * @param user The user of the to-do list.
     */
    public ToDoList(User user) {
        this.user = user;
        this.isRunning = true;
    }

    /**
     * Displays the main menu to the user.
     */
    private void showMenu() {
        System.out.println("1. Show all items");
        System.out.println("2. Add an item");
        System.out.println("3. Mark an item as done");
        System.out.println("4. Mark an item as undone");
        System.out.println("5. Delete an item");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        handleMenu(choice);
    }

    /**
     * Loops the showMenu method based on the `isRunning` flag.
     */
    public void run() {
        while (isRunning) {
            showMenu();
        }
    }

    /**
     * Handles the user's choice, mapping the menu options to the corresponding methods.
     * @param choice The user's choice.
     */
    public void handleMenu(int choice) {
        switch (choice) {
            case 1:
                onShowAllItems();
                break;
            case 2:
                onAddItem();
                break;
            case 3:
                onMarkAsDone();
                break;
            case 4:
                onMarkAsUndone();
                break;
            case 5:
                onDeleteItem();
                break;
            case 6:
                onExit();
                break;
            default:
                System.out.println("Invalid choice!");
                showMenu();
        }
    }

    /**
     * Displays all the items in the list.
     */
    public void onShowAllItems() {
        for (int i = 0; i < user.getToDoItems().size(); i++) {
            ToDoItem item = user.getToDoItems().get(i);
            if (item.isDone()) {
                System.out.println(i + ". [X] " + item.getDescription());
            } else {
                System.out.println(i + ". [ ] " + item.getDescription());
            }
        }
    }

    /**
     * Requests the user for a new item description and adds it to the list.
     */
    public void onAddItem() {
        System.out.print("Enter the description of the item: ");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.nextLine();
        // TODO Now: Call the add(ToDoItem item) method of the user's to-do items list to add a new item
    }
    /**
     * Requests the user for an item number and marks it as done.
     */
    public void onMarkAsDone() {
        onShowAllItems();
        System.out.print("Enter the number of the item to mark as done: ");
        Scanner scanner = new Scanner(System.in);
        int itemNumber = scanner.nextInt();
        // TODO Now: Set the isDone field of the item at the specified index to true
    }
    /**
     * Requests the user for an item number and marks it as undone.
     */
    public void onMarkAsUndone() {
        onShowAllItems();
        System.out.print("Enter the number of the item to mark as undone: ");
        Scanner scanner = new Scanner(System.in);
        int itemNumber = scanner.nextInt();
        // TODO Now: Set the isDone field of the item at the specified index to false
    }
    /**
     * Requests the user for an item number and deletes it from the list.
     */
    public void onDeleteItem() {
        onShowAllItems();
        System.out.print("Enter the number of the item to delete: ");
        Scanner scanner = new Scanner(System.in);
        int itemNumber = scanner.nextInt();
        // TODO Now: Remove the item at the specified index from the user's to-do items list
    }
    /**
     * Exits the application, which sets the `isRunning` flag to false.
     */
    public void onExit() {
        isRunning = false;
    }
}

class Main {
    // ... (other methods and fields)

    public static void onLogIn() {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = authService.logIn(username, password);
        System.out.println("Welcome, " + user.getUsername() + "!");
        // TODO Now: Create an instance of the ToDoList class with the logged-in user and call the run method
    }
}