/**
 * RenderingWindow.java
 *
 * Created on 11 décembre 2005, 16:17
 */

package server;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * JFrame to render the image.
 * 
 * @author Guilhem Duché
 */
public class RenderingWindow extends javax.swing.JFrame {

	private boolean firstRendering;
	private Image mImage;
	private SharedImage image = null;

	private int h;
	private int l;

	/**
	 * Creates new form RenderingWindow.
	 * 
	 * @param width
	 *            X size of the image.
	 * @param height
	 *            Y size of the rendered image.
	 */
	public RenderingWindow(int width, int height) {
		super();
		initComponents();
		setSize(new Dimension(width, height));
		h = height;
		l = width;
		firstRendering = true;
		mImage = createImage(width, height);
	}

	/*
	 * public void setImageResult(SharedImage s,String filename){ image = s;
	 * Dimension d = getSize(); java.awt.Graphics offG = mImage.getGraphics();
	 * offG.setColor(Color.BLACK); offG.fillRect(0,0, d.width,d.height);
	 * Graphics2D g2 = (Graphics2D)mImage.getGraphics(); //calculate images for
	 * (int y=0;y<h;y++){ for (int x=0;x<l;x++){ Materials.Color res =
	 * image.getColor(x,y); java.awt.Color col = new
	 * java.awt.Color((float)res.red,(float)res.green,(float)res.blue);
	 * g2.setColor(col); g2.drawRect(x,y,1,1); } } mImage.flush(); //save image
	 * on disk try{ if (!filename.equals("")){
	 * javax.imageio.ImageIO.write((BufferedImage)mImage,"PNG",new
	 * File(filename+".png")); }else{
	 * javax.imageio.ImageIO.write((BufferedImage)mImage,"PNG",new
	 * File("default"+".png")); } }catch(Exception e){
	 * System.out.println(e.getMessage()); } }
	 */

	public Image getImage() {
		return mImage;
	}

	public void setImageResult(Image s) {
		mImage = s;
		repaint();
	}

	public void setFilename(String filename) {
		// save image on disk
		try {
			if (!filename.equals("")) {
				javax.imageio.ImageIO.write((BufferedImage) mImage, "PNG",
						new File(filename + ".png"));
			} else {
				javax.imageio.ImageIO.write((BufferedImage) mImage, "PNG",
						new File("default" + ".png"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method called to repaint.
	 * 
	 * @param g
	 *            the Graphics object where we draw.
	 */
	public void paint(java.awt.Graphics g) {
		super.paint(g);
		// JOptionPane.showMessageDialog(null,
		// "Rendering time : "+(end-begin+1)+" images "+time+"  ms or "+(double)time/1000+" sec or "+(double)time/(1000*60)+" min!","Rendered Successfully !",JOptionPane.WARNING_MESSAGE);
		if (mImage != null) {
			g.drawImage(mImage, 0, 0, null);
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
	}

}
