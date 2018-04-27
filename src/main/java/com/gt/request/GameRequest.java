package com.gt.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


import generated.BetState;
import generated.Header;
import generated.Init;
import generated.Play;
import generated.Stats;
import generated.Close;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "init",
    "play",
    "stats",
    "betState",
    "close"
})
@XmlRootElement(name = "GameRequest")
public class GameRequest {
	
	@XmlElement(name = "Header", required = false)
    private Header header;
    @XmlElement(name = "Init", required = false)
    private Init init;
    @XmlElement(name = "Stats", required = false)
    private Stats stats;
    @XmlElement(name = "Play", required = false)
    private Play play;
    @XmlElement(name = "BetState", required = false)
    private BetState betState;
    @XmlElement(name = "Close", required = false)
    private Close close;
    
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
	public Play getPlay() {
		return play;
	}
	public void setPlay(Play play) {
		this.play = play;
	}
	public BetState getBetState() {
		return betState;
	}
	public void setBetState(BetState betState) {
		this.betState = betState;
	}
	public Close getClose() {
		return close;
	}
	public void setClose(Close close) {
		this.close = close;
	}
}
