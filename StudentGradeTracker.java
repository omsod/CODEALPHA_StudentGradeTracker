import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> marks;

    public Student(String name) {
        this.name = name;
        this.marks = new ArrayList<>();
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();
            Student student = new Student(name);

            for (int j = 0; j < numSubjects; j++) {
                System.out.print("Enter mark for subject " + (j + 1) + ": ");
                int mark = scanner.nextInt();
                if (mark < 0 || mark > 100) {
                    System.out.println("Invalid mark! Please enter a value between 0 and 100.");
                    j--;
                    continue;
                }
                student.marks.add(mark);
            }
            scanner.nextLine(); // Consume newline
            students.add(student);
        }

        if (students.isEmpty()) {
            System.out.println("No students entered.");
        } else {
            calculateStatistics(students);
            calculateOverallStatistics(students);
        }
        
        scanner.close();
    }

    private static void calculateStatistics(ArrayList<Student> students) {
        System.out.println("\nIndividual Grade Statistics:");
        for (Student student : students) {
            int sum = 0;
            int highest = student.marks.get(0);
            int lowest = student.marks.get(0);

            for (int mark : student.marks) {
                sum += mark;
                if (mark > highest) {
                    highest = mark;
                }
                if (mark < lowest) {
                    lowest = mark;
                }
            }
            double average = (double) sum / student.marks.size();
            System.out.println("Student: " + student.name);
            System.out.println("Average Score: " + average);
            System.out.println("Highest Score: " + highest);
            System.out.println("Lowest Score: " + lowest);
            System.out.println("----------------------------");
        }
    }

    private static void calculateOverallStatistics(ArrayList<Student> students) {
        int totalSum = 0;
        int totalMarks = 0;
        int overallHighest = Integer.MIN_VALUE;
        int overallLowest = Integer.MAX_VALUE;

        for (Student student : students) {
            for (int mark : student.marks) {
                totalSum += mark;
                totalMarks++;
                if (mark > overallHighest) {
                    overallHighest = mark;
                }
                if (mark < overallLowest) {
                    overallLowest = mark;
                }
            }
        }
        double overallAverage = (double) totalSum / totalMarks;

        System.out.println("\nOverall Grade Statistics:");
        System.out.println("Overall Average Score: " + overallAverage);
        System.out.println("Overall Highest Score: " + overallHighest);
        System.out.println("Overall Lowest Score: " + overallLowest);
    }
}
