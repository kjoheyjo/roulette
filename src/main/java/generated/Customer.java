//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.11 at 05:34:21 PM IST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Account">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="balance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="held_funds" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="ccy_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="adjusted_free_balance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="ccy_decimal_separator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="ccy_thousand_separator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "account"
})
public class Customer {

    @XmlElement(name = "Account", required = false)
    private Account account;
    @XmlAttribute(name = "cookie", required = false)
    private String cookie;
    @XmlAttribute(name = "is_guest", required = false)
    private String isGuest;
    @XmlAttribute(name = "csrf_token", required = false)
    private String csrfToken;
    @XmlAttribute(name = "affiliate", required = false)
    private String affiliate;

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link Account }
     *     
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link Account }
     *     
     */
    public void setAccount(Account value) {
        this.account = value;
    }

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getIsGuest() {
		return isGuest;
	}

	public void setIsGuest(String isGuest) {
		this.isGuest = isGuest;
	}

	public String getCsrfToken() {
		return csrfToken;
	}

	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}
    
    
}