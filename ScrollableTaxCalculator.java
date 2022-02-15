/* Program: ScrollableTaxCalculator
   Date: May 6, 2018
   Author: Jonathan Price
   
   This program is designed to ask the user to
   enter the amount of a purchase and use a slider
   to adjust the tax rate between 0% and 10%,
   then display the amount of sales tax due on the
   purchase. When run, this program will display a 
   window with an editable text box for the user to
   input the purchase amount, an uneditable text box
   to display the amount of tax due, and a slider to
   edit the tax rate. After being executed, this 
   program needs the user to enter a dollar amount 
   into the textbox labeled "Purchase" and move the
   slider to their desired tax rate percentage.
   
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
   This class displays a window with two text boxes and
   a slider component. The user can get the amount of
   sales tax due on an inputted amount of a purchase
   from 0% to 10% by moving the slider.
*/

public class ScrollableTaxCalculator extends JFrame
{
   private JLabel label1, label2;   //Message labels
   private JTextField purchaseAmount;  //Amount of purchase
   private JTextField taxDue; //Amount of tax due
   private JPanel purchasePanel; //Purchase panel
   private JPanel taxPanel;   //Tax panel
   private JPanel sliderPanel;   //Slider panel
   private JSlider slider; //Tax rate adjuster
   
   /**
      Constructor
   */
   
   public ScrollableTaxCalculator()
   {
      //Set the title
      setTitle("Tax Calculator");
      
      //Specify an action for the close button
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //Create the message labels
      label1 = new JLabel("Purchase:  $");
      label2 = new JLabel("Tax Due: ");
      
      //Create the editable text field for the purchase amount
      purchaseAmount = new JTextField(10);
      purchaseAmount.setEditable(true);
      
      //Create the read-only text field
      taxDue = new JTextField("$0.0", 10);
      taxDue.setEditable(false);
      
      //Create the slider
      slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
      slider.setMajorTickSpacing(5);   //Major tick every 5
      slider.setMinorTickSpacing(1);   //Major tick every 1
      slider.setPaintTicks(true);   //Display tick marks
      slider.setPaintLabels(true);  //Display numbers
      slider.addChangeListener(new SliderListener());
      
      //Create panels and place the components in them
      purchasePanel = new JPanel();
      purchasePanel.add(label1);
      purchasePanel.add(purchaseAmount);
      taxPanel = new JPanel();
      taxPanel.add(label2);
      taxPanel.add(taxDue);
      sliderPanel = new JPanel();
      sliderPanel.add(slider);
      
      //Create the GridLayout manager
      setLayout(new GridLayout(3, 1));
      
      //Add the panels to the content pane
      add(purchasePanel);
      add(taxPanel);
      add(sliderPanel);
      
      //Pack and display the frame
      pack();
      setVisible(true);
   }
   
   /**
      Private inner class to handle the change events that
      are generated when the slider is moved and calculate
      taxes due
   */
   
   private class SliderListener implements ChangeListener
   {
      public void stateChanged(ChangeEvent e)
      {
         double amount, tax, tax_rate; //Hold the amount, tax due, and tax rate
         String amount_input; //Hold the purchase amount input
         
         //Get the purchase amount
         amount_input = purchaseAmount.getText();
         
         //Convert the purchase amount input to a double
         amount = Double.parseDouble(amount_input);
         
         //Get the slider value
         tax_rate = slider.getValue();
         
         //Convert slider value to decimal
         tax_rate = tax_rate / 100;
         
         //Calculate the tax due
         tax = tax_rate * amount;
         
         //Store the tax due in its display field
         taxDue.setText(String.format("$%.2f", tax));
      }
   }
   
   /*
      The main method creates an instance of the class,
      which displays a window with a slider
   */
   
   public static void main(String[] args)
   {
      new ScrollableTaxCalculator();
   }
}