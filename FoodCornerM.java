
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodCornerM implements ActionListener {
    JFrame f1;
    JLabel lTitle, lMenu, lCostLabel, lQtyLabel;
    JLabel lPaneerPrice, lPulavPrice, lChickenPrice, lMuttonPrice, lFishPrice;
    JTextField tfPaneerQty, tfPulavQty, tfChickenQty, tfMuttonQty, tfFishQty;
    JButton bOrder, bCancel;
    JCheckBox cbPaneer, cbPulav, cbChicken, cbMutton, cbFish;
    JProgressBar br;

    public FoodCornerM() {
        f1 = new JFrame("Food Corner");
        f1.setSize(600, 500);
        f1.setLayout(null);
        
        
        br=new JProgressBar();
		br.setOrientation(0);
		br.setBounds(100, 20, 400, 60);
		br.setBackground(Color.yellow);
		br.setFont(new Font("Arial",Font.BOLD,50));
		br.setForeground(Color.orange);
		br.setIndeterminate(true);
		br.setString("FOOD CORNER");
		br.setStringPainted (true);
		f1.add(br);

        

        lMenu = new JLabel("MENU");
        lMenu.setBounds(60, 70, 100, 50);
        lMenu.setFont(new Font("Arial", Font.BOLD, 15));
        f1.add(lMenu);

        cbPaneer = new JCheckBox("Paneer");
        cbPaneer.setBounds(50, 110, 100, 50);
        f1.add(cbPaneer);

        cbPulav = new JCheckBox("Pulav");
        cbPulav.setBounds(50, 150, 100, 50);
        f1.add(cbPulav);

        cbChicken = new JCheckBox("Chicken");
        cbChicken.setBounds(50, 190, 100, 50);
        f1.add(cbChicken);

        cbMutton = new JCheckBox("Mutton");
        cbMutton.setBounds(50, 230, 100, 50);
        f1.add(cbMutton);

        cbFish = new JCheckBox("Fish");
        cbFish.setBounds(50, 270, 100, 50);
        f1.add(cbFish);

        lCostLabel = new JLabel("COST");
        lCostLabel.setBounds(240, 70, 100, 50);
        lCostLabel.setFont(new Font("Arial", Font.BOLD, 15));
        f1.add(lCostLabel);

        lPaneerPrice = new JLabel("180/-");
        lPaneerPrice.setBounds(250, 110, 100, 50);
        f1.add(lPaneerPrice);

        lPulavPrice = new JLabel("120/-");
        lPulavPrice.setBounds(250, 150, 100, 50);
        f1.add(lPulavPrice);

        lChickenPrice = new JLabel("150/-");
        lChickenPrice.setBounds(250, 190, 100, 50);
        f1.add(lChickenPrice);

        lMuttonPrice = new JLabel("380/-");
        lMuttonPrice.setBounds(250, 230, 100, 50);
        f1.add(lMuttonPrice);

        lFishPrice = new JLabel("180/-");
        lFishPrice.setBounds(250, 270, 100, 50);
        f1.add(lFishPrice);

        lQtyLabel = new JLabel("QTY");
        lQtyLabel.setBounds(410, 70, 100, 50);
        lQtyLabel.setFont(new Font("Arial", Font.BOLD, 15));
        f1.add(lQtyLabel);

        tfPaneerQty = new JTextField("");
        tfPaneerQty.setBounds(400, 120, 60, 30);
        f1.add(tfPaneerQty);

        tfPulavQty = new JTextField("");
        tfPulavQty.setBounds(400, 160, 60, 30);
        f1.add(tfPulavQty);

        tfChickenQty = new JTextField("");
        tfChickenQty.setBounds(400, 200, 60, 30);
        f1.add(tfChickenQty);

        tfMuttonQty = new JTextField("");
        tfMuttonQty.setBounds(400, 240, 60, 30);
        f1.add(tfMuttonQty);

        tfFishQty = new JTextField("");
        tfFishQty.setBounds(400, 280, 60, 30);
        f1.add(tfFishQty);

        bOrder = new JButton("Order");
        bOrder.setBounds(200,400, 100, 30);
        f1.add(bOrder);

        bCancel = new JButton("Cancel");
        bCancel.setBounds(320, 400, 100, 30);
        f1.add(bCancel);

        bOrder.addActionListener(this);
        bCancel.addActionListener(this);

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bOrder) {
            String billSummary = generateBillSummary();
            if (!billSummary.isEmpty()) {
                JOptionPane.showMessageDialog(f1, billSummary, "Bill Summary", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(f1, "Thank You for your order!", "Thank You", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == bCancel) {
            resetFieldsAndCheckBoxes();
        }
    }

    private String generateBillSummary() {
        int[] prices = {180, 120, 150, 380, 180};
        JCheckBox[] checkBoxes = {cbPaneer, cbPulav, cbChicken, cbMutton, cbFish};
        JTextField[] fields = {tfPaneerQty, tfPulavQty, tfChickenQty, tfMuttonQty, tfFishQty};
        String[] items = {"Paneer", "Pulav", "Chicken", "Mutton", "Fish"};

        int cost = 0;
        StringBuilder msg = new StringBuilder("Your Order:\n");

        for (int i = 0; i < fields.length; i++) {
            if (checkBoxes[i].isSelected()) {
                int quantity;
                try {
                    quantity = Math.min(10, Integer.parseInt(fields[i].getText().isEmpty() ? "0" : fields[i].getText()));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(f1, "Please enter a valid number for " + items[i], "Input Error", JOptionPane.ERROR_MESSAGE);
                    return "";
                }
                if (quantity > 0) {
                    cost += prices[i] * quantity;
                    msg.append(items[i]).append(" x ").append(quantity).append(" = ").append(prices[i] * quantity).append("/-\n");
                }
            }
        }

        if (cost > 0) {
            int gst = (cost * 18) / 100;
            int total = cost + gst;
            msg.append("\nSubtotal: ").append(cost).append("/-\nGST (18%): ").append(gst).append("/-\nTotal: ").append(total).append("/-");
            return msg.toString();
        } else {
            return "No items selected.";
        }
    }

    private void resetFieldsAndCheckBoxes() {
        JCheckBox[] checkBoxes = {cbPaneer, cbPulav, cbChicken, cbMutton, cbFish};
        JTextField[] fields = {tfPaneerQty, tfPulavQty, tfChickenQty, tfMuttonQty, tfFishQty};

        for (JTextField field : fields) {
            field.setText("");
        }
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FoodCornerM();
            }
        });
    }
}
 
