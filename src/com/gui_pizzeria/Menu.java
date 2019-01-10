package com.gui_pizzeria;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Yuneir on 1/28/2018.
 */
public class Menu {
    // menu
    private ArrayList<String> size = new ArrayList<String>(Arrays.asList("Small", "Medium", "Large", "Extra Large"));
    private ArrayList<String> toping = new ArrayList<String>(Arrays.asList("Pepperoni", "Onions", "Green Peppers", "Extra Cheese"));
    private ArrayList<String> drink = new ArrayList<String>(Arrays.asList("Soda", "Tea", "Bottled Water", "Tap Water"));

    // prices
    private ArrayList<Double> $size = new ArrayList<Double>(Arrays.asList(7.0, 9.0, 11.0, 14.0));
    private final double $toping = 1.0;
    private ArrayList<Double> $drink = new ArrayList<Double>(Arrays.asList(2.00, 1.50, 1.25, 0.0));

    // constructor
    public Menu () {}

    public ArrayList<String> getSize() {
        return size;
    }

    public void setSize(ArrayList<String> size) {
        this.size = size;
    }

    public ArrayList<String> getToping() {
        return toping;
    }

    public void setToping(ArrayList<String> toping) {
        this.toping = toping;
    }

    public ArrayList<String> getDrink() {
        return drink;
    }

    public void setDrink(ArrayList<String> drink) {
        this.drink = drink;
    }

    public double get$toping() {
        return $toping;
    }

    public ArrayList<Double> get$drink() {
        return $drink;
    }

    public void set$drink(ArrayList<Double> $drink) {
        this.$drink = $drink;
    }

    public ArrayList<Double> get$size() {
        return $size;
    }

    public void set$size(ArrayList<Double> $size) {
        this.$size = $size;
    }
}
