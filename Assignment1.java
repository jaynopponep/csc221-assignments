import java.util.Scanner;
public class Assignment1 {
    static void TaskOne(){
        int bananas = 3;
        double cost = 2.79;
        String fruitBowl = "bowl 1";
        String output = "Bananas: " + bananas + "\n" + "Cost: " + cost + "\n" + "Fruit Bowl: " + fruitBowl + "\n";
        System.out.println(output);
    } // Demonstration of declaring & initiating an integer, float, and string values.
    static void TaskTwo(){
        Scanner userObj = new Scanner(System.in);
        System.out.println("Please enter your age: ");
        int userInput = userObj.nextInt();
        if (userInput >= 18) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are a minor.");
        }
    } // Accepts user input and checks if above or below 18.
    static void printEvens(){
        for (int i=1; i<=20; i++){
            if (i%2==0){
                System.out.println(i + " ");
            }
        }
    } // Uses a for loop to print all even numbers between 1 to 20 inclusive.
    static void sumOfOdds(){
        int sum = 0;
        for (int i=1; i<=50; i++){
            if (i%2!=0){
                sum += i;
            }
        }
        System.out.println("The sum of odds between 1 and 50 are: " + sum);
    } // Uses another for loop to add odd numbers between 1-50 to int variable sum then prints it out after.
    static void calculateArea(int length, int width){
        int area = length*width;
        System.out.println("Area: " + area);
    } // Calculates the area which is length * width.
    static int calculateFactorial(int n){
        if (n==0 || n==1) { return 1; }
        if (n==2){ return 2; }
        return n*(calculateFactorial(n-1));
    } // Calculates the factorial of n using recursion by calling the function with input (n-1) until base case is met.
    static void basicCalculator(){
        Scanner userObj = new Scanner(System.in);
        Scanner userObjTwo = new Scanner(System.in);
        Scanner userObjThree = new Scanner(System.in);
        System.out.println("Enter your first number: ");
        int userNum = userObj.nextInt();
        System.out.println("Enter your operator (+,-,*,/): ");
        char userOperation = userObjThree.next().charAt(0);
        System.out.println("Enter your second number: ");
        int userNumTwo = userObjTwo.nextInt();
        int output = 0;
        if (userOperation == '+'){
            output = userNum + userNumTwo;
            System.out.println("Output: " + output);
        } else if (userOperation == '-'){
            output = userNum - userNumTwo;
            System.out.println("Output: " + output);
        } else if (userOperation == '*'){
            output = userNum*userNumTwo;
            System.out.println("Output: " + output);
        } else {
            output = userNum/userNumTwo;
            System.out.println("Output: " + output);
        }
    } // Basic calculator that accepts two numbers and four basic operations to calculate the result.
    public static void main(String[] args) {
        TaskOne();
        TaskTwo();
        printEvens();
        sumOfOdds();
        calculateArea(23,36);
        System.out.println("Factorial of 8: " + calculateFactorial(8));
        basicCalculator();
    }
}