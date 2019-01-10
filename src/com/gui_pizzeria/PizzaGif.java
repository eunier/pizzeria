package com.gui_pizzeria;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by Yuneir on 1/28/2018.
 */
public class PizzaGif extends JFrame {
    // constructor
    public PizzaGif() {
        // widnow title
        super("Delicius");

        // set size
        this.setSize(550, 340);

        // set visible
        this.setVisible(true);

        // set layout
        this.setLayout(new BorderLayout());

        // add pizza gif to panel
        JPanel pnlPizza = new JPanel();
        ImageIcon pizzaGif = new ImageIcon("pizza.gif");
        JLabel lblPizza = new JLabel();
        lblPizza.setIcon(pizzaGif);
        pnlPizza.add(lblPizza);

        // add panel to frame
        this.add(pnlPizza, BorderLayout.CENTER);
    }
}
