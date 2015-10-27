import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;




public class PhotoComponent extends JComponent {
	
	//attributes
	
	public BufferedImage  img;   //image to be drawn
	public boolean flipped;		//flip bool
	public BufferedImage bckgPhoto;		//background image
	
	//the image on which get drawn the annotations
	public BufferedImage backannotation;
	
	
	public List textAnnotList;
	public List strokeAnnotList;
	
	
	
	
	//size
		public int p_size_x;		
		public int p_size_y;
		public int a_size_x;
		public int a_size_y;
	
	
	
	
	
	
	public PhotoComponent(){
		super();
		this.flipped=false;
		a_size_x=360;
		a_size_y=360;
		p_size_x=360;
		p_size_y=360;
		this.setSize(360, 360);
		this.setBackground(Color.darkGray);
		 this.repaint();
		 
}
	
	
	public PhotoComponent(int a,int b){
		super();
		a_size_x=a;
		a_size_y=b;
		p_size_x=360;
		p_size_y=360;
		this.setSize(a, b);
		this.flipped=false;
		this.setBackground(Color.darkGray);
		this.repaint();
}
		
	
	
	public PhotoComponent(int pa,int pb,int a,int b,BufferedImage bg){
			super();
			a_size_x=a;
			a_size_y=b;
			p_size_x=pa;
			p_size_y=pb;
			this.setSize(a, b);
			this.flipped=false;
			this.bckgPhoto=bg;
			this.setBackground(Color.black);
			this.repaint();
			
			
	}
			
	public void setImage(File fl){
		BufferedImage img2 = null;
		
		try {
		    img2 = ImageIO.read(fl);
		} catch (IOException e) {
			this.setBackground(Color.red);
		}
		this.img=img2;
		
		
		this.repaint();
		
	}
	
	
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(getForeground());
        if (flipped==false){
        g2.drawImage(img, 0, 0, null);
        }else{
        //g.drawImage(backannotation, 0, 0, null);
        	g2.setColor(Color.WHITE);
        	g2.drawRect(0, 0, img.getWidth(), img.getHeight());
        	g2.fillRect(0, 0, img.getWidth(), img.getHeight());
        }
    }


	public void setPreferredSize(int i, int j) {
		// TODO Auto-generated method stub
		this.setSize(i,j);
	}
	
	public class flipListenerin implements MouseListener{
		
		public void actionPerformed (MouseEvent ev){
			if(ev.getClickCount()==2){
				flipped=!flipped;
				setBackground(Color.blue);
				repaint();
			}
			
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
	}
	
	
	
	
}
