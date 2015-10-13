import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;




public class PhotoComponent extends JComponent {
	public Image img;
	public int flipped;
	//public Size actualSize;
	//public Size preferredSize;
	public int p_size_x;
	public int p_size_y;
	public int a_size_x;
	public int a_size_y;
	public Image bckgPhoto;
	
	public PhotoComponent(){
		super();
		this.flipped=0;
		a_size_x=360;
		a_size_y=360;
		p_size_x=360;
		p_size_y=360;
		this.setSize(360, 360);
		this.setBackground(Color.darkGray);
	//	this.setOpaque(true);
		 this.repaint();
		 
}
	
	
	public PhotoComponent(int a,int b){
		super();
		a_size_x=a;
		a_size_y=b;
		p_size_x=360;
		p_size_y=360;
		this.setSize(a, b);
		this.flipped=0;
		this.setBackground(Color.black);
		this.repaint();
}
		
	
	public PhotoComponent(int pa,int pb,int a,int b,Image bg){
			super();
			a_size_x=a;
			a_size_y=b;
			p_size_x=pa;
			p_size_y=pb;
			this.setSize(a, b);
			this.flipped=0;
			this.bckgPhoto=bg;
			this.setBackground(Color.black);
			this.repaint();
	}
			
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(getForeground());
    }
}
