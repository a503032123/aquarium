import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class WaterFrame extends JFrame {
	
	private final JButton addF;
	private final JButton addT;
	private final JButton feed;
	private final JButton removechose;
	private final JButton removeall;
	private final JButton stopall;
	private final JLabel current;
	private final JLabel count;
	private boolean k=true;
	private String currentw="目前選取功能: ";
	private int fnum=0;
	private int tnum=0;
	private final JPanel OptionPanel;
	
	private WaterPanel waterpanel;
	public WaterFrame() {
		
		addF=new JButton("新增魚");
		addT=new JButton("新增烏龜");
		feed=new JButton("餵飼料");
		stopall=new JButton("停止/繼續魚的動作");
		removechose=new JButton("移除選取");
		removeall=new JButton("移除全部");
		current=new JLabel(currentw);
		//fnum=waterpanel.getfn();
		//tnum=waterpanel.gettn();
		count=new JLabel(" 魚數量"+0+"     烏龜數量 "+0+"     飼料數量"+0 );
		waterpanel=new WaterPanel(count);
		OptionPanel=new JPanel();
		OptionPanel.setLayout(new GridLayout(3,3,5,5) );
		OptionPanel.add(addF);
		OptionPanel.add(addT);
		OptionPanel.add(feed);
		OptionPanel.add(removechose);
		OptionPanel.add(removeall);
		OptionPanel.add(stopall);
		OptionPanel.add(current);
		OptionPanel.add(count);
		waterpanel.setVisible(true);
		waterpanel.setBackground(new Color(100,200,255));
		waterpanel.setBounds(0,0,1000,400);
		add(OptionPanel,BorderLayout.NORTH);
		add(waterpanel,BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
		addF.addActionListener(new Btlistener());
		addT.addActionListener(new Btlistener());
		removechose.addActionListener(new Btlistener());
		removeall.addActionListener(new Btlistener());
		stopall.addActionListener(new Btlistener());
		feed.addActionListener(new Btlistener());
	}

		class Btlistener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()== addF) 
				{current.setText(currentw+e.getActionCommand());
				 waterpanel.setnum(1);
				 /*fnum=waterpanel.getfn();
				 tnum=waterpanel.gettn();
				 count.setText("魚數量"+fnum+"烏龜數量"+tnum);*/
				
				}
				else if(e.getSource()==addT) 
				{current.setText(currentw+e.getActionCommand());
				 waterpanel.setnum(2);
				}
				else if(e.getSource()==removechose)
				{current.setText(currentw+e.getActionCommand());
				 waterpanel.setnum(3);
				}
				else if(e.getSource()==removeall)
				{current.setText(currentw+e.getActionCommand());
				
				waterpanel.removeAll();
				waterpanel.repaint();
				waterpanel.removeall();
				}
				else if(e.getSource()==stopall) {
					current.setText(currentw+e.getActionCommand());
					waterpanel.setstop( k);
					waterpanel.stopf();
					addF.setEnabled(!k);
					waterpanel.setnum(0);
					k=!k;
				}
				else if(e.getSource()==feed) {
					current.setText(currentw+e.getActionCommand());
					waterpanel.setnum(4);
				}
			}
		
		
		
		}
}
