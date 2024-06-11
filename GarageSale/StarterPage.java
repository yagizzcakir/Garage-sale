import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarterPage extends JFrame implements ActionListener {

    JButton signInButton, logInButton;

    StarterPage() {

        signInButton = new JButton();
        signInButton.setBounds(100, 100, 600, 150);
        signInButton.addActionListener(this);
        signInButton.setText("SIGN IN");
        signInButton.setHorizontalTextPosition(JButton.CENTER);
        signInButton.setVerticalTextPosition(JButton.CENTER);
        signInButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        signInButton.setFocusable(false);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(new Color(10, 47, 41));

        logInButton = new JButton();
        logInButton.setBounds(100, 300, 600, 150);
        logInButton.addActionListener(this);
        logInButton.setText("LOG IN");
        logInButton.setHorizontalTextPosition(JButton.CENTER);
        logInButton.setVerticalTextPosition(JButton.CENTER);
        logInButton.setFont(new Font("MV Boli", Font.BOLD, 40));
        logInButton.setFocusable(false);
        logInButton.setForeground(Color.WHITE);
        logInButton.setBackground(new Color(10, 47, 41));

        this.add(signInButton);
        this.add(logInButton);
        this.setTitle("Garage Sale");
        this.setLayout(null);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == signInButton) {
            new SignInPage();
            this.dispose();
        }

        else if (e.getSource() == logInButton) {
            new LogInPage();
            this.dispose();
        }

    }

}
