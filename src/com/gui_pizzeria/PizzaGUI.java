package com.gui_pizzeria;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Created by Yuneir on 1/28/2018.
 */
public class PizzaGUI extends JFrame {
    // welcome panel
    JPanel pnlWelcome = new JPanel();
    final JLabel lblWelcome = new JLabel("Welcome, how do want your pizza?");

    // create menu
    Menu menu = new Menu();

    // size panel
    private JPanel pnlSize = new JPanel();
    private final JLabel lblSize = new JLabel("Size");
    private ArrayList<JRadioButton> rdoSize = new ArrayList<JRadioButton>();
    private ButtonGroup grpRdoSize = new ButtonGroup();
    private double sizeCost = 0.0;

    // toping panel
    private JPanel pnlToping = new JPanel();;
    private final JLabel lblToping = new JLabel("Toping");
    private ArrayList<JCheckBox> chkToping = new ArrayList<JCheckBox>();
    private double topingCost = 0.0;
    private ArrayList<Boolean> checkedBefore = new ArrayList<Boolean>();

    // drink panel
    private JPanel pnlDrink = new JPanel();
    private final JLabel lblDrink = new JLabel("Drink");
    private ArrayList<JRadioButton> rdoDrink = new ArrayList<JRadioButton>();
    private ButtonGroup grpRdoDrink = new ButtonGroup();
    private double drinkCost = 0.0;

    // south panel
    private JPanel pnlSouth = new JPanel();
    private JLabel lblTotal = new JLabel("Total: 0.0");
    private JButton btnBuy = new JButton("Buy");
    private JButton btnAddToping = new JButton("Add Toping");
    private JButton btnAddDrink = new JButton("Add Drink");
    private JButton btnExit = new JButton("Exit");

    // to calculate total
    private DecimalFormat money = new DecimalFormat("0.00");

    // constructor
    public PizzaGUI() {
        // name of window
        super("Pizzaria");

        // size of window
        this.setSize(600, 350);

        // set default close operation when click X
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set visible
        this.setVisible(true);

        // set layout for JFrame
        this.setLayout(new BorderLayout());

        // create panels
        createWelcomePanel();
        createPanelSize();
        createPanelToping();
        createPanelDrink();
        createPanelSouth();
    }

    private void createWelcomePanel() {
        // set layout
        pnlWelcome.setLayout(new GridLayout(1, 1));

        // add label to panel
        pnlWelcome.add(lblWelcome);

        // center label
        lblWelcome.setHorizontalAlignment(JLabel.CENTER);

        // add panels to frame
        super.add(pnlWelcome, BorderLayout.NORTH);
    }

    private void createPanelSize() {
        // set layout
        pnlSize.setLayout(new GridLayout(menu.getSize().size() + 1, 1));

        // add label
        pnlSize.add(lblSize);

        // center label
        lblSize.setHorizontalAlignment(JLabel.CENTER);

        // declare radio buttons
        for(int i = 0; i < menu.getSize().size(); i++) {
            rdoSize.add(new JRadioButton(menu.getSize().get(i) + " ($" + menu.get$size().get(i) + ")"));
        }

        // add radio button to group object
        for(JRadioButton current : rdoSize) {
            grpRdoSize.add(current);
        }

        // add radio buttons to panel
        for(JRadioButton current : rdoSize) {
            pnlSize.add(current);
        }

        // add action listener to radio buttons
        for(JRadioButton current : rdoSize) {
            current.addActionListener(new RadioButtonSizeListener());
        }

        // add panel to frame
        super.add(pnlSize, BorderLayout.WEST);
    }

    private class RadioButtonSizeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // reset
            setSizeCost(0.0);

            // calculate size cost
            for(int i = 0; i < menu.getSize().size(); i++) {
                if(e.getSource() == rdoSize.get(i)) {
                    setSizeCost(menu.get$size().get(i));
                }
            }

            updateTotalCost();
        }
    }

    private void createPanelToping() {
        // set layout
        pnlToping.setLayout(new GridLayout(menu.getToping().size() + 1, 1));

        // add label
        pnlToping.add(lblToping);

        // center label
        lblToping.setHorizontalAlignment(JLabel.CENTER);

        // declare check boxes
        for(int i = 0; i < menu.getToping().size(); i++) {
            chkToping.add(new JCheckBox(menu.getToping().get(i) + " ($" + menu.get$toping() + ")"));
        }

        // add check boxes to panel
        for(JCheckBox current : chkToping) {
            pnlToping.add(current);
        }

        // declare checkBefore and set to false
        for(int i = 0; i < chkToping.size(); i++) {
            checkedBefore.add(false);
        }

        // add action listener to check boxes
        for(JCheckBox current : chkToping) {
            current.addItemListener( new CheckBoxTopingListener());
        }

        // add panel to frame
        super.add(pnlToping, BorderLayout.CENTER);
    }

    private class CheckBoxTopingListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent arg0) {
            // calculate toping  cost
            for(int i = 0; i < menu.getToping().size(); i++) {
                if(chkToping.get(i).isSelected() && checkedBefore.get(i) == false) {
                    setTopingCost(getTopingCost() + menu.get$toping());
                    checkedBefore.set(i, true);
                }
                else if(!chkToping.get(i).isSelected() && checkedBefore.get(i) == true) {
                    setTopingCost(getTopingCost() - menu.get$toping());
                    checkedBefore.set(i, false);
                }
            }

            updateTotalCost();
        }
    }

    private void createPanelDrink() {
        // set layout
        pnlDrink.setLayout(new GridLayout(menu.getDrink().size() + 1, 1));

        // add label
        pnlDrink.add(lblDrink);

        // center label
        lblDrink.setHorizontalAlignment(JLabel.CENTER);

        // declare radio buttons
        for(int i = 0; i < menu.getDrink().size(); i++) {
            rdoDrink.add(new JRadioButton(menu.getDrink().get(i) + " ($" + menu.get$drink().get(i) + ")"));
        }

        // add radio button to group object
        for(JRadioButton current : rdoDrink) {
            grpRdoDrink.add(current);
        }

        // add radio buttons to panel
        for(JRadioButton current : rdoDrink) {
            pnlDrink.add(current);
        }

        // add action listener to radio buttons
        for(JRadioButton current : rdoDrink) {
            current.addActionListener(new CadioButtonDrinkListener());
        }

        // add panel to frame
        super.add(pnlDrink, BorderLayout.EAST);
    }

    private class CadioButtonDrinkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // reset
            setDrinkCost(0.0);

            // calculate drink cost
            for(int i = 0; i < menu.getDrink().size(); i++) {
                if(e.getSource() == rdoDrink.get(i)) {
                    setDrinkCost(getDrinkCost() + menu.get$drink().get(i));
                }
            }

            updateTotalCost();
        }
    }

    private void createPanelSouth() {
        // set Layout
        pnlSouth.setLayout(new GridLayout(1, 4));

        // add label
        pnlSouth.add(lblTotal);

        //add buttons
        pnlSouth.add(btnBuy);
        pnlSouth.add(btnAddToping);
        pnlSouth.add(btnAddDrink);
        pnlSouth.add(btnExit);

        // add action listener to buttons
        btnBuy.addActionListener(new btnBuyListener());
        btnAddToping.addActionListener(new btnAddTopingListener());
        btnAddDrink.addActionListener(new btnAddDrinkListener());
        btnExit.addActionListener(new btnExitListener());

        // add panel to frame
        super.add(pnlSouth, BorderLayout.SOUTH);
    }

    private void updateTotalCost() {
        lblTotal.setText("Total: " + money.format(getSizeCost() + getTopingCost() + getDrinkCost()));
    }

    public class btnBuyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // flag variables
            boolean sizeSelected = true;
            boolean topingSelected = true;

            // check if one size was selected
            for(int i = 0; i < menu.getSize().size(); i++) {
                if(grpRdoSize.getSelection() == null) {
                    sizeSelected = false;
                }
            }

            // check if at least one toping was selected
            for(int i = 0; i < menu.getToping().size(); i++) {
                if(!chkToping.get(i).isSelected()) {
                    topingSelected = false;
                }
                else {
                    topingSelected = true;
                    break;
                }
            }

            // validation, user may not choose a drink
            if(sizeSelected == false|| topingSelected == false) {
                JOptionPane.showMessageDialog(null, "Error. Must select a size and a toping.");
            }

            // show total and pizza gif if validation passed
            if(sizeSelected && topingSelected) {
                showTotal();
                showPizza();
            }

        }
    }

    private void showTotal() {
        JOptionPane.showMessageDialog(null, "The total is: " + calculateTotalCost());
    }

    private double calculateTotalCost() {
        return Double.parseDouble(money.format(getSizeCost() + getTopingCost() + getDrinkCost()));
    }

    private void showPizza() {
        new PizzaGif();
    }

    public class btnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            short exit = (short) JOptionPane.showConfirmDialog(null, "Are you sure you want to exit application?", "Exit?", JOptionPane.YES_NO_OPTION);

            if(exit == 0) {
                System.exit(0);
            }
        }
    }

    public class btnAddTopingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // get new toping
            menu.getToping().add(JOptionPane.showInputDialog("Enter new toping:"));

            // add new toping to panel
            pnlToping.removeAll();
            chkToping.removeAll(chkToping);
            createPanelToping();
            pnlToping.revalidate();
            pnlToping.repaint();
        }

    }

    public class btnAddDrinkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // get new drink and price
            menu.getDrink().add(JOptionPane.showInputDialog("Enter new drink:"));
            menu.get$drink().add(Double.parseDouble((JOptionPane.showInputDialog("Enter new drink's price:"))));

            // add new drink and price to the panel
            pnlDrink.removeAll();
            rdoDrink.removeAll(rdoDrink);
            createPanelDrink();
            pnlDrink.revalidate();
            pnlDrink.repaint();
        }

    }

    private JLabel getLblSize() {
        return lblSize;
    }

    private ArrayList<JRadioButton> getRdoSize() {
        return rdoSize;
    }

    private void setRdoSize(ArrayList<JRadioButton> rdoSize) {
        this.rdoSize = rdoSize;
    }

    private ButtonGroup getGrpRdoSize() {
        return grpRdoSize;
    }

    private void setGrpRdoSize(ButtonGroup grpRdoSize) {
        this.grpRdoSize = grpRdoSize;
    }

    private double getSizeCost() {
        return sizeCost;
    }

    private void setSizeCost(double sizeCost) {
        this.sizeCost = sizeCost;
    }


    public void setPnlToping(JPanel pnlToping) {
        this.pnlToping = pnlToping;
    }

    public JLabel getLblToping() {
        return lblToping;
    }

    public ArrayList<JCheckBox> getChkToping() {
        return chkToping;
    }

    public void setChkToping(ArrayList<JCheckBox> chkToping) {
        this.chkToping = chkToping;
    }

    public double getTopingCost() {
        return topingCost;
    }

    public void setTopingCost(double topingCost) {
        this.topingCost = topingCost;
    }

    public ArrayList<Boolean> getCheckedBefore() {
        return checkedBefore;
    }

    public void setCheckedBefore(ArrayList<Boolean> checkedBefore) {
        this.checkedBefore = checkedBefore;
    }


    public JLabel getLblDrink() {
        return lblDrink;
    }

    public ArrayList<JRadioButton> getRdoDrink() {
        return rdoDrink;
    }

    public void setRdoDrink(ArrayList<JRadioButton> rdoDrink) {
        this.rdoDrink = rdoDrink;
    }

    public ButtonGroup getGrpRdoDrink() {
        return grpRdoDrink;
    }

    public void setGrpRdoDrink(ButtonGroup grpRdoDrink) {
        this.grpRdoDrink = grpRdoDrink;
    }

    public double getDrinkCost() {
        return drinkCost;
    }

    public void setDrinkCost(double drinkCost) {
        this.drinkCost = drinkCost;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    public JButton getBtnBuy() {
        return btnBuy;
    }

    public void setBtnBuy(JButton btnBuy) {
        this.btnBuy = btnBuy;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
        this.btnExit = btnExit;
    }
}
