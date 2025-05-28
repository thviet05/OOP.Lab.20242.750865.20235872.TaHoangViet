package hust.soict.hedspi.lab01.Homework;

import java.util.Scanner;
public class Caculate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Get input
        System.out.print("Enter the first number: ");
        double num1 = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the second number: ");
        double num2 = Double.parseDouble(scanner.nextLine());

        //Caculations
        double sum  =num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;

        //Check division by zero
        Double quotient = (num2 != 0) ? (num1 / num2) : null;

        //Display results
        System.out.println("The product of the two numbers is " + product + ".");
        System.out.println("The sum of the two numbers is " + sum + ".");
        System.out.println("The difference of the two numbers is " + difference + ".");
        if(quotient != null) {
            System.out.println("The quotient of the two numbers is " + quotient + ".");
        }
        else{
            System.out.println("Cannot find the quotient of the two numbers.");
        }

        //Close scanner
        scanner.close();
    }
}
