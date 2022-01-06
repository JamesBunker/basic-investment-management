package Assignment3;
/** 
 * @author Name: James Bunker
 * Student ID: 1130246
 * Course: CIS 2430 
 * Semester: F21
 * 
 * Title: Assignment3 extends Assignment2
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
