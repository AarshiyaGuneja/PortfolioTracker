package portfolio.tracker.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "trades")
@EntityListeners(AuditingEntityListener.class)
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "security_ticker", nullable = false)
    private String security_ticker;

    /** Buy (B) or Sell (S) **/
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price")
    private Integer price;

    public Trade() {}
    public Trade(int id, String security_ticker, String type, int quantity, Integer price) {
        this.id = id;
        this.security_ticker = security_ticker;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
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
     * Gets Type.
     *
     * @return the Type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets Type.
     *
     * @param type the Type
     */
    public void setType(String type) {
        this.type = type;
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
     * Gets price.
     *
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

}
