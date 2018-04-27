package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Config")
public class Config {
	@XmlAttribute(name="next_spin_id")
	private long nextSpinId;
	@XmlAttribute(name="next_spin_time")
	private long nextSpinTimer;
	
	public Config() {
		
	}
	public Config(long nextSpinId, long nextSpinTimer) {
		this.nextSpinId = nextSpinId;
		this.nextSpinTimer = nextSpinTimer;
	}
	public long getNextSpinId() {
		return nextSpinId;
	}
	public void setNextSpinId(long nextSpinId) {
		this.nextSpinId = nextSpinId;
	}
	public long getNextSpinTimer() {
		return nextSpinTimer;
	}
	public void setNextSpinTimer(long nextSpinTimer) {
		this.nextSpinTimer = nextSpinTimer;
	}
	
}
