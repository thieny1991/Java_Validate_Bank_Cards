/**
 * 
 */
package finalProject;

import java.util.regex.Pattern;

/**
 * Description:This class provides many methods to determine the issuer and
 * IINRANGE of a valid credit card number. if issuer is not American express,
 * Dinner Club, Discover, InstaPayment, MasterCard, JCB, LASER, Maestro,Visa,
 * Visa electron class will return "Undefined"
 * 
 * @author nguyen
 * @version 05/05/2017
 */
public class Issuer {
	private String issuerBank = "";
	private String inRange = "";
	private String validCard;

	/**
	 * Constructor constructs a Issuer object
	 * 
	 */
	public Issuer() {
		super();
	}

	/**
	 * This method returns the value in the issuerBank field
	 * 
	 * @return issuerBank String
	 */
	public String getIssuerBank() {
		return issuerBank;
	}

	/**
	 * This method returns the value in the inRange field
	 * 
	 * @return inRange
	 */
	public String getInRange() {
		return inRange;
	}

	/**
	 * setValidCard method get valid credit card number and stores that value to
	 * validCard field
	 * 
	 * @param validCard
	 *            ^validCard reference an String object holds a valid credit
	 *            card number.
	 */
	public void setValidCard(String validCard) {
		this.validCard = validCard;
	}
	/*
	 * /**setIssuerBank store the value of parameter s to issuerBank field
	 * 
	 * @param s
	 * 
	 * public void setIssuerBank(String s){ issuerBank=s; } /**setInRange stores
	 * the value of parameter r to inRange field
	 * 
	 * @param r
	 * 
	 * public void setInRange(String r){ inRange=r; }
	 */

	/**
	 * findIssuer method will return the value in issuerBank field which holds
	 * the issuer's name of the validCard
	 * 
	 * @return issuerBank String
	 */
	public String findIssuer() {

		if (!isAmericanExpress() && !isDinnersClub() && !isDiscover() && !isInstaPayment() && !isMasterCard()
				&& !isJCB() && !isLaser() && !isMaestro() && !isVisaElectron() && !isVisa()) {
			issuerBank = "Undefined";
			return issuerBank;
		} else {
			return issuerBank;

		}
	}// end of findIssuer method

	/**
	 * This method validates whether a credit card is issued by American Express
	 * or not
	 * 
	 * @return true if validCard is issued by American Express, false if not
	 */
	public boolean isAmericanExpress() {
		// iinRange 34,37 length=15
		// American Express is the only card that have length =15
		if (validCard.length() == 15) {
			inRange = validCard.substring(0, 2);
			issuerBank = "American Express(AMEX)";
			return true;
		} else {
			return false;
		}
	}// end of method americanExpressIssuer

	/**
	 * This method validates whether a credit card is issued by Dinners Club or
	 * not
	 * 
	 * @return true if validCard is issued by Dinner Club, false if not
	 */
	public boolean isDinnersClub() {
		String DINERS_CLUB_INTE = "36";
		String DINERS_CLUB_USA_CANA = "54";
		// Diners Club - Carte Blanche 300, 301, 302, 303, 304, 305 length= 14
		// Diners Club - International 36 length 14
		// Diners Club - USA & Canada 54 length=16

		// if length=14, it can be Carte Blanche or International
		if (validCard.length() == 14) {
			// if validCard start with 36 it its Diners club international
			if (validCard.startsWith(DINERS_CLUB_INTE)) {
				inRange = DINERS_CLUB_INTE;
				issuerBank = "Dinners Club - International";
			} else {
				// if it is not internatinal it will be Carte Blanche
				inRange = validCard.substring(0, 3);
				issuerBank = "Dinners Club - Carte Blanche";
			}
			return true;
		} else if (validCard.startsWith(DINERS_CLUB_USA_CANA)) {
			inRange = DINERS_CLUB_USA_CANA;
			issuerBank = "Dinners Club - North American";
			return true;
		} else {
			return false;
		} // end else
	}// end method dinnerClubIssuer

	/**
	 * This method validates whether a credit card is issued by Discover or not
	 * 
	 * @return true if validCard is issued by Discover, if not false
	 */
	public boolean isDiscover() {
		// Discover 6011, 622126 to 622925, 644, 645, 646,
		// 647, 648, 649, 65
		if (validCard.startsWith("6011")) {
			inRange = "6011";
			issuerBank = "Discover";
			return true;
		} else if (validCard.startsWith("65")) {
			inRange = "65";
			issuerBank = "Discover";
			return true;

		} // if validCard start with a number from the range [644-649]
		else if (Pattern.matches("[6][4][4-9].*", validCard)) {
			inRange = validCard.substring(0, 3);
			issuerBank = "Discover";
			return true;
		} else if (Pattern.matches("[6][2][2][1,9][2][5,6].*", validCard)) {
			// Integer.parseInt(validCard.substring(0,
			// 6))>=622126&&Integer.parseInt(validCard.substring(0, 6))<=622925)

			inRange = validCard.substring(0, 6);
			issuerBank = "Discover";
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method validates whether a credit card is issued by InstaPayment or
	 * not
	 * 
	 * @return true if validCard is issued by InstaPayment, if not: false
	 */
	public boolean isInstaPayment() {
		// InstaPayment 637, 638, 639 length 16
		if (Pattern.matches("[6][3][7-9].*", validCard)) {
			// if
			// (validCard.startsWith("637")||validCard.startsWith("638")||validCard.startsWith("639")){
			// inRange is the first 3 number of validCard
			inRange = validCard.substring(0, 3);
			issuerBank = "InstaPayment";
			return true;
		} else {
			return false;
		}
	}// end of method isInstalPayment

	/**
	 * This method validates whether a credit card is issued by JCB or not
	 * 
	 * @return true if validCard is issued by JCB, if not: false
	 */
	public boolean isJCB() {
		// JCB 3528 to 3589 length 16
		if (Pattern.matches("[3][5][2,8][8,9].*", validCard)) {
			inRange = validCard.substring(0, 4);
			issuerBank = "JCB";
			return true;
		} else {
			return false;
		}

	}// end of method isJCB

	/**
	 * This method validates whether a credit card is issued by LASER or not
	 * 
	 * @return true if validCard is issued by LASER, if not false
	 */
	public boolean isLaser() {

		// String[] laser={"6304","6706","6771","6709"}; length 16-19
		if (Pattern.matches("[6][3,7][0,7][1,4,6,9].*", validCard)) {
			inRange = validCard.substring(0, 4);
			issuerBank = "LASER";
			return true;
		} else {
			return false;
		}
	}// end of method is Laser

	/**
	 * This method validates whether a credit card is issued by Maestro or not
	 * 
	 * @return true if validCard is issued by Maestro, if not false
	 */
	public boolean isMaestro() {
		String maestro[] = { "5018", "5020", "5038", "5893", "6304", "6759", "6761", "6762", "6763" };
		if (searchInRange(maestro) >= 0) {
			inRange = maestro[searchInRange(maestro)];
			issuerBank = "Maestro";
			return true;
		} else {
			return false;
		}
	}// end of method isMaestro

	/**
	 * This method validates whether a credit card is issued by Master Card or
	 * not
	 * 
	 * @return true if validCard is issued by isMasterCard, if not : false
	 */
	public boolean isMasterCard() {
		if (Pattern.matches("[5][1-5].*", validCard)) {
			inRange = validCard.substring(0, 2);
			issuerBank = "MasterCard";
			return true;
		} else {
			return false;
		}
	}// end of method isMasterCard

	/**
	 * This method validates whether a credit card is issued by Visa Electron or
	 * not
	 * 
	 * @return true if validCard is issued by VisaElectron, if not: false
	 */
	public boolean isVisaElectron() {
		// Visa Electron 4026, 417500, 4508, 4844, 4913, 4917 leng16
		String visaElectron[] = { "4026", "417500", "4508", "4844", "4913", "4917" };
		if (searchInRange(visaElectron) >= 0) {
			inRange = visaElectron[searchInRange(visaElectron)];
			issuerBank = "Visa Electron";
			return true;
		} else {
			return false;
		}
	}// end of method isViasElectron

	/**
	 * This method validates whether a credit card is issued by Visa or not
	 * 
	 * @return true if validCard is issued by Visa, if not false
	 */
	public boolean isVisa() {

		if (Pattern.matches("[4].*", validCard)) {
			inRange = validCard.substring(0, 1);
			issuerBank = "VISA";
			return true;
		} else {
			return false;
		}
	}// end of method isVisa

	/**
	 * searchInRange method will search a specific String Array holds
	 * information of a Issuer's IIRANGE to find out a Iusser's IIRANGE that
	 * matches with the validCard. It then will return the position of that
	 * matched IIRANGE
	 * 
	 * @param s
	 *            is a String Array includes information of a Issuer's IIRANGE
	 * @return element int
	 */
	private int searchInRange(String[] s) {
		int index, element;
		boolean found;
		index = 0;
		element = -1;
		found = false;
		while (!found && index < s.length) {
			if (validCard.startsWith(s[index])) {
				found = true;
				element = index;
			}
			index++;
		}
		return element;
	}// end of searchInRange
}// end of class
