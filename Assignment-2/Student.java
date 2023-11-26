import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Student {
    private String name;
    private int rollNumber;
    private int[] marks;
    public void setName(String name) {
        this.name = name;
    }
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    public void setMarks(int[] marks) {
        this.marks = marks;
    }
    public String getName() {
        return name;
    }
    public int getRollNumber() {
        return rollNumber;
    }
    public int[] getMarks() {
        return marks;
    }
    public Student(String name, int rollNumber, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    public int averageMarks(int[] marks) {
        int sum = 0;
        int n = marks.length;
        for (int i = 0; i < n; i++) {
            sum += marks[i];
        }
        return sum / n;
    }
    public static String marksToString(int[] marks) {
        StringBuilder sb = new StringBuilder();
        int n = marks.length;
        for (int i = 0; i < n; i++) {
            sb.append(Integer.toString(marks[i]));
            if (i < n - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private static void saveStudent(List<Student> students) {
        Scanner newStudent = new Scanner(System.in);
        System.out.println("Please enter student's name:");
        String inputName = newStudent.nextLine();
        System.out.println("Please enter the student's roll number:");
        int inputRollNum = newStudent.nextInt();
        System.out.println("Please enter the quantity of marks:");
        int marksSize = newStudent.nextInt();
        int[] marks = new int[marksSize];
        System.out.println("Please enter the student's marks:");
        for (int i = 0; i < marksSize; i++) {
            marks[i] = newStudent.nextInt();
        }
        students.add(new Student(inputName, inputRollNum, marks));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/StudentRecords.txt", true))) {
            for (Student student : students) {
                String record = "Student name: " + student.getName() + ", " + "Student's roll number: " + student.getRollNumber() + ", " + "Student's marks: " + marksToString(student.getMarks());
                bw.write(record);
                bw.newLine();
                System.out.println("Saved.");
            }
        } catch (IOException e) {
            System.out.println("Error saving Student Record.");
        }
    }

    public static void printRecords() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/StudentRecords.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading Student Records.");
        }
    }

    public static void quitProgram() throws IOException {
        Scanner userObj = new Scanner(System.in);
        System.out.println("If you would like to perform anymore actions, enter 1. If done, then enter 0.");
        int furtherActions = userObj.nextInt();
        if (furtherActions == 1) {
            mainMenu();
        } else {
            System.out.println("Closing the program...");
            userObj.close();
        }
    }

    public static Student findStudent(int rollNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/StudentRecords.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String studentName = parts[0].substring("Student name: ".length());
                    String rollNumberString = parts[1].substring("Student's roll number: ".length());
                    int roll = Integer.parseInt(rollNumberString);
                    if (roll == rollNumber) {
                        String marksString = parts[2].substring("Student's marks: ".length());
                        String[] marksArray = marksString.split(", ");
                        int[] marks = new int[marksArray.length];
                        for (int i = 0; i < marksArray.length; i++) {
                            marks[i] = Integer.parseInt(marksArray[i]);
                        }
                        return new Student(studentName, rollNumber, marks);
                    }
                }
            }
            System.out.println("Student with roll number " + rollNumber + " not found.");
        } catch (IOException e) {
            System.out.println("Error reading Student Records.");
        }
        return null; // Return null if the student is not found
    }

    /*public static void editRecord(Scanner scanner, int rollNumber) {
        Optional<Student> student = students.stream()
        //        .filter(s -> s.getRollNumber() == rollNumber)
              .findFirst();

        if (student.isPresent()) {
            System.out.println("Student record found:\n" + student.get());
            System.out.print("Enter new student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new marks (comma-separated, e.g., 95,80,70): ");
            String marksInput = scanner.nextLine();
            int[] marks = Arrays.stream(marksInput.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            student.get().setName(name);
            student.get().setMarks(marks);
            System.out.println("Student record updated successfully.");
        } else {
            System.out.println("Invalid marks. Please ensure all marks are between 0 and 100.");
        }
    }*/
    /*private static void deleteStudentRecord(Scanner scanner) {
        System.out.print("Enter roll number to delete: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        Student student = findStudent(rollNumber);

        if (student != null) {
            students.remove(student);
            System.out.println("Student record deleted successfully.");
        } else {
            System.out.println("Student record not found for roll number: " + rollNumber);
        }
    }*/
        public static void mainMenu() throws IOException, FileNotFoundException {
            Scanner userObj = new Scanner(System.in);
            List<Student> students = new ArrayList<>();
            System.out.println("Please select the number corresponding to what you would like to do: ");
            System.out.println("1. Add a new student record");
            System.out.println("2. View all student records");
            System.out.println("3. Search for a student's record with roll number");
            System.out.println("4. Edit a student's record with roll number");
            System.out.println("5. Delete a student's record with roll number");
            System.out.println("6. Exit program");
            int menuOption = userObj.nextInt();
            userObj.nextLine();
            switch (menuOption) {
                case 1:
                    saveStudent(students);
                    quitProgram();
                case 2:
                    printRecords();
                    quitProgram();
                    break;
                case 3:
                    Scanner editInput = new Scanner(System.in);
                    System.out.print("Enter roll number to search: ");
                    int rollNumber = editInput.nextInt();
                    editInput.nextLine();
                    findStudent(rollNumber);
                    System.out.println(rollNumber);
                    break;
                case 4:
                    /*Scanner editInput = new Scanner(System.in);
                    System.out.print("Enter roll number to edit: ");
                    int rollNumber = editInput.nextInt();
                    editInput.nextLine();
                    editRecord(editInput, rollNumber);*/
                    break;
                case 5:
                    Scanner scanner = new Scanner(System.in);
                    /*deleteStudentRecord(scanner);*/
                    break;
                case 6:
                    System.out.println("Closing the program...");
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    mainMenu();
                    break;
            }
            userObj.close();
        }
    public static void main(String[] args) throws IOException, FileNotFoundException{
        mainMenu();
    }
}

