/**
 * 
 */
package finalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description: This class can't work by itself, when creating an object, have
 * to have an specific CreditCardInfo object connect to it. This class provides
 * methods that interacting with user and get String input from user, Input can
 * be a name of a file that hold credit card number, or input can be a specific
 * credit card number that user wants it to be verified. The verified results
 * will be write in to a file or print out to the screen.
 * 
 * @author nguyen
 * @version 05/05/2017
 */
public class CreditCardValidator {
	private ArrayList<String> validList = new ArrayList<String>();
	private ArrayList<String> invalidList = new ArrayList<String>();
	private CreditCardInfo detector;// reference CreditCardInfo object

	/**
	 * Constructor constructs a CreditCardValidator object given a
	 * CreditCardInfo object
	 * 
	 * @param detector
	 *            ^variable reference an CreditCardInfo object
	 */
	public CreditCardValidator(CreditCardInfo detector) {
		this.detector = detector;
	}

	/**
	 * seperateToArrayList method cheks the validation of credit card number and
	 * stores the result into two seperates ArrayList. validList for valid
	 * credit card numbers, invalidList for invalid credit card numbers
	 * 
	 * @param cardNumberStr
	 *            ^ cardNumberStr reference a string holds credit card numbers
	 * @throws NotCreditCardNumberFormat
	 *             ^If String object referenced by cardNumberStr contains a non
	 *             numeric character.
	 */
	public void seperateToArrayList(String cardNumberStr) throws NotCreditCardNumberFormat { // call
																								// method
																								// setCardNumberStr
																								// from
																								// CreditCardInfo
																								// class
																								// to
																								// store
																								// the
																								// value
																								// of
																								// cardNumberStr
																								// argument
																								// to
																								// the
																								// field
																								// cardNumberStr
																								// of
																								// CreditCardInfo
																								// class
		detector.setCardNumberStr(cardNumberStr);

		// call method isValidCreditCardNumber from CreditCardInfo class. This
		// method will validate the cardNumberStr
		// it will return true if cardNumberStr is a valid credit card number
		if (detector.isValidCreditCardNumber()) {

			// This is a valid card. get card number, get the Issuer and IIRANGE
			// of this card, and store it to validList
			validList.add(
					detector.getCardNumberStr() + "   " + detector.getIssuerBank() + "   " + detector.getInRange());
			// print out to screen
			System.out.printf("%-20s%s   %s\n", detector.getCardNumberStr(), detector.getIssuerBank(),
					detector.getInRange());
		} else {
			// If this is an invalid card. store this number to ArrayList
			// invalidList
			invalidList.add(detector.getCardNumberStr());
			// print out to screen
			System.out.printf("%-20s#INVALID\n", detector.getCardNumberStr());
		}
	}

	/**
	 * This method returns a reference to object is pointed by validList field
	 * 
	 * @return validList ArrayList
	 */
	public ArrayList<String> getValidList() {
		return validList;
	}

	/**
	 * This method returns a reference to object is pointed by ivalidList field
	 * 
	 * @return invalidList ArrayList
	 */
	public ArrayList<String> getInvalidList() {
		return invalidList;
	}

	/**
	 * creditCardNumberValidator method will get input from user. Input is
	 * string that holds a credit card number. This method then will check for
	 * validation of that credit card number, then print out the result to the
	 * screen
	 */
	public void creditCardNumberValidator() {
		// Create Scanner variable keyboard to reference a Scanner object
		Scanner keyboard = new Scanner(System.in);
		// get a specific credit card number needed to be verified
		System.out.println("Enter a credit card number you want to verify");
		/// call method setCardNumberStr from CreditCardInfo class
		// to store user's input to the field cardNumberStr of CreditCardInfo
		/// class
		detector.setCardNumberStr(keyboard.next());

		try { // Handling exception
			if (detector.isValidCreditCardNumber()) {

				System.out.printf("%-20s%s   %s\n\n", detector.getCardNumberStr(), detector.getIssuerBank(),
						detector.getInRange());
			} else {

				System.out.printf("%-20s#INVALID\n\n", detector.getCardNumberStr());
			}
		} catch (NotCreditCardNumberFormat e) { // method when a nonnumeric data
												// is found
			System.out.println(e.getMessage());
		} // end of catch
	}// end of method

	/**
	 * This method will get input from user. Input is a String holds name of an
	 * actual file that contains credit card numbers needed to be verified. This
	 * method then will check for validation of each of credit card number in
	 * the file, print out the result to the screen and write the result into 2
	 * different files. By default valid credit card Number will be written to
	 * valid_card.txt, invalid numbers will be written to invalid_numbers.txt
	 * file
	 * 
	 * @throws IOException
	 *             ^ if the named file exists but is a directory rather than a
	 *             regular file, does not exist but cannot be created, or cannot
	 *             be opened for any other reason
	 */
	public void creditCardFileValidator() throws IOException {
		String filename;
		String validFile = "valid_cards.txt";
		String invalidFile = "invalid_numbers.txt";

		// Creates keyboard reference Scanner object to
		// read each line from the file stream.
		Scanner keyboard = new Scanner(System.in);

		try {//
			System.out.print("Enter a file you want to process: ");
			// get the file name
			filename = keyboard.nextLine();
			// open the file
			File file = new File(filename);
			Scanner inputFile = new Scanner(file);

			// Process the content of the file
			System.out.println("Processing...");
			// Force a NotCreditCardNumber exception
			try {
				// read lines from the file until no more are left
				while (inputFile.hasNext()) {
					seperateToArrayList(inputFile.nextLine());
				} // end of while

				System.out.println("\n\t" + validList.size() + " VALID\t" + invalidList.size() + " INVALID");
			} catch (NotCreditCardNumberFormat e) {// method when the input is
													// an non numeric data
				// print out its not a valid credit card number
				System.out.println(e.getMessage());
			}

			FileWriter fWriterValid = new FileWriter(validFile);// change to new
																// FileWriter(validFile,true)
																// to append to
																// existing file
			FileWriter fWriterInvalid = new FileWriter(invalidFile);
			PrintWriter outFileValid = new PrintWriter(fWriterValid);// new
																		// FileWriter(validFile,true);
			PrintWriter outFileInvalid = new PrintWriter(fWriterInvalid);

			// This is enhanced for loop method, write every elements of
			// ArrayList validList to validFile
			for (String card : validList) {
				outFileValid.println(card);
			}
			// This is enhanced for loop method, write every elements of
			// ArrayList validList to inValidFile
			for (String card : invalidList) {
				outFileInvalid.println(card);
			}
			// Close the file
			inputFile.close();
			outFileValid.close();
			outFileInvalid.close();

			// Inform to user that process is success an show user the names of
			// files
			// so they can retrieve later.
			System.out.println("The valid credit cards written to the file " + validFile
					+ "\nThe invalid credit cards were written to the file " + invalidFile + "\n");

			System.out.println("\t\t Success!\n");

		} catch (FileNotFoundException e) {
			// The file was not found
			System.out.println("File not found");
		} // end of catch

	}// end of method creditCardFileValidator
}// end of class
