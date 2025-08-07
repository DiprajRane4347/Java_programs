import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class LibraryManagementSystem extends JFrame {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    
    private JTextField bookIdField, titleField, authorField, userIdField, userNameField, searchField;
    private JCheckBox availableCheckBox;
    private JTextArea displayArea;
    private JComboBox<String> searchComboBox;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize components
        JPanel bookPanel = createBookPanel();
        JPanel userPanel = createUserPanel();
        JPanel transactionPanel = createTransactionPanel();
        JPanel searchPanel = createSearchPanel();
        
        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        // Tabbed pane for different functionalities
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Books", bookPanel);
        tabbedPane.addTab("Users", userPanel);
        tabbedPane.addTab("Transactions", transactionPanel);
        tabbedPane.addTab("Search", searchPanel);
        
        // Add components to frame
        add(tabbedPane, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Book Management"));
        
        panel.add(new JLabel("Book ID:"));
        bookIdField = new JTextField();
        panel.add(bookIdField);
        
        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);
        
        panel.add(new JLabel("Author:"));
        authorField = new JTextField();
        panel.add(authorField);
        
        panel.add(new JLabel("Available:"));
        availableCheckBox = new JCheckBox();
        panel.add(availableCheckBox);
        
        JButton addBookBtn = new JButton("Add Book");
        JButton displayBooksBtn = new JButton("Display All Books");
        
        addBookBtn.addActionListener(e -> addBook());
        displayBooksBtn.addActionListener(e -> displayAllBooks());
        
        panel.add(addBookBtn);
        panel.add(displayBooksBtn);
        
        return panel;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("User Management"));
        
        panel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        panel.add(userIdField);
        
        panel.add(new JLabel("Name:"));
        userNameField = new JTextField();
        panel.add(userNameField);
        
        JButton addUserBtn = new JButton("Add User");
        JButton displayUsersBtn = new JButton("Display All Users");
        
        addUserBtn.addActionListener(e -> addUser());
        displayUsersBtn.addActionListener(e -> displayAllUsers());
        
        panel.add(addUserBtn);
        panel.add(displayUsersBtn);
        
        return panel;
    }

    private JPanel createTransactionPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Transaction Management"));
        
        JTextField transactionBookId = new JTextField();
        JTextField transactionUserId = new JTextField();
        
        panel.add(new JLabel("Book ID:"));
        panel.add(transactionBookId);
        
        panel.add(new JLabel("User ID:"));
        panel.add(transactionUserId);
        
        JButton issueBtn = new JButton("Issue Book");
        JButton returnBtn = new JButton("Return Book");
        JButton displayTransBtn = new JButton("Display All Transactions");
        
        issueBtn.addActionListener(e -> issueBook(
            transactionBookId.getText(), 
            transactionUserId.getText()
        ));
        
        returnBtn.addActionListener(e -> returnBook(
            transactionBookId.getText(), 
            transactionUserId.getText()
        ));
        
        displayTransBtn.addActionListener(e -> displayAllTransactions());
        
        panel.add(issueBtn);
        panel.add(returnBtn);
        panel.add(new JLabel(""));
        panel.add(displayTransBtn);
        
        return panel;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Search"));
        
        JPanel topPanel = new JPanel(new FlowLayout());
        searchComboBox = new JComboBox<>(new String[]{
            "Books", "Users", "Transactions"
        });
        
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        
        searchBtn.addActionListener(e -> search());
        
        topPanel.add(searchComboBox);
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        
        panel.add(topPanel, BorderLayout.NORTH);
        return panel;
    }
    // ...existing code...
class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(String bookId, String title, String author, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("Book[ID:%s, Title:%s, Author:%s, Available:%s]", 
            bookId, title, author, available ? "Yes" : "No");
    }
}

class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("User[ID:%s, Name:%s]", userId, name);
    }
}

class Transaction {
    private String bookId;
    private String userId;
    private String status;
    private Date issueDate;
    private Date returnDate;

    public Transaction(String bookId, String userId, String status, Date issueDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.status = status;
        this.issueDate = issueDate;
    }

    public String getBookId() { return bookId; }
    public String getUserId() { return userId; }
    public String getStatus() { return status; }
    public Date getIssueDate() { return issueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setStatus(String status) { this.status = status; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return String.format("Transaction[BookID:%s, UserID:%s, Status:%s, Issued:%s, Returned:%s]",
            bookId, userId, status, issueDate, returnDate != null ? returnDate : "Not Returned");
    }
}
// ...existing code...

    // Business Logic Methods
    private Book findBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private User findUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    private void addBook() {
        String bookId = bookIdField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        boolean available = availableCheckBox.isSelected();
        
        if (bookId.isEmpty() || title.isEmpty() || author.isEmpty()) {
            showError("Please fill all book details");
            return;
        }
        
        books.add(new Book(bookId, title, author, available));
        clearBookFields();
        displayArea.append("Added Book: " + title + " by " + author + "\n");
    }

    private void addUser() {
        String userId = userIdField.getText();
        String name = userNameField.getText();
        
        if (userId.isEmpty() || name.isEmpty()) {
            showError("Please fill all user details");
            return;
        }
        
        users.add(new User(userId, name));
        clearUserFields();
        displayArea.append("Added User: " + name + "\n");
    }

    private void issueBook(String bookId, String userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);
        
        if (book == null) {
            showError("Book not found!");
            return;
        }
        
        if (user == null) {
            showError("User not found!");
            return;
        }
        
        if (!book.isAvailable()) {
            showError("Book is not available for issue");
            return;
        }
        
        book.setAvailable(false);
        transactions.add(new Transaction(bookId, userId, "ISSUED", new Date()));
        displayArea.append("Book issued: " + book.getTitle() + " to " + user.getName() + "\n");
    }

    private void returnBook(String bookId, String userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);
        
        if (book == null) {
            showError("Book not found!");
            return;
        }
        
        if (user == null) {
            showError("User not found!");
            return;
        }
        
        for (Transaction t : transactions) {
            if (t.getBookId().equals(bookId) && t.getUserId().equals(userId) && t.getStatus().equals("ISSUED")) {
                t.setStatus("RETURNED");
                t.setReturnDate(new Date());
                book.setAvailable(true);
                displayArea.append("Book returned: " + book.getTitle() + " by " + user.getName() + "\n");
                return;
            }
        }
        
        showError("No matching issued book found");
    }

    // Display Methods
    private void displayAllBooks() {
        if (books.isEmpty()) {
            displayArea.setText("No books in library");
            return;
        }
        
        StringBuilder sb = new StringBuilder("All Books:\n");
        for (Book book : books) {
            sb.append(book).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void displayAllUsers() {
        if (users.isEmpty()) {
            displayArea.setText("No users registered");
            return;
        }
        
        StringBuilder sb = new StringBuilder("All Users:\n");
        for (User user : users) {
            sb.append(user).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void displayAllTransactions() {
        if (transactions.isEmpty()) {
            displayArea.setText("No transactions recorded");
            return;
        }
        
        StringBuilder sb = new StringBuilder("All Transactions:\n");
        for (Transaction t : transactions) {
            sb.append(t).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void search() {
        String query = searchField.getText().toLowerCase();
        String type = (String) searchComboBox.getSelectedItem();
        
        if (query.isEmpty()) {
            showError("Please enter search query");
            return;
        }
        
        switch(type) {
            case "Books":
                searchBooks(query);
                break;
            case "Users":
                searchUsers(query);
                break;
            case "Transactions":
                searchTransactions(query);
                break;
        }
    }

    private void searchBooks(String query) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookId().toLowerCase().contains(query) || 
                book.getTitle().toLowerCase().contains(query) || 
                book.getAuthor().toLowerCase().contains(query)) {
                results.add(book);
            }
        }
        
        if (results.isEmpty()) {
            displayArea.setText("No matching books found");
            return;
        }
        
        StringBuilder sb = new StringBuilder("Search Results (Books):\n");
        for (Book book : results) {
            sb.append(book).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    // Other search methods would follow similar pattern...

    private void searchTransactions(String query) {
        List<Transaction> results = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getBookId().toLowerCase().contains(query) ||
                t.getUserId().toLowerCase().contains(query) ||
                t.getStatus().toLowerCase().contains(query)) {
                results.add(t);
            }
        }

        if (results.isEmpty()) {
            displayArea.setText("No matching transactions found");
            return;
        }

        StringBuilder sb = new StringBuilder("Search Results (Transactions):\n");
        for (Transaction t : results) {
            sb.append(t).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void searchUsers(String query) {
        List<User> results = new ArrayList<>();
        for (User user : users) {
            if (user.getUserId().toLowerCase().contains(query) ||
                user.getName().toLowerCase().contains(query)) {
                results.add(user);
            }
        }

        if (results.isEmpty()) {
            displayArea.setText("No matching users found");
            return;
        }

        StringBuilder sb = new StringBuilder("Search Results (Users):\n");
        for (User user : results) {
            sb.append(user).append("\n");
        }
        displayArea.setText(sb.toString());
    }
    
    private void clearBookFields() {
        bookIdField.setText("");
        titleField.setText("");
        authorField.setText("");
        availableCheckBox.setSelected(false);
    }

    private void clearUserFields() {
        userIdField.setText("");
        userNameField.setText("");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementSystem system = new LibraryManagementSystem();
            system.setVisible(true);
        });
    }
}

// Data Classes
class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available;
    
    public Book(String bookId, String title, String author, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = available;
    }
    
    // Getters and setters...
    @Override
    public String toString() {
        return String.format("Book[ID:%s, Title:%s, Author:%s, Available:%s]", 
            bookId, title, author, available ? "Yes" : "No");
    }
}

class User {
    private String userId;
    private String name;
    
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    
    // Getters and setters...
    @Override
    public String toString() {
        return String.format("User[ID:%s, Name:%s]", userId, name);
    }
}

class Transaction {
    private String bookId;
    private String userId;
    private String status;
    private Date issueDate;
    private Date returnDate;
    
    public Transaction(String bookId, String userId, String status, Date issueDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.status = status;
        this.issueDate = issueDate;
    }
    
    // Getters and setters...
    @Override
    public String toString() {
        return String.format("Transaction[BookID:%s, UserID:%s, Status:%s, Issued:%s, Returned:%s]",
            bookId, userId, status, issueDate, returnDate != null ? returnDate : "Not Returned");
    }
}
