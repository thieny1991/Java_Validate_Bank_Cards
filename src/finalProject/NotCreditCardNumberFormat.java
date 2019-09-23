/**
 * 
 */
package finalProject;

/**
 * Description: Signals that an attempt to validate an non numeric credit card
 * number. This exception will be thrown by CreditCardInfo.convertToIntArray()
 * method,CreditCardInfo.isValidCreditCardNumber()method,
 * CreditCardValidator.seperateToArrayList()method
 * 
 * @author nguyen
 * @version 05/09/2017
 */

public class NotCreditCardNumberFormat extends Exception {
	public NotCreditCardNumberFormat() {
		super("#INVALID 	Not a credit card number\n");
	}

	/**
	 * The following constructor accepts the String that hold credit card number
	 * 
	 * @param creditCardStr
	 *            ^ cardNumberStr reference a string holds credit card numbers
	 */
	public NotCreditCardNumberFormat(String creditCardStr) {
		super(creditCardStr + "\t#INVALID" + ":  Nonnumeric data was found\n");
	}

}
