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
	// main method
	public static void main(String[] args) {
		boolean isRunning = true;// Controls if Continue running
		BankAccount account = null; //Hold the Bank Object
		String accountName =""; // 
		
		
		while (accountName.isEmpty()){ // check if account name is empty
			accountName = JOptionPane.showInputDialog(null, "--- Welcome to the Simple Bank System --- \n\n"
				+ "Enter Account Holder Name: "); // input
		}
		
		account = new BankAccount(accountName);//NEW ACCOUNT
		
		// Main Loop
		while (isRunning) { // Check if the program is still running
		String choice = JOptionPane.showInputDialog(null, "-----------------------------\n"
				+ "1. Check Balance \n"
				+ "2. Deposit Money \n"
				+ "3. Withdraw Money \n"
				+ "4. Exit \n\n"
				+ "Enter your choice (1-4):");
			
			switch(choice) {
			case"1":
				account.checkBalance(); // Displays balance dialog
				break;// Exits switch statement and returns to main loop
			case"2":
				account.deposit();// Triggers deposit process
				break;
			case"3":
				account.withdraw();// Triggers withdraw process
				break;
			case "4":
				JOptionPane.showMessageDialog(null, "Thank you for using the Bank System, " +account.accountHolder + " Goodbye!");
				isRunning = false; // Close the program Terminate while Loop
				break;
			default:
				JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
			}
		}
	}
}
