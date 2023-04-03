import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import io.netty.util.internal.ThreadLocalRandom;

public class Nallas_demo {

	public static String Set1 = "Yet to start";
	public static String Set2 = "Yet to start";
	public static JLabel Set_list1;
	public static JLabel Set_list2;
	public static JLabel Status;
	public static String user_status ="Yet to start";
	public static  String  Verification="";
	public static  int Guess_count=0;
	public static JTextField userEnt;
	public static JTextField userEnt1;
	public static  String  finalstring="";
	public static  String  finalstring1="";
	public static int length;
	public static  int count;  
	public static Integer [] int_array;
	public static JButton number = new JButton();
	public static JButton reset;
	public static String finalvalue="";
	public static JPanel mainPanel, subPanel1, subPanel2;
	public static ArrayList<Integer> value = new ArrayList<Integer>();
	public static JLabel textfield = new JLabel();
	public static Random random = new Random();
	public static int a[][];
	
	public static void main(String[] args) 
	{
					
     try
     {
    	
    	 new TicTacToeGameinSwing();
    	 
	 }
	catch(Exception g)
	{
		System.out.println(g.toString());
	}
	}
	

	
    
   
public static int[] system_resolution()
{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		
		int height = (int)screenSize.getHeight();
		int[] val = {width,height};
		return val;
		
}



public static class TicTacToeGameinSwing implements ActionListener 
{  
    JFrame frame = new JFrame();
    JPanel t_panel = new JPanel();
    JPanel bt_panel = new JPanel();
    JPanel v_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] bton = new JButton[9];
     JButton number1 = new JButton("Validate");
    
    
    int chance_flag = 0;
    Random random = new Random();
    boolean pl1_chance;
    
    // Creating class constructor for creating grid
    TicTacToeGameinSwing() 
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(250, 184, 97));
        frame.setTitle("Welcome To NALLAS");
        
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(0,0,0));
        textfield.setForeground(new Color(255,0,0));
        textfield.setFont(new Font("Serif", Font.BOLD, 50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Nallas -Tic Tac Toe Validation");
        textfield.setOpaque(true);
        
        number1.setOpaque(true);
        number1.setContentAreaFilled(true);
        number1.setBorderPainted(false);
        number1.setFocusPainted(false);
        number1.setBackground(Color.blue); // for the background
        number1.setForeground(Color.white);
        
        v_panel.setLayout(new BorderLayout());
        v_panel.setBounds(0, 0, 50, 50);
        
        t_panel.setLayout(new BorderLayout());
        t_panel.setBounds(0, 0, 800, 100);

        bt_panel.setLayout(new GridLayout(3, 3));
        bt_panel.setBackground(new Color(0, 0, 0));
        
       

        for (int i = 0; i < 9; i++) 
        {
            bton[i] = new JButton();
            bt_panel.add(bton[i]);
            bton[i].setFont(new Font("Serif", Font.BOLD, 120));
            bton[i].setFocusable(false);
            bton[i].addActionListener(this);
            bton[i].setBackground(Color.cyan);
        }
        
        t_panel.add(textfield);
        v_panel.add(number1);
       
        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);
        frame.add(v_panel,BorderLayout.SOUTH);
        
        startGame();
        
        number1.addActionListener(new ActionListener()
        {  
			  public void actionPerformed(ActionEvent e)
			  {
        matchCheck();
			  }
        });
    }
    
    // Creating method to start the game and decide the chance
    public  void startGame() 
    {
        try 
        {
            textfield.setText("Loading....");
            Thread.sleep(4000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        int chance=random.nextInt(100);

        if (chance%2 == 0) 
        {
            pl1_chance = true;
            textfield.setText("Player X turn");
        } 
        else 
        {
            pl1_chance = false;
            textfield.setText("Player O turn");
        }
    }
    
    public void gameOver(String s)
    {
        chance_flag = 0;
        Object[] option={"Restart","Exit"};
        int n=JOptionPane.showOptionDialog(frame, "Game Over\n"+s,"Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if(n==0)
        {
            frame.dispose();
            new TicTacToeGameinSwing();
        }
        else
        {
            frame.dispose();
        }
    
    }

    // Creating method for checking winning conditions 
    public void matchCheck() 
    {
        if ((bton[0].getText() == "X") && (bton[1].getText() == "X") && (bton[2].getText() == "X")) 
        {
            xWins(0, 1, 2);
        }
        else if ((bton[0].getText() == "X") && (bton[4].getText() == "X") && (bton[8].getText() == "X")) 
        {
            xWins(0, 4, 8);
        }
        else if ((bton[0].getText() == "X") && (bton[3].getText() == "X") && (bton[6].getText() == "X")) 
        {
            xWins(0, 3, 6);
        }
        else if ((bton[1].getText() == "X") && (bton[4].getText() == "X") && (bton[7].getText() == "X")) 
        {
            xWins(1, 4, 7);
        }
        else if ((bton[2].getText() == "X") && (bton[4].getText() == "X") && (bton[6].getText() == "X")) 
        {
            xWins(2, 4, 6);
        }
        else if ((bton[2].getText() == "X") && (bton[5].getText() == "X") && (bton[8].getText() == "X")) 
        {
            xWins(2, 5, 8);
        }
       else if ((bton[3].getText() == "X") && (bton[4].getText() == "X") && (bton[5].getText() == "X")) 
       {
            xWins(3, 4, 5);
        }
       else if ((bton[6].getText() == "X") && (bton[7].getText() == "X") && (bton[8].getText() == "X")) 
       {
            xWins(6, 7, 8);
        }
      
        else if ((bton[0].getText() == "O") && (bton[1].getText() == "O") && (bton[2].getText() == "O")) 
        {
            oWins(0, 1, 2);
        }
        else if ((bton[0].getText() == "O") && (bton[3].getText() == "O") && (bton[6].getText() == "O")) 
        {
            oWins(0, 3, 6);
        }
        else if ((bton[0].getText() == "O") && (bton[4].getText() == "O") && (bton[8].getText() == "O")) 
        {
            oWins(0, 4, 8);
        }
        else if ((bton[1].getText() == "O") && (bton[4].getText() == "O") && (bton[7].getText() == "O")) 
        {
            oWins(1, 4, 7);
        }
        else if ((bton[2].getText() == "O") && (bton[4].getText() == "O") && (bton[6].getText() == "O")) 
        {
            oWins(2, 4, 6);
        }
        else if ((bton[2].getText() == "O") && (bton[5].getText() == "O") && (bton[8].getText() == "O")) 
        {
            oWins(2, 5, 8);
        }
        else if ((bton[3].getText() == "O") && (bton[4].getText() == "O") && (bton[5].getText() == "O")) 
        {
            oWins(3, 4, 5);
        } else if ((bton[6].getText() == "O") && (bton[7].getText() == "O") && (bton[8].getText() == "O")) 
        {
            oWins(6, 7, 8);
        }
        else if(chance_flag==9) 
        {
            textfield.setText("Game Draw!!");
             gameOver("Game Draw!!");
        }
    }

    // Method to print that Player X wins
    public void xWins(int x1, int x2, int x3) 
    {
    	bton[x1].setBackground(Color.YELLOW);
        bton[x2].setBackground(Color.YELLOW);
        bton[x3].setBackground(Color.YELLOW);

        for (int i = 0; i < 9; i++) 
        {
            bton[i].setEnabled(false);
        }
        textfield.setText("Player X wins");
        gameOver("Player X Wins");
    }

    // Method to print that Player O wins
    public void oWins(int x1, int x2, int x3) 
    {
        bton[x1].setBackground(Color.YELLOW);
        bton[x2].setBackground(Color.YELLOW);
        bton[x3].setBackground(Color.YELLOW);

        for (int i = 0; i < 9; i++) 
        {
            bton[i].setEnabled(false);
        }
        textfield.setText("Player O Wins");
        gameOver("Player O Wins");
    }
    
    // Method for performing action after every turn
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for (int i = 0; i < 9; i++) 
        {
            if (e.getSource() == bton[i]) 
            {
                if (pl1_chance) 
                {
                    if (bton[i].getText() == "") 
                    {
                        bton[i].setForeground(new Color(0, 188, 255));
                        bton[i].setText("X");
                        pl1_chance = false;
                        textfield.setText("O turn");
                        chance_flag++;
                        
                    }
                } 
                else 
                {
                    if (bton[i].getText() == "") 
                    {
                        bton[i].setForeground(new Color(0, 255, 9));
                        bton[i].setText("O");
                        pl1_chance = true;
                        textfield.setText("X turn");
                        chance_flag++;
                        
                    }
                }
            }
            System.out.println("i value"+i);
            
        }
       
        
    }
}
}	
	










