import java.util.Scanner;


public class miniapps {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        //call menu
        menu();
    }

    public static void menu() {
        /* menu */
        System.out.println("\n");
        System.out.println("\nMini Applications");
        System.out.println("------------------");
        System.out.println("Select an option:");
        System.out.println("1: Keep Counting Game");
        System.out.println("2: Number Conversion Tool");
        System.out.println("3: Universal Product Code (UPC) Calculator");
        System.out.println("4: Universal Product Code (UPC) Checker");
        System.out.println("9: Exit");

        System.out.println("Enter an Option");

        int user_choice = in.nextInt();
        //choices
        switch (user_choice) {
            case 1:
                System.out.println("Keep Counting Game");
                keep_counting.K_C_G();
                break;
            case 2:
                System.out.println("Number Conversion Tool");
                Hex_Converter.H_C();
                break;
            case 3:
                System.out.println("Universal Product Code (UPC) Calculator");
                UPC_calculator.UPC_Calc();
                break;
            case 4:
                System.out.println("Universal Product Code (UPC) Checker");
                UPC_Checker.UPC_Check();
                break;
            case 9:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid input!");
                menu();
                break;
        }
    }

    private static int RandomOperand(int maximum, int minimum) {
        //random operand within certain range generation
        return ((int) ((Math.random() * maximum - minimum + 1) + minimum));
    }

    private static int RandomInteger() {
        //random number generation for addition and subtraction
        return ((int) (Math.random() * 10));
    }

    public static class keep_counting {

        /*
        Testing Values:







         */
        public static void K_C_G() {
            //user input
            System.out.println("Welcome to the counting game! In this game, you are given a series of 8 questions of random addition or subtraction operations. After the first operation, the left hand side operand from the last equation is used as the operand for the left side of the next question. ");

            //timer for program with all ints
            long Time_Start = System.nanoTime();

            //final int values are locked as current values stored in int.
            final int maximum = 10;
            final int minimum = 1;
            final int max_q = 8;

            //int for increment of questions
            int Q_answer;
            int Q_Number = 1;

            //operand ints
            int operand1 = RandomOperand(maximum, minimum);
            int operand2;
            boolean game = true;

            while(Q_Number <= max_q && game)
            {
                //random operand generated for second operand
                operand2 = RandomOperand(maximum, minimum);
                int operation = RandomInteger();

                //operation sets operator of question to user
                if (operation < 5) {
                    //operator of next question is negative
                    System.out.println("Q" + Q_Number + ": " + operand1 + " - " + operand2 + " = ");
                    Q_answer = operand1 - operand2;
                } else {
                    //operator of next question is positive
                    System.out.println("Q" + Q_Number + ": " + operand1 + " + " + operand2 + " = ");
                    Q_answer = operand1 + operand2;
                }

                //int value
                int user_guess = in.nextInt();

                if (user_guess == Q_answer) {
                    operand1 = Q_answer;
                    System.out.println("You got it right!");
                    ++Q_Number;
                } else {
                    System.out.println("You got it wrong, the answer was: " + Q_answer);
                    game = false;
                    menu();
                }
            } 

            if (game) {
                long Time_End = System.nanoTime();
                long Taken_Time = (Time_End - Time_Start) / 1000000000;
                System.out.println("\nYou got all questions right in: " + Taken_Time + " secs");
                menu();
            }
        }
            }

    public static class Hex_Converter {

        /*









         */
        public static void H_C() {
            System.out.println("Enter a positive base 10 number to convert to binary and hexadecimal: ");
            int user_input = in.nextInt();

            int input = user_input;

            //remainder
            int remainder;

            //result
            String str16 = "";

            //array of base 16 values
            char[] base_16 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

            //loop
            while (input > 0) {
                remainder = input % 16;
                str16 = base_16[remainder] + str16;
                input = input / 16;
            }
            System.out.println("Base 16 value is: \n" + str16);

            //base10_number uses user_input as value.
            int Base10_Number = user_input;
            int Binary_Array[] = new int[32];
            int Binary_Index = 0;
            while(Base10_Number > 0){
                Binary_Array[Binary_Index++] = Base10_Number % 2;
                Base10_Number = Base10_Number / 2;
            }
            System.out.println("\nBinary value is: ");
            for(int i = Binary_Index-1;i >= 0;i--) {
                System.out.print(Binary_Array[i]);
            }
            menu();
        }
        }



    public static class UPC_Checker {

        /*






         */
        public static void UPC_Check() {
            System.out.println("\nEnter a 7 digit UPC value to check: ");
            String user_input = in.next();
            int length = user_input.length();
            int[] upc_verify = new int[7];

            if (length < 7) {
                System.out.println("Error! Number inputted is less than 7 digits long. Try again: ");
                user_input = in.next();
                length = user_input.length();
            }else if (length > 7){
                System.out.println("Error! Number inputted is greater than 7 digits long. try again: ");
                user_input = in.next();
                length = user_input.length();
            }

            //For loop iterates through array of user input.
            for (int n = 0; n < user_input.length(); n++) {
                char a = user_input.charAt(n);

                upc_verify[n] = Character.getNumericValue(a);
            }

            //Maths for calculating the check digit
            int sum_odd = upc_verify[0] + upc_verify[2] + upc_verify[4];
            int sum_even = upc_verify[1] + upc_verify[3] + upc_verify[5];
            int final_odd_sum = sum_odd * 3;
            int final_sum = final_odd_sum + sum_even;
            int rem = final_sum % 10;
            int check_digit = 10 - rem;
            int Verify_Check = upc_verify[6];

            //validation of UPC number using check digit.
            if (Verify_Check == check_digit){
                System.out.println("\nValid UPC number.");
            }
            else
            {
                System.out.println("Invalid UPC number.");
            }
        }
    }

        public static class UPC_calculator {

            //UPC calculator

            /*
            code below is part of the UPC_Calculator class.
            UPC calculator will take in a 6 digit value, then use the maths code to work out a check digit. This check digit is added onto the end to make a 7 digit UPC code.

            Below is a table of tests to show that the calculator works.

            Input: 123456         Maths: (3 * (1 + 3 + 5)) + 2 + 4 + 6) = 39 Remainder: 39/10 equals a remainder of 9. Check digit: 10-9 = 1 Final result: 1234561
            input: 999999         Maths: (3 * (9 + 9 + 9)) + 9 + 9 + 9) = 108 Remainder: 108/10 equals a remainder of 8. Check digit: 10-8 = 2 Final result: 9999992


             */
            public static void UPC_Calc() {
                System.out.println("Enter a 6 digit code which will be used to generate a 7 digit upc value: ");
                String UPC_Num = in.next();
                int length = UPC_Num.length();
                int[] UPC = new int[6];

                //while loop checks length of user input, if user input is greater than 6 digits long, error is given. If less, output is padded with zeros
                while (length > 6) {
                    System.out.println("Error! Inputted number is greater than 6 digits long. Try again: ");
                    UPC_Num = in.next();
                    length = UPC_Num.length();
                }

                // pads user input with zeros if input is less than 6 digits long
                if (length < 6) {
                    UPC_Num = String.format("%06d", Integer.valueOf(UPC_Num));
                }

                //for loop iterates through user input array.
                for (int n = 0; n < UPC_Num.length(); n++) {
                    char a = UPC_Num.charAt(n);

                    UPC[n] = Character.getNumericValue(a);
                }

                int sum_odd = UPC[0] + UPC[2] + UPC[4];
                int sum_even = UPC[1] + UPC[3] + UPC[5];
                int final_odd_sum = sum_odd * 3;
                int final_sum = final_odd_sum + sum_even;
                int rem = final_sum % 10;
                int check_digit = 10 - rem;


                if (rem == 0 ) {
                    System.out.println("Check digit is: 0");
                } else {
                    System.out.println("7 digit UPC number is: ");
                    System.out.println(UPC_Num + check_digit);
                }
            }
        }
    }
