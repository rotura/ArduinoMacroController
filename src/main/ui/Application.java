package main.ui;

import com.fazecast.jSerialComm.SerialPort;

import main.ui.controller.ApplicationController;
import main.ui.listener.TextFieldListener;
import main.utils.Constants;
import main.utils.renderer.PortRenderer;
import main.utils.renderer.TemplateRenderer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSlider;
import javax.swing.JPanel;

public class Application extends JFrame {
    private final static Logger LOGGER = Logger.getLogger("Application");

	private static final long serialVersionUID = 1L;
	private ApplicationController controller;
	private TextInternalizatorController internalizator;
	
	private JComboBox<SerialPort> comPortSelect;
	private JLabel comLabel;
	private JButton btnConnect;
	private JButton btnSave;
	private JLabel lblKey1;
	private JLabel lblKey2;
	private JTextField textKey1;
	private JTextField textKey2;
	private JButton btnUpdateLists;
	private JMenuBar menuBar;
	private JMenu menuOptions;
	private Component horizontalGlue;
	private JMenu btnExit;
	private JMenuItem mntmExportConfiguration;
	private JMenuItem mntmImportConfiguration;
	private JButton btnRestore1;
	private JButton btnRestore2;
	private JButton btnRestoreAll;
	private JSeparator separator;
	private JLabel lblKeys;
	private JSpinner keysNumber;
	private JMenu mnLanguage;
	private JMenuItem mntmSpanish;
	private JMenuItem mntmEnglish;
	private JSeparator separator_1;
	private JLabel lblR;
	private JLabel lblG;
	private JLabel lblB;
	private JSlider slider_R;
	private JSlider slider_G;
	private JSlider slider_B;
	private JSpinner spinner_R;
	private JSpinner spinner_G;
	private JSpinner spinner_B;
	private JButton btnTest;
	private JLabel lblKey3;
	private JTextField textKey3;
	private JButton btnRestore3;
	private JLabel lblKey4;
	private JTextField textKey4;
	private JButton btnRestore4;
	private JLabel lblKey5;
	private JTextField textKey5;
	private JButton btnRestore5;
	private JLabel lblKey6;
	private JTextField textKey6;
	private JButton btnRestore6;
	private JSeparator separator_1_1;
	private JButton btnRestoreRGB;
	private JLabel lblExample;
	private JPanel panelRGB;
	private JComboBox<File> templateSelect;
	private JLabel lblTemplate;
	private JButton btnTemplate;
	
	public static void main(String[] args) {
		Application ventana=new Application(); 
		ventana.setVisible(true); 
		ventana.setResizable(false);
        LOGGER.log(Level.INFO, "Aplication started");
    }

	public Application() {
		Handler fileHandler = null;
		try {
			generateFolders();
			fileHandler = new FileHandler("./arconf/log/arduinoMacroController.log", true);
	        SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
		} catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "IO exception", ex);
        } catch (SecurityException ex) {
            LOGGER.log(Level.SEVERE, "Security exception", ex);
        }
		setIconImage(resizeImage("arduino-icon.png").getImage());
		setResizable(false);
		setTitle("Arduino Macro Controller");
		setSize(new Dimension(500, 520));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                exit();
            }
        });
		
		this.internalizator = new TextInternalizatorController(this, getLocale());
		this.controller = new ApplicationController(this.internalizator, fileHandler);
		
		getContentPane().setLayout(null);
		getContentPane().add(getComPortSelect());
		getContentPane().add(getComLabel());
		getContentPane().add(getBtnConnect());
		getContentPane().add(getBtnSave());
		getContentPane().add(getLblKey1());
		getContentPane().add(getLblKey2());
		getContentPane().add(getTextKey1());
		getContentPane().add(getTextKey2());
		getContentPane().add(getBtnUpdateLists());
		getContentPane().add(getBtnRestore1());
		getContentPane().add(getBtnRestore2());
		getContentPane().add(getBtnRestoreAll());
		getContentPane().add(getSeparator());
		getContentPane().add(getLblKeys());
		getContentPane().add(getKeysNumber());
		getContentPane().add(getSeparator_1());
		getContentPane().add(getLblR());
		getContentPane().add(getLblG());
		getContentPane().add(getLblB());
		getContentPane().add(getSlider_R());
		getContentPane().add(getSlider_G());
		getContentPane().add(getSlider_B());
		getContentPane().add(getSpinner_R());
		getContentPane().add(getSpinner_G());
		getContentPane().add(getSpinner_B());
		getContentPane().add(getBtnTest());
		getContentPane().add(getLblKey3());
		getContentPane().add(getTextKey3());
		getContentPane().add(getBtnRestore3());
		getContentPane().add(getLblKey4());
		getContentPane().add(getTextKey4());
		getContentPane().add(getBtnRestore4());
		getContentPane().add(getLblKey5());
		getContentPane().add(getTextKey5());
		getContentPane().add(getBtnRestore5());
		getContentPane().add(getLblKey6());
		getContentPane().add(getTextKey6());
		getContentPane().add(getBtnRestore6());
		getContentPane().add(getSeparator_1_1());
		getContentPane().add(getBtnRestoreRGB());
		getContentPane().add(getLblExample());
		getContentPane().add(getPanelRGB());
		getContentPane().add(getTemplateSelect());
		getContentPane().add(getLblTemplate());
		getContentPane().add(getBtnTemplate());
		setJMenuBar(getMenuBar_1());
		
		chargeUsserSetting();
		this.internalizator.updateGUITexts();
		
	}
	
	  ///////////////////////
	 ///   UI ELEMENTS   ///
	///////////////////////
	
	private void chargeUsserSetting() {
		try {
			String[] userSettings = this.controller.chargeAppStatus();
			this.internalizator.setLocale(new Locale(userSettings[0]));
			Integer keys = Integer.parseInt(userSettings[1]);
			if(Constants.keysConfigurables.contains(keys)) {
				this.keysNumber.setValue(keys);
			}
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "userSettings not found");
		}		
	}

	private JComboBox<SerialPort> getComPortSelect() {
		if (comPortSelect == null) {
			comPortSelect = new WideComboBox<SerialPort>();
			comPortSelect.setBounds(95, 11, 142, 22);
			comPortSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			for (SerialPort port: controller.getPorts()) {
				comPortSelect.addItem(port);
			}
			comPortSelect.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			        btnConnect.grabFocus();
			    }
			});			
			comPortSelect.setRenderer(new PortRenderer());
		}
		return comPortSelect;
	}
	
	
	protected JLabel getComLabel() {
		if (comLabel == null) {
			comLabel = new JLabel("COM port:");
			comLabel.setLabelFor(getComPortSelect());
			comLabel.setBounds(10, 15, 87, 14);
		}
		return comLabel;
	}
	protected JButton getBtnConnect() {
		if (btnConnect == null) {
			btnConnect = new JButton("Connect");
			btnConnect.setMnemonic('c');
			btnConnect.setMnemonic(KeyEvent.VK_C);
			btnConnect.setBounds(333, 11, 142, 23);
			btnConnect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnConnect.setIcon(resizeImage("Conection-icon.png"));  
			
			btnConnect.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
				    connect();
				  } 
				} );
			btnConnect.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {
						connect();
					}
				}
				
				@Override
				public void keyTyped(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {}	
			});
			
		}
		return btnConnect;
	}
	protected JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Save");
			btnSave.setMnemonic('s');
			btnSave.setMnemonic(KeyEvent.VK_S);
			btnSave.setEnabled(false);
			btnSave.setBounds(343, 423, 132, 23);
			btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSave.setIcon(resizeImage("Save-icon.png"));  
			
			btnSave.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  if(controller.getStatus() != Constants.STATUS_DISCONNECTED) {
						  HashMap<String, String> keys = generateKeysMap();
						  if(controller.saveConfiguration(keys, true)) {
							  JOptionPane.showMessageDialog(null, internalizator.getCONFIGURATION_SAVED_MESSAGE(),
									  internalizator.getCONFIGURATION_SAVED(), JOptionPane.INFORMATION_MESSAGE);
						  } else {
							  HashMap<String,String> keysList = controller.getKeyConfiguration();
							  chargeConfiguredData(keysList);
						  }
					  }
				  } 
				} );
		}
		return btnSave;
	}
	protected JLabel getLblKey1() {
		if (lblKey1 == null) {
			lblKey1 = new JLabel("Key 1:");
			lblKey1.setLabelFor(getTextKey1());
			lblKey1.setBounds(10, 59, 46, 14);
		}
		return lblKey1;
	}
	protected JLabel getLblKey2() {
		if (lblKey2 == null) {
			lblKey2 = new JLabel("Key 2:");
			lblKey2.setLabelFor(getTextKey2());
			lblKey2.setBounds(10, 90, 46, 14);
		}
		return lblKey2;
	}
	private JTextField getTextKey1() {
		if (textKey1 == null) {
			textKey1 = new JTextField();
			textKey1.setEnabled(false);
			textKey1.setBounds(95, 56, 228, 20);
			textKey1.setColumns(10);
			textKey1.addKeyListener(new TextFieldListener(textKey1));
		}
		return textKey1;
	}
	private JTextField getTextKey2() {
		if (textKey2 == null) {
			textKey2 = new JTextField();
			textKey2.setEnabled(false);
			textKey2.setColumns(10);
			textKey2.setBounds(95, 87, 228, 20);
			textKey2.addKeyListener(new TextFieldListener(textKey2));
		}
		return textKey2;
	}
	protected JButton getBtnUpdateLists() {
		if (btnUpdateLists == null) {
			btnUpdateLists = new JButton("Update ports");
			btnUpdateLists.setEnabled(true);
			btnUpdateLists.setMnemonic('u');
			btnUpdateLists.setMnemonic(KeyEvent.VK_U);
			btnUpdateLists.setBounds(10, 423, 150, 23);
			btnUpdateLists.setMargin(new Insets(2, 5, 2, 5));
			btnUpdateLists.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnUpdateLists.setIcon(resizeImage("Refresh-icon.png"));  
			
			btnUpdateLists.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  if(controller.getStatus() == Constants.STATUS_DISCONNECTED) {
						  comPortSelect.removeAllItems();
						  templateSelect.removeAllItems();
						  for (SerialPort port: controller.getPorts()) {
							comPortSelect.addItem(port);
						  }
						  for (File file: controller.getConfFiles()) {
							  templateSelect.addItem(file);
						  }
					  }
				  } 
				} );
		}
		return btnUpdateLists;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenuOptions());
			menuBar.add(getMnLanguage());
			menuBar.add(getHorizontalGlue());
			menuBar.add(getBtnExit());
		}
		return menuBar;
	}
	protected JMenu getMenuOptions() {
		if (menuOptions == null) {
			menuOptions = new JMenu("Options");
			menuOptions.setMnemonic('o');
			menuOptions.setMnemonic(KeyEvent.VK_O);
			menuOptions.add(getMntmImportConfiguration());
			menuOptions.add(getMntmExportConfiguration());
			menuOptions.addActionListener(comPortSelect);
			menuOptions.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent me) {
	            	menuOptions.setOpaque(true);
	            	menuOptions.setBackground(Color.LIGHT_GRAY);
	            }
	            
	            @Override
	            public void mouseExited(MouseEvent me) {
	            	menuOptions.setOpaque(false);
	            	menuOptions.setBackground(new Color(240,240,240));
	            }
	        });
		}
		return menuOptions;
	}

	protected JMenu getMnLanguage() {
		if (mnLanguage == null) {
			mnLanguage = new JMenu("Language");
			mnLanguage.setMnemonic('l');
			mnLanguage.setMnemonic(KeyEvent.VK_L);
			mnLanguage.add(getMntmSpanish());
			mnLanguage.add(getMntmEnglish());
			mnLanguage.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent me) {
	            	mnLanguage.setOpaque(true);
	            	mnLanguage.setBackground(Color.LIGHT_GRAY);
	            }
	            
	            @Override
	            public void mouseExited(MouseEvent me) {
	            	mnLanguage.setOpaque(false);
	            	mnLanguage.setBackground(new Color(240,240,240));
	            }
	        });
		}
		return mnLanguage;
	}
	protected JMenuItem getMntmSpanish() {
		if (mntmSpanish == null) {
			mntmSpanish = new JMenuItem("Spanish");
			mntmSpanish.setIconTextGap(4);
			mntmSpanish.setIcon(resizeImage("es.png"));  
			mntmSpanish.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent me) {
			        LOGGER.log(Level.INFO, "Language changed to ES");
	            	internalizator.setLocale(new Locale("es","ES"));
	            	internalizator.updateGUITexts();
	            }
	        });
		}
		return mntmSpanish;
	}
	protected JMenuItem getMntmEnglish() {
		if (mntmEnglish == null) {
			mntmEnglish = new JMenuItem("English");
			mntmEnglish.setIconTextGap(4);
			mntmEnglish.setIcon(resizeImage("us.png")); 
			mntmEnglish.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent me) {
			        LOGGER.log(Level.INFO, "Language changed to US");
	            	internalizator.setLocale(Locale.ENGLISH);
	            	internalizator.updateGUITexts();
	            }
	        });
		}
		return mntmEnglish;
	}
	
	protected JMenu getBtnExit() {
		if (btnExit == null) {
			btnExit = new JMenu("Exit");
			btnExit.setBorderPainted(true);
			btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
			btnExit.setHorizontalAlignment(SwingConstants.CENTER);
			btnExit.setMnemonic('e');
			btnExit.setMnemonic(KeyEvent.VK_E);
			btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnExit.setMargin(new Insets(1, 5, 1, 5));
			
			btnExit.addMouseListener(new MouseAdapter() {
				@Override
	            public void mouseClicked(MouseEvent me) {
	            	exit();
	            }
				
	            @Override
	            public void mouseEntered(MouseEvent me) {
	            	btnExit.setOpaque(true);
	                btnExit.setBackground(Color.LIGHT_GRAY);
	            }
	            
	            @Override
	            public void mouseExited(MouseEvent me) {
	            	btnExit.setOpaque(false);
	                btnExit.setBackground(new Color(240,240,240));
	            }
	        });
			
		}
		return btnExit;
	}
	private Component getHorizontalGlue() {
		if (horizontalGlue == null) {
			horizontalGlue = Box.createHorizontalGlue();
			horizontalGlue.setPreferredSize(new Dimension(300, 0));
		}
		return horizontalGlue;
	}
	protected JMenuItem getMntmExportConfiguration() {
		if (mntmExportConfiguration == null) {
			mntmExportConfiguration = new JMenuItem("Export Configuration");
			mntmExportConfiguration.setEnabled(false);
			mntmExportConfiguration.setMnemonic('e');
			mntmExportConfiguration.setMnemonic(KeyEvent.VK_E);
			mntmExportConfiguration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			mntmExportConfiguration.setIcon(resizeImage("Export-icon.png"));  
			mntmExportConfiguration.setIconTextGap(4);
			mntmExportConfiguration.setSelected(true);
			mntmExportConfiguration.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent me) {
					  try {
							JFileChooser file = new JFileChooser(System.getProperty("user.dir") + "/arconf");
							FileFilter ff = new FileNameExtensionFilter("Valid files", "arconf");
							file.addChoosableFileFilter(ff);
							file.setFileFilter(ff);
							file.setDialogTitle("Export");
							file.showSaveDialog(file);
							File fileToSave = file.getSelectedFile();
							if(fileToSave == null) {
								return;
							}
							if(!fileToSave.getName().contains(".arconf")) {
								fileToSave = new File(file.getSelectedFile() +".arconf");	
							}
							if (fileToSave != null) {
								if (new File(fileToSave.getAbsolutePath()).exists()) {
									if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(file, internalizator.getFILE_EXIST(), 
											internalizator.getINFO(), JOptionPane.YES_NO_OPTION)) {
										controller.exportConfiguration(fileToSave, generateKeysMap());
										JOptionPane.showMessageDialog(null, internalizator.getFILE_SAVED(), 
												internalizator.getINFO(), JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									controller.exportConfiguration(fileToSave, generateKeysMap());
									JOptionPane.showMessageDialog(null, internalizator.getFILE_SAVED(), 
											internalizator.getINFO(), JOptionPane.INFORMATION_MESSAGE);
								}
							}
						} catch (IOException ex) {
							LOGGER.log(Level.SEVERE, "Error exporting conf",ex);
							JOptionPane.showMessageDialog(null, internalizator.getFILE_NO_SAVED(), 
									internalizator.getERROR(), JOptionPane.WARNING_MESSAGE);
						}
	            }
	        });
		}
		return mntmExportConfiguration;
	}
	
	protected JMenuItem getMntmImportConfiguration() {
		if (mntmImportConfiguration == null) {
			mntmImportConfiguration = new JMenuItem("Import Configuration");
			mntmImportConfiguration.setEnabled(false);
			mntmImportConfiguration.setIcon(resizeImage("Import-icon.png"));  
			mntmImportConfiguration.setIconTextGap(4);
			mntmImportConfiguration.setMnemonic('i');
			mntmImportConfiguration.setMnemonic(KeyEvent.VK_I);
			mntmImportConfiguration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			mntmImportConfiguration.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent me) {
					try {
						JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
						FileFilter ff = new FileNameExtensionFilter("Valid files", "conf");
						file.addChoosableFileFilter(ff);
						file.setFileFilter(ff);
						file.setDialogTitle("Import");
						file.setApproveButtonText("Import file");
						file.showOpenDialog(file);
						File fileToOpen = file.getSelectedFile();

						if (fileToOpen != null) {
							HashMap<String, String> valuesImported = controller.importConfiguration(fileToOpen);
							if(valuesImported != null) {
								chargeConfiguredData(valuesImported);
								JOptionPane.showMessageDialog(null, internalizator.getIMPORT_SUCESSFULLY(),
										internalizator.getINFO(), JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, internalizator.getFORMAT_INCORRECT(), 
										internalizator.getINFO(), JOptionPane.INFORMATION_MESSAGE);
							}
						}
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, internalizator.getCANT_OPEN_FILE(), 
								internalizator.getERROR(), JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
		}
		return mntmImportConfiguration;
	}
	
	protected JButton getBtnRestore1() {
		if (btnRestore1 == null) {
			btnRestore1 = new JButton("Restore");
			btnRestore1.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestore1.setBounds(333, 55, 142, 23);
			btnRestore1.setEnabled(false);
			btnRestore1.setIcon(resizeImage("Trash-icon.png"));  
			btnRestore1.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  textKey1.setText(controller.getKeyConfiguration().get("0"));
				  } 
				} );
		}
		return btnRestore1;
	}
	protected JButton getBtnRestore2() {
		if (btnRestore2 == null) {
			btnRestore2 = new JButton("Restore");
			btnRestore2.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestore2.setBounds(333, 86, 142, 23);
			btnRestore2.setEnabled(false);
			btnRestore2.setIcon(resizeImage("Trash-icon.png"));  
			btnRestore2.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  textKey2.setText(controller.getKeyConfiguration().get("1"));
				  } 
				} );
		}
		return btnRestore2;
	}
	
	protected JButton getBtnRestoreAll() {
		if (btnRestoreAll == null) {
			btnRestoreAll = new JButton("Restore all");
			btnRestoreAll.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestoreAll.setEnabled(false);
			btnRestoreAll.setBounds(333, 242, 142, 23);
			btnRestoreAll.setIcon(resizeImage("Trash-icon.png"));  
			btnRestoreAll.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  int i=JOptionPane.showConfirmDialog(null, internalizator.getRESTORE_VALUES_MESSAGE(),
							  internalizator.getRESTORE_VALUES(), JOptionPane.YES_NO_OPTION, 
							  JOptionPane.WARNING_MESSAGE);
				        if(i==JOptionPane.YES_OPTION) {
				        	resetKeyFields();
				        }
				  }
			} );
		}
		return btnRestoreAll;
	}
	
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(5, 44, 479, 2);
		}
		return separator;
	}
	
	protected JLabel getLblKeys() {
		if (lblKeys == null) {
			lblKeys = new JLabel("Keys:");
			lblKeys.setLabelFor(getKeysNumber());
			lblKeys.setBounds(247, 15, 56, 14);
		}
		return lblKeys;
	}
	
	protected JSpinner getKeysNumber() {
		if (keysNumber == null) {
			keysNumber = new JSpinner();
			List<Integer> keys = Constants.keysConfigurables;
			keysNumber.setModel(new SpinnerListModel(keys));
			keysNumber.setBounds(296, 12, 30, 20);
		}
		return keysNumber;
	}
	
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(10, 276, 479, 2);
		}
		return separator_1;
	}
	private JLabel getLblR() {
		if (lblR == null) {
			lblR = new JLabel("R:");
			lblR.setBounds(15, 289, 46, 26);
		}
		return lblR;
	}
	private JLabel getLblG() {
		if (lblG == null) {
			lblG = new JLabel("G:");
			lblG.setBounds(15, 314, 46, 27);
		}
		return lblG;
	}
	private JLabel getLblB() {
		if (lblB == null) {
			lblB = new JLabel("B:");
			lblB.setBounds(15, 339, 46, 27);
		}
		return lblB;
	}
	private JSlider getSlider_R() {
		if (slider_R == null) {
			slider_R = new JSlider();
			slider_R.setEnabled(false);
			slider_R.setValue(0);
			slider_R.setMaximum(255);
			slider_R.setBounds(95, 289, 228, 26);
			slider_R.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent e) {
			          spinner_R.setValue(slider_R.getValue());
			          updateRGBPanel();
			        }
			      });
		}
		return slider_R;
	}
	private JSlider getSlider_G() {
		if (slider_G == null) {
			slider_G = new JSlider();
			slider_G.setEnabled(false);
			slider_G.setValue(0);
			slider_G.setMaximum(255);
			slider_G.setBounds(95, 315, 228, 26);
			slider_G.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent e) {
			          spinner_G.setValue(slider_G.getValue());
			          updateRGBPanel();
			        }
			      });
		}
		return slider_G;
	}
	private JSlider getSlider_B() {
		if (slider_B == null) {
			slider_B = new JSlider();
			slider_B.setEnabled(false);
			slider_B.setValue(0);
			slider_B.setMaximum(255);
			slider_B.setBounds(95, 340, 228, 26);
			slider_B.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent e) {
			          spinner_B.setValue(slider_B.getValue());
			          updateRGBPanel();
			        }
			      });
		}
		return slider_B;
	}
	private JSpinner getSpinner_R() {
		if (spinner_R == null) {
			spinner_R = new JSpinner();
			spinner_R.setEnabled(false);
			spinner_R.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			spinner_R.setBounds(333, 292, 142, 20);
			spinner_R.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent e) {
			    	  slider_R.setValue((int)spinner_R.getValue());
			    	  updateRGBPanel();
			        }
			      });
		}
		return spinner_R;
	}
	private JSpinner getSpinner_G() {
		if (spinner_G == null) {
			spinner_G = new JSpinner();
			spinner_G.setEnabled(false);
			spinner_G.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			spinner_G.setBounds(333, 317, 142, 20);
			spinner_G.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent e) {
			    	  slider_G.setValue((int)spinner_G.getValue());
			    	  updateRGBPanel();
			        }
			      });
		}
		return spinner_G;
	}
	private JSpinner getSpinner_B() {
		if (spinner_B == null) {
			spinner_B = new JSpinner();
			spinner_B.setEnabled(false);
			spinner_B.setModel(new SpinnerNumberModel(0, 0, 255, 1));
			spinner_B.setBounds(333, 342, 142, 20);
			spinner_B.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent e) {
			    	  slider_B.setValue((int)spinner_B.getValue());
			    	  updateRGBPanel();
			        }
			      });
		}
		return spinner_B;
	}
	
	protected JButton getBtnTest() {
		if (btnTest == null) {
			btnTest = new JButton("Test");
			btnTest.setEnabled(false);
			btnTest.setMnemonic('t');
			btnTest.setBounds(201, 423, 132, 23);
			btnTest.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  if(controller.getStatus() != Constants.STATUS_DISCONNECTED) {
						  HashMap<String, String> keys = generateKeysMap();
						  if(controller.saveConfiguration(keys, false)) {
							  JOptionPane.showMessageDialog(null, internalizator.getCONFIGURATION_SAVED_MESSAGE(),
									  internalizator.getCONFIGURATION_SAVED(), JOptionPane.INFORMATION_MESSAGE);
						  } else {
							  HashMap<String,String> keysList = controller.getKeyConfiguration();
							  chargeConfiguredData(keysList);
						  }
					  }
				  }
			} );
		}
		return btnTest;
	}
	
	protected JLabel getLblKey3() {
		if (lblKey3 == null) {
			lblKey3 = new JLabel("Key 3:");
			lblKey3.setLabelFor(getTextKey3());
			lblKey3.setBounds(10, 119, 46, 14);
		}
		return lblKey3;
	}
	private JTextField getTextKey3() {
		if (textKey3 == null) {
			textKey3 = new JTextField();
			textKey3.setEnabled(false);
			textKey3.setColumns(10);
			textKey3.setBounds(95, 116, 228, 20);
			textKey3.addKeyListener(new TextFieldListener(textKey3));
		}
		return textKey3;
	}
	protected JButton getBtnRestore3() {
		if (btnRestore3 == null) {
			btnRestore3 = new JButton("Restore");
			btnRestore3.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestore3.setEnabled(false);
			btnRestore3.setBounds(333, 115, 142, 23);
			btnRestore3.setIcon(resizeImage("Trash-icon.png"));  
			btnRestore3.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  textKey3.setText(controller.getKeyConfiguration().get("2"));
				  } 
				} );		
			}
		return btnRestore3;
	}
	protected JLabel getLblKey4() {
		if (lblKey4 == null) {
			lblKey4 = new JLabel("Key 4:");
			lblKey4.setLabelFor(getTextKey4());
			lblKey4.setBounds(10, 150, 46, 14);
		}
		return lblKey4;
	}
	private JTextField getTextKey4() {
		if (textKey4 == null) {
			textKey4 = new JTextField();
			textKey4.setEnabled(false);
			textKey4.setColumns(10);
			textKey4.setBounds(95, 147, 228, 20);
			textKey4.addKeyListener(new TextFieldListener(textKey4));
		}
		return textKey4;
	}
	protected JButton getBtnRestore4() {
		if (btnRestore4 == null) {
			btnRestore4 = new JButton("Restore");
			btnRestore4.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestore4.setEnabled(false);
			btnRestore4.setBounds(333, 147, 142, 23);
			btnRestore4.setIcon(resizeImage("Trash-icon.png"));  
			btnRestore4.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  textKey4.setText(controller.getKeyConfiguration().get("3"));
				  } 
				} );		}
		return btnRestore4;
	}
	protected JLabel getLblKey5() {
		if (lblKey5 == null) {
			lblKey5 = new JLabel("Key 5:");
			lblKey5.setLabelFor(getTextKey5());
			lblKey5.setBounds(10, 180, 46, 14);
		}
		return lblKey5;
	}
	private JTextField getTextKey5() {
		if (textKey5 == null) {
			textKey5 = new JTextField();
			textKey5.setEnabled(false);
			textKey5.setColumns(10);
			textKey5.setBounds(95, 177, 228, 20);
			textKey5.addKeyListener(new TextFieldListener(textKey5));
		}
		return textKey5;
	}
	protected JButton getBtnRestore5() {
		if (btnRestore5 == null) {
			btnRestore5 = new JButton("Restore");
			btnRestore5.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestore5.setEnabled(false);
			btnRestore5.setBounds(333, 176, 142, 23);
			btnRestore5.setIcon(resizeImage("Trash-icon.png"));  
			btnRestore5.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  textKey5.setText(controller.getKeyConfiguration().get("4"));
				  } 
				} );
		}
		return btnRestore5;
	}
	protected JLabel getLblKey6() {
		if (lblKey6 == null) {
			lblKey6 = new JLabel("Key 6:");
			lblKey6.setLabelFor(getTextKey6());
			lblKey6.setBounds(10, 212, 46, 14);
		}
		return lblKey6;
	}
	private JTextField getTextKey6() {
		if (textKey6 == null) {
			textKey6 = new JTextField();
			textKey6.setEnabled(false);
			textKey6.setColumns(10);
			textKey6.setBounds(95, 209, 228, 20);
			textKey6.addKeyListener(new TextFieldListener(textKey6));
		}
		return textKey6;
	}
	protected JButton getBtnRestore6() {
		if (btnRestore6 == null) {
			btnRestore6 = new JButton("Restore");
			btnRestore6.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestore6.setEnabled(false);
			btnRestore6.setBounds(333, 208, 142, 23);
			btnRestore6.setIcon(resizeImage("Trash-icon.png"));  
			btnRestore6.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  textKey6.setText(controller.getKeyConfiguration().get("5"));
				  } 
				} );
		}
		return btnRestore6;
	}
	private JSeparator getSeparator_1_1() {
		if (separator_1_1 == null) {
			separator_1_1 = new JSeparator();
			separator_1_1.setBounds(5, 401, 479, 2);
		}
		return separator_1_1;
	}
	
	protected JButton getBtnRestoreRGB() {
		if (btnRestoreRGB == null) {
			btnRestoreRGB = new JButton("Restore");
			btnRestoreRGB.setHorizontalAlignment(SwingConstants.LEFT);
			btnRestoreRGB.setEnabled(false);
			btnRestoreRGB.setBounds(333, 367, 142, 23);
			btnRestoreRGB.setIcon(resizeImage("Trash-icon.png"));  
			btnRestoreRGB.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  resetRGBFields();
				  } 
			} );
		}
		return btnRestoreRGB;
	}
	
	protected JLabel getLblExample() {
		if (lblExample == null) {
			lblExample = new JLabel("Example:");
			lblExample.setLabelFor(getPanelRGB());
			lblExample.setBounds(15, 371, 82, 14);
		}
		return lblExample;
	}
	
	private JPanel getPanelRGB() {
		if (panelRGB == null) {
			panelRGB = new JPanel();
			panelRGB.setBounds(95, 367, 228, 23);
		}
		return panelRGB;
	}
	
	private JComboBox<File> getTemplateSelect() {
		if (templateSelect == null) {
			templateSelect = new WideComboBox<File>();
			templateSelect.setEnabled(false);
			templateSelect.setBounds(95, 242, 129, 22);
			File[] files = controller.getConfFiles();
			if(files == null) {
				JOptionPane.showMessageDialog(null, "\"arconf\" folder not found.", "Template folder not found", 
						JOptionPane.WARNING_MESSAGE);
			} else {
				for (File file: files) {
					templateSelect.addItem(file);
				}
			}
			templateSelect.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	btnTemplate.grabFocus();
			    }
			});	
			templateSelect.setRenderer(new TemplateRenderer());
		}
		return templateSelect;
	}
	protected JLabel getLblTemplate() {
		if (lblTemplate == null) {
			lblTemplate = new JLabel("Template:");
			lblTemplate.setLabelFor(getTemplateSelect());
			lblTemplate.setBounds(10, 246, 75, 14);
		}
		return lblTemplate;
	}
	protected JButton getBtnTemplate() {
		if (btnTemplate == null) {
			btnTemplate = new JButton("Charge");
			btnTemplate.setEnabled(false);
			btnTemplate.setBounds(234, 242, 89, 23);
			btnTemplate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			btnTemplate.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					loadTemplate();
				  }
			} );
			btnTemplate.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {
						loadTemplate();
					}
				}
				@Override
				public void keyTyped(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {}	
			});
		}
		return btnTemplate;
	}
	
	  ////////////////////////
	 //  INTERNAL METHODS  //
	////////////////////////
	
	/*
	 * Confirm dialog after exit application
	 */
	private void exit() {
		int i=JOptionPane.showConfirmDialog(null, internalizator.getEXIT_MESSAGE(), internalizator.getEXIT(), 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(i==JOptionPane.YES_OPTION) {
        	try {
				controller.saveAppStatus((int)this.keysNumber.getValue());
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Error saving userSettings", e);
			}
        	LOGGER.log(Level.INFO, "Application exit.");
            System.exit(0);
        }
	}
	
	/*
	 * Return an image scaled to use as button icon
	 */
	private ImageIcon resizeImage(String resource) {
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(resource)); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return new ImageIcon(newimg); // transform it back
	}
	
	/*
	 * Try to connect with Arduino and charge default configuration
	 */
	private void connect() {
		switch(controller.getStatus()) {
	    	case Constants.STATUS_DISCONNECTED:
	    		// Si está desconectado se intenta conectar
	    		int status = controller.connect(((SerialPort)comPortSelect.getSelectedItem())
	    				.getSystemPortName());
	    		if(status != Constants.STATUS_DISCONNECTED) {
	    			enableFields();
	    			internalizator.updateConnectButton();
					if(status == Constants.STATUS_CONNECTED_CONFIGURED) {
	    				HashMap<String,String> keys = controller.getKeyConfiguration();
	    				chargeConfiguredData(keys);
	    				updateRGBPanel();
		    			JOptionPane.showMessageDialog(null, internalizator.getCONNECTED_MESSAGE(),
		    					internalizator.getCONNECTED(), JOptionPane.INFORMATION_MESSAGE);
	    			} 
	    			btnConnect.setIcon(resizeImage("Disconection-icon.png"));  
	    		} else {
	    			JOptionPane.showMessageDialog(null, internalizator.getERROR_MESSAGE(), 
	    					internalizator.getERROR(), JOptionPane.WARNING_MESSAGE);
	    		}
	    		break;
	    	case Constants.STATUS_CONNECTED,
	    	Constants.STATUS_CONNECTED_CONFIGURED:
	    		controller.disconnect();
			disableFields();
    			btnConnect.setText("Connect");
    			controller.restoreValues();
    			btnConnect.setIcon(resizeImage("Conection-icon.png"));  
    			JOptionPane.showMessageDialog(null, internalizator.getDISCONNECTED_MESSAGE(),
    					internalizator.getDISCONNECTED(), JOptionPane.INFORMATION_MESSAGE);
    			break;
	    }
	}

	/*
	 * Disabled fields after disconnect or start
	 */
	private void disableFields() {
		// Enable conf fields
		comPortSelect.setEnabled(true);
		btnUpdateLists.setEnabled(true);
		keysNumber.setEnabled(true);
		
		// Disabled Arduino fields
		btnRestore1.setEnabled(false);
		btnRestore2.setEnabled(false);
		btnRestore3.setEnabled(false);
		btnRestore4.setEnabled(false);
		btnRestore5.setEnabled(false);
		btnRestore6.setEnabled(false);
		btnRestoreAll.setEnabled(false);
		textKey1.setEnabled(false);
		textKey2.setEnabled(false);
		btnSave.setEnabled(false);
		spinner_R.setEnabled(false);
		spinner_G.setEnabled(false);
		spinner_B.setEnabled(false);
		slider_R.setEnabled(false);
		slider_G.setEnabled(false);
		slider_B.setEnabled(false);
		btnTest.setEnabled(false);
		btnRestoreRGB.setEnabled(false);
		mntmExportConfiguration.setEnabled(false);
		mntmImportConfiguration.setEnabled(false);
		templateSelect.setEnabled(true);
		btnTemplate.setEnabled(true);
		
		// Reset Arduino fields
		textKey1.setText("");
		textKey2.setText("");
		textKey3.setText("");
		textKey4.setText("");
		textKey5.setText("");
		textKey6.setText("");
		spinner_R.setValue(0);
		spinner_G.setValue(0);
		spinner_B.setValue(0);
		slider_R.setValue(0);
		slider_G.setValue(0);
		slider_B.setValue(0);
		panelRGB.setOpaque(false);
		panelRGB.setBackground(new Color(240,240,240));
	}

	/*
	 * Enable fields after connect
	 */
	private void enableFields() {
		// Disable conf fields
		comPortSelect.setEnabled(false);
		keysNumber.setEnabled(false);
		btnUpdateLists.setEnabled(false);
		
		// Enable Arduino fields
		textKey1.setEnabled(true);
		textKey2.setEnabled(true);
		btnRestore1.setEnabled(true);
		btnRestore2.setEnabled(true);
		btnRestoreAll.setEnabled(true);
		btnSave.setEnabled(true);
		spinner_R.setEnabled(true);
		spinner_G.setEnabled(true);
		spinner_B.setEnabled(true);
		slider_R.setEnabled(true);
		slider_G.setEnabled(true);
		slider_B.setEnabled(true);
		btnTest.setEnabled(true);
		btnRestoreRGB.setEnabled(true);
		mntmExportConfiguration.setEnabled(true);
		mntmImportConfiguration.setEnabled(true);
		if((int)keysNumber.getValue() > 2) {
			textKey3.setEnabled(true);
			textKey4.setEnabled(true);
			btnRestore3.setEnabled(true);
			btnRestore4.setEnabled(true);
		}
		if((int)keysNumber.getValue() > 4) {
			textKey5.setEnabled(true);
			textKey6.setEnabled(true);
			btnRestore5.setEnabled(true);
			btnRestore6.setEnabled(true);
		}
		if(templateSelect.getItemCount() > 0) {
			templateSelect.setEnabled(true);
			btnTemplate.setEnabled(true);
		}
	}

	/*
	 * Update fields with the given configuration
	 */
	private void chargeConfiguredData(HashMap<String, String> keys) {
		textKey1.setText(keys.get("0"));
		textKey2.setText(keys.get("1"));
		textKey3.setText(keys.get("2"));
		textKey4.setText(keys.get("3"));
		textKey5.setText(keys.get("4"));
		textKey6.setText(keys.get("5"));
		spinner_R.setValue(Integer.parseInt(keys.get("R")));
		spinner_G.setValue(Integer.parseInt(keys.get("G")));
		spinner_B.setValue(Integer.parseInt(keys.get("B")));
		slider_R.setValue(Integer.parseInt(keys.get("R")));
		slider_G.setValue(Integer.parseInt(keys.get("G")));
		slider_B.setValue(Integer.parseInt(keys.get("B")));
	}

	/*
	 * Reset Key fields with the stored configuration
	 */
	private void resetKeyFields() {
		HashMap<String, String> keyConfiguration = controller.getKeyConfiguration();
		textKey1.setText(keyConfiguration.get("0"));
		textKey2.setText(keyConfiguration.get("1"));
		textKey3.setText(keyConfiguration.get("2"));
		textKey4.setText(keyConfiguration.get("3"));
		textKey5.setText(keyConfiguration.get("4"));
		textKey6.setText(keyConfiguration.get("5"));
	} 
	
	/*
	 * Reset RGB fields with the stored configuration
	 */
	private void resetRGBFields() {
		spinner_R.setValue(Integer.parseInt(controller.getKeyConfiguration().get("R")));
		spinner_G.setValue(Integer.parseInt(controller.getKeyConfiguration().get("G")));
		spinner_B.setValue(Integer.parseInt(controller.getKeyConfiguration().get("B")));
		slider_R.setValue(Integer.parseInt(controller.getKeyConfiguration().get("R")));
		slider_G.setValue(Integer.parseInt(controller.getKeyConfiguration().get("G")));
		slider_B.setValue(Integer.parseInt(controller.getKeyConfiguration().get("B")));
	} 
	
	/*
	 * Return a map with all the configuration
	 */
	private HashMap<String, String> generateKeysMap() {
		HashMap<String,String> keys = new HashMap<String, String>();
		  keys.put("0", textKey1.getText());
		  keys.put("1", textKey2.getText());
		  keys.put("2", textKey3.getText());
		  keys.put("3", textKey4.getText());
		  keys.put("4", textKey5.getText());
		  keys.put("5", textKey6.getText());
		  keys.put("R", spinner_R.getValue().toString());
		  keys.put("G", spinner_G.getValue().toString());
		  keys.put("B", spinner_B.getValue().toString());
		return keys;
	} 
	
	/*
	 * Update example color
	 */
	private void updateRGBPanel() {
		Color color = new Color((int)spinner_R.getValue(),(int)spinner_G.getValue(),(int)spinner_B.getValue());
		panelRGB.setBackground(color);
		panelRGB.setOpaque(true);
	}
	
	/*
	 * Import the template to the App
	 */
	private void loadTemplate() {
		File file = (File) templateSelect.getSelectedItem();
		if(file != null) {
		    try {
		    	chargeConfiguredData(controller.importConfiguration(file));
				LOGGER.log(Level.INFO, "Template loaded: " + file.getName());
			} catch (IOException em) {
				LOGGER.log(Level.SEVERE, "Error loading template", em);
			}
		}
	} 
	
	/*
	 * Generate the folder structure of the App
	 */
	private static void generateFolders() {
		File f = new File("./arconf");
		if(!f.exists()) {
			f.mkdir();
		}	
		File f2 = new File("./arconf/log");
		if(!f2.exists()) {
			f2.mkdir();
		}
	}
	
}
