//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.11 at 05:34:21 PM IST 
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
 *         &lt;element name="Bet" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="winnings" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="seln" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="drawn" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="total_bets" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bet"
})
public class BetState {

    @XmlElement(name = "Bet")
    protected List<Bet> bet;
    @XmlAttribute(name = "drawn")
    protected int drawn;
    @XmlAttribute(name = "total_bets")
    protected Byte totalBets;
    @XmlAttribute(name = "num_bets")
    protected Byte numBets;

    /**
     * Gets the value of the bet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bet }
     * 
     * 
     */
    public List<Bet> getBet() {
        if (bet == null) {
            bet = new ArrayList<Bet>();
        }
        return this.bet;
    }

    /**
     * Gets the value of the drawn property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public int getDrawn() {
        return drawn;
    }

    /**
     * Sets the value of the drawn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setDrawn(int value) {
        this.drawn = value;
    }

    /**
     * Gets the value of the totalBets property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getTotalBets() {
        return totalBets;
    }

    /**
     * Sets the value of the totalBets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setTotalBets(Byte value) {
        this.totalBets = value;
    }

	public Byte getNumBets() {
		return numBets;
	}

	public void setNumBets(Byte numBets) {
		this.numBets = numBets;
	}
    
}
