import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
//import PhotoComponent.flipListener;
import javax.swing.JFileChooser;
	public class ImageGUI {
	private JFrame frame;
	public File pfl;
	PhotoComponent ptcomp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageGUI window = new ImageGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImageGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//File fl;
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmImport = new JMenuItem("Import");
		
		mnFile.add(mntmImport);
		openfilehandler ofh=new openfilehandler();
		mntmImport.addActionListener(ofh);
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mnFile.add(mntmDelete);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		quithandler handleQ= new quithandler();
		mntmQuit.addActionListener(handleQ);
		mnFile.add(mntmQuit);
		
		JMenu mnNewMenu = new JMenu("View");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmPhotoViewer = new JMenuItem("Photo Viewer");
		mnNewMenu.add(mntmPhotoViewer);
		
		JMenuItem mntmBrowser = new JMenuItem("Browser");
		mnNewMenu.add(mntmBrowser);
		
		JMenuItem mntmSplitMode = new JMenuItem("Split Mode");
		mnNewMenu.add(mntmSplitMode);
		
		JPanel statusBarContainer = new JPanel();
		statusBarContainer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(statusBarContainer, BorderLayout.SOUTH);
		
		JLabel lblStatusBar = new JLabel("Status Bar");
		statusBarContainer.add(lblStatusBar);
		
		JPanel toolbar = new JPanel();
		frame.getContentPane().add(toolbar, BorderLayout.WEST);
		toolbar.setLayout(new MigLayout("", "[74px]", "[23px][][23px][][]"));
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Photo Type");
		toolbar.add(tglbtnNewToggleButton, "cell 0 0,growx,aligny top");
		
		JButton btnFlip = new JButton("Flip");
		toolbar.add(btnFlip, "cell 0 1");
		flipListener flp=new flipListener();
		btnFlip.addActionListener(flp);
		
		
		JButton btnNewButton = new JButton("Text");
		toolbar.add(btnNewButton, "flowx,cell 0 2,alignx left,aligny top");
		
		JButton btnPen = new JButton("Pen");
		toolbar.add(btnPen, "cell 0 2");
		
		JButton btnEraser = new JButton("Eraser");
		toolbar.add(btnEraser, "cell 0 3");
		
		ptcomp= new PhotoComponent(360,360);
		//container for the PhotoComponent
		JScrollPane scrollPane = new JScrollPane(ptcomp);
		scrollPane.setViewportBorder(new LineBorder(Color.WHITE, 3, true));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setBounds(100,100,200,300);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		
	}
		
	
	
	
	
	
	class quithandler implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			System.exit(0);
		}
	}
	
	
	
	class flipListener implements ActionListener {
		public void actionPerformed(ActionEvent ev){
			ptcomp.flipped=!ptcomp.flipped;
			ptcomp.repaint();
		}
		
	}
	
	class openfilehandler implements ActionListener{
		public File fl;
		public void actionPerformed(ActionEvent ev){
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(frame);
	        if (returnVal == JFileChooser.APPROVE_OPTION){
	        	fl = fc.getSelectedFile();
	        	ptcomp.setImage(fl);
	        	ptcomp.flipped=false;
	        	ptcomp.backannotation=new BufferedImage(ptcomp.img.getWidth(),ptcomp.img.getHeight(),BufferedImage.TYPE_INT_RGB);
	    		ptcomp.backannotation.setRGB(0, 0, 0);;
	        }
	            
		}		
	}
	
	
}
