package Navigator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Graph.Vertex;
import Path.Path;

public class Navigator extends JFrame
{
    int count = 0;
    Path path;
    Border old;
    JLabel background;
    JToggleButton btn;
    JToggleButton[] buttons = new JToggleButton[2];
    LinkedList<DrawLine> lineList;
    JLabel output = new JLabel("Distance", SwingConstants.CENTER);

    ActionListener action = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            btn = (JToggleButton) e.getSource();

            if (btn.getModel().isSelected())
            {
                count++;
                if (count == 1) 
                {
                    //Set button to start point.
                    buttons[0] = btn;
                    btn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
                }
                
                if (count == 2)
                {
                    //Set button to finish point.
                    buttons[1] = btn;
                    btn.setBorder(BorderFactory.createLineBorder(Color.green, 3));
                    System.out.println("Path from: " + buttons[0].getName() + " to: " + buttons[1].getName());
                    path.calcPath(buttons[0].getName(), buttons[1].getName());
                    String distance = "" + path.getDistance();
                    output.setText(distance + " feet");
                    LinkedList<Vertex> vPath = path.getPath();
                    lineList = generateLines(vPath);

                }
                
                if (count > 2)
                {
                    // Reset path
                    System.out.println("Reseting");
                    btn.setSelected(false);
                    buttons[0].setSelected(false);
                    buttons[0].setBorder(old);
                    buttons[1].setSelected(false);
                    buttons[1].setBorder(old);
                    output.setText("Select Path");

                    deleteLines(lineList);
                    background.repaint();
                    count = 0;
                }
            } 
            else 
            {
                // Reset all 
                System.out.println("Reseting");
                btn.setSelected(false);
                buttons[0].setSelected(false);
                buttons[0].setBorder(old);
                buttons[1].setSelected(false);
                buttons[1].setBorder(old);
                output.setText("Select Path");

                deleteLines(lineList);
                background.repaint();
                count = 0;
            }
        }
    };

    MouseAdapter onClick = new MouseAdapter() {
        @Override //I override only one method for presentation
        public void mousePressed(MouseEvent e)
        {
            System.out.println(e.getX() + "," + e.getY());

            if (count == 1)
            {
                System.out.println("Reseting");
                btn.setSelected(false);
                buttons[0].setSelected(false);
                buttons[0].setBorder(old);
                count = 0;
            }

            if (count >= 2)
            {
                System.out.println("Reseting");
                buttons[0].setSelected(false);
                buttons[0].setBorder(old);
                buttons[1].setSelected(false);
                buttons[1].setBorder(old);
                output.setText("Select Path");
                
                deleteLines(lineList);
                background.repaint();
                count = 0;
            }
        }
    };

    private LinkedList<DrawLine> generateLines(LinkedList<Vertex> vPath)
    {
        LinkedList<PathList> centers = new LinkedList<>();
        centers.add(new PathList("PC", 916,552));
        centers.add(new PathList("GC", 998,493));
        centers.add(new PathList("DM", 847,496));
        centers.add(new PathList("GL", 912,416));
        centers.add(new PathList("OE", 992,334));
        centers.add(new PathList("VH", 847,343));
        centers.add(new PathList("CASE", 918,254));
        centers.add(new PathList("CP", 1044,302));
        centers.add(new PathList("PCA", 797,257));
        centers.add(new PathList("AHC4", 1044,232));
        centers.add(new PathList("PG1", 1048,618));
        centers.add(new PathList("PG2", 1048,704));
        centers.add(new PathList("PG4", 976,156));
        centers.add(new PathList("PG5", 1094,158));
        centers.add(new PathList("PG6", 864,152));

        LinkedList<PathList> lines = new LinkedList<>();

        for (int i = 0; i < vPath.size(); i++)
        {
            for (int j = 0; j < centers.size(); j++)
            {
                if (centers.get(j).vertex.equals(vPath.get(i).getLabel()))
                {
                    lines.add(centers.get(j));
                }
            }
        }

        LinkedList<DrawLine> paintLines = drawLines(lines);

        return paintLines;
    }

    private LinkedList<DrawLine> drawLines(LinkedList<PathList> pList)
    {
        LinkedList<DrawLine> lines = new LinkedList<>();
        
        for (int i = 0; i < pList.size() - 1; i++)
        {
            DrawLine l = new DrawLine(pList.get(i).vertex + " " + pList.get(i + 1).vertex, pList.get(i).x, pList.get(i).y, pList.get(i + 1).x ,pList.get(i + 1).y);
            l.setBounds(0,0, 1500, 970);
            lines.add(l);
            background.add(l);
            background.repaint();
        }

        return lines;
    }

    private void deleteLines(LinkedList<DrawLine> lines)
    {
        for (int i = 0; i < lines.size(); i++)
        {
            background.remove(lines.get(i));
        }
    }

    public Navigator() throws Exception
    {
        path = new Path();

        JFrame frame = new JFrame();

        frame.setSize(1500, 970);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        background = new JLabel(new ImageIcon("src/main/resources/MMC.png"));

        background.addMouseListener(onClick);

        frame.add(background);
        background.setLayout(null);

        // Create building nodes as Toggle Buttons
        JToggleButton PC = new JToggleButton();
        PC.setBounds(885, 530, 70, 40);
        PC.setContentAreaFilled(false);
        PC.setName("PC");
        PC.addActionListener(action);

        old = PC.getBorder();

        JToggleButton GC = new JToggleButton();
        GC.setBounds(960, 443, 80, 120);
        GC.setContentAreaFilled(false);
        GC.setName("GC");
        GC.addActionListener(action);

        JToggleButton DM = new JToggleButton();
        DM.setBounds(832, 466, 40, 70);
        DM.setContentAreaFilled(false);
        DM.setName("DM");
        DM.addActionListener(action);

        JToggleButton GL = new JToggleButton();
        GL.setBounds(876, 373, 80, 80);
        GL.setContentAreaFilled(false);
        GL.setName("GL");
        GL.addActionListener(action);

        JToggleButton OE = new JToggleButton();
        OE.setBounds(969, 285, 40, 95);
        OE.setContentAreaFilled(false);
        OE.setName("OE");
        OE.addActionListener(action);

        JToggleButton VH = new JToggleButton();
        VH.setBounds(830, 310, 40, 70);
        VH.setContentAreaFilled(false);
        VH.setName("VH");
        VH.addActionListener(action);

        JToggleButton CASE = new JToggleButton();
        CASE.setBounds(887, 227, 60, 60);
        CASE.setContentAreaFilled(false);
        CASE.setName("CASE");
        CASE.addActionListener(action);

        JToggleButton CP = new JToggleButton();
        CP.setBounds(1018, 267, 55, 60);
        CP.setContentAreaFilled(false);
        CP.setName("CP");
        CP.addActionListener(action);

        JToggleButton PCA = new JToggleButton();
        PCA.setBounds(772, 231, 55, 50);
        PCA.setContentAreaFilled(false);
        PCA.setName("PCA");
        PCA.addActionListener(action);

        JToggleButton AHC4 = new JToggleButton();
        AHC4.setBounds(1006, 220, 80, 25);
        AHC4.setContentAreaFilled(false);
        AHC4.setName("AHC4");
        AHC4.addActionListener(action);

        JToggleButton PG1 = new JToggleButton();
        PG1.setBounds(1016, 588, 70, 60);
        PG1.setContentAreaFilled(false);
        PG1.setName("PG1");
        PG1.addActionListener(action);

        JToggleButton PG2 = new JToggleButton();
        PG2.setBounds(1013, 673, 70, 60);
        PG2.setContentAreaFilled(false);
        PG2.setName("PG2");
        PG2.addActionListener(action);


        JToggleButton PG4 = new JToggleButton();
        PG4.setBounds(936, 125, 80, 60);
        PG4.setContentAreaFilled(false);
        PG4.setName("PG4");
        PG4.addActionListener(action);

        JToggleButton PG5 = new JToggleButton();
        PG5.setBounds(1050, 121, 85, 75);
        PG5.setContentAreaFilled(false);
        PG5.setName("PG5");
        PG5.addActionListener(action);

        JToggleButton PG6 = new JToggleButton();
        PG6.setBounds(823, 113, 85, 75);
        PG6.setContentAreaFilled(false);
        PG6.setName("PG6");
        PG6.addActionListener(action);

        output.setText("Select Path");
        output.setBounds(10, 10, 100, 50);
        output.setBackground(Color.white);
        output.setOpaque(true);
        output.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        background.add(PC);
        background.add(GC);
        background.add(DM);
        background.add(GL);
        background.add(OE);
        background.add(VH);
        background.add(CASE);
        background.add(CP);
        background.add(PCA);
        background.add(AHC4);
        background.add(PG1);
        background.add(PG2);
        background.add(PG4);
        background.add(PG5);
        background.add(PG6);
        background.add(output);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
    public static void main(String args[]) throws Exception
    {
        System.out.println("FIU Navigator.");
        new Navigator();
    }
}
