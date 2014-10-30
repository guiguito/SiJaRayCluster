package network;

/**
 *  Message with the scene selected and the configuration for the raytracer.
 */
import java.awt.Color;
import java.io.Serializable;

public class MessageConf implements Serializable {

	private int illumination;
	private boolean supersamp;
	private Color backgroundcolor;
	private String scene;
	private int beginFrame;
	private int endFrame;
	private int xRes;
	private int yRes;
	private int recurs;

	public MessageConf(int ill, boolean samp, Color color, String scn,
			int begin, int end, int x, int y, int rec) {
		illumination = ill;
		supersamp = samp;
		backgroundcolor = color;
		scene = scn;
		beginFrame = begin;
		endFrame = end;
		xRes = x;
		yRes = y;
		recurs = rec;
	}

	public Color getBackgroundcolor() {
		return backgroundcolor;
	}

	public void setBackgroundcolor(Color backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
	}

	public int getBeginFrame() {
		return beginFrame;
	}

	public void setBeginFrame(int beginFrame) {
		this.beginFrame = beginFrame;
	}

	public int getEndFrame() {
		return endFrame;
	}

	public void setEndFrame(int endFrame) {
		this.endFrame = endFrame;
	}

	public int getIllumination() {
		return illumination;
	}

	public void setIllumination(int illumination) {
		this.illumination = illumination;
	}

	public int getRecurs() {
		return recurs;
	}

	public void setRecurs(int recurs) {
		this.recurs = recurs;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public boolean isSupersamp() {
		return supersamp;
	}

	public void setSupersamp(boolean supersamp) {
		this.supersamp = supersamp;
	}

	public int getXRes() {
		return xRes;
	}

	public void setXRes(int res) {
		xRes = res;
	}

	public int getYRes() {
		return yRes;
	}

	public void setYRes(int res) {
		yRes = res;
	}

}
