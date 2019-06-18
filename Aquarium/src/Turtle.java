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
public class Turtle extends JLabel implements Runnable{
	private Point pa = new Point();
	private ImageIcon turtle1=new ImageIcon(getClass().getResource("w.png"));
	private ImageIcon turtle2=new ImageIcon(getClass().getResource("w2.png"));
	boolean eat=false;
	boolean ceat=true;
	private static final SecureRandom generator=new SecureRandom();
	private Timer t1,t2;
	private static int speed = generator.nextInt(150)+100;
	private int x,y,heigh,width,dir,ydir,size,num;
	public Turtle(Point p) {
		pa.setLocation(p);
		dir=generator.nextInt(2);//1¦V¥k,0¦V¥ª
		size=generator.nextInt(70)+50;
		turtle1.setImage(turtle1.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		turtle2.setImage(turtle2.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		this.setBounds(pa.x,pa.y,size+3,size+3);
		if(dir==1)
		this.setIcon(turtle1);
		else if (dir==0) {this.setIcon(turtle2);}
	}


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
	


	@Override
	public void run() {
		pa.setLocation(pa.x,pa.y);
		try
		{
			t2 = new Timer(600, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dir = generator.nextInt(2);
					ydir = generator.nextInt(2);
					speed = generator.nextInt(6)+1;
					t1.setDelay(speed);
					x= generator.nextInt(2)+1;
					y= generator.nextInt(3)+1;
					
				}
								}
							);
			t2.start();
			t1 = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(eat) {
						if(size<119)
						{size+=15;
						turtle1.setImage(turtle1.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						turtle2.setImage(turtle2.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
						eat=false;
						if(size>119)
								ceat=false;
						}
						else {
							eat=false;
							
						}
					}
					if(pa.x<0) {dir = 1;}
					else if(pa.x-width>0){dir = 0;}
					if(size/2+10+pa.y-heigh<0){pa.y+=y;}
					else {
					if(dir==0) {
						pa.x-=x;
						Turtle.this.setIcon(turtle2);		
						//System.out.println(width);
						}
					else if(dir==1) {
						pa.x+=x;			
						Turtle.this.setIcon(turtle1);							
						}
						
					}Turtle.this.setLocation(pa);
					
				}
			});
			t1.start();	
			Thread.sleep(1);
		}
		catch(InterruptedException exception)
		{
		exception.printStackTrace();
		Thread.currentThread().interrupt();
		}
	}

}
