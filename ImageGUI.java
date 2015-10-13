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

public class ImageGUI {
	private JFrame frame;

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
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmImport = new JMenuItem("Import");
		mnFile.add(mntmImport);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mnFile.add(mntmDelete);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
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
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Photo Type");
		toolbar.add(tglbtnNewToggleButton);
		
		
		//container for the PhotoComponent
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		PhotoComponent ptcomp= new PhotoComponent();
		scrollPane.setViewportView(ptcomp);

	}

}
