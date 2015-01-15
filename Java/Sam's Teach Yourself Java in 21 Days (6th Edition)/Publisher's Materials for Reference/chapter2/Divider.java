/* Solution to Chapter 2, Exercise 2 in Teach Yourself Java in 21 Days (Covering Java 7
   and Android by Rogers Cadenhead. */
   
class Divider {
    public static void main(String[] arguments) {
        float number1 = 15;
        float number2 = 6;
        float result = number1 / number2;
        float remainder = number1 % number2;
        System.out.println(number1 + " divided by " + number2);
        System.out.println("\nResult\tRemainder");
        System.out.println(result + "\t" + remainder);
    }
}
