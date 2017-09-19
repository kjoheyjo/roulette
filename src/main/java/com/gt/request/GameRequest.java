package com.gt.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import generated.Header;
import generated.Init;
import generated.Play;
import generated.Stats;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "init",
    "stats"
})
@XmlRootElement(name = "GameRequest")
public class GameRequest {
	
	@XmlElement(name = "Header", required = true)
    private Header header;
    @XmlElement(name = "Init", required = true)
    private Init init;
    @XmlElement(name = "Stats", required = true)
    private Stats stats;
    
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Init getInit() {
		return init;
	}
	public void setInit(Init init) {
		this.init = init;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
    
}
