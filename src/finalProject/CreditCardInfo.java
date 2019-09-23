/**
 * 
 */
package finalProject;

/**
 * CreditCardInfo class is an subclass of Issuer class. CreditCardInfo contains
 * methods that stores and return a credit card number(a string) and provides
 * methods to validate a credit card number
 * 
 * @author nguyen
 * @version 05/06/2017
 *
 */
public class CreditCardInfo extends Issuer {

	private String cardNumberStr;
	private int[] cardNumber;
	private int size;
	// private Issuer banker;

	/**
	 * Contructor no argument
	 */
	public CreditCardInfo() {
		super();
	}

	/**
	 * Constructor constructs a CreditCardInfo object given a string holds
	 * credit card number
	 * 
	 * @param cardNumberStr
	 *            ^ cardNumberStr reference a string holds credit card numbers
	 */
	public CreditCardInfo(String cardNumberStr) {

		this.cardNumberStr = cardNumberStr;
		this.size = cardNumberStr.length();
	}

	/**
	 * This method passes a reference point to parameter cardNumberStr to field
	 * cardNumberStr and store the value of length of cardNumberStr String to
	 * field size
	 * 
	 * @param cardNumberStr
	 *            ^ cardNumberStr reference a string holds credit card numbers
	 */
	public void setCardNumberStr(String cardNumberStr) {
		this.cardNumberStr = cardNumberStr;
		this.size = cardNumberStr.length();
	}

	/**
	 * This method return a reference of a String object that is pointed by
	 * field cardNumberStr
	 * 
	 * @return cardNumberStr String
	 */
	public String getCardNumberStr() {
		return cardNumberStr;
	}

	/**
	 * This method will validate cardNumberStr to determine cardNumberStr is a
	 * valid credit card number or not
	 * 
	 * @return true if cardNumberStr is a valid credit card number, if not
	 *         return false
	 * @throws NotCreditCardNumberFormat
	 *             ^If String object referenced by cardNumberStr contains a non
	 *             numeric character.
	 */
	public boolean isValidCreditCardNumber() throws NotCreditCardNumberFormat {
		final int MIN_SIZE = 13;
		final int MAX_SIZE = 19;
		// convert cardnumberStr into digit in reversed order
		// before calling function checkValid
		convertToIntArray();
		if (size >= MIN_SIZE && size <= MAX_SIZE) {
			// If It is possible an CreditCardNumber
			return checkValid();
		} else {
			return checkValid();
		}
	}

	/**
	 * This method will do operation on cardNumber int Array which is an array
	 * of digits of credit card number in reversed order. This method will
	 * validate an credit card number
	 * 
	 * @return boolean isValid
	 * @throws NotCreditCardNumberFormat
	 *             ^If String object referenced by cardNumberStr contains a non
	 *             numeric character.
	 */
	private boolean checkValid() {

		// convert original number cardNumberStr into a int array cardNumber[]
		// with reversed order
		// Original Number(String cardNumberStr): 4 5 5 6 7 3 7 5 8 6 8 9 9 8 5
		// 5
		// after calling function convertToIntArray,
		// int cardNumber[]: 5 5 8 9 9 8 6 8 5 7 4 7 6 5 5 4
		// the first element cardNumber[0]=5
		// checkValid() method will work on cardNumber[1] to the last element

		int checkDigit = 0; // is the amount that you would need to add to get a
		// multiple of 10 (Modulo 10)
		int sum = 0; // to hold the sum of all numbers in reverse array
		for (int i = 1; i < size; i++) {// cardNumber[0]= the last digit on
										// credit card
			// multiply cardNumber at add even position(or an odd index) by 2.
			if (i % 2 != 0) {
				cardNumber[i] = cardNumber[i] * 2;
				// if result is greater than 9, minus the result by 9
				if (cardNumber[i] > 9) {
					cardNumber[i] = cardNumber[i] - 9;
				} // end if
			} // end if

			sum = sum + cardNumber[i];
		} // end of For loop

		if (sum % 10 != 0) {
			checkDigit = 10 - sum % 10; // Example (84+checkDigit)/10=0,
			// therefore checkDigit=6
			// Create Formula: remainder of 84/10=4, 10-4=6, 6+84=90,90%90=0
		}

		// compare the checkDigit and the last digit of the Credit Card
		if (checkDigit == cardNumber[0]) {
			setValidCard(cardNumberStr);
			findIssuer();
			return true;
		} else {
			return false;
		}
	}// end of checkValid method

	/**
	 * convertToIntArray convert the value stored in cardNumberStr field into an
	 * Int array with reversed order
	 * 
	 * @throws NotCreditCardNumberFormat
	 *             ^If String object referenced by cardNumberStr contains a non
	 *             numeric character.
	 */
	private void convertToIntArray() throws NotCreditCardNumberFormat {
		// convert original number cardNumberStr into a int array cardNumber[]
		// with reversed order
		// Original Number(String cardNumberStr)4 5 5 6 7 3 7 5 8 6 8 9 9 8 5 5
		// reverse order(int cardNumber[]): 5 5 8 9 9 8 6 8 5 7 4 7 6 5 5 4

		// Create an int array
		cardNumber = new int[size];

		// Convert the line String into int array
		int j = 0;
		// start at the last element of cardNumberStr and go backward
		for (int i = size - 1; i >= 0; i--) {
			// if value at that element is a digit
			if (Character.isDigit(cardNumberStr.charAt(i))) {
				// convert that value into integer type and store the result to
				// cardNumber
				cardNumber[j] = Integer.parseInt(cardNumberStr.charAt(i) + "");
				j++;
			} else {// if the crruent element of cardNumberStr is not a digit
				// throw an exception
				throw new NotCreditCardNumberFormat(cardNumberStr);
			} // end else

		} // end of for loop

	}// end of method convertToIntArray
}
