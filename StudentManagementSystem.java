import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private String dob;

    public Student(String name, int rollNumber, String grade, String dob) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.dob = dob;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String toString() {
        return "Name : " + name + "\nRoll Number : " + rollNumber + "\nGrade : " + grade + "\nDate of birth : " + dob;
    }

    public String getDateOfBirth() {
        return dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }
        public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void displayAllStudents() {
        System.out.println("Detaials of students : ");
        for (Student student : students) {
            System.out.println(student.toString());
        }
        System.out.println("Number of students : " + students.size());
    }

    public void setVisible(boolean b) {
        return;
    }
}

public class StudentMangSys {
    protected static final String dob = null;
    private JFrame frame;
    private JTextArea resultArea;
    private StudentManagementSystem system;

    public StudentMangSys() {
        system = new StudentManagementSystem();
        initializeUI();
    }

    public ArrayList<Student> getAllStudents() {
        return system.getAllStudents();
    }

    private void initializeUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JLabel label = new JLabel("Enter Roll Number : ");
        JTextField rollNumberField = new JTextField(10);

        inputPanel.add(label);
        inputPanel.add(rollNumberField);

        new JTextField(20);
        new JTextField(10);

        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton displayAllButton = new JButton("Display All");

        JPanel buttonPanel = new JPanel(); // New panel for buttons
        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(displayAllButton);
    
        inputPanel.add(buttonPanel); // Add the button panel to the main input panel

        // button to search students
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                Student student = system.searchStudent(rollNumber);
                if (student != null) {
                    resultArea.setText(student.toString());
                } else {
                    resultArea.setText("Student not found.\n");
                }
            }
        });

        // button to add students
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter student's full name : ");
                int rollNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter roll number : "));
                String grade = JOptionPane.showInputDialog(frame, "Enter grade : ");
                String dob = JOptionPane.showInputDialog(frame, "Enter date of birth(dd-mm-yyyy) : ");
        
                Student newStudent = new Student(name, rollNumber, grade, dob);
                system.addStudent(newStudent);
                resultArea.setText("Student added successfully :\n\n" + newStudent.toString());
            }
        });
        
        // button to remove students
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                system.removeStudent(rollNumber);
                resultArea.setText("Student removed successfully.");
            }
        });

        // button to display the details of students
        displayAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> students = system.getAllStudents();
                if (students.isEmpty()) {
                    resultArea.setText("No students found.");
                } else {
                    StringBuilder info = new StringBuilder("Details of students :\n\n");
                    for (Student student : students) {
                        info.append(student.toString()).append("\n\n");
                    }
                    System.out.println(info.toString());
                    resultArea.setText(info.toString());
                }
            }
        });

        inputPanel.add(label);
        inputPanel.add(rollNumberField);
        inputPanel.add(searchButton);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(displayAllButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentMangSys());
    }
}
