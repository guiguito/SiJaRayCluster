package client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import network.EndOfImageComputation;
import network.ErrorForClient;
import network.IncomingConnection;
import network.MessageConf;
import network.PointsToCompute;
import network.ResultsComputation;
import network.ThreadSocket;
import network.ThreadSocketObservable;
import network.ThreadSocketObserver;
import Gui.AllScenes;
import Raytracer.PointOutOfImageException;
import Raytracer.Raytracer;
import Raytracer.Scene;

public class GuiClient extends JFrame implements ThreadSocketObserver{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel titlePanel = null;

	private JLabel titleSoft = null;

	private JPanel scenePanel = null;

	private JComboBox scenesList = null;

	private JPanel idPanel = null;

	private JPanel ipServerPanel = null;

	private JPanel portServerPanel = null;

	private JPanel buttonsPanel = null;

	private JLabel idText = null;

	private JTextField identifiant = null;

	private JLabel ipText = null;

	private JTextField ipAdress = null;

	private JLabel portText = null;

	private JTextField port = null;

	private JButton connectButton = null;
	
	//private
	private static GuiClient instance = null;
	private ThreadSocket sock = null;  //  @jve:decl-index=0:
	private HashMap<String,Scene> scenes;  //  @jve:decl-index=0:

	private JScrollPane logsPane = null;

	private JTextArea logs = null;
	private Raytracer raytracer = null;  //  @jve:decl-index=0:

	private JButton cleanLogs = null;
	
	ConcurrentList<ResultsComputation> results = null;
	ConcurrentList<PointsToCompute> jobs = null;
	Semaphore jobsSemaphore = null;
	Semaphore resultsSemaphore = null;
	
	ComputingThread computingThread = null;  //  @jve:decl-index=0:
	SendingThread sendingThread = null;


	/**
	 * This is the default constructor
	 */
	private GuiClient() {
		super();
		initialize();		
	}
	
	public static synchronized GuiClient getInstance(){
		if(instance == null){
			instance = new GuiClient();
		}
		return instance;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jobs = new ConcurrentList<PointsToCompute>(new ArrayList<PointsToCompute>());
		results = new ConcurrentList<ResultsComputation>(new ArrayList<ResultsComputation>());
		scenes = new HashMap<String,Scene>();
        Scene s1 = AllScenes.getScene1();
        scenes.put(s1.getName(),s1);
        Scene s2 = AllScenes.getScene2();
        scenes.put(s2.getName(),s2);
        Scene s3 = AllScenes.getScene3();
        scenes.put(s3.getName(),s3);
        Scene s4 = AllScenes.getScene4();
        scenes.put(s4.getName(),s4);
        Scene s5 = AllScenes.getScene5();
        scenes.put(s5.getName(),s5);
        Scene s6 = AllScenes.getScene6();
        scenes.put(s6.getName(),s6);
        Scene s7 = AllScenes.getScene7();
        scenes.put(s7.getName(),s7);
        Scene s8 = AllScenes.getScene8();
        scenes.put(s8.getName(),s8);
        Scene s9 = AllScenes.getScene9();
        scenes.put(s9.getName(),s9);
        Scene s10 = AllScenes.getScene10();
        scenes.put(s10.getName(),s10);
        Scene s11 = AllScenes.getScene11();
        scenes.put(s11.getName(),s11);
        Scene s12 = AllScenes.getScene12();
        scenes.put(s12.getName(),s12);
        Scene s13 = AllScenes.getScene13();
        scenes.put(s13.getName(),s13);
        Scene s14 = AllScenes.getScene14();
        scenes.put(s14.getName(),s14);
        Scene s15 = AllScenes.getScene15();
        scenes.put(s15.getName(),s15);
        Scene s16 = AllScenes.getScene16();
        scenes.put(s16.getName(),s16);
        Scene s17 = AllScenes.getScene17();
        scenes.put(s17.getName(),s17);
        Scene s18 = AllScenes.getScene18();
        scenes.put(s18.getName(),s18);
        Scene s19 = AllScenes.getScene19();
        scenes.put(s19.getName(),s19);
        
		this.setSize(400, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 600));
		this.setPreferredSize(new Dimension(400, 600));
		this.setContentPane(getJContentPane());
		this.setTitle("Java Raytracer Client by GGT");
	
		Set ens = scenes.keySet();        
	    Iterator it = ens.iterator();
	    while(it.hasNext()){
	       scenesList.addItem(it.next());
	    }
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(getTitlePanel(), null);
			jContentPane.add(getScenePanel(), null);
			jContentPane.add(getIdPanel(), null);
			jContentPane.add(getIpServerPanel(), null);
			jContentPane.add(getPortServerPanel(), null);
			jContentPane.add(getButtonsPanel(), null);
			jContentPane.add(getLogsPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes titlePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTitlePanel() {
		if (titlePanel == null) {
			titleSoft = new JLabel();
			titleSoft.setText("Java Raytracer Client");
			titleSoft.setFont(new Font("Courier New", Font.BOLD, 24));
			titlePanel = new JPanel();
			titlePanel.setLayout(new GridBagLayout());
			titlePanel.add(titleSoft, new GridBagConstraints());
		}
		return titlePanel;
	}

	/**
	 * This method initializes scenePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getScenePanel() {
		if (scenePanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.weightx = 1.0;
			scenePanel = new JPanel();
			scenePanel.setLayout(new GridBagLayout());
			scenePanel.add(getScenesList(), gridBagConstraints);
		}
		return scenePanel;
	}

	/**
	 * This method initializes scenesList	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getScenesList() {
		if (scenesList == null) {
			scenesList = new JComboBox();
			scenesList.setPreferredSize(new Dimension(250, 25));
		}
		return scenesList;
	}

	/**
	 * This method initializes idPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getIdPanel() {
		if (idPanel == null) {
			idText = new JLabel();
			idText.setText("ID of this client");
			idText.setName("idText");
			idPanel = new JPanel();
			idPanel.setLayout(new FlowLayout());
			idPanel.add(idText, null);
			idPanel.add(getIdentifiant(), null);
		}
		return idPanel;
	}

	/**
	 * This method initializes ipServerPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getIpServerPanel() {
		if (ipServerPanel == null) {
			ipText = new JLabel();
			ipText.setText("Adress of the server");
			ipServerPanel = new JPanel();
			ipServerPanel.setLayout(new FlowLayout());
			ipServerPanel.add(ipText, null);
			ipServerPanel.add(getIpAdress(), null);
		}
		return ipServerPanel;
	}

	/**
	 * This method initializes portServerPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPortServerPanel() {
		if (portServerPanel == null) {
			portText = new JLabel();
			portText.setText("Server's Port");
			portServerPanel = new JPanel();
			portServerPanel.setLayout(new FlowLayout());
			portServerPanel.add(portText, null);
			portServerPanel.add(getPort(), null);
		}
		return portServerPanel;
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new GridBagLayout());
			buttonsPanel.add(getConnectButton(), new GridBagConstraints());
			buttonsPanel.add(getCleanLogs(), gridBagConstraints1);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes identifiant	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIdentifiant() {
		if (identifiant == null) {
			identifiant = new JTextField();
			identifiant.setPreferredSize(new Dimension(200, 20));			
			identifiant.setText(System.getenv("USERDOMAIN"));
			identifiant.setName("identifiant");
		}
		return identifiant;
	}

	/**
	 * This method initializes ipAdress	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIpAdress() {
		if (ipAdress == null) {
			ipAdress = new JTextField();
			ipAdress.setPreferredSize(new Dimension(250, 20));
			ipAdress.setText("127.0.0.1");
		}
		return ipAdress;
	}

	/**
	 * This method initializes port	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPort() {
		if (port == null) {
			port = new JTextField();
			port.setPreferredSize(new Dimension(60, 20));
			port.setText("5000");
		}
		return port;
	}

	/**
	 * This method initializes connectButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getConnectButton() {
		if (connectButton == null) {
			connectButton = new JButton();
			connectButton.setText("Connect");
			connectButton.addMouseListener(new java.awt.event.MouseListener() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(connectButton.getText().equals("Connect")){//button is "connect"
						//try to connect
						String id = identifiant.getText();
						String address = ipAdress.getText();
						int por = 5000;
						
						boolean errors = false;
						String problems = "";
						try{
							por = Integer.parseInt(port.getText());
						}catch(NumberFormatException ex){
							problems += "The port is not a number !\n";
							errors = true;
						}
						
						if (errors){//there are some errors
							System.out.println("Connection not done because there are some errors in the fields : \n"+problems);
						}else{// no errors, try to connect
							boolean errorConnection = false;					
							try {
								sock = new ThreadSocket(new Socket(address,por));								
							} catch (UnknownHostException e1) {
								addLog("Error connecting. UnknowHost !");
								errorConnection = true;
							} catch (IOException e1) {
								addLog("Error connecting. Connection failed !");
								errorConnection = true;
							}
							if (!errorConnection){
								//start threadSocket
								sock.registerObserver(instance);
								sock.start();
								//prepare fisrt message
								Set<String> scn = scenes.keySet();
								String[] scns = new String[scn.size()];
								Iterator<String> it = scn.iterator();
								int i =0;
								while(it.hasNext()){
									scns[i]= it.next();
									i++;
								}
								//send message to identify this client to the server.
								addLog("Connected to server. Sending lists of scenes and id.");
								sock.send(new IncomingConnection(id,scns));
								connectButton.setText("Disconnect");
							}else{
								sock = null;
							}
						}
					}else{//button is "Disconnect"
						//disconnect from the server						
						connectButton.setText("Connect");
						sock.stopThread();
						sock = null;
						stopComputation();
						addLog("Disconnection from the server.");
					}
					
				}
				public void mousePressed(java.awt.event.MouseEvent e) {
				}
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
				}
			});
		}
		return connectButton;
	}
    /**
     * Stop computation
     */
	private void stopComputation(){
		if (raytracer != null){
			raytracer = null;
		}
		if (computingThread != null){
			computingThread.stopThread();
			computingThread = null;
			jobsSemaphore = null;
		}
		if (sendingThread != null){
			sendingThread.stopThread();
			sendingThread = null;
			resultsSemaphore = null;
		}
		System.gc();
	}
	
	public void updateConnectiondead(ThreadSocketObservable src, String mess) {
		sock = null;
		stopComputation();
		addLog("Disconnected from server ! Connection closed : "+mess+"!");		
		connectButton.setText("Connect");
	}

	public void updateObserverObjectReceived(Object obj) {		
		if (obj instanceof MessageConf){			
			//receiving scene to choose and raytracer's configuration
			addLog("Raytracer's configuration received !");
			MessageConf mess = (MessageConf)obj;
			java.awt.Color color = mess.getBackgroundcolor();
			Materials.Color col = new Materials.Color(color.getRed()/255.0,color.getGreen()/255.0,color.getBlue()/255.0); 
			raytracer = new Raytracer(scenes.get(mess.getScene()),mess.getXRes(),mess.getYRes(),
				mess.isSupersamp(),col,mess.getIllumination(),mess.getRecurs());
			/*
			jobs.clear();
			results.clear();
			*/			
			if (computingThread == null && sendingThread == null){//start threads if not started already
				jobsSemaphore = new Semaphore(0);
				resultsSemaphore = new Semaphore(0);
				computingThread = new ComputingThread(jobs,jobsSemaphore,results,resultsSemaphore,this);
				computingThread.start();
				sendingThread = new SendingThread(results,resultsSemaphore,this);
				sendingThread.start();
			}		
		}else if(obj instanceof ErrorForClient){
			//receiving an error
			ErrorForClient err = (ErrorForClient)obj;
			addLog("Error : "+err.getError());
			addLog("We disconnected you !");
			sock.stopThread();
			sock = null;
			stopComputation();
			connectButton.setText("Connect");
		}else if(obj instanceof PointsToCompute){
			//receiving list of points to compute
			if (raytracer != null){
				jobs.add((PointsToCompute)obj);
				jobsSemaphore.release();
			}
		}else if(obj instanceof EndOfImageComputation){
			//receiving message for the end of the computation
			EndOfImageComputation end = (EndOfImageComputation)obj;			
			//stopComputation();
			raytracer = null;
			addLog("Image rendering finished in "+end.getDuration()+" ms ! Waiting new renderings !");			
		}
	}

	public void updateObserverObjectReceived(ThreadSocketObservable src, Object obj) {
		//do nothing		
	}
	
	/**
	 * Add lopg in the gui.
	 * @param text
	 */
	public void addLog(String text){
		JScrollBar scrollBar = logsPane.getVerticalScrollBar();
		scrollBar.setValue(scrollBar.getMaximum());
		logs.append(text+"\n");		
	}
	
	/**
	 * Get the raytracer.
	 * @return return the raytracer.
	 */
	public Raytracer getRaytracer(){
		return raytracer;
	}
	
	/**
	 * Get the socket.
	 * @return the socket.
	 */
	public ThreadSocket getThreadSocket(){
		return sock;
	}

	/**
	 * This method initializes logsPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getLogsPane() {
		if (logsPane == null) {
			logsPane = new JScrollPane();
			logsPane.setPreferredSize(new Dimension(400, 200));
			logsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			logsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			logsPane.setViewportView(getLogs());
		}
		return logsPane;
	}

	/**
	 * This method initializes logs	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getLogs() {
		if (logs == null) {
			logs = new JTextArea();
			logs.setLineWrap(true);
		}
		return logs;
	}

	/**
	 * This method initializes cleanLogs	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCleanLogs() {
		if (cleanLogs == null) {
			cleanLogs = new JButton();
			cleanLogs.setText("Clean logs");
			cleanLogs.addMouseListener(new java.awt.event.MouseListener() {
				public void mouseClicked(java.awt.event.MouseEvent e) {					
					logs.setText("");
				}
				public void mousePressed(java.awt.event.MouseEvent e) {
				}
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
				}
			});
		}
		return cleanLogs;
	}

}  //  @jve:decl-index=0:visual-constraint="308,19"
