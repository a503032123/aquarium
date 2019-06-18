//104403041
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Fish extends JLabel implements Runnable {
	private Point pa = new Point();
	private ImageIcon fish11=new ImageIcon(getClass().getResource("1.png"));
	private ImageIcon fish12=new ImageIcon(getClass().getResource("2.png"));
	private ImageIcon fish21=new ImageIcon(getClass().getResource("3.png"));
	private ImageIcon fish22=new ImageIcon(getClass().getResource("4.png"));
	private ImageIcon fish31=new ImageIcon(getClass().getResource("5.png"));
	private ImageIcon fish32=new ImageIcon(getClass().getResource("6.png"));
	private static final SecureRandom generator=new SecureRandom();
	private Timer t1,t2,t3;
	boolean ceat=true;
	boolean eat=false;
	private static int speed = generator.nextInt(150)+100;
	private int x,y,heigh,width,dir,ydir,num;
	private int ftype;
	private int size;
	public Fish(Point p, int n) {
		ydir= generator.nextInt(2);
		ftype=n;
		pa.setLocation(p);
		dir=generator.nextInt(2);//1向右,0向左
		//this.dir=dir;
		size=generator.nextInt(100)+50;
		fish11.setImage(fish11.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		fish12.setImage(fish12.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		fish21.setImage(fish21.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		fish22.setImage(fish22.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		fish31.setImage(fish31.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		fish32.setImage(fish32.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		
		this.setIcon(getIcon(ftype));
		
		this.setBounds(pa.x,pa.y,size,size);
		/*addMouseListener(new MouseAdapter() 
		{
		    @Override
		    public void mouseClicked(MouseEvent e) 
		    {
		    //	pa.setLocation(pa.getX(),pa.getY());
		    }
		});*/
	}

	
	public ImageIcon getIcon (int n) {
		switch (n)
		{
		case 1: if(dir==1)
				{
					return fish11;
				}
				else if(dir==0)
				{
					return fish12;
				}
		case 2: if(dir==1)
				{	
					return fish21;
				}
				else if(dir==0)
				{	
					return fish22;
				}
		case 3: if(dir==1)
				{
					return fish31;
				}
				else if(dir==0)
				{
					return fish32;
				}
			
		
		default: return fish11;
			
		}
	}
	
	/*public Icon getIcon2 (int n) {
		switch (n)
		{
		case 1: //if(xVellock>=0)
				{
					return fish12;
				}
				//else if(xVellock<0)
			//	{
					//return fish11;
				//}
		case 2: //if(xVellock>0)
				{
					return fish22;
				}
				/*else if(xVellock<0)
				{
					return fish22;
				}
		case 3: //if(xVellock>0)
				{
					return fish32;
				}
				/*else if(xVellock<0)
				{
					return fish32;
				}
			
		
		default: return fish12;
			
		}
	}*/
	
	public Point getPoint()
	{
		return pa;
	}
	
	public void sethigh(int n) {
		this.heigh=n;
		 
	}
	public void setwidth(int n) {
		this.width=n;
	}
	public void startstop(boolean b) {
		if(b)
		{/*x=0;
		 y=0;*/
		
		 t2.stop();
		 t1.stop();
		}
		else {
		/*	x= generator.nextInt(2)+1;
			y= generator.nextInt(3);*/
	
		t2.start();
		t1.start();
		}
	}



	@Override
	public void run() {
		try
		{
			t2 = new Timer(1000, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dir = generator.nextInt(2);
					ydir = generator.nextInt(2);
					speed = generator.nextInt(6)+1;
					t1.setDelay(speed);
					x= generator.nextInt(2)+1;
					y= generator.nextInt(3);
				}
								}
							);
			t2.start();
		
			t1 = new Timer(50, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(pa.x<0) {dir = 1;}
					else if(pa.x-width>0){dir = 0;}
					if(pa.y<0) {ydir = 0;}//0向下  1向上
					else if(pa.y-heigh>0){ydir = 1;}
					if(ydir==0) {pa.y+=y;}
					else if(ydir ==1) {pa.y-=y;}
					if(dir==0) {
						pa.x-=x;
						Fish.this.setIcon(getIcon(ftype));		
						//System.out.println(width);
					}
					else if(dir==1) {
						pa.x+=x;			
						Fish.this.setIcon(getIcon(ftype));							
					}
					Fish.this.setLocation(pa);
					if(eat) {
						if(size<129)
						{size+=15;
						fish11.setImage(fish11.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						fish12.setImage(fish12.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						fish21.setImage(fish21.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						fish22.setImage(fish22.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						fish31.setImage(fish31.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						fish32.setImage(fish32.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						Fish.this.setIcon(getIcon(ftype));	
						eat=false;
						if(size>129)
							ceat=false;
								if(dir==1)
									dir=0;
								if(dir==0)
									dir=1;
								
						}
						else {
							eat=false;
							
						}
					}
				}
				
			});
			t1.start();	
			t3 = new Timer(5600, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(size>70)
					size-=5;
					fish11.setImage(fish11.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
					fish12.setImage(fish12.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
					fish21.setImage(fish21.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
					fish22.setImage(fish22.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
					fish31.setImage(fish31.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
					fish32.setImage(fish32.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
					Fish.this.setIcon(getIcon(ftype));	
				}
								}
							);
			t3.start();
			Thread.sleep(1);
		}
		catch(InterruptedException exception)
		{
		exception.printStackTrace();
		Thread.currentThread().interrupt();
		}
		
		//	setIcon(getIcon());
			/*while (notdeleted)
			{
				
					if(pointt.getY()>400||pointt.getY()<0)
					{
						yVel=-yVel;
						ytemp=yVel;
						yVellock=yVel;
					}
					
					
					if(pointt.getX()>800||pointt.getX()<0)
					{	
						xVel=-xVel;
						xtemp=xVel;
						xVellock=xVel;
					}
					pointt.setLocation(pointt.x+xVel,pointt.y+yVel);
					
						setIcon(getIcon());
					Thread.sleep(5);
				
			}*/
		//}
		//catch(InterruptedException exception)
		//{
			//exception.printStackTrace();
			//Thread.currentThread().interrupt();
		//}

	}
		
}


