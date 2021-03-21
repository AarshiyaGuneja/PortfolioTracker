package portfolio.tracker.Model;

public class Portfolio {

    private String security_ticker;
    private double avg_price;
    private int quantity;

    public Portfolio(String security_ticker, double avg_price, int quantity) {
        this.security_ticker = security_ticker;
        this.avg_price = avg_price;
        this.quantity = quantity;
    }

    /**
     * Gets Security Ticker.
     *
     * @return the Security Ticker
     */
    public String getSecurity_ticker() {
        return security_ticker;
    }

    /**
     * Sets Security Ticker.
     *
     * @param security_ticker the Security Ticker
     */
    public void setSecurity_ticker(String security_ticker) {
        this.security_ticker = security_ticker;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets average price.
     *
     * @return the average price
     */
    public double getAvg_price() {
        return avg_price;
    }

    /**
     * Sets average price.
     *
     * @param avg_price the average price
     */
    public void setPrice(double avg_price) {
        this.avg_price = avg_price;
    }

}
