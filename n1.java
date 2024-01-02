//Write a program which reads five numbers of type int and after reading the third,
//fourth and fifth prints the sum of three numbers last read. You can define at most
//three variables of type int. Do not use loops or arrays.

// Taha Burak Sahin

import java.util.Scanner;

public class n1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number: ");
        int num1 = scanner.nextInt();
        System.out.println("Enter second number: ");
        int num2 = scanner.nextInt();
        System.out.println("Enter third number: ");
        int num3 = scanner.nextInt();
        System.out.println("Enter fourth number: ");
        int num4 = scanner.nextInt();
        System.out.println("Enter fifth number: ");
        int num5 = scanner.nextInt();

        int sum= num3+num4+num5;
        System.out.println("The sum of the last three numbers:" + sum);
    }

}
