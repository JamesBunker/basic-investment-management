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

import java.text.DecimalFormat;

public class Investment {
    protected String type;
    protected String symbol;
    protected String name;
    protected int quantity;
    protected double price;
    protected double bookValue;

    static DecimalFormat df = new DecimalFormat("0.00");

    public Investment(String symbolIn, String nameIn, int quantityIn, double priceIn) {
	this.symbol = symbolIn;
	this.name = nameIn;
	this.quantity = quantityIn;
	this.price = Double.parseDouble(df.format(priceIn));
	this.bookValue = Double.parseDouble(df.format(calculateBookValue()));
    }

    
    /** 
     * Method gets the symbol from the class investment
     * @return String This is the symbol from this investment
     */
    protected String getSymbol() {
	return this.symbol;
    }

    
    /** 
     * Method gets the name from the class investment
     * @return String This is the name from this investment
     */
    protected String getName() {
	return this.name;
    }

    
    /** 
     * Method gets the quantity from the class investment
     * @return int This is the quantity from this investment
     */
    protected int getQuantity() {
	return this.quantity;
    }

    
    /** 
     * Method gets the price from the class investment
     * @return double This is the price from this investment
     */
    protected double getPrice() {
	return this.price;
    }

    
    /** 
     * Method gets the bookValue from the class investment
     * @return double This is the bookValue from this investment
     */
    protected double getBookValue() {
	return this.bookValue;
    }

    
    /** 
     * Method gets the type from the class investment
     * @return String This is the type from this investment
     */
    protected String getType() {
	return this.type;
    }

    
    /** 
     * Method sets the bookvalue as the parameter, parsed to 2 decimal places
     * @param bookValueIn This is the book value that will be set to the investments bookvalue
     */
    protected void setBookValue(double bookValueIn) {
	this.bookValue = Double.parseDouble(df.format(bookValueIn));
    }

    
    /** 
     * Method updates the investments quantity by the parameter
     * @param input This is the quanity to update by
     */
    protected void updateQuantity(int input){
	this.quantity = this.quantity + input;
    }

    
    /** Method updates the investments price by the parameter, parsed to 2 decimal places
     * @param input This is the quanity to update by
     */
    protected void updatePrice(double input){
	this.price = Double.parseDouble(df.format(input));
    }

    
    /** 
     * Method is base for investment but will change depending on the type
     * @return double This is the standard bookvalue of the investment
     */
    protected double calculateBookValue() {
	return this.bookValue;
    }

    
    /** 
     * Method updates the bookvalue based on the parameter, parsed to 2 decimal places
     * @param bookValueIn This is the updated book value
     */
    protected void updateBookValue(double bookValueIn) {
	this.bookValue +=  Double.parseDouble(df.format(bookValueIn));
    }

    
    /** 
     * Method calculates the sell price by multiplying the bookValueIn (fraction) by the current bookvalue
     * @param bookValueIn This is the bookvalue of the investment
     */
    protected void sell(double bookValueIn) {
	this.bookValue *= Double.parseDouble(df.format(bookValueIn));
    }

    
    /** 
     * Method calculates the gain on the investment if it were to fully sell changes with type
     * @return double This is the base gain on the investment
     */
    protected double getGain() {
	return Double.parseDouble(df.format(this.bookValue));
    }

}
