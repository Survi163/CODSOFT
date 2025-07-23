import java.util.Scanner;

public class STUDENT_GRADE_CALCULATOR {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\tSTUDENT GRADE CALCULATOR");

    //Get number of subjects.
    System.out.print("Enter the number of subject: ");
    int numSubjects = scanner.nextInt();

    while (numSubjects < 0) {
     System.out.println("Number of subjects sould be positive. Please try again.");
     System.out.print("Enter the number of subjects: ");
     numSubjects = scanner.nextInt(); 
    }

    int[] marks = new int[numSubjects];
    int totalMarks = 0;

    //Get marks of each subject.
    for (int i = 0; i < numSubjects; i++) {
      System.out.print("Enter marks in subject-" + (i + 1) + " (out of 100): ");
      marks[i] = scanner.nextInt();

      while (marks[i] < 0 || marks[i] > 100) {
        System.out.println("Marks must be between 0 and 100. Please try again.");
        System.out.println("Enter marks obtained in subject-" + (i + 1) + " (out of 100): ");
        
        marks[i] = scanner.nextInt();
      }

      //Calcuate sum of marks of all subject.
      totalMarks += marks[i];
    }

    //Calculate average percentage.
    double averagePercentage = (double) totalMarks / numSubjects;

    //Grades.
    String grade;

    if(averagePercentage >= 90 && averagePercentage <= 100) {
      grade = "O";
    }
    else if(averagePercentage >= 80 && averagePercentage < 90) {
      grade = "A";
    }
    else if(averagePercentage >= 70 && averagePercentage < 80) {
      grade = "B";
    }
    else if(averagePercentage >= 60 && averagePercentage < 70) {
      grade = "C";
    }
    else if(averagePercentage >= 50 && averagePercentage < 60) {
      grade = "D";
    }
    else {
      grade = "E";
    }

    System.out.println("\n \t\tRESULTS");
    System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
    System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
    System.out.println("Grade: " + grade);
        
    scanner.close();
  }  
}
