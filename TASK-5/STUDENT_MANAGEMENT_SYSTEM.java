import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private String email;
    private String phoneNumber;

    public Student(String name, int rollNumber, String grade, String email, String phoneNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getRollNumber() { return rollNumber; }
    public void setRollNumber(int rollNumber) { this.rollNumber = rollNumber; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return String.format("Name: %-20s | Roll No: %-5d | Grade: %-5s | Email: %-25s | Phone: %-15s",
                name, rollNumber, grade, email, phoneNumber);
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private static final String DATA_FILE = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudents();
    }

    // Add a new student with validation
    public boolean addStudent(Student student) {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            System.out.println("Error: Student name cannot be empty.");
            return false;
        }
        
        if (findStudent(student.getRollNumber()) != null) {
            System.out.println("Error: Student with this roll number already exists.");
            return false;
        }
        
        if (!isValidEmail(student.getEmail())) {
            System.out.println("Error: Invalid email format.");
            return false;
        }
        
        if (!isValidPhone(student.getPhoneNumber())) {
            System.out.println("Error: Invalid phone number format.");
            return false;
        }
        
        students.add(student);
        saveStudents();
        return true;
    }

    // Remove a student
    public boolean removeStudent(int rollNumber) {
        Student student = findStudent(rollNumber);
        if (student != null) {
            students.remove(student);
            saveStudents();
            return true;
        }
        return false;
    }

    // Find a student by roll number
    public Student findStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        
        System.out.println("\nList of Students:");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Total students: " + students.size());
    }

    // Update student information
    public boolean updateStudent(int rollNumber, String name, String grade, String email, String phoneNumber) {
        Student student = findStudent(rollNumber);
        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }
        
        if (name != null && !name.trim().isEmpty()) {
            student.setName(name);
        }
        
        if (grade != null && !grade.trim().isEmpty()) {
            student.setGrade(grade);
        }
        
        if (email != null && !email.trim().isEmpty()) {
            if (!isValidEmail(email)) {
                System.out.println("Error: Invalid email format.");
                return false;
            }
            student.setEmail(email);
        }
        
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            if (!isValidPhone(phoneNumber)) {
                System.out.println("Error: Invalid phone number format.");
                return false;
            }
            student.setPhoneNumber(phoneNumber);
        }
        
        saveStudents();
        return true;
    }

    // Validation methods
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^[+\\d\\s()-]{10,15}$");
    }

    // File operations
    @SuppressWarnings("unchecked")
    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, that's okay
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }
}

public class STUDENT_MANAGEMENT_SYSTEM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("-------------------------");
        
        while (true) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Update Student Information");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1-6.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }
            
            switch (choice) {
                case 1: // Add Student
                    System.out.println("\nAdd New Student");
                    System.out.println("----------------");
                    
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    
                    Student newStudent = new Student(name, rollNumber, grade, email, phone);
                    if (sms.addStudent(newStudent)) {
                        System.out.println("Student added successfully!");
                    }
                    break;
                    
                case 2: // View All Students
                    sms.displayAllStudents();
                    break;
                    
                case 3: // Search Student
                    System.out.print("\nEnter roll number to search: ");
                    int searchRoll = scanner.nextInt();
                    scanner.nextLine();
                    
                    Student foundStudent = sms.findStudent(searchRoll);
                    if (foundStudent != null) {
                        System.out.println("\nStudent Found:");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                    
                case 4: // Update Student
                    System.out.print("\nEnter roll number of student to update: ");
                    int updateRoll = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.println("Leave field blank to keep current value.");
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    
                    System.out.print("Enter new grade: ");
                    String newGrade = scanner.nextLine();
                    
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    
                    System.out.print("Enter new phone number: ");
                    String newPhone = scanner.nextLine();
                    
                    if (sms.updateStudent(updateRoll, newName, newGrade, newEmail, newPhone)) {
                        System.out.println("Student information updated successfully!");
                    }
                    break;
                    
                case 5: // Remove Student
                    System.out.print("\nEnter roll number of student to remove: ");
                    int removeRoll = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (sms.removeStudent(removeRoll)) {
                        System.out.println("Student removed successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                    
                case 6: // Exit
                    System.out.println("Exiting Student Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter a number between 1-6.");
            }
        }
    }
}