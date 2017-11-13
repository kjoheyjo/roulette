//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.29 at 11:27:12 AM IST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="balance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="held_funds" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="ccy_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="adjusted_free_balance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ccy_decimal_separator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ccy_thousand_separator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
public class Account {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "balance")
    protected double balance;
    @XmlAttribute(name = "held_funds")
    protected Float heldFunds;
    @XmlAttribute(name = "ccy_code")
    protected String ccyCode;
    @XmlAttribute(name = "adjusted_free_balance")
    protected String adjustedFreeBalance;
    @XmlAttribute(name = "ccy_decimal_separator")
    protected String ccyDecimalSeparator;
    @XmlAttribute(name = "ccy_thousand_separator")
    protected String ccyThousandSeparator;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setBalance(double value) {
        this.balance = value;
    }

    /**
     * Gets the value of the heldFunds property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getHeldFunds() {
        return heldFunds;
    }

    /**
     * Sets the value of the heldFunds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setHeldFunds(Float value) {
        this.heldFunds = value;
    }

    /**
     * Gets the value of the ccyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcyCode() {
        return ccyCode;
    }

    /**
     * Sets the value of the ccyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcyCode(String value) {
        this.ccyCode = value;
    }

    /**
     * Gets the value of the adjustedFreeBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustedFreeBalance() {
        return adjustedFreeBalance;
    }

    /**
     * Sets the value of the adjustedFreeBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustedFreeBalance(String value) {
        this.adjustedFreeBalance = value;
    }

    /**
     * Gets the value of the ccyDecimalSeparator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcyDecimalSeparator() {
        return ccyDecimalSeparator;
    }

    /**
     * Sets the value of the ccyDecimalSeparator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcyDecimalSeparator(String value) {
        this.ccyDecimalSeparator = value;
    }

    /**
     * Gets the value of the ccyThousandSeparator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcyThousandSeparator() {
        return ccyThousandSeparator;
    }

    /**
     * Sets the value of the ccyThousandSeparator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcyThousandSeparator(String value) {
        this.ccyThousandSeparator = value;
    }

}
