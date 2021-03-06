//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.11 at 05:34:21 PM IST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="Header">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="GameId">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                           &lt;attribute name="ver" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="channel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Customer">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Account">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="balance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="held_funds" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="ccy_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="adjusted_free_balance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="ccy_decimal_separator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="ccy_thousand_separator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Init">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BetDef">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="min_number" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="max_number" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="total_drawn" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="draw_duplicates" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="num_draw_sets" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="use_la_partage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="max_groups" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="BetPayout">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="BetSeln" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="seln" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="la_partage_seln" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="payout" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="min_stake" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="max_stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="lifetime_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="payout_incr" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="mth_act" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="mth_no" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="total" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="FreebetSummary">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="available_balance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="num_tokens" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="BonusPromotion">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="bonus_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="bonus_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="points_earned" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="award_target" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="earned_pcnt" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="GameConfig">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="GameConfigSection" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Theme" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;simpleContent>
 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                               &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="selected" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/extension>
 *                                           &lt;/simpleContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="FavouriteBet" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="FavouriteBetSeln" maxOccurs="unbounded" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;simpleContent>
 *                                                       &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                                         &lt;attribute name="seln_id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                                         &lt;attribute name="bet" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                         &lt;attribute name="group_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                         &lt;attribute name="seln" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                                         &lt;attribute name="stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                                       &lt;/extension>
 *                                                     &lt;/simpleContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                               &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="creation_date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="allowed_actions" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="min_anim_time" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                 &lt;attribute name="max_anim_time" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                 &lt;attribute name="min_stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="max_stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="dflt_stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="max_winnings" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="stake_incr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="new" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="stake_per_line" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="win" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Play">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FreebetSummary">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="available_balance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="num_tokens" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="BonusPromotion">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="bonus_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="bonus_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="points_earned" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="award_target" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="earned_pcnt" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="BetState">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Bet" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="winnings" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="seln" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="drawn" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="total_bets" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="stake" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="win" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Stats">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StatsAttribute">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="valueAttribute" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
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
    "header",
    "init",
    "play",
    "stats",
    "close",
    "config"
})
@XmlRootElement(name = "GameResponse")
public class GameResponse {

    @XmlElement(name = "Header", required = false)
    protected Header header;
    @XmlElement(name = "Init", required = false)
    protected Init init;
    @XmlElement(name = "Play", required = false)
    protected Play play;
    @XmlElement(name = "Stats", required = false)
    protected Stats stats;
    @XmlElement(name = "Close", required = false)
    protected Close close;
    @XmlElement(name = "Config", required = false)
    protected Config config;

    public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	/**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the init property.
     * 
     * @return
     *     possible object is
     *     {@link Init }
     *     
     */
    public Init getInit() {
        return init;
    }

    /**
     * Sets the value of the init property.
     * 
     * @param value
     *     allowed object is
     *     {@link Init }
     *     
     */
    public void setInit(Init value) {
        this.init = value;
    }

    /**
     * Gets the value of the play property.
     * 
     * @return
     *     possible object is
     *     {@link Play }
     *     
     */
    public Play getPlay() {
        return play;
    }

    /**
     * Sets the value of the play property.
     * 
     * @param value
     *     allowed object is
     *     {@link Play }
     *     
     */
    public void setPlay(Play value) {
        this.play = value;
    }

    /**
     * Gets the value of the stats property.
     * 
     * @return
     *     possible object is
     *     {@link Stats }
     *     
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Sets the value of the stats property.
     * 
     * @param value
     *     allowed object is
     *     {@link Stats }
     *     
     */
    public void setStats(Stats value) {
        this.stats = value;
    }

	public Close getClose() {
		return close;
	}

	public void setClose(Close close) {
		this.close = close;
	}
    
    
    
}
