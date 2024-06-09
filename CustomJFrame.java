package project4;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;


/**
 * Class that creates a CustomJFrame object and extends functionality of the JFrame class.
 * The class utilizes swing elements to represent a graphical user interface that collects dietary information from the user's input.
 * Data is collected via the submit button and the form can be cleared by pressing the clear button. The inner action listener 
 * class enables button functionality. If submit is pressed, the data entered into the text fields, radio buttons, check boxes, combo box, and slider
 * is collected and printed to a csv file. If the clear button is pressed, the clearForm() class handles clearing the form 
 * and returning to the default window setting.
 */
@SuppressWarnings("serial")
public class CustomJFrame extends JFrame
{
	
	private JLabel headingLabel, firstNameLabel, lastNameLabel, phonenumberLabel, emailLabel, dietaryLabel, 
				   genderLabel, waterLabel, mealsLabel, checkBoxLabel, walkLabel, weightLabel;
	private JTextField firstNameTextField, lastNameTextField, phonenumberTextField, emailTextField;
	private JRadioButton maleRadioButton, femaleRadioButton, preferRadioButton;
	private ButtonGroup radioButtonGroup;
	private JSpinner waterIntakeSpinner;
	private JSlider mealSlider;
	private JCheckBox wheatCheckBox, sugarCheckBox, dairyCheckBox;
	private JComboBox <String>walkComboBox;
	private String[] walkOptions = 
	{"Less than 1 Mile", "More than 1 mile but less than 2 miles", "More than 2 miles but less than 3 miles", "More than 3 miles"};
	private JFormattedTextField weightFormattedTextField;
	private JButton clearButton, submitButton;
	private FileHandler fileHandler;
	
	
	/**
	 * creates the CustomJFrame object and sets up all components of the JFrame.
	 */
	public CustomJFrame()
	{
		//setting layoutManager, and initiating GridBagLayout and its constraints. Also initiating action listener.
		this.setLayout(new GridBagLayout());
		GridBagConstraints layoutManager = new GridBagConstraints();
		layoutManager.insets = new Insets(1,10,1,10);
		InnerActionListener listener = new InnerActionListener();
		
		//setting title of the GUI and initiating variables
		this.setTitle("Dietary Survey");
		headingLabel = new JLabel("Personal Information");
		firstNameLabel = new JLabel("First Name:");
		lastNameLabel = new JLabel("Last Name:");
		phonenumberLabel = new JLabel("Phone Number:");
		emailLabel = new JLabel ("Email:");
		firstNameTextField = new JTextField(20);
		lastNameTextField = new JTextField(20);
		phonenumberTextField = new JTextField(20);
		emailTextField = new JTextField(20);
		
		genderLabel = new JLabel ("Sex:");
		
		//initiating variables
		dietaryLabel = new JLabel("Dietary Questions");
		waterLabel = new JLabel("On average, how many cups of water do you drink in one day?");
		mealsLabel = new JLabel("On average, how many meals do you eat in one day?");
		checkBoxLabel = new JLabel("Do any of these meals regularly contain:");
		walkLabel = new JLabel("On average, how many miles do you walk in a day?");
		weightLabel = new JLabel("How much do you weigh?");
		
		//set the heading label
		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		this.add(headingLabel, layoutManager);
		
		//set the first name label and text field
		layoutManager.gridx = 0;
		layoutManager.gridy = 1;
		this.add(firstNameLabel, layoutManager);
		layoutManager.gridx = 1;
		layoutManager.gridy = 1;
		this.add(firstNameTextField, layoutManager);
		
		//set the last name label and text field
		layoutManager.gridx = 0;
		layoutManager.gridy = 2;
		this.add(lastNameLabel, layoutManager);
		layoutManager.gridx = 1;
		layoutManager.gridy = 2;
		this.add(lastNameTextField, layoutManager);
		
		//set phone number label and text field
		layoutManager.gridx = 0;
		layoutManager.gridy = 3;
		this.add(phonenumberLabel, layoutManager);
		layoutManager.gridx = 1;
		layoutManager.gridy = 3;
		this.add(phonenumberTextField, layoutManager);
		
		//set email label and text field
		layoutManager.gridx = 0;
		layoutManager.gridy = 4;
		this.add(emailLabel, layoutManager);
		layoutManager.gridx = 1;
		layoutManager.gridy = 4;
		this.add(emailTextField, layoutManager);
		
		//set gender text field and associated button group
		layoutManager.gridx = 0;
		layoutManager.gridy = 5;
		this.add(genderLabel, layoutManager);
		radioButtonGroup = new ButtonGroup();
		maleRadioButton = new JRadioButton("Male");
		femaleRadioButton = new JRadioButton("Female");
		preferRadioButton = new JRadioButton("Prefer not to say");
		radioButtonGroup.add(maleRadioButton);
		radioButtonGroup.add(femaleRadioButton);
		radioButtonGroup.add(preferRadioButton);
		//create vertical box to align the radio buttons
		Box box = Box.createVerticalBox();
		box.add(maleRadioButton);
		box.add(femaleRadioButton);
		box.add(preferRadioButton);
		//add the box to frame
		layoutManager.gridx = 1;
		layoutManager.gridy = 5;
		layoutManager.gridheight=2;
		layoutManager.anchor = GridBagConstraints.WEST;
		this.add(box, layoutManager);
		//set dietary label
		layoutManager.gridx = 0;
		layoutManager.gridy = 8;
		layoutManager.gridwidth = 2;
		this.add(dietaryLabel, layoutManager);
		
		//set water label and spinner
		layoutManager.gridx = 0;
		layoutManager.gridy = 10;
		layoutManager.gridwidth = 2;
		layoutManager.anchor = GridBagConstraints.CENTER;
		this.add(waterLabel, layoutManager);
		waterIntakeSpinner = new JSpinner(new SpinnerNumberModel(15, 0, 50, 1));//initial, minimum, max, step
		layoutManager.gridx = 0;
		layoutManager.gridy = 12;
		layoutManager.anchor = GridBagConstraints.CENTER;
		this.add(waterIntakeSpinner, layoutManager);
		
		//set meal label and slider
		layoutManager.gridx = 0;
		layoutManager.gridy = 14;
		layoutManager.anchor = GridBagConstraints.CENTER;
		this.add(mealsLabel, layoutManager);
		mealSlider = new JSlider(0, 10, 3);
		mealSlider.setMinorTickSpacing(1);
		mealSlider.setMajorTickSpacing(1);
		mealSlider.setPaintTicks(true);
		mealSlider.setPaintLabels(true);
		layoutManager.gridx = 0;
		layoutManager.gridy = 16;
		layoutManager.anchor = GridBagConstraints.CENTER;
		this.add(mealSlider, layoutManager);
		
		//set check box label, and check boxes 
		layoutManager.gridx = 0;
		layoutManager.gridy = 18;
		layoutManager.anchor = GridBagConstraints.CENTER;
		this.add(checkBoxLabel, layoutManager);
		//initialize check boxes, create a horizontal box and set it 
		wheatCheckBox = new JCheckBox("Dairy");
		sugarCheckBox = new JCheckBox("Wheat");
		dairyCheckBox = new JCheckBox("Sugar");
		Box checks = Box.createHorizontalBox();
		checks.add(wheatCheckBox);
		checks.add(dairyCheckBox);
		checks.add(sugarCheckBox);
		layoutManager.gridx = 0;
		layoutManager.gridy = 20;
		layoutManager.anchor = GridBagConstraints.CENTER;
		this.add(checks, layoutManager);
		
		//set walk label and combo box
		layoutManager.gridx = 0;
		layoutManager.gridy = 22;
		this.add(walkLabel, layoutManager);
		walkComboBox = new JComboBox<>(walkOptions);
		layoutManager.gridx = 0;
		layoutManager.gridy = 24;
		this.add(walkComboBox, layoutManager);
		
		
		//add weight label and formatted text box
		layoutManager.gridx = 0;
		layoutManager.gridy= 26;
		this.add(weightLabel, layoutManager);
		NumberFormatter nf = new NumberFormatter();
		weightFormattedTextField = new JFormattedTextField(nf);
		weightFormattedTextField.setColumns(10);
		layoutManager.gridx = 0;
		layoutManager.gridy = 28;
		this.add(weightFormattedTextField, layoutManager);
		
		//setting the clear and submit buttons, applying color to the buttons, and the action listeners
		clearButton = new JButton("Clear");
		clearButton.setName("Clear");
		clearButton.setBackground(Color.red);
		clearButton.setOpaque(true);
		clearButton.setBorderPainted(false);
		clearButton.addActionListener(listener);
		layoutManager.gridx = 0;
		layoutManager.gridy = 30;
		layoutManager.anchor = GridBagConstraints.WEST;
		this.add(clearButton, layoutManager);
		
		submitButton = new JButton("Submit");
		submitButton.setName("Submit");
		submitButton.setBackground(Color.green);
		submitButton.setOpaque(true);
		submitButton.setBorderPainted(false);
		submitButton.addActionListener(listener);
		layoutManager.gridx = 0;
		layoutManager.gridy = 30;
		layoutManager.anchor = GridBagConstraints.EAST;
		this.add(submitButton, layoutManager);
		
		
		
		//sets window visibility and exit on close of the window.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	
	/**
	 * inner class that enables button functionality
	 */
	private class InnerActionListener implements ActionListener
	{
		
		/**
		 * Handles action events associated with the clear and submit button. If clear button is pressed, the clearForm()
		 * method is invoked, and the GUI is returned to its default settings. If the submit button is pressed,
		 * the method creates a fileHandler object and data from all fields is collected, turned into strings, printed to
		 *  a csv file, and clearForm() is invoked.
		 * 
		 *@param e action listener event triggered by pressing either the clear or submit button
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton button = (JButton) e.getSource();
			String name = button.getName();
			
			if(name.equals("Submit"))
			{
				fileHandler = new FileHandler();
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String phone = phonenumberTextField.getText();
				String email = emailTextField.getText();
				String weight = weightFormattedTextField.getText();
				String selectedGender = getSelection();
				String walkIndex = walkComboBox.getSelectedItem().toString();
				boolean wheat = wheatCheckBox.isSelected();
				boolean dairy = dairyCheckBox.isSelected();
				boolean sugar = sugarCheckBox.isSelected();
				int meals = mealSlider.getValue();
				int water = (int) waterIntakeSpinner.getValue();
				fileHandler.writeResults(dateFormat() + "," + firstName + "," + lastName + "," + phone + "," + email + "," + selectedGender + "," 
							+ water + "," + meals + "," + wheat + "," + sugar + "," + dairy + "," + walkIndex + "," + weight);
			}
			
			clearForm();
		}
		
		/**
		 * Creates a string that contains the current date and time
		 * @return String containing current date and time
		 */
		private static String dateFormat() 
		{

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			Date date = new Date();

			return dateFormat.format(date);
		}
		
		/**
		 * supplemental method that generates a string which represents the corresponding check box that was chosen by the user
		 * @return String that represents which check box was selected in the "Sex" category
		 */
		private String getSelection()
		{
			if(maleRadioButton.isSelected())
			{
				return "Male";
			}
			else if(femaleRadioButton.isSelected())
			{
				return "Female";
			}
			else if(preferRadioButton.isSelected())
			{
				return "Prefer not to say";
			}
			
			return"no selection";
		}
		
		/**
		 * clears the GUI and returns it to its default settings.
		 */
		private void clearForm()
		{
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			phonenumberTextField.setText("");
			emailTextField.setText("");
			weightFormattedTextField.setText("");
			radioButtonGroup.clearSelection();
			walkComboBox.setSelectedIndex(0);
			wheatCheckBox.setSelected(false);
			dairyCheckBox.setSelected(false);
			sugarCheckBox.setSelected(false);
			mealSlider.setValue(3);
			waterIntakeSpinner.setValue(15);
			
		}
	}
}