import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ConcurrentModificationException;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
public class WaterPanel extends JPanel{
	private int n=0;
	private Point point= new Point();
	private boolean ss=true;
	private Fish fishtask;
	private boolean stop;
	private Feed feedtask;
	private Turtle turtletask;
	private static final SecureRandom generator=new SecureRandom();
	private ArrayList<Fish> fish=new ArrayList<>();
	private ArrayList<Turtle> turtles=new ArrayList<>();
	private ArrayList<Feed> feeds=new ArrayList<>();
	private final ArrayList<Thread> thread = new ArrayList<>();
	private static int width,high;
	private JLabel count;
	private ExecutorService executorService = Executors.newCachedThreadPool();
	public WaterPanel(final JLabel count) {
		
		// TODO Auto-generated constructor stub
		setLayout(null);
		this.setBounds(0,0,800,450);
		this.count=count;
		width = WaterPanel.this.getWidth();
		high = WaterPanel.this.getHeight();
		eating();
		addMouseListener(
				new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent event)
					{
						eating();
						getnum();
						point.setLocation(event.getPoint());
						if (n==1) {
							addfish();
							getfn();
						
						}
						else if(n==2) {
							addTurtle();
						}	
						else if(n==4) {
							addCookie();
						}
					/*	else if(n==3) {
							JLabel pressed = (JLabel)event.getSource();
							if(event.getSource() instanceof Fish){
								//functionLabel.setText("GOT A FISH");
								fish.remove(event.getSource());
								
							}				
							if(event.getSource() instanceof Turtle) {
								//functionLabel.setText("GOT A TURTLE");
								turtles.remove(event.getSource());
								//printInfo();
							}
							WaterPanel.this.remove(pressed);
							repaint();
							
						}*/
						else if(n==5) {
							stopf();
							stopt();
							
						}
					count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
					}
					@Override
					public void mousePressed(MouseEvent event){
					
					
				}
				}
		);
 
	
	
	}
	
	
	public void addfish() {
		
		int fishcode=generator.nextInt(3)+1;
		fishtask=new Fish(point,fishcode);
		fishtask.sethigh(high);
		fishtask.setwidth(width);
		fish.add(fishtask);
		
		WaterPanel.this.add(fish.get(fish.size()-1)).addMouseListener(new Handler());
		thread.add(new Thread(fish.get(fish.size()-1)));
		thread.get(thread.size()-1).start();
		eating();
		//executorService.execute(fishtask);
	}
	public void addTurtle() {
		//int n=2;
		Turtle turtletask=new Turtle(point);
		turtletask.sethigh(high);
		turtletask.setwidth(width);
		turtles.add(turtletask);
		WaterPanel.this.add(turtles.get(turtles.size()-1)).addMouseListener(new Handler());
		thread.add(new Thread(turtles.get(turtles.size()-1)));
		thread.get(thread.size()-1).start();
		//executorService.execute(turtletask);
	}
	public void addCookie() {
		Feed feedtask= new Feed(point);
		feedtask.sethigh(high);
		feedtask.setwidth(width);
		feeds.add(feedtask);
		WaterPanel.this.add(feeds.get(feeds.size()-1)).addMouseListener(new Handler());
		thread.add(new Thread(feeds.get(feeds.size()-1)));
		thread.get(thread.size()-1).start();
		eating();
	}
	private class Handler extends MouseAdapter{
		public void mousePressed(MouseEvent event) {
			if(n==3) {
				JLabel press = (JLabel)event.getSource();
				if(event.getSource() instanceof Fish){
					//functionLabel.setText("GOT A FISH");
					
					fish.remove(event.getSource());
					count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
			
				}				
				if(event.getSource() instanceof Turtle) {
					//functionLabel.setText("GOT A TURTLE");
					turtles.remove(event.getSource());
					//printInfo();
					count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());	
				}
				if(event.getSource() instanceof Feed) {
					//functionLabel.setText("GOT A TURTLE");
					feeds.remove(event.getSource());
					//printInfo();
					count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());	
				}
				WaterPanel.this.remove(press);
				repaint();
				
			
			}
		}
	}
	public void stopf() {
		for(Fish fishy:fish) 
		fishy.startstop(stop);}

	public void stopt() {}
	public void removechose() {
		//int n=3;
	}
	public void removeall() {
		fish.removeAll(fish);
		turtles.removeAll(turtles);
		feeds.removeAll(feeds);
		count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
	}

	public void setnum(int a) {
		 n=a;
	}
	public int getnum() {
		return n;
	}
	public int getfn() {
		return fish.size();
		
	}
	public int gettn() {
		return turtles.size();
	}
	public void setstop(boolean stop) {
		this.stop=stop;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.drawImage();
		//if(sslock==false&&deletelock==false)
		//{
			for(Fish fishy:fish)
			{
				add(fishy);
				fishy.setBounds(fishy.getPoint().x, fishy.getPoint().y,200,200);
			}
			for(Turtle turtle:turtles)
			{
				add(turtle);
				turtle.setBounds(turtle.getPoint().x, turtle.getPoint().y,200,200);
			}
			//fishlabel.clear();
			for(Feed feed:feeds) 
			{
				
				add(feed);
				feed.setBounds(feed.getPoint().x, feed.getPoint().y,200,200);
				try {
				if(feed.clean) {
					feeds.remove(feed);
					WaterPanel.this.remove(feed);
					count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
			
				}}
				catch(ConcurrentModificationException concurrentModificationException) {}
			 repaint();
			 System.out.printf("");
			}
			for(Feed feed:feeds) {
				for(Fish fishy:fish) {
					if(fishy.ceat) {
						if(feed.getPoint().getY()-fishy.getPoint().getY()<=50&&feed.getPoint().getY()-fishy.getPoint().getY()>=-50)
						{
							if(feed.getPoint().getX()-fishy.getPoint().getX()<50&&feed.getPoint().getX()-fishy.getPoint().getX()>-50)
							{
								fishy.eat=true;
								feeds.remove(feed);
								WaterPanel.this.remove(feed);
								count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
							}
						}
					}
				}
				for(Turtle turtle:turtles) {if(turtle.ceat) {
					if(feed.getPoint().getY()-turtle.getPoint().getY()<=50&&feed.getPoint().getY()-turtle.getPoint().getY()>=-50)
					{
						if(feed.getPoint().getX()-turtle.getPoint().getX()<50&&feed.getPoint().getX()-turtle.getPoint().getX()>-50)
						{
							turtle.eat=true;
							feeds.remove(feed);
							WaterPanel.this.remove(feed);
							count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
						}
					}
				}
					
				}
			}
			eating();
			repaint();
		
		
	}
	public void eating() {
		for(Feed feed:feeds) {
			for(Fish fishy:fish) {
				if(fishy.ceat) {
					if(feed.getPoint().getY()-fishy.getPoint().getY()<=50&&feed.getPoint().getY()-fishy.getPoint().getY()>=-50)
					{
						if(feed.getPoint().getX()-fishy.getPoint().getX()<50&&feed.getPoint().getX()-fishy.getPoint().getX()>-50)
						{
							fishy.eat=true;
							feeds.remove(feed);
							WaterPanel.this.remove(feed);
							count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
						}
					}
				}
			}
			for(Turtle turtle:turtles) {if(turtle.ceat) {
				if(feed.getPoint().getY()-turtle.getPoint().getY()<=50&&feed.getPoint().getY()-turtle.getPoint().getY()>=-50)
				{
					if(feed.getPoint().getX()-turtle.getPoint().getX()<50&&feed.getPoint().getX()-turtle.getPoint().getX()>-50)
					{
						turtle.eat=true;
						feeds.remove(feed);
						WaterPanel.this.remove(feed);
						count.setText("辰计秖"+fish.size()+"   疩纓计秖"+turtles.size()+"   箎计秖"+feeds.size());
					}
				}
			}
				
			}
		}
	}
}

