/**
 * 
 */
package finalProject;

import java.io.IOException;

import java.util.Scanner;

/**
 * Description: Credit Card Verification program will validate a credit card
 * number or a file of credit card number based on Luhn Formula
 * Class:Spring-COSC 1437.81002 Assignment: Final Project Date: 05/09/2017
 * @author nguyen
 * @version 05/09/2017
 */
public class InvestigatorDemo {

	/**
	 * Entry to program
	 * 
	 * @param args
	 *            ^accept argument from command line
	 * @throws IOException
	 *             ^ if the named file exists but is a directory rather than a
	 *             regular file, does not exist but cannot be created, or cannot
	 *             be opened for any other reaso
	 * @throws NotCreditCardNumberFormat
	 *             ^If String object referenced by cardNumberStr contains a non
	 *             numeric character.
	 */
	public static void main(String[] args) throws IOException, NotCreditCardNumberFormat {
		// call method welcome() to introduce and give instruction to user
		welcome();
		String option; // store user's option ( 1,2,3 one, two,three
		String exit;// user's option of continue or exit program

		// Create an CreditCardInfo variable case MobiusDuck to reference
		// CreditCardInfo object
		CreditCardInfo caseMobiusDuck = new CreditCardInfo();
		// Create an CreditCardValidator variable validator to reference
		// CreditCardValidator object
		// and call CreditCardValidator constructor receives a parameter type
		// CreditCardValidator caseMobiusDuck
		CreditCardValidator validator = new CreditCardValidator(caseMobiusDuck);

		// Create Scanner variable keyboard to reference a Scanner object
		Scanner keyboard = new Scanner(System.in);
		// Ask user option they one to chose
		// option 1: verify a specific number they enter from keyboard
		// option 2: verify a file of credit card numbers.
		// option 3: exit program
		System.out.print("Chose option: ");
		// get option
		option = keyboard.nextLine();
		processUserOption(option, validator);

		// ask user to continue or quit program
		System.out.print("Do you want to continue( yes/no):");
		// get user's option to continue program
		exit = keyboard.nextLine();

		while (exit.equalsIgnoreCase("YES") || exit.equalsIgnoreCase("Y")) {
			System.out.print("Chose option: ");
			// get user's option
			option = keyboard.nextLine();
			processUserOption(option, validator);
			System.out.print("Do you want to continue( yes/no):");
			// get user's option to continue or quit
			exit = keyboard.nextLine();
		}

		System.out.println("\t\t Exit!\n\tThanks for using my program");
		System.exit(0);
	}// end of main

	private static void welcome() {
		System.out.println("*****************************************************");
		System.out.println("********************** Welcome **********************");
		System.out.printf("*%51s*\n", ' ');
		System.out.println("*	      CREDIT CARD VERIFICATION	            *");
		System.out.printf("*%51s*\n", ' ');
		System.out.println("*****************************************************");
		System.out.println("*****************************************************\n");

		System.out.println("This application will verify credit card numbers");
		System.out.println("Option 1: Validate a credit card number");
		System.out.println("Option 2: Validate a file of credit card numbers");
		System.out.println("Option 3: Exit Program\n");
	}

	private static void processUserOption(String option, CreditCardValidator validator)
			throws IOException, NotCreditCardNumberFormat {
		// check if user enter option in a digit or a string
		if (Character.isDigit(option.charAt(0))) {
			// if option is 1
			if (Integer.parseInt(option) == 1) {
				// call method creditCardNumberValidator()
				// This method from CreditCardValidator class will ask user for
				// a specific number to validate
				validator.creditCardNumberValidator();
			}
			// if option is 2
			else if (Integer.parseInt(option) == 2) {

				// call method creditCardFileValidator. This method will ask
				// user for a file that contains
				// the number of Credit card number needed to be verified
				// then write the result into 2 different files for valid number
				// and invalid number
				validator.creditCardFileValidator();
			} else if (Integer.parseInt(option) == 3) {
				System.out.println("\t\t    Exit!\n\tThanks for using my program");
				System.exit(0);
			} else {
				// if option is different from 1, 2,3
				// Tell user it is not a valid choice
				System.out.println("Sorry! We don't offer this option.\n");
			}
		}
		// if option is a string
		else {
			// if option is "one"
			if (option.equalsIgnoreCase("ONE")) {// this method ignore upper
				// case or lower case
				// call method creditCardNumberValidator()
				// This method from CreditCardValidator class will ask user for
				// a specific number to validate
				validator.creditCardNumberValidator();
			}
			// if option is "two"
			else if (option.equalsIgnoreCase("TWO")) {
				// call method creditCardFileValidator. This method will ask
				// user for a file that contains
				// the number of Credit card number needed to be verified then
				// write the result into 2 different files
				validator.creditCardFileValidator();
			} else if (option.equalsIgnoreCase("THREE")) {
				System.out.println("\t\t     Exit!\n\tThanks for using my program");
				System.exit(0);

			}

			else {
				// if option is different from "one" or "two"
				// Tell user it is not a valid choice
				System.out.println("Sorry! We don't offer this option.");

			}
		}
	}

}
