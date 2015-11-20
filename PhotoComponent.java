import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.BasicStroke;



public class PhotoComponent extends JComponent implements MouseListener, MouseMotionListener {
	
	//attributes
	
	public BufferedImage  img;   //image to be drawn
	public boolean flipped;		//flip bool
	public BufferedImage bckgPhoto;		//background image
	//the image on which get drawn the annotations
	public BufferedImage backannotation;
	private int index=0;
	
	public List<BasicStroke> textAnnotList;
	public boolean[][] strokeAnnotList;
	public boolean typing;
	public char mode;
	
	
	
	//size
		public int p_size_x;		
		public int p_size_y;
		public int a_size_x;
		public int a_size_y;
	
	//default constructor
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
	
	//constructor with size
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
		textAnnotList=new ArrayList<BasicStroke>();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
}
		
	
	//constructor with size and image
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
			this.mode='n';
			textAnnotList=new ArrayList<BasicStroke>();
			this.repaint();
	}
		
	public void setMode(char a){
		mode=a;
	}
	public char getMode(){
		return mode;
	}
	
	//set image
	public void setImage(File fl){
		BufferedImage img2 = null;
		
		try {
		    img2 = ImageIO.read(fl);
		} catch (IOException e) {
			this.setBackground(Color.red);
		}
		this.img=img2;
		strokeAnnotList=new boolean[img2.getWidth()][img2.getHeight()];
		
		this.repaint();
		
	}
	
	
	//paint method
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(getForeground());
        if (flipped==false){
        	g2.drawImage(img, 0, 0, null);
        }else{
        	g2.setColor(Color.WHITE);
        	g2.drawRect(0, 0, img.getWidth(), img.getHeight());
        	g2.fillRect(0, 0, img.getWidth(), img.getHeight());
        	int xi=img.getWidth();
        	int yi=img.getHeight();
        	int x;
        	int y;
        	g2.setColor(Color.lightGray);
        	for (x=5;x<xi;x+=10)  g2.draw(new Line2D.Double(x, 0, x, yi));
        	for (y=5;y<yi;y+=10)  g2.draw(new Line2D.Double(0, y, xi, y));
        	g2.setColor(Color.BLACK);
        	/*for (int i = 0; i < strokeAnnotList.size(); i++) {
        	    BasicStroke sto = (BasicStroke) strokeAnnotList.get(i);
        	    g2.setStroke(sto);
        	    g2.draw((Shape) sto);
        	}*/
        	
        	for (int i = 0; i < xi ; i++)
        		for(int j=0;j<yi;j++){
        			if(strokeAnnotList[i][j]==true) g2.drawLine(i, j, i,j);
        		}
        	}	
        	
        }
    

	
	//set size
	public void setPreferredSize(int i, int j) {
		// TODO Auto-generated method stub
		this.setSize(i,j);
	}
	
	//stroke listener


		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int a=arg0.getX();
			int b=arg0.getY();
			if(this.flipped==true && this.img!=null && a<img.getWidth() && b<img.getHeight()){
				if(mode=='s'){
					strokeAnnotList[a][b]=true;
					if(a>0){
						strokeAnnotList[a-1][b]=true;
						
					}
					if(a<img.getWidth()-1){
						strokeAnnotList[a+1][b]=true;
					}
					if(b>0){
						strokeAnnotList[a-1][b-1]=true;
						
					}
					if(b<img.getHeight()-1){
						strokeAnnotList[a][b+1]=true;
					}
					
					//strokeAnnotList[index]=p;
			        //index++;
			        repaint();
			        }else if(mode=='e'){
			        	strokeAnnotList[a][b]=false;
						if(a>0){
							strokeAnnotList[a-1][b]=false;
							
						}
						if(a<img.getWidth()-2){
							strokeAnnotList[a+1][b]=false;
							strokeAnnotList[a+2][b]=false;
						}
						if(b>0){
							strokeAnnotList[a-1][b-1]=false;
							
						}
						if(b<img.getHeight()-2){
							strokeAnnotList[a][b+1]=false;
							strokeAnnotList[a][b+2]=false;
						}	
			        	repaint();
			        }
				
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub	
		}

			

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int a=arg0.getX();
				int b=arg0.getY();
				if(this.flipped==true && this.img!=null && a<img.getWidth() && b<img.getHeight()){
					if(mode=='s'){
						strokeAnnotList[a][b]=true;
						if(a>0){
							strokeAnnotList[a-1][b]=true;
							
						}
						if(a<img.getWidth()-1){
							strokeAnnotList[a+1][b]=true;
						}
						if(b>0){
							strokeAnnotList[a-1][b-1]=true;
							
						}
						if(b<img.getHeight()-1){
							strokeAnnotList[a][b+1]=true;
						}
						
						//strokeAnnotList[index]=p;
				        //index++;
				        repaint();
				        }else if(mode=='e'){
				        	strokeAnnotList[a][b]=false;
							if(a>0){
								strokeAnnotList[a-1][b]=false;
								
							}
							if(a<img.getWidth()-2){
								strokeAnnotList[a+1][b]=false;
								strokeAnnotList[a+2][b]=false;
							}
							if(b>0){
								strokeAnnotList[a-1][b-1]=false;
								
							}
							if(b<img.getHeight()-2){
								strokeAnnotList[a][b+1]=false;
								strokeAnnotList[a][b+2]=false;
							}	
				        	repaint();
				        }
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		
		
	}
	
	
	
	
	
	




