import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SignInPage extends JFrame implements ActionListener {

    JButton dealerSignInButton, customerSignInButton;
    GarageSaleDatabase GSDB = new GarageSaleDatabase();

    SignInPage() {

        dealerSignInButton = new JButton();
        dealerSignInButton.setBounds(100, 100, 600, 150);
        dealerSignInButton.addActionListener(this);
        dealerSignInButton.setText("For Dealers");
        dealerSignInButton.setHorizontalTextPosition(JButton.CENTER);
        dealerSignInButton.setVerticalTextPosition(JButton.CENTER);
        dealerSignInButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        dealerSignInButton.setFocusable(false);
        dealerSignInButton.setForeground(Color.WHITE);
        dealerSignInButton.setBackground(new Color(10, 47, 41));

        customerSignInButton = new JButton();
        customerSignInButton.setBounds(100, 300, 600, 150);
        customerSignInButton.addActionListener(this);
        customerSignInButton.setText("For Customers");
        customerSignInButton.setHorizontalTextPosition(JButton.CENTER);
        customerSignInButton.setVerticalTextPosition(JButton.CENTER);
        customerSignInButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        customerSignInButton.setFocusable(false);
        customerSignInButton.setForeground(Color.WHITE);
        customerSignInButton.setBackground(new Color(10, 47, 41));

        this.add(dealerSignInButton);
        this.add(customerSignInButton);
        this.setTitle("Garage Sale");
        this.setLayout(null);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dealerSignInButton) {
            String first_name, last_name, phone_number;
            String[] showroomIDStrings = { "1", "2", "3"};

            first_name = JOptionPane.showInputDialog("Enter your first name: ");
            last_name = JOptionPane.showInputDialog("Enter your last name: ");
            phone_number = JOptionPane.showInputDialog("Enter your phone number: ");

            int showroomID = JOptionPane.showOptionDialog(null, "Choose a showroom ID: ", "SignIn",
                    JOptionPane.YES_NO_CANCEL_OPTION, 0, null, showroomIDStrings, null);

            GSDB.insertIntoDealers(first_name, last_name, phone_number, showroomID);

            this.dispose();
            new DealersPage();
        }

        else if (e.getSource() == customerSignInButton) {
            String first_name, last_name, phone_number, email, address, gender, ageString;

            first_name = JOptionPane.showInputDialog("Enter your first name: ");
            last_name = JOptionPane.showInputDialog("Enter your last name: ");
            phone_number = JOptionPane.showInputDialog("Enter your phone number: ");
            email = JOptionPane.showInputDialog("Enter your email: ");
            address = JOptionPane.showInputDialog("Enter your address: ");
            gender = JOptionPane.showInputDialog("Enter your gender: ");
            ageString = JOptionPane.showInputDialog("Enter your age: ");

            int age = Integer.parseInt(ageString);

            GSDB.insertIntoCustomers(first_name, last_name, phone_number, email, address,
                    gender.substring(0, 1).toUpperCase(), age);

            this.dispose();
            new CustomersPage();
        }

    }

}
