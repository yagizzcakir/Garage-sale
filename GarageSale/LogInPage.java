import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LogInPage extends JFrame implements ActionListener {

    JButton dealerLogInButton, customerLogInButton;
    GarageSaleDatabase GSDB = new GarageSaleDatabase();

    LogInPage() {

        dealerLogInButton = new JButton();
        dealerLogInButton.setBounds(100, 100, 600, 150);
        dealerLogInButton.addActionListener(this);
        dealerLogInButton.setText("For Dealers");
        dealerLogInButton.setHorizontalTextPosition(JButton.CENTER);
        dealerLogInButton.setVerticalTextPosition(JButton.CENTER);
        dealerLogInButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        dealerLogInButton.setFocusable(false);
        dealerLogInButton.setForeground(Color.WHITE);
        dealerLogInButton.setBackground(new Color(10, 47, 41));

        customerLogInButton = new JButton();
        customerLogInButton.setBounds(100, 300, 600, 150);
        customerLogInButton.addActionListener(this);
        customerLogInButton.setText("For Customers");
        customerLogInButton.setHorizontalTextPosition(JButton.CENTER);
        customerLogInButton.setVerticalTextPosition(JButton.CENTER);
        customerLogInButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        customerLogInButton.setFocusable(false);
        customerLogInButton.setForeground(Color.WHITE);
        customerLogInButton.setBackground(new Color(10, 47, 41));

        this.add(dealerLogInButton);
        this.add(customerLogInButton);
        this.setTitle("Garage Sale");
        this.setLayout(null);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dealerLogInButton) {
            this.dispose();
            new DealersPage();
        }

        else if (e.getSource() == customerLogInButton) {
            this.dispose();
            new CustomersPage();
        }

    }

}
