//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.29 at 11:27:12 AM IST 
//


package generated;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="BetSeln" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="seln" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="la_partage_seln" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="payout" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="min_stake" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="max_stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="lifetime_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="payout_incr" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="mth_act" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="mth_no" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="total" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "betSeln"
})
public class BetPayout {

    @XmlElement(name = "BetSeln")
    protected List<BetSeln> betSeln;
    @XmlAttribute(name = "total")
    protected Byte total;

    /**
     * Gets the value of the betSeln property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the betSeln property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBetSeln().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BetSeln }
     * 
     * 
     */
    public List<BetSeln> getBetSeln() {
        if (betSeln == null) {
            betSeln = new ArrayList<BetSeln>();
        }
        return this.betSeln;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setTotal(Byte value) {
        this.total = value;
    }

}