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

public class Stock extends Investment{
    
    protected Stock(String symbolIn, String nameIn, int quantityIn, double priceIn) {
        super(symbolIn, nameIn, quantityIn, priceIn);
        this.type = "stock";
    }

    
    /** 
     * Method calculates the book value of a stock investment, overrides parent method
     * @return double This is the calculated bookvalue of a stock investment
     */
    @Override
    protected double calculateBookValue() {
        return this.price * this.quantity + 9.99;
    }

    
    /** 
     * Method updates the book value of a stock investment, overrides parent method
     * @param bookValueIn This is the book value to add
     */
    @Override
    protected void updateBookValue(double bookValueIn) {
        this.bookValue += bookValueIn + 9.99;
    }

    
    /** 
     * Method calulates the gain on the stock investment
     * @return double This is the gain on the investment
     */
    @Override
    protected double getGain() {
        return Double.parseDouble(df.format((this.price * this.quantity - 9.99) - this.bookValue));
    }
}
