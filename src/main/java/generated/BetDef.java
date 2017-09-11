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
 *       &lt;attribute name="min_number" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="max_number" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="total_drawn" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="draw_duplicates" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="num_draw_sets" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="use_la_partage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="max_groups" type="{http://www.w3.org/2001/XMLSchema}byte" />
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
public class BetDef {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "min_number")
    protected Byte minNumber;
    @XmlAttribute(name = "max_number")
    protected Byte maxNumber;
    @XmlAttribute(name = "total_drawn")
    protected Byte totalDrawn;
    @XmlAttribute(name = "draw_duplicates")
    protected String drawDuplicates;
    @XmlAttribute(name = "num_draw_sets")
    protected Byte numDrawSets;
    @XmlAttribute(name = "use_la_partage")
    protected String useLaPartage;
    @XmlAttribute(name = "max_groups")
    protected Byte maxGroups;

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
     * Gets the value of the minNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getMinNumber() {
        return minNumber;
    }

    /**
     * Sets the value of the minNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setMinNumber(Byte value) {
        this.minNumber = value;
    }

    /**
     * Gets the value of the maxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getMaxNumber() {
        return maxNumber;
    }

    /**
     * Sets the value of the maxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setMaxNumber(Byte value) {
        this.maxNumber = value;
    }

    /**
     * Gets the value of the totalDrawn property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getTotalDrawn() {
        return totalDrawn;
    }

    /**
     * Sets the value of the totalDrawn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setTotalDrawn(Byte value) {
        this.totalDrawn = value;
    }

    /**
     * Gets the value of the drawDuplicates property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrawDuplicates() {
        return drawDuplicates;
    }

    /**
     * Sets the value of the drawDuplicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrawDuplicates(String value) {
        this.drawDuplicates = value;
    }

    /**
     * Gets the value of the numDrawSets property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getNumDrawSets() {
        return numDrawSets;
    }

    /**
     * Sets the value of the numDrawSets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setNumDrawSets(Byte value) {
        this.numDrawSets = value;
    }

    /**
     * Gets the value of the useLaPartage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseLaPartage() {
        return useLaPartage;
    }

    /**
     * Sets the value of the useLaPartage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseLaPartage(String value) {
        this.useLaPartage = value;
    }

    /**
     * Gets the value of the maxGroups property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getMaxGroups() {
        return maxGroups;
    }

    /**
     * Sets the value of the maxGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setMaxGroups(Byte value) {
        this.maxGroups = value;
    }

}
