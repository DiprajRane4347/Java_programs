import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GradingSystem extends JFrame {
    private List<Student> students = new ArrayList<>();
    private JTextField idField, nameField, marksField;
    private JTextArea displayArea;
    private JComboBox<String> courseComboBox;

    public GradingSystem() {
        setTitle("Student Grading System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Course:"));
        String[] courses = {"Mathematics", "Physics", "Chemistry", "Biology", "English"};
        courseComboBox = new JComboBox<>(courses);
        inputPanel.add(courseComboBox);

        inputPanel.add(new JLabel("Marks (0-100):"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        JButton calculateButton = new JButton("Calculate Grades");
        JButton displayButton = new JButton("Display All");
        JButton searchButton = new JButton("Search Student");

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addStudent());
        calculateButton.addActionListener(e -> calculateGrades());
        displayButton.addActionListener(e -> displayAllStudents());
        searchButton.addActionListener(e -> searchStudent());
    }

    private void addStudent() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String course = (String) courseComboBox.getSelectedItem();
            double marks = Double.parseDouble(marksField.getText());

            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            students.add(new Student(id, name, course, marks));
            clearFields();
            displayArea.append("Student added: " + name + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid marks", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateGrades() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students to calculate grades", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        displayArea.setText("");
        for (Student student : students) {
            String grade;
            double marks = student.getMarks();

            if (marks >= 90) grade = "A";
            else if (marks >= 80) grade = "B";
            else if (marks >= 70) grade = "C";
            else if (marks >= 60) grade = "D";
            else grade = "F";

            displayArea.append(student.getName() + " (" + student.getCourse() + "): " + grade + "\n");
        }
    }

    private void displayAllStudents() {
        if (students.isEmpty()) {
            displayArea.setText("No students in the system");
            return;
        }

        displayArea.setText("");
        for (Student student : students) {
            displayArea.append(student + "\n");
        }
    }

    private void searchStudent() {
        String searchId = JOptionPane.showInputDialog(this, "Enter Student ID to search:");
        if (searchId == null || searchId.trim().isEmpty()) return;

        displayArea.setText("");
        boolean found = false;
        
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(searchId)) {
                displayArea.append("Student found:\n" + student);
                found = true;
                break;
            }
        }

        if (!found) {
            displayArea.setText("No student found with ID: " + searchId);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        marksField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GradingSystem system = new GradingSystem();
            system.setVisible(true);
        });
    }
}

class Student {
    private String id;
    private String name;
    private String course;
    private double marks;

    public Student(String id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Course: %s | Marks: %.1f", id, name, course, marks);
    }
}
