package my.com.ggwp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class VDF2JSON_GUI extends JFrame
{
    JTextArea inputstring, outputstring;
    JScrollPane inputscroll, outputscroll;
    JButton convert, copyoutput;

    public VDF2JSON_GUI()
    {
        setTitle("VDF2JSON GUI");
        setLayout(new GridLayout(2, 0));

        JPanel p1=new JPanel(new BorderLayout());
        p1.add(inputscroll=new JScrollPane(inputstring=new JTextArea()));
        p1.add(convert=new JButton("Convert"), BorderLayout.PAGE_END);

        JPanel p2=new JPanel(new BorderLayout());
        p2.add(outputscroll=new JScrollPane(outputstring=new JTextArea()));
        p2.add(copyoutput=new JButton("Copy Output"), BorderLayout.PAGE_END);

        add(p1);
        add(p2);

        outputstring.setEditable(false);
        convert.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(inputstring.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "SMLJ also don't have, how do I convert for you?", "Warning", JOptionPane.WARNING_MESSAGE);
                else
                {
                    String outputstr=VDF2JSON_Common.convertString2JSON(inputstring.getText());
                    if(outputstr.equals(""))
                        JOptionPane.showMessageDialog(null, "Conversion failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    else
                        outputstring.setText(outputstr);
                }
            }
        });
        copyoutput.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Ctrl_C().setClipboard(outputstring.getText());
            }
        });
    }

    public static void startGUI(String file)
    {
        VDF2JSON_GUI f=new VDF2JSON_GUI();
        f.setSize(780, 675);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        if(!(file.equals("")))
        {
            try
            {
                Scanner s=new Scanner(new File(file)).useDelimiter("\\A");
                String string=s.hasNext()?s.next():"";
                f.inputstring.setText(string);
            }
            catch(FileNotFoundException e)
            {
                System.out.println("[Exception|VDF2JSON_GUI.main] Shit, something happened.");
                System.out.println("------------------------------- Stack Trace Begin -------------------------------");
                e.printStackTrace();
                System.out.println("-------------------------------- Stack Trace End --------------------------------\n");
            }
        }
    }
}