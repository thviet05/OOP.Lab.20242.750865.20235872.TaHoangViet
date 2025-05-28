package hust.soict.hedspi.lab01.Homework;

import java.util.Scanner;
public class Triangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the height of the triangle: ");
        int n = input.nextInt();
        for(int i = 0; i <= n - 1; i++){
            for(int j = 0; j < n - i;j++){
                System.out.print(" ");
            }
            for(int j = 1;j <= 2*i + 15;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
