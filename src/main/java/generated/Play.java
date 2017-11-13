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
 *         &lt;element name="FreebetSummary">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="available_balance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="num_tokens" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BonusPromotion">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="bonus_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="bonus_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="points_earned" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                 &lt;attribute name="award_target" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                 &lt;attribute name="earned_pcnt" type="{http://www.w3.org/2001/XMLSchema}float" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BetState">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Bet" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="winnings" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="seln" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="drawn" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                 &lt;attribute name="total_bets" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="win" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "freebetSummary",
    "bonusPromotion",
    "betState"
})
public class Play {

    @XmlElement(name = "FreebetSummary", required = true)
    protected FreeBetSummary freebetSummary;
    @XmlElement(name = "BonusPromotion", required = true)
    protected BonusPromotion bonusPromotion;
    @XmlElement(name = "BetState", required = true)
    protected BetState betState;
    @XmlAttribute(name = "stake")
    protected Float stake;
    @XmlAttribute(name = "win")
    protected Float win;
    @XmlAttribute(name = "freebets")
    protected String freebets;
    @XmlAttribute(name = "id")
    protected Short id;

    /**
     * Gets the value of the freebetSummary property.
     * 
     * @return
     *     possible object is
     *     {@link FreeBetSummary }
     *     
     */
    public FreeBetSummary getFreebetSummary() {
        return freebetSummary;
    }

    /**
     * Sets the value of the freebetSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeBetSummary }
     *     
     */
    public void setFreebetSummary(FreeBetSummary value) {
        this.freebetSummary = value;
    }

    /**
     * Gets the value of the bonusPromotion property.
     * 
     * @return
     *     possible object is
     *     {@link BonusPromotion }
     *     
     */
    public BonusPromotion getBonusPromotion() {
        return bonusPromotion;
    }

    /**
     * Sets the value of the bonusPromotion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BonusPromotion }
     *     
     */
    public void setBonusPromotion(BonusPromotion value) {
        this.bonusPromotion = value;
    }

    /**
     * Gets the value of the betState property.
     * 
     * @return
     *     possible object is
     *     {@link BetState }
     *     
     */
    public BetState getBetState() {
        return betState;
    }

    /**
     * Sets the value of the betState property.
     * 
     * @param value
     *     allowed object is
     *     {@link BetState }
     *     
     */
    public void setBetState(BetState value) {
        this.betState = value;
    }

    /**
     * Gets the value of the stake property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getStake() {
        return stake;
    }

    /**
     * Sets the value of the stake property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setStake(Float value) {
        this.stake = value;
    }

    /**
     * Gets the value of the win property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWin() {
        return win;
    }

    /**
     * Sets the value of the win property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWin(Float value) {
        this.win = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setId(Short value) {
        this.id = value;
    }

	public String getFreebets() {
		return freebets;
	}

	public void setFreebets(String freebets) {
		this.freebets = freebets;
	}

}
