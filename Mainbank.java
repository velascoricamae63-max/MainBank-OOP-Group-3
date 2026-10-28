//Simple Bank System
// Group 3 

//RICA
//JOption Dialogue Boxes
import javax.swing.JOptionPane;

/ ===== NEW ADDITION: LESSON 12 - JAVA GUI =====
// These imports are used to create the graphical interface.
import javax.swing.JFrame;   // NEW: Creates the main window.
import javax.swing.JButton;  // NEW: Creates clickable buttons.
import javax.swing.JLabel;   // NEW: Displays text in the window.
import javax.swing.JPanel;   // NEW: Groups GUI components.
import java.awt.GridLayout;  // NEW: Arranges components in rows and columns.

public class Mainbank {
	
	// Class Bank Account
	static class BankAccount{
		String accountHolder;
		double balance=0.0;
		
		// Constructor to initialize the account with the owner's name
		public BankAccount(String name) {
			this.accountHolder = name;
		}

		// NEW ADDITION: GETTER METHOD
        // Returns the current account holder's name.
        public String getAccountHolder() {
            return accountHolder;
        }

        // NEW ADDITION: SETTER METHOD
        // Updates the account holder's name.
        public void setAccountHolder(String name) {
            this.accountHolder = name;
        }

        // NEW ADDITION: UPDATE ACCOUNT HOLDER FEATURE
        // Allows the user to change the account holder's name.
        public void changeAccountHolder() {
			 // Asks the user to enter a new account holder's name.
            String newName = JOptionPane.showInputDialog(
                null,
                "Current Account Holder: " + getAccountHolder()
                + "\nEnter new account holder name:",
                "Update Account Name",
                JOptionPane.QUESTION_MESSAGE
            );

            // Stops if the user clicks Cancel.
            if (newName == null) {
                return;
            }

            // Removes spaces before and after the entered name.
            newName = newName.trim();

            // Checks whether the entered name is empty.
            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Name cannot be empty.",
                    "Invalid Name",
                    JOptionPane.ERROR_MESSAGE
                );

                // Stops because the entered name is invalid.
                return;
            }

            // Saves the new name using the setter method.
            setAccountHolder(newName);

            // Confirms that the name was updated successfully.
            JOptionPane.showMessageDialog(
                null,
                "Account holder name successfully updated to: "
                + getAccountHolder(),
                "Update Successful",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

		
		// Method to check balance
		public void checkBalance(){
			JOptionPane.showMessageDialog(null, "Balance: $" + balance);
		}

		//Elgen Balila
        // Method to catch invalid inputs
        public static boolean isValid(String str) {
            if (str == null || str.trim().isEmpty()) { // Checks for null and empty inputs
                return false; // returns false
            }
            int decimal = 0; // variable to check for decimal points
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i); // Assigns the current string element into a usable char
                if (c == '.') {
                    decimal++; //add one to the decimal counter if it passess a period
                        if (decimal > 1) { // Checks if there are more than 1 decimal points
                            return false; // returns false as 2 decimal points is invalid
                        }
                } else if (!Character.isDigit(c)) { // Checks if the current character is not a number
                    return false; // returns false
                }
            }
            return true; // returns true if it passes all checks
        }
		
		//George Remoral
		// Method to deposit money
		public void deposit(){
			String amount = JOptionPane.showInputDialog(null, "Enter amount to deposit:", "Deposit", JOptionPane.QUESTION_MESSAGE);
			
            if (amount != null) { // // Check if the input is not null 
                if (isValid(amount)) { // Call the isValid method to check validity of input
                    double deposit = Double.parseDouble(amount); 
                    
                    if (deposit > 0) { // Check if the deposit is greater than 0
                        this.balance += deposit; // Add the deposit to the balance
                        JOptionPane.showMessageDialog(null, "Successfully deposited $" + deposit +"\nNew Balance: $" + this.balance);
                        
                    }else { // If deposit is less than or equal to 0
                        JOptionPane.showMessageDialog(null, "Deposit amount must be greater than zero.");
                    }
                } else {
                    // Display a clear error message for the user
                    JOptionPane.showMessageDialog(null, "Invalid input. Please try again."); 
					this.deposit();
                }
            }
		}
		
		//Mike Pangramuyen & Kaye
		// Method to withdraw money
		public void withdraw() {
			String amount = JOptionPane.showInputDialog(null, "Enter amount to withdraw:", "Deposit", JOptionPane.QUESTION_MESSAGE);
			
			if (amount != null) { // Check if amount is not null and not empty
                if (isValid(amount)) { // Calls isValid method to check validity of input
                    double withdraw = Double.parseDouble(amount);
                    
                    if (withdraw <= 0) { // Check if withdraw is less than or equal to 0
                        JOptionPane.showMessageDialog(null, "Withdraw amount must be greater than zero.");
                        
                    }else if (withdraw > balance) { // Check if withdraw is greater than balance
                        JOptionPane.showMessageDialog(null, "Insufficient funds! Current balance: $" + balance);
                        
                    }else {
                        this.balance -= withdraw; // Subtract the withdrawal from the balance
                        JOptionPane.showMessageDialog(null, "Successfully withdraw $" + withdraw +"\nRemaining Balance: $" + this.balance);
                    }
                } else {
                    // Display a clear error message for the userm
                    JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
					this.withdraw();
                }
			}
			
		}
	}

	//	RICA
// MAIN METHOD: The program starts running here.
    public static void main(String[] args) {

        // Stores the account holder's name.
        String accountName = "";
        while (accountName.trim().isEmpty()) {  // Keeps asking until the user enters a non-empty name.

            // Displays the welcome message and asks for the account name.
            String input = JOptionPane.showInputDialog(
                null,
                "--- Welcome to the Simple Bank System ---"
                + "\n\nEnter Account Holder Name:",
                "Welcome",
                JOptionPane.QUESTION_MESSAGE
            );

            // Ends the program if the user clicks Cancel.
            if (input == null) {
                return;
            }

            // Removes unnecessary spaces from the entered name.
            accountName = input.trim();

            // Checks whether the name is empty.
            if (accountName.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Name cannot be empty. Please enter your name."
                );
            }
        }

        // Creates a bank account using the entered name.
        BankAccount account = new BankAccount(accountName);

        // ===== NEW ADDITION: JAVA GUI - JFrame =====
        // Creates the main application window.
        JFrame frame = new JFrame("Simple Bank System");

        // Sets the window width to 350 and height to 330 pixels.
        frame.setSize(350, 330);

        // Closes the application when the window is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centers the window on the screen.
        frame.setLocationRelativeTo(null);

        // ===== NEW ADDITION: JAVA GUI - JLabel =====
        // Displays the system title and account holder's name.
        JLabel title = new JLabel(
            "Simple Bank System - " + account.getAccountHolder(),
            JLabel.CENTER
        );

        // ===== NEW ADDITION: JAVA GUI - JPanel =====
        // Creates a panel to hold and organize the buttons.
        JPanel panel = new JPanel();

        // ===== NEW ADDITION: LAYOUT MANAGER - GridLayout =====
        // Arranges five buttons in one column with spacing between them.
        panel.setLayout(new GridLayout(5, 1, 8, 8));

        // ===== NEW ADDITION: JAVA GUI - JButton =====
        // Creates the Check Balance button.
        JButton checkButton = new JButton("Check Balance");

        // Creates the Deposit Money button.
        JButton depositButton = new JButton("Deposit Money");

        // Creates the Withdraw Money button.
        JButton withdrawButton = new JButton("Withdraw Money");

        // Creates the button for changing the account holder's name.
        JButton updateNameButton =
            new JButton("Update Account Holder Name");

        // Creates the Exit button.
        JButton exitButton = new JButton("Exit");

        // ===== NEW ADDITION: EVENT HANDLING =====
        // addActionListener() responds when the user clicks a button.

        // Calls checkBalance() when Check Balance is clicked.
        checkButton.addActionListener(e -> account.checkBalance());

        // Calls deposit() when Deposit Money is clicked.
        depositButton.addActionListener(e -> account.deposit());

        // Calls withdraw() when Withdraw Money is clicked.
        withdrawButton.addActionListener(e -> account.withdraw());

        // NEW ADDITION: Handles the account name update button.
        updateNameButton.addActionListener(e -> {

            // Opens the method that asks for a new account holder's name.
            account.changeAccountHolder();

            // Refreshes the title using the current account holder's name.
            title.setText(
                "Simple Bank System - " + account.getAccountHolder()
            );
        });

        // NEW ADDITION: Handles the Exit button click.
        exitButton.addActionListener(e -> {

            // Displays a goodbye message before closing the application.
            JOptionPane.showMessageDialog(
                frame,
                "Thank you for using the Bank System, "
                + account.getAccountHolder() + "!\nGoodbye!",
                "Thank You",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Closes the main window.
            frame.dispose();
        });

        // ===== NEW ADDITION: ADDING COMPONENTS TO THE GUI =====

        // Adds the Check Balance button to the panel.
        panel.add(checkButton);

        // Adds the Deposit Money button to the panel.
        panel.add(depositButton);

        // Adds the Withdraw Money button to the panel.
        panel.add(withdrawButton);

        // Adds the Update Account Holder Name button to the panel.
        panel.add(updateNameButton);

        // Adds the Exit button to the panel.
        panel.add(exitButton);

        // Places the title at the top of the window.
        frame.add(title, "North");

        // Places the panel containing the buttons in the center.
        frame.add(panel, "Center");

        // Displays the completed GUI window.
        frame.setVisible(true);
    }
}
