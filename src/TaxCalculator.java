import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Hashtable;

/**
 * This class displays a window with a slider component. The user can calculate
 * the cost of tax on a purchase from 0% to 10% by moving the slider.
 * @Author Josh Alcoba
 * 4/18/2016
 */

public class TaxCalculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField purchaseTextField; 
	private JTextField taxTextField; 
	private JTextField taxedPurchaseField;
	private JPanel panel_purchase;
	private JPanel panel_tax_rate;
	private JPanel panel_total;
	private JPanel sliderPanel;
	private JSlider slider;

	public TaxCalculator() {
		super("Scrollable Tax Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label1 = new JLabel("Enter your purchase amount: $");
		JLabel label2 = new JLabel("Tax rate ");
		JLabel label3 = new JLabel("Your amount with tax : $");

		purchaseTextField = new JTextField(10);
		purchaseTextField.setEditable(true);
		taxTextField = new JTextField("0.0", 10);
		taxTextField.setEditable(false);
		taxedPurchaseField = new JTextField(10);
		taxedPurchaseField.setEditable(true);

		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.addChangeListener(new SliderListener());

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(0, new JLabel("0%"));
		labelTable.put(10, new JLabel("1%"));
		labelTable.put(20, new JLabel("2%"));
		labelTable.put(30, new JLabel("3%"));
		labelTable.put(40, new JLabel("4%"));
		labelTable.put(50, new JLabel("5%"));
		labelTable.put(60, new JLabel("6%"));
		labelTable.put(70, new JLabel("7%"));
		labelTable.put(80, new JLabel("8%"));
		labelTable.put(90, new JLabel("9%"));
		labelTable.put(100, new JLabel("10%"));
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);
		
		panel_purchase = new JPanel();
		panel_purchase.add(label1);
		panel_purchase.add(purchaseTextField);
		panel_tax_rate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_tax_rate.add(label3);
		panel_tax_rate.add(taxedPurchaseField);
		panel_total = new JPanel();
		panel_total.add(label2);
		panel_total.add(taxTextField);
		sliderPanel = new JPanel();
		sliderPanel.add(slider);

		// Create a layout manager for the content pane.
		setLayout(new GridLayout(3, 1));

		// Add the panels to the content pane.
		add(panel_purchase);
		add(panel_tax_rate);
		add(panel_total);
		add(sliderPanel);

		// Pack and display the frame.
		pack();
		setVisible(true);
	}

	/**
	 * Private inner class to handle the change events that are generated when the
	 * slider is moved.
	 */

	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			double initialPurchase, taxedPurchase, taxRate;
			DecimalFormat fmt = new DecimalFormat("#,##0.00");

			initialPurchase = Double.parseDouble(purchaseTextField.getText());

			taxRate = slider.getValue();
			taxRate = taxRate / 1000;
			taxedPurchase = initialPurchase + (initialPurchase * taxRate);
			taxTextField.setText(Double.toString(taxRate));
			taxedPurchaseField.setText(fmt.format(taxedPurchase));

		}
	}

	/**
	 * The main method creates an instance of the TempConverter class.
	 */

	public static void main(String[] args) {
		new TaxCalculator();
	}
}