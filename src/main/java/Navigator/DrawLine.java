package Navigator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DrawLine extends JLabel
{

    int aX, aY;
    int bX, bY;

    public DrawLine (String text, int aX, int aY, int bX, int bY)
    {
        super("", null, SwingConstants.CENTER);
        super.setName(text);
        this.aX = aX;
        this.aY = aY;
        this.bX = bX;
        this.bY = bY;
        
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(aX, aY, bX, bY);
    }
}
