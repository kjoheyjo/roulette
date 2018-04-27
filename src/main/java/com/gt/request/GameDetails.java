package com.gt.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
public class GameDetails {
	 	@XmlValue
	    private String value;
	    @XmlAttribute(name = "class")
	    private String gameClass;
	    @XmlAttribute(name = "name")
	    private String name;
	    @XmlAttribute(name = "channel")
	    private String channel;
	    @XmlAttribute(name = "free_play")
	    private String freePlay;
	    @XmlAttribute(name="time_roulette")
	    private String timeRoulette = "Yes";
		public String getFreePlay() {
			return freePlay;
		}
		public void setFreePlay(String freePlay) {
			this.freePlay = freePlay;
		}
		public String getTimeRoulette() {
			return timeRoulette;
		}
		public void setTimeRoulette(String timeRoulette) {
			this.timeRoulette = timeRoulette;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getGameClass() {
			return gameClass;
		}
		public void setGameClass(String gameClass) {
			this.gameClass = gameClass;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public String getFreePLay() {
			return freePlay;
		}
		public void setFreePLay(String freePLay) {
			this.freePlay = freePLay;
		}
	    
	    
}
