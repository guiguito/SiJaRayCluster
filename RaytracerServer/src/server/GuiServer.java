package server;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import network.MessageConf;
import network.ThreadSocket;
import Raytracer.Raytracer;

public class GuiServer extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel gauche = null;

	private JPanel droite = null;

	private JPanel options = null;

	private JPanel optionsRaytracer = null;

	private JPanel logs = null;

	private JLabel nameSoft = null;

	private JTextField chunkSize = null;

	private JTextField portServer = null;

	private JPanel valueStrategieContainer = null;

	private JLabel chunkValue = null;

	private JTextField xResolution = null;

	private JTextField yResolution = null;

	private JPanel xRes = null;

	private JPanel yRes = null;

	private JLabel xResText = null;

	private JLabel yResText = null;

	private JLabel logsLabel = null;

	private JPanel logsContainer = null;

	private JPanel nameContainer = null;

	private JPanel recursivityPanel = null;

	private JLabel recursivityText = null;

	private JTextField recursivity = null;

	private JPanel supersamplingPanel = null;

	private JLabel supersamplingText = null;

	private JCheckBox supersampling = null;

	private JPanel backgroundColorPanel = null;

	private JPanel filenamePanel = null;

	private JLabel filenameText = null;

	private JTextField filename = null;

	private JComboBox scenesList = null;

	private JComboBox strategiesList = null;

	private JPanel scenesPanel = null;

	private JPanel strategiesPanel = null;

	private JLabel scenesText = null;

	private JLabel strategiesText = null;

	private JScrollPane logsScrollPane = null;

	private JTextArea logsText = null;

	private JPanel illuminationPanel = null;

	private JLabel jLabel = null;

	private JComboBox illuminationList = null;

	private JColorChooser jColorChooser = null;

	private JPanel timesPanel = null;

	private JLabel beginText = null;

	private JTextField beginning = null;

	private JLabel endingText = null;

	private JTextField ending = null;

	private JPanel clients = null;

	private JPanel clientsContainer = null;

	private JPanel clientsTitleContainer = null;

	private JLabel clientsText = null;

	private JPanel clientsList = null;

	private JPanel buttonsServer = null;

	private JButton startServer = null;

	private JButton goRender = null;

	private JLabel chooseColorText = null;

	private static GuiServer instance;

	private ServerLogic server;

	private JScrollPane clientsScrollPane = null;

	private JTextArea clientsDisplay = null;

	private JButton cleanLogs = null;

	private JPanel serverPanel = null;

	private JLabel port1 = null;

	/**
	 * This is the default constructor
	 */
	private GuiServer() {
		super();
		initialize();
		server = ServerLogic.getInstance(this);
	}

	public static synchronized GuiServer getInstance() {
		if (instance == null) {
			instance = new GuiServer();
		}
		return instance;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 800);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(800, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 800));
		this.setContentPane(getJContentPane());
		this.setTitle("Java RaytracerServer by GGT");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(),
					BoxLayout.X_AXIS));
			jContentPane.add(getGauche(), null);
			jContentPane.add(getDroite(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes gauche
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getGauche() {
		if (gauche == null) {
			gauche = new JPanel();
			gauche.setLayout(new BoxLayout(getGauche(), BoxLayout.Y_AXIS));
			gauche.setPreferredSize(new Dimension(400, 800));
			gauche.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			gauche.add(getOptions(), null);
			gauche.add(getClients(), null);
		}
		return gauche;
	}

	/**
	 * This method initializes droite
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getDroite() {
		if (droite == null) {
			droite = new JPanel();
			droite.setLayout(new BoxLayout(getDroite(), BoxLayout.Y_AXIS));
			droite.setPreferredSize(new Dimension(400, 800));
			droite.add(getOptionsRaytracer(), null);
			droite.add(getLogs(), null);
		}
		return droite;
	}

	/**
	 * This method initializes options
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getOptions() {
		if (options == null) {
			nameSoft = new JLabel();
			nameSoft.setText("Java Raytracer Server");
			nameSoft.setHorizontalTextPosition(SwingConstants.CENTER);
			nameSoft.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC,
					24));
			nameSoft.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			nameSoft.setForeground(new Color(0, 51, 51));
			nameSoft.setBackground(new Color(249, 179, 88));
			nameSoft.setHorizontalAlignment(SwingConstants.CENTER);
			options = new JPanel();
			options.setLayout(new BoxLayout(getOptions(), BoxLayout.Y_AXIS));
			options.setPreferredSize(new Dimension(400, 300));
			options.add(getNameContainer(), null);
			options.add(getServerPanel(), null);
			options.add(getScenesPanel(), null);
			options.add(getStrategiesPanel(), null);
			options.add(getValueStrategieContainer(), null);
			options.add(getTimesPanel(), null);
			options.add(getButtonsServer(), null);
		}
		return options;
	}

	/**
	 * This method initializes optionsRaytracer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getOptionsRaytracer() {
		if (optionsRaytracer == null) {
			optionsRaytracer = new JPanel();
			optionsRaytracer.setLayout(new BoxLayout(getOptionsRaytracer(),
					BoxLayout.Y_AXIS));
			optionsRaytracer.setPreferredSize(new Dimension(400, 500));
			optionsRaytracer.add(getXRes(), null);
			optionsRaytracer.add(getYRes(), null);
			optionsRaytracer.add(getIlluminationPanel(), null);
			optionsRaytracer.add(getRecursivityPanel(), null);
			optionsRaytracer.add(getSupersamplingPanel(), null);
			optionsRaytracer.add(getBackgroundColorPanel(), null);
			optionsRaytracer.add(getFilenamePanel(), null);
		}
		return optionsRaytracer;
	}

	/**
	 * This method initializes logs
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getLogs() {
		if (logs == null) {
			logsLabel = new JLabel();
			logsLabel.setText("Logs of the server");
			logsLabel.setFont(new Font("Times New Roman", Font.ITALIC, 24));
			logs = new JPanel();
			logs.setLayout(new BoxLayout(getLogs(), BoxLayout.Y_AXIS));
			logs.setPreferredSize(new Dimension(400, 300));
			logs.add(getLogsContainer(), null);
			logs.add(getLogsScrollPane(), null);
		}
		return logs;
	}

	/**
	 * This method initializes chunkSize
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getChunkSize() {
		if (chunkSize == null) {
			chunkSize = new JTextField();
			chunkSize.setPreferredSize(new Dimension(100, 20));
			chunkSize.setText("100");
		}
		return chunkSize;
	}

	/**
	 * This method initializes portServer
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getPortServer() {
		if (portServer == null) {
			portServer = new JTextField();
			portServer.setPreferredSize(new Dimension(100, 20));
			portServer.setText("5000");
		}
		return portServer;
	}

	/**
	 * This method initializes valueStrategieContainer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getValueStrategieContainer() {
		if (valueStrategieContainer == null) {
			chunkValue = new JLabel();
			chunkValue.setText("Chunk size");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = -1;
			gridBagConstraints2.gridy = -1;
			valueStrategieContainer = new JPanel();
			valueStrategieContainer.setLayout(new FlowLayout());
			valueStrategieContainer.add(chunkValue, null);
			valueStrategieContainer.add(getChunkSize(), null);
		}
		return valueStrategieContainer;
	}

	/**
	 * This method initializes xResolution
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getXResolution() {
		if (xResolution == null) {
			xResolution = new JTextField();
			xResolution.setPreferredSize(new Dimension(80, 20));
			xResolution.setText("500");
		}
		return xResolution;
	}

	/**
	 * This method initializes yResolution
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getYResolution() {
		if (yResolution == null) {
			yResolution = new JTextField();
			yResolution.setPreferredSize(new Dimension(80, 20));
			yResolution.setText("500");
		}
		return yResolution;
	}

	/**
	 * This method initializes xRes
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getXRes() {
		if (xRes == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.gridy = -1;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.gridx = -1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = -1;
			gridBagConstraints6.gridy = -1;
			xResText = new JLabel();
			xResText.setText("X Resolution");
			xRes = new JPanel();
			xRes.setLayout(new FlowLayout());
			xRes.add(xResText, null);
			xRes.add(getXResolution(), null);
		}
		return xRes;
	}

	/**
	 * This method initializes yRes
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getYRes() {
		if (yRes == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = -1;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = -1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = -1;
			gridBagConstraints7.gridy = -1;
			yResText = new JLabel();
			yResText.setText("Y Resolution");
			yRes = new JPanel();
			yRes.setLayout(new FlowLayout());
			yRes.add(yResText, null);
			yRes.add(getYResolution(), null);
		}
		return yRes;
	}

	/**
	 * This method initializes logsContainer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getLogsContainer() {
		if (logsContainer == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = -1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = -1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = -1;
			gridBagConstraints8.gridy = -1;
			logsContainer = new JPanel();
			logsContainer.setLayout(new FlowLayout());
			logsContainer.setPreferredSize(new Dimension(400, 35));
			logsContainer.add(logsLabel, null);
			logsContainer.add(getCleanLogs(), null);
		}
		return logsContainer;
	}

	/**
	 * This method initializes nameContainer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getNameContainer() {
		if (nameContainer == null) {
			nameContainer = new JPanel();
			nameContainer.setLayout(new FlowLayout());
			nameContainer.add(nameSoft, null);
		}
		return nameContainer;
	}

	/**
	 * This method initializes recursivityPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getRecursivityPanel() {
		if (recursivityPanel == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = -1;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.gridx = -1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = -1;
			gridBagConstraints10.gridy = -1;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = -1;
			gridBagConstraints11.gridy = -1;
			recursivityText = new JLabel();
			recursivityText.setText("Number of recursivities ");
			recursivityPanel = new JPanel();
			recursivityPanel.setLayout(new FlowLayout());
			recursivityPanel.add(recursivityText, null);
			recursivityPanel.add(getRecursivity(), null);
		}
		return recursivityPanel;
	}

	/**
	 * This method initializes recursivity
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getRecursivity() {
		if (recursivity == null) {
			recursivity = new JTextField();
			recursivity.setPreferredSize(new Dimension(20, 20));
			recursivity.setText("6");
		}
		return recursivity;
	}

	/**
	 * This method initializes supersamplingPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getSupersamplingPanel() {
		if (supersamplingPanel == null) {
			chooseColorText = new JLabel();
			chooseColorText.setText("Choose background color");
			supersamplingText = new JLabel();
			supersamplingText.setText("ActivateSupersampling");
			supersamplingPanel = new JPanel();
			supersamplingPanel.setLayout(new FlowLayout());
			supersamplingPanel.add(supersamplingText, null);
			supersamplingPanel.add(getSupersampling(), null);
			supersamplingPanel.add(chooseColorText, null);
		}
		return supersamplingPanel;
	}

	/**
	 * This method initializes supersampling
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getSupersampling() {
		if (supersampling == null) {
			supersampling = new JCheckBox();
		}
		return supersampling;
	}

	/**
	 * This method initializes backgroundColorPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getBackgroundColorPanel() {
		if (backgroundColorPanel == null) {
			backgroundColorPanel = new JPanel();
			backgroundColorPanel.setLayout(new BoxLayout(
					getBackgroundColorPanel(), BoxLayout.Y_AXIS));
			backgroundColorPanel.add(getJColorChooser(), null);
		}
		return backgroundColorPanel;
	}

	/**
	 * This method initializes filenamePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getFilenamePanel() {
		if (filenamePanel == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints12.gridy = -1;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.gridx = -1;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = -1;
			gridBagConstraints13.gridy = -1;
			filenameText = new JLabel();
			filenameText.setText("Name of the file (default : defaultX.png)");
			filenamePanel = new JPanel();
			filenamePanel.setLayout(new FlowLayout());
			filenamePanel.add(filenameText, null);
			filenamePanel.add(getFilename(), null);
		}
		return filenamePanel;
	}

	/**
	 * This method initializes filename
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getFilename() {
		if (filename == null) {
			filename = new JTextField();
			filename.setPreferredSize(new Dimension(100, 20));
		}
		return filename;
	}

	/**
	 * This method initializes scenesList
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getScenesList() {
		if (scenesList == null) {
			scenesList = new JComboBox();
			scenesList.setPreferredSize(new Dimension(200, 25));
			scenesList.addItem("A) Whitted's scene");
			scenesList.addItem("B) Yellow ball red triangle");
			scenesList.addItem("Z) Presentation mapping 1");
			scenesList.addItem("D) Benchmark 2 mushroom.off");
			scenesList.addItem("E) Presentation mapping 2");
			scenesList.addItem("F) Benchmark 1 heightfield");
			scenesList.addItem("Z) Presentation Off1");
			scenesList.addItem("H) Animation 1");
			scenesList.addItem("I) Animation 2");
			scenesList.addItem("Z) Presentation light 3");
			scenesList.addItem("Z) Presentation Mapping 3");
			scenesList.addItem("L) Animation mushroom.off");
			scenesList.addItem("M) Animation final 1");
			scenesList.addItem("Z) Presentation Light 1");
			scenesList.addItem("Z)  Presentation Light 2");
			scenesList.addItem("Z) Presentation SmoothOff 2");
			scenesList.addItem("X) Hello world ");
			scenesList.addItem("z) Animation final 2");
			scenesList.addItem("P) Perlin Noise Test");
		}
		return scenesList;
	}

	/**
	 * This method initializes strategiesList
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getStrategiesList() {
		if (strategiesList == null) {
			strategiesList = new JComboBox();
			strategiesList.setPreferredSize(new Dimension(200, 25));
			strategiesList.addItem("chunks");
			strategiesList.addItem("other");
		}
		return strategiesList;
	}

	/**
	 * This method initializes scenesPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getScenesPanel() {
		if (scenesPanel == null) {
			scenesText = new JLabel();
			scenesText.setText("Choose scene");
			scenesPanel = new JPanel();
			scenesPanel.setLayout(new FlowLayout());
			scenesPanel.add(scenesText, null);
			scenesPanel.add(getScenesList(), null);
		}
		return scenesPanel;
	}

	/**
	 * This method initializes strategiesPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getStrategiesPanel() {
		if (strategiesPanel == null) {
			strategiesText = new JLabel();
			strategiesText.setText("Choose strategie");
			strategiesPanel = new JPanel();
			strategiesPanel.setLayout(new FlowLayout());
			strategiesPanel.add(strategiesText, null);
			strategiesPanel.add(getStrategiesList(), null);
		}
		return strategiesPanel;
	}

	/**
	 * This method initializes logsScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getLogsScrollPane() {
		if (logsScrollPane == null) {
			logsScrollPane = new JScrollPane();
			logsScrollPane.setPreferredSize(new Dimension(400, 265));
			logsScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			logsScrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			logsScrollPane.setViewportBorder(new SoftBevelBorder(
					SoftBevelBorder.LOWERED));
			logsScrollPane.setViewportView(getLogsText());
		}
		return logsScrollPane;
	}

	/**
	 * This method initializes logsText
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getLogsText() {
		if (logsText == null) {
			logsText = new JTextArea();
			logsText.setLineWrap(true);
		}
		return logsText;
	}

	/**
	 * This method initializes illuminationPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getIlluminationPanel() {
		if (illuminationPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Illumination model");
			illuminationPanel = new JPanel();
			illuminationPanel.setLayout(new FlowLayout());
			illuminationPanel.add(jLabel, null);
			illuminationPanel.add(getIlluminationList(), null);
		}
		return illuminationPanel;
	}

	/**
	 * This method initializes illuminationList
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getIlluminationList() {
		if (illuminationList == null) {
			illuminationList = new JComboBox();
			illuminationList.setPreferredSize(new Dimension(200, 25));
			illuminationList.addItem("Phong");
			illuminationList.addItem("Phong-Blinn");
		}
		return illuminationList;
	}

	/**
	 * This method initializes jColorChooser
	 * 
	 * @return javax.swing.JColorChooser
	 */
	private JColorChooser getJColorChooser() {
		if (jColorChooser == null) {
			jColorChooser = new JColorChooser(Color.BLACK);
			jColorChooser.setEnabled(true);
			jColorChooser.setPreferredSize(new Dimension(409, 250));
		}
		return jColorChooser;
	}

	/**
	 * This method initializes timesPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getTimesPanel() {
		if (timesPanel == null) {
			endingText = new JLabel();
			endingText.setText("Ending frame");
			beginText = new JLabel();
			beginText.setText("Beginning frame");
			timesPanel = new JPanel();
			timesPanel.setLayout(new FlowLayout());
			timesPanel.add(beginText, null);
			timesPanel.add(getBeginning(), null);
			timesPanel.add(endingText, null);
			timesPanel.add(getEnding(), null);
		}
		return timesPanel;
	}

	/**
	 * This method initializes beginning
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getBeginning() {
		if (beginning == null) {
			beginning = new JTextField();
			beginning.setPreferredSize(new Dimension(40, 20));
			beginning.setText("1");
		}
		return beginning;
	}

	/**
	 * This method initializes ending
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getEnding() {
		if (ending == null) {
			ending = new JTextField();
			ending.setPreferredSize(new Dimension(40, 20));
			ending.setText("1");
		}
		return ending;
	}

	/**
	 * This method initializes clients
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getClients() {
		if (clients == null) {
			clients = new JPanel();
			clients.setLayout(new BoxLayout(getClients(), BoxLayout.Y_AXIS));
			clients.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			clients.setPreferredSize(new Dimension(400, 700));
			clients.add(getClientsContainer(), null);
		}
		return clients;
	}

	/**
	 * This method initializes clientsContainer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getClientsContainer() {
		if (clientsContainer == null) {
			clientsContainer = new JPanel();
			clientsContainer.setLayout(new BoxLayout(getClientsContainer(),
					BoxLayout.Y_AXIS));
			clientsContainer.add(getClientsTitleContainer(), null);
			clientsContainer.add(getClientsList(), null);
		}
		return clientsContainer;
	}

	/**
	 * This method initializes clientsTitleContainer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getClientsTitleContainer() {
		if (clientsTitleContainer == null) {
			clientsText = new JLabel();
			clientsText.setFont(new Font("Times New Roman", Font.ITALIC, 24));
			clientsText.setHorizontalTextPosition(SwingConstants.LEFT);
			clientsText.setText("Clients Connected");
			clientsText.setHorizontalAlignment(SwingConstants.LEFT);
			clientsTitleContainer = new JPanel();
			clientsTitleContainer.setLayout(new GridBagLayout());
			clientsTitleContainer.setPreferredSize(new Dimension(400, 35));
			clientsTitleContainer.add(clientsText, new GridBagConstraints());
		}
		return clientsTitleContainer;
	}

	/**
	 * This method initializes clientsList
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getClientsList() {
		if (clientsList == null) {
			clientsList = new JPanel();
			clientsList.setLayout(new BoxLayout(getClientsList(),
					BoxLayout.Y_AXIS));
			clientsList.setPreferredSize(new Dimension(400, 655));
			clientsList.add(getClientsScrollPane(), null);
		}
		return clientsList;
	}

	/**
	 * This method initializes buttonsServer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getButtonsServer() {
		if (buttonsServer == null) {
			buttonsServer = new JPanel();
			buttonsServer.setLayout(new FlowLayout());
			buttonsServer.add(getGoRender(), null);
		}
		return buttonsServer;
	}

	/**
	 * This method initializes startServer
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getStartServer() {
		if (startServer == null) {
			startServer = new JButton();
			startServer.setText("Start Server");
			startServer.addMouseListener(new java.awt.event.MouseListener() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					pushStartServerButton();
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
		return startServer;
	}

	/**
	 * This method initializes goRender
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getGoRender() {
		if (goRender == null) {
			goRender = new JButton();
			goRender.setText("Go Render !");
			goRender.setEnabled(false);
			goRender.addMouseListener(new java.awt.event.MouseListener() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					pushGoRenderButton();
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
		return goRender;
	}

	/**
	 * This method initializes clientsScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getClientsScrollPane() {
		if (clientsScrollPane == null) {
			clientsScrollPane = new JScrollPane();
			clientsScrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			clientsScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			clientsScrollPane.setBorder(null);
			clientsScrollPane.setViewportView(getClientsDisplay());
		}
		return clientsScrollPane;
	}

	/**
	 * This method initializes clientsDisplay
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getClientsDisplay() {
		if (clientsDisplay == null) {
			clientsDisplay = new JTextArea();
			clientsDisplay.setLineWrap(true);
		}
		return clientsDisplay;
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
				}

				public void mousePressed(java.awt.event.MouseEvent e) {
					logsText.setText("");
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

	/**
	 * Add text to the logs.
	 * 
	 * @param text
	 *            text to add.
	 */
	public void addLog(String text) {
		JScrollBar scrollBar = logsScrollPane.getVerticalScrollBar();
		scrollBar.setValue(scrollBar.getMaximum());
		logsText.append(text + "\n");
	}

	/**
	 * Update the displayed list of clients.
	 * 
	 * @param clients
	 *            list of clients.
	 */
	public void updateGuiForClients(HashMap<ThreadSocket, Client> clients) {
		if (clients.size() > 0) {
			goRender.setEnabled(true);
		} else {
			goRender.setEnabled(false);
		}
		clientsDisplay.setText("");
		Collection<Client> list = clients.values();
		Iterator<Client> it = list.iterator();
		while (it.hasNext()) {
			Client cl = it.next();
			clientsDisplay.append(cl.getId() + " : " + cl.getJobsDone()
					+ " jobs, " + cl.getPointsComputed() + " points\n");
			double d = cl.getPtsPerSecond();
			clientsDisplay.append(cl.getId() + " : " + cl.getTimeComputing()
					+ " ms calculating, " + cl.getPtsPerSecond() + " pcps\n");
		}
	}

	/**
	 * Method called to change the value of goRender Button.
	 */
	public void pushGoRenderButton() {
		if (goRender.getText().equals("Go Render !")
				&& goRender.isEnabled() == true) {
			int chunk = 100;
			int begin = 1;
			int end = 1;
			int xRes = 500;
			int yRes = 500;
			int recurs = 6;
			String scene = "";
			String strategie = "";
			String illumination = "";
			boolean supersamp = false;
			Color color = Color.BLACK;
			String file = "default";

			boolean errors = false;
			String problems = "";

			try {
				chunk = Integer.parseInt(chunkSize.getText());
			} catch (NumberFormatException e1) {
				errors = true;
				problems += "Chunk size is not a number !";
			}
			if (chunk <= 0) {
				errors = true;
				problems += "Wrong size for Chunk size !";
			}

			try {
				begin = Integer.parseInt(beginning.getText());
			} catch (NumberFormatException e1) {
				errors = true;
				problems += "beginning frame is not a number !";
			}
			try {
				end = Integer.parseInt(ending.getText());
			} catch (NumberFormatException e1) {
				errors = true;
				problems += "ending frame is not a number !";
			}
			try {
				xRes = Integer.parseInt(xResolution.getText());
			} catch (NumberFormatException e1) {
				errors = true;
				problems += "x resolution is not a number !";
			}
			try {
				yRes = Integer.parseInt(yResolution.getText());
			} catch (NumberFormatException e1) {
				errors = true;
				problems += "y resolution is not a number !";
			}
			if (xRes <= 0) {
				errors = true;
				problems += "x resolution is wrong !";
			}
			if (yRes <= 0) {
				errors = true;
				problems += "y resolution is wrong !";
			}
			try {
				recurs = Integer.parseInt(recursivity.getText());
			} catch (NumberFormatException e1) {
				errors = true;
				problems += "recursivity is not a number !";
			}
			scene = (String) scenesList.getSelectedItem();
			strategie = (String) strategiesList.getSelectedItem();
			illumination = (String) illuminationList.getSelectedItem();
			supersamp = supersampling.isSelected();
			color = jColorChooser.getColor();
			if (!(filename.getText()).equals("")) {
				file = filename.getText();
			}
			if (errors) {// there are some errors
				addLog("Rendering not launched because of the following errors : "
						+ problems);
			} else {// Everything is ok start the rendering.
				chunkSize.setEnabled(false);
				beginning.setEnabled(false);
				ending.setEnabled(false);
				xResolution.setEnabled(false);
				yResolution.setEnabled(false);
				recursivity.setEnabled(false);
				jColorChooser.setEnabled(false);
				supersampling.setEnabled(false);
				illuminationList.setEnabled(false);
				strategiesList.setEnabled(false);
				scenesList.setEnabled(false);
				filename.setEnabled(false);
				jColorChooser.setEnabled(false);
				int ill = Raytracer.PHONG;
				if (illumination.equals("Phong")) {
					ill = Raytracer.PHONG;
				} else if (illumination.equals("Phong-Blinn")) {
					ill = Raytracer.PHONGBLINN;
				}
				MessageConf mess = new MessageConf(ill, supersamp, color,
						scene, begin, end, xRes, yRes, recurs);

				server.goRender(strategie, chunk, file, mess);
				goRender.setText("Stop Rendering");
			}
		} else if (goRender.getText().equals("Stop Rendering")
				&& goRender.isEnabled() == true) {
			chunkSize.setEnabled(true);
			beginning.setEnabled(true);
			ending.setEnabled(true);
			xResolution.setEnabled(true);
			yResolution.setEnabled(true);
			recursivity.setEnabled(true);
			jColorChooser.setEnabled(true);
			supersampling.setEnabled(true);
			illuminationList.setEnabled(true);
			strategiesList.setEnabled(true);
			scenesList.setEnabled(true);
			filename.setEnabled(true);
			jColorChooser.setEnabled(true);
			server.stopRendering();
			goRender.setText("Go Render !");
		}
	}

	/**
	 * Method called to change the value of startServer Button.
	 */
	public void pushStartServerButton() {
		if ((startServer.getText()).equals("Start Server")) {// button is start
																// Server
			// try to start the server
			int port = 5000;

			boolean errors = false;
			String problems = "";

			// get values from the GUI
			try {
			} catch (NumberFormatException e1) {
				errors = true;
				problems += "the port is not a number !";
			}

			if (errors) {// there are some errors
				addLog("Server not started because of the following errors : "
						+ problems);
			} else {// Everything is ok start the server.
				portServer.setEnabled(false);
				startServer.setText("Stop Server");
				server.startServer(port);
			}
		} else {// button is Stop Server
				// stop the server
			if (goRender.getText().equals("Stop Rendering")) {
				pushGoRenderButton();
				goRender.setText("Go Render !");
			}
			goRender.setEnabled(false);
			portServer.setEnabled(true);
			startServer.setText("Start Server");
			server.stopServer();
		}
	}

	/**
	 * This method initializes serverPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getServerPanel() {
		if (serverPanel == null) {
			port1 = new JLabel();
			port1.setText("Port used");
			serverPanel = new JPanel();
			serverPanel.setLayout(new FlowLayout());
			serverPanel.add(port1, null);
			serverPanel.add(getPortServer(), null);
			serverPanel.add(getStartServer(), null);
		}
		return serverPanel;
	}

} // @jve:decl-index=0:visual-constraint="117,13"
