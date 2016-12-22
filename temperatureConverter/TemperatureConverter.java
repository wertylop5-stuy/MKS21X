import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame 
	implements ActionListener {
	private static final String ACTION_CONVERT = "Convert";
	private static final String FAREN = "Fahrenheit";
	private static final String CELSIUS = "Celsius";
	
	private Container mPane;
	
	private JLabel mEntryLabel;
	private JLabel mResultLabel;
	
	private JTextField mEntry;
	
	private ButtonGroup mTemperatures;
	private JRadioButton mFahrenheit;
	private JRadioButton mCelsius;
	
	private JButton mConvertButton;
	
	public static double CtoF(double t) {
		return t*(1.8)+32;
	}
	
	public static double FtoC(double t) {
		return 5*(t - 32)/9;
	}
	
	public TemperatureConverter() {
		this.setTitle("Convert Temperature");
		this.setSize(600, 400);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mPane = this.getContentPane();
		mPane.setLayout(new GridLayout(4, 2));
		
		mEntryLabel = new JLabel("Enter temperature: ");
		mResultLabel = new JLabel("Result: ");
		
		mEntry = new JTextField();
		
		mTemperatures = new ButtonGroup();
		mFahrenheit = new JRadioButton("F");
		mFahrenheit.setActionCommand(FAREN);
		
		mCelsius = new JRadioButton("C");
		mCelsius.setActionCommand(CELSIUS);
		mTemperatures.add(mFahrenheit);
		mTemperatures.add(mCelsius);
		
		mConvertButton = new JButton("Convert");
		mConvertButton.setActionCommand(ACTION_CONVERT);
		mConvertButton.addActionListener(this);
		
		mPane.add(mEntryLabel);
		mPane.add(mEntry);
		mPane.add(mFahrenheit);
		mPane.add(mCelsius);
		mPane.add(mConvertButton);
		mPane.add(mResultLabel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ACTION_CONVERT)) {
			System.out.println("Converting");
			if (mTemperatures.getSelection()
				.getActionCommand().equals(FAREN)) {
				mResultLabel.setText("Result in Celsius: " + FtoC(
					Double.parseDouble( mEntry.getText() ) ));
			}
			else if (mTemperatures.getSelection()
				.getActionCommand().equals(CELSIUS)){
				mResultLabel.setText("Result in Fahrenheit: " + CtoF(
					Double.parseDouble( mEntry.getText() ) ));
			}
		}
	}
	
	public static void main(String[] args) {
		TemperatureConverter thing = new TemperatureConverter();
		thing.setVisible(true);
	}
}
