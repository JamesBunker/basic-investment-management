package basicinvestmentmanagement;

/** 
 * @author Name: James Bunker
 * Course: CIS 2430 
 * Semester: F21
 * 
 * Title: basic-investment-management
 * Files: Investment.java, MutualFund.java, Stock.java, Portfolio.java
 * @version Final
**/


public class MutualFund extends Investment{
    protected MutualFund(String symbolIn, String nameIn, int quantityIn, double priceIn) {
	super(symbolIn, nameIn, quantityIn, priceIn);
	this.type = "mutualfund";
    }
    
    
    /** 
     * Method calculates the book value of a mutualfund investment, overrides parent method
     * @return double This is the calculated bookvalue of a mutualfund investment
     */
    @Override
    protected double calculateBookValue() {
	return this.price * this.quantity;
    }

    
    /** 
     * Method calulates the gain on the mutualfund investment
     * @return double This is the gain on the investment
     */
    @Override
    protected double getGain() {
	return Double.parseDouble(df.format((this.price * this.quantity - 45) - this.bookValue));
    }
}
