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
public class Feed extends JLabel implements Runnable{
	private Point pa = new Point();
	private ImageIcon cookie=new ImageIcon(getClass().getResource("cookie.png"));
	private static final SecureRandom generator=new SecureRandom();
	private Timer t1,t2,t3;
	boolean clean= false;
	private static int speed = generator.nextInt(150)+100;
	private int x,y,heigh,width,dir,ydir,size,num;
	public Feed(Point p) {
		pa.setLocation(p);
		dir=generator.nextInt(2);//1¦V¥k,0¦V¥ª
		size=generator.nextInt(50)+16;
		cookie.setImage(cookie.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
		
		this.setBounds(pa.x,pa.y,size+3,size+3);
		this.setIcon(cookie);	
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
					x= 0;
					y= generator.nextInt(1)+1;
					
				}
								}
							);
			t2.start();
			t1 = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(pa.x<0) {dir = 1;}
					else if(pa.x-width>0){dir = 0;}
					if(size/2+10+pa.y-heigh<0){pa.y+=y;}
					else {		
					}
				}
			});
			t1.start();	
			Thread.sleep(1);
			
		t3= new Timer(10000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					clean=true;
				}
				
			});
		t3.start();
		}
		catch(InterruptedException exception)
		{
		exception.printStackTrace();
		Thread.currentThread().interrupt();
		}
	}

}
