package network;

import java.io.Serializable;

/**
 * Message announcing the end of the computation.
 * 
 * @author guiguito
 */
public class EndOfImageComputation implements Serializable {

	private long duration;

	public EndOfImageComputation(long dur) {
		duration = dur;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
