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
 *       &lt;attribute name="available_balance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="num_tokens" type="{http://www.w3.org/2001/XMLSchema}byte" />
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
public class FreeBetSummary {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "available_balance")
    protected Float availableBalance;
    @XmlAttribute(name = "num_tokens")
    protected Byte numTokens;

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
     * Gets the value of the availableBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getAvailableBalance() {
        return availableBalance;
    }

    /**
     * Sets the value of the availableBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setAvailableBalance(Float value) {
        this.availableBalance = value;
    }

    /**
     * Gets the value of the numTokens property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getNumTokens() {
        return numTokens;
    }

    /**
     * Sets the value of the numTokens property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setNumTokens(Byte value) {
        this.numTokens = value;
    }

}
