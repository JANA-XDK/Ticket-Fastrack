import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.w3c.dom.events.MouseEvent;

import javax.swing.*;



public class RailwayTicketBookingSystem extends JFrame {
    private JButton registerButton;
    private JButton userLoginButton;
    private JButton adminLoginButton;

    public RailwayTicketBookingSystem() {
        setTitle("Ticket FastTrack Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(33, 33, 33)); // Set a dark background color
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 33, 33));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      // Create the heading label
JLabel headingLabel = new JLabel("\u23EA Welcome to Ticket FastTrack \u23E9");
headingLabel.setForeground(Color.WHITE);
headingLabel.setFont(new Font("Arial", Font.BOLD, 25));

// Timer to animate the opacity
Timer opacityTimer = new Timer(100, new ActionListener() {
    private float opacity = 1.0f;
    private float delta = -0.05f;

    @Override
    public void actionPerformed(ActionEvent e) {
        opacity += delta;
        if (opacity <= 0.5f || opacity >= 1.0f) {
            delta *= -1;
        }
        headingLabel.setForeground(new Color(0, 255, 255, Math.round(opacity * 255)));
    }
});
opacityTimer.start();

// Add the heading label to the header panel
headerPanel.add(headingLabel);


        // Create the marquee panel
        JPanel marqueePanel = new JPanel(new GridBagLayout());
        marqueePanel.setBackground(new Color(33, 33, 33));
        marqueePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the marquee label
        JLabel marqueeLabel = new JLabel("Train services will resume from tomorrow. Please check for updated schedules. ");
        marqueeLabel.setForeground(Color.BLUE);
        marqueeLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        // Create a timer to update the marquee label text
        Timer marqueeTimer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = marqueeLabel.getText();
                marqueeLabel.setText(text.substring(1) + text.charAt(0));
            }
        });
        marqueeTimer.start();

        // Add the marquee label to the marquee panel
        marqueePanel.add(marqueeLabel);

        // Add the header panel and marquee panel to the main panel
         mainPanel.add(headerPanel, BorderLayout.NORTH);
         mainPanel.add(marqueePanel, BorderLayout.CENTER);

        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(marqueePanel);
        mainPanel.add(Box.createVerticalGlue());


        // Create the marquee panel
JPanel middlePanel = new JPanel(new GridBagLayout());
middlePanel.setBackground(new Color(33, 33, 33));
middlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

// Create a list of facilities
List<String> facilities = new ArrayList<>();
facilities.add("Book Trains");
facilities.add("Check Availability");
facilities.add("View Bookings");

// Create the marquee label
JLabel marqueeLabell = new JLabel();
marqueeLabell.setForeground(Color.WHITE);
marqueeLabell.setFont(new Font("Arial", Font.PLAIN, 25));

// Create a timer to update the marquee label text and color
Timer timer = new Timer(1000, new ActionListener() {
    int index = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        String facility = facilities.get(index);
        marqueeLabell.setText(facility);
        marqueeLabell.setForeground(getRandomColor());
        index = (index + 1) % facilities.size();
    }
});
timer.start();

// Create a wrapper panel for centering
JPanel centerWrapperPanel = new JPanel(new GridBagLayout());
centerWrapperPanel.setBackground(new Color(33, 33, 33));
centerWrapperPanel.add(marqueeLabell);

// Add the wrapper panel to the marquee panel
middlePanel.add(centerWrapperPanel);

// Add the marquee panel to the main panel
mainPanel.add(middlePanel, BorderLayout.CENTER);



         // Additional features panel
        JPanel additionalFeaturesPanel = new JPanel(new BorderLayout());
        additionalFeaturesPanel.setBackground(new Color(33, 33, 33));

       
JLabel customMessageLabel = new JLabel("About FastTrack: We provide fast and reliable train services for your travel needs.");
        customMessageLabel.setForeground(Color.WHITE);
        customMessageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        additionalFeaturesPanel.add(customMessageLabel, BorderLayout.NORTH);
        
        // Create a wrapper panel for centering
        JPanel centreWrapperPanel = new JPanel(new GridBagLayout());
        centreWrapperPanel.setBackground(new Color(33, 33, 33));
        centreWrapperPanel.add(additionalFeaturesPanel);
        
        // Add the wrapper panel to the main panel
        mainPanel.add(centreWrapperPanel, BorderLayout.CENTER);

       

        // Create the button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(new Color(33, 33, 33));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and customize the buttons
        registerButton = createButton("User Registration", new Color(0, 102, 204), Color.WHITE, Color.WHITE);
        userLoginButton = createButton("User Login", new Color(0, 153, 51), Color.WHITE, Color.WHITE);
        adminLoginButton = createButton("Admin Login", new Color(204, 51, 0), Color.WHITE, Color.WHITE);

        // Add the buttons to the button panel
        buttonPanel.add(registerButton);
        buttonPanel.add(userLoginButton);
        buttonPanel.add(adminLoginButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
         // Set the main panel as the content pane of the frame
        setContentPane(mainPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserRegistration();
            }
        });

        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserLogin();
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAdminLogin();
            }
        });

        setVisible(true);
    }

        

    // Helper method to create custom-styled buttons
    private JButton createButton(String text, Color backgroundColor, Color textColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Apply hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }
    // Helper method to generate a random color
    private Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }



    private void handleUserRegistration() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        final JDialog dialog = new JDialog(this, "User Registration", true);
    
        JPanel registrationPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        registrationPanel.setBackground(new Color(33, 33, 33)); // Set a dark background color
        registrationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        Font fieldFont = new Font("Arial", Font.PLAIN, 16); // Set the desired font
    
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(fieldFont);
        usernameLabel.setForeground(Color.WHITE); // Set text color to white
        registrationPanel.add(usernameLabel);
    
        usernameField.setFont(fieldFont);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Set a white border for the text field
        registrationPanel.add(usernameField);
    
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(fieldFont);
        passwordLabel.setForeground(Color.WHITE); // Set text color to white
        registrationPanel.add(passwordLabel);
    
        passwordField.setFont(fieldFont);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Set a white border for the password field
        registrationPanel.add(passwordField);
    
        JButton signupButton = new JButton("Signup");
        signupButton.setFont(fieldFont);
        signupButton.setForeground(Color.WHITE); // Set text color to white
        signupButton.setBackground(new Color(66, 139, 202)); // Set a custom background color
        signupButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button
        signupButton.setFocusPainted(false); // Remove the focus border
    
        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(46, 116, 179)); // Set the hover background color
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(66, 139, 202)); // Set the default background color
            }
    
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
    
                if (username.isEmpty() || password.isEmpty()) {
                    showCustomMessageDialog("Username and password are required.", "Error");
                    return;
                }
    
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                    writer.write(username + ":" + password);
                    writer.newLine();
                    showCustomMessageDialog("User registered successfully!!\n\nUsername: " + username, "Registration Success");
                } catch (IOException ex) {
                    showCustomMessageDialog("Error occurred while saving user details.", "Error");
                }
            }
        });
    
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(fieldFont);
        cancelButton.setForeground(Color.WHITE); // Set text color to white
        cancelButton.setBackground(Color.RED); // Set a custom background color
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button
        cancelButton.setFocusPainted(false); // Remove the focus border
        cancelButton.addActionListener(e -> {
            Container dialog1 = cancelButton.getParent();
            do {
                dialog1 = dialog1.getParent();
            } while (!(dialog1 instanceof JDialog));
            ((JDialog) dialog1).dispose();
        });
    
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(205, 92, 92)); // Set the hover background color
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(Color.RED); // Set the default background color
            }
    
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dialog.dispose();
            }

        });
    
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setOpaque(false); // Set the button panel to be transparent
        buttonPanel.add(signupButton);
        buttonPanel.add(cancelButton);
    
        registrationPanel.add(new JLabel()); // Add an empty label for spacing
        registrationPanel.add(buttonPanel); // Add the button panel to the registration panel
    
        int option = JOptionPane.showOptionDialog(this, registrationPanel, "User Registration", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    
        if (option == JOptionPane.OK_OPTION) {
            dialog.dispose();
        }
    }
    
    private void showCustomMessageDialog(String message, String title) {
        JLabel messageLabel = new JLabel("<html><center>" + message.replaceAll("\n", "<br>") + "</center></html>");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBackground(Color.DARK_GRAY);
        messageLabel.setOpaque(true);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER); 
    
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("OptionPane.okButtonBackground", new Color(66, 139, 202));
        UIManager.put("OptionPane.okButtonHoverBackground", new Color(255, 165, 0));
    
        JButton thanksButton = new JButton("Thanks for Registering");
        thanksButton.setFont(new Font("Arial", Font.PLAIN, 14));
        thanksButton.setForeground(Color.WHITE);
        thanksButton.setBackground(new Color(66, 139, 202));
        thanksButton.setOpaque(true);
        thanksButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        thanksButton.setFocusPainted(false);
        thanksButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                thanksButton.setBackground(new Color(255, 165, 0)); // Set the hover background color
            }
    
            public void mouseExited(MouseEvent evt) {
                thanksButton.setBackground(new Color(66, 139, 202)); // Set the default background color
            }
        });
        thanksButton.addActionListener(e -> {
            Container dialog = thanksButton.getParent();
            do {
                dialog = dialog.getParent();
            } while (!(dialog instanceof JDialog));
            ((JDialog) dialog).dispose();
        });
    
        Object[] options = {thanksButton};
    
        JOptionPane.showOptionDialog(
                this,
                messageLabel,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
    }


 

    private void handleUserLogin() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
    
        final JDialog dialog = new JDialog(this, "User Login", true);
    
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBackground(new Color(33, 33, 33)); // Set a dark background color
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        Font fieldFont = new Font("Arial", Font.PLAIN, 16); // Set the desired font
    
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(fieldFont);
        usernameLabel.setForeground(Color.WHITE); // Set text color to white
        loginPanel.add(usernameLabel);
    
        usernameField.setFont(fieldFont);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Set a white border for the text field
        loginPanel.add(usernameField);
    
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(fieldFont);
        passwordLabel.setForeground(Color.WHITE); // Set text color to white
        loginPanel.add(passwordLabel);
    
        passwordField.setFont(fieldFont);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Set a white border for the password field
        loginPanel.add(passwordField);
    
        JButton loginButton = new JButton("Login");
        loginButton.setFont(fieldFont);
        loginButton.setForeground(Color.WHITE); // Set text color to white
        loginButton.setBackground(new Color(66, 139, 202)); // Set a custom background color
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button
        loginButton.setFocusPainted(false); // Remove the focus border
    
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (!username.isEmpty() && !password.isEmpty()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
                    String line;
                    boolean isValidUser = false;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                            isValidUser = true;
                            break;
                        }
                    }
                    reader.close();
    
                    if (isValidUser) {
                        JOptionPane.showMessageDialog(dialog, "User login successful!\nUsername: " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                        showUserOptionsDialog();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Account Doesnot Exist, Please Create One and Try again Later!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error occurred while loading user details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Username and password are required.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(fieldFont);
        cancelButton.setForeground(Color.WHITE); // Set text color to white
        cancelButton.setBackground(Color.RED); // Set a custom background color
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button
        cancelButton.setFocusPainted(false); // Remove the focus border
        cancelButton.addActionListener(e -> dialog.dispose());
    
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setOpaque(false); // Set the button panel to be transparent
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
    
        loginPanel.add(new JLabel()); // Add an empty label for spacing
        loginPanel.add(buttonPanel); // Add the button panel to the registration panel
    
        dialog.getContentPane().add(loginPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    

private void handleAdminLogin() {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    final JDialog dialog = new JDialog(this, "Admin Login", true);

    JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    loginPanel.setBackground(new Color(33, 33, 33)); // Set a dark background color
    loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    Font fieldFont = new Font("Arial", Font.PLAIN, 16); // Set the desired font

    JLabel usernameLabel = new JLabel("Username:");
    usernameLabel.setFont(fieldFont);
    usernameLabel.setForeground(Color.WHITE); // Set text color to white
    loginPanel.add(usernameLabel);

    usernameField.setFont(fieldFont);
    usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Set a white border for the text field
    loginPanel.add(usernameField);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setFont(fieldFont);
    passwordLabel.setForeground(Color.WHITE); // Set text color to white
    loginPanel.add(passwordLabel);

    passwordField.setFont(fieldFont);
    passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Set a white border for the password field
    loginPanel.add(passwordField);

    JButton loginButton = new JButton("Login");
    loginButton.setFont(fieldFont);
    loginButton.setForeground(Color.WHITE); // Set text color to white
    loginButton.setBackground(new Color(66, 139, 202)); // Set a custom background color
    loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button
    loginButton.setFocusPainted(false); // Remove the focus border
    loginButton.addActionListener(e -> {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!username.isEmpty() && !password.isEmpty()) {
            if (username.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(dialog, "Admin login successful!\nUsername: " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                showAdminOptionsDialog();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(dialog, "Username and password are required.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setFont(fieldFont);
    cancelButton.setForeground(Color.WHITE); // Set text color to white
    cancelButton.setBackground(Color.RED); // Set a custom background color
    cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to the button
    cancelButton.setFocusPainted(false); // Remove the focus border
    cancelButton.addActionListener(e -> dialog.dispose());

    JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    buttonPanel.setOpaque(false); // Set the button panel to be transparent
    buttonPanel.add(loginButton);
    buttonPanel.add(cancelButton);

    loginPanel.add(new JLabel()); // Add an empty label for spacing
    loginPanel.add(buttonPanel); // Add the button panel to the registration panel

    dialog.getContentPane().add(loginPanel);
    dialog.pack();
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
}



private void showAdminOptionsDialog() {
    String[] options = {"Add Train", "Delete Train", "Update Train", "Show Trains", "Logout"};

    // Create a custom panel to apply colors and icons
    JPanel panel = new JPanel();
    panel.setBackground(new Color(23, 23, 23));

    // Create a custom label with a colorful title
    JLabel titleLabel = new JLabel("Admin Options");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    titleLabel.setForeground(Color.WHITE);
    panel.add(titleLabel);

    // Customize the option pane appearance
    UIManager.put("OptionPane.background", new Color(150, 33, 44));
    UIManager.put("Panel.background", new Color(150, 33, 44));

    // Create a custom icon for the options
    ImageIcon icon = new ImageIcon("path/to/icon.png");

    int choice = JOptionPane.showOptionDialog(
            this,
            panel,
            "Admin Options",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            icon,
            options,
            options[0]
    );

    if (choice == 0) {
        handleAddTrain();
    } else if (choice == 1) {
        handleDeleteTrain();
    } else if (choice == 2) {
        handleUpdateTrain();
    } else if (choice == 3) {
        showTrains();
    } else if (choice == 4) {
        // Create a custom dialog with colorful text
        JLabel messageLabel = new JLabel("<html><font color='#00008B'><strong>Please Wait Closing Application...</strong></font></html>");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(
                this,
                messageLabel,
                "Admin Options",
                JOptionPane.INFORMATION_MESSAGE
        );
        System.exit(0);
    }
}

private JButton createButton(String text, Color backgroundColor, Color textColor) {
    JButton button = new JButton(text);
    button.setBackground(backgroundColor);
    button.setForeground(textColor);
    button.setFont(new Font("Arial", Font.BOLD, 16));
    button.setFocusPainted(false);
    button.setPreferredSize(new Dimension(150, 50)); // Set button size
    button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding

    return button;
}

// Inside the method where the buttons are created:
JButton addButton = createButton("Add Train", Color.RED, Color.WHITE);
JButton deleteButton = createButton("Delete Train", Color.BLUE, Color.WHITE);
JButton updateButton = createButton("Update Train", Color.GREEN, Color.WHITE);
JButton showButton = createButton("Show Trains", Color.RED, Color.WHITE);
JButton logoutButton = createButton("Logout", Color.RED, Color.WHITE);





private void showTrains() {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("trainlist.txt"));
        String line;
        StringBuilder sb = new StringBuilder();
        sb.append("<html><h2>Available Trains:</h2><ul>");
        
        // Define an array of colors with red, blue, and dark pink
        Color[] colors = {Color.RED, Color.BLUE, new Color(207, 20, 106)}; // RGB values for dark pink: (207, 20, 106)

        int colorIndex = 0;
        while ((line = reader.readLine()) != null) {
            // Assign a color to each line
            Color lineColor = colors[colorIndex % colors.length];
            
            // Format the line with the assigned color
            sb.append("<li><font color='").append(getHexColor(lineColor)).append("'>").append(line).append("</font></li>");

            colorIndex++;
        }
        sb.append("</ul></html>");
        reader.close();

        // Create a custom message label with colorful HTML-formatted text
        JLabel messageLabel = new JLabel(sb.toString());
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Customize the option pane appearance
        UIManager.put("OptionPane.background", Color.lightGray);
        UIManager.put("Panel.background", Color.lightGray);

        JOptionPane.showMessageDialog(
                this,
                messageLabel,
                "Trains",
                JOptionPane.INFORMATION_MESSAGE
        );
    } catch (IOException ex) {
        // Create a custom error dialog with colorful text
        JLabel errorLabel = new JLabel("<html><font color='#FF0000'>Error occurred while loading train details.</font></html>");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Customize the option pane appearance
        UIManager.put("OptionPane.background", Color.lightGray);
        UIManager.put("Panel.background", Color.lightGray);

        JOptionPane.showMessageDialog(
                this,
                errorLabel,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    showAdminOptionsDialog();
}

// Helper method to convert Color to hexadecimal representation
private String getHexColor(Color color) {
    return "#" + Integer.toHexString(color.getRGB()).substring(2);
}



private void showUserOptionsDialog() {
    String[] options = {"Book Train", "Show Available Trains", "Check Ticket Status", "Logout"};

    JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    optionsPanel.setBackground(new Color(211, 204, 204)); // Set a light pink background color

    Color[] buttonColors = {new Color(178, 153, 204), new Color(153, 204, 153), new Color(255, 204, 153), new Color(204, 153, 153)};

    for (int i = 0; i < options.length; i++) {
        String option = options[i];
        Color buttonColor = buttonColors[i];

        JButton button = createButton(option, buttonColor, Color.WHITE);
        optionsPanel.add(button);
        button.addActionListener(e -> handleUserOption(option));
    }

    JDialog dialog = new JDialog();
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setTitle("User Options");
    dialog.setModal(true);
    dialog.setContentPane(optionsPanel);
    dialog.pack();
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
}

private void handleUserOption(String option) {
    if (option.equals("Book Train")) {
            handleBookTrain();
    } else if (option.equals("Show Available Trains")) {
        showAvailableTrains();
    } else if (option.equals("Check Ticket Status")) {
        handleCheckTicketStatus();
    } else if (option.equals("Logout")) {
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(140, 40, 240)); // Set a custom background color

        JLabel messageLabel = new JLabel("Do you want to exit the application...");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        messageLabel.setForeground(Color.RED);
        messagePanel.add(messageLabel);

        JOptionPane.showMessageDialog(this, messagePanel, "User Options", JOptionPane.INFORMATION_MESSAGE);
        handleUserLogin();
        System.exit(0);
       
    }
}




private void handleCheckTicketStatus() {
    JPanel ticketStatusPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    JTextField ticketIdField = new JTextField();
    ticketStatusPanel.add(new JLabel("Enter Ticket ID:"));
    ticketStatusPanel.add(ticketIdField);

    int option = JOptionPane.showConfirmDialog(this, ticketStatusPanel, "Check Ticket Status", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        String ticketId = ticketIdField.getText();
        if (!ticketId.isEmpty()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("passenger.txt"));
                String line;
                boolean isTicketFound = false;
                StringBuilder ticketDetails = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Ticket ID: " + ticketId)) {
                        isTicketFound = true;
                        ticketDetails.append(line).append("\n");
                        while ((line = reader.readLine()) != null && !line.isEmpty()) {
                            ticketDetails.append(line).append("\n");
                            
                        }
                        ticketDetails.append(line).append("Status: Confirmed");
                        break;
                    }
                }
                reader.close();

                if (isTicketFound) {
                    int cancelOption = JOptionPane.showOptionDialog(this, ticketDetails.toString(), "Ticket Status", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Cancel Ticket", "Exit"}, "OK");
                    if (cancelOption == 0) {
                        int confirmCancelOption = JOptionPane.showConfirmDialog(this, "Note: Cancel Ticket only in Emergency Situations. No Refunds will be Initiated..\nAre you sure you want to cancel your ticket?", "Cancel Ticket Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (confirmCancelOption == JOptionPane.YES_OPTION) {
                            deleteTicketFromBookings(ticketId);
                            JOptionPane.showMessageDialog(this, "Ticket canceled successfully.", "Ticket Canceled", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ticket ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error occurred while loading booking details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ticket ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    showUserOptionsDialog();
}

private void deleteTicketFromBookings(String ticketId) {
    try {
        File inputFile = new File("passenger.txt");
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean isTicketFound = false;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Ticket ID: " + ticketId)) {
                isTicketFound = true;
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    // Skip the lines related to this ticket
                }
            } else {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        if (isTicketFound) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                // Perform any additional operations if required
            } else {
                JOptionPane.showMessageDialog(this, "Error occurred while canceling the ticket.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ticket with the specified Ticket ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error occurred while canceling the ticket.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}




private void handleBookTrain() {
    // Create the dialog panel for booking train
    JPanel bookTrainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 10, 15, 10);

    JComboBox<String> passengerCountComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
    JTextField[] passengerNameFields = new JTextField[5]; // Assuming maximum 5 passengers

    ButtonGroup genderGroup = new ButtonGroup();
    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    genderGroup.add(maleRadioButton);
    genderGroup.add(femaleRadioButton);
    JPanel genderPanel = new JPanel();
    genderPanel.add(maleRadioButton);
    genderPanel.add(femaleRadioButton);

    JComboBox<String> sourceComboBox = new JComboBox<>(getStates());
    JComboBox<String> destinationComboBox = new JComboBox<>(getStates());
    JComboBox<String> boardingStationComboBox = new JComboBox<>();
    JComboBox<String> seatTypeComboBox = new JComboBox<>(getSeat());

    passengerCountComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int passengerCount = Integer.parseInt(passengerCountComboBox.getSelectedItem().toString());

            // Clear existing passenger name fields
            for (int i = 0; i < passengerNameFields.length; i++) {
                if (passengerNameFields[i] != null) {
                    bookTrainPanel.remove(passengerNameFields[i]);
                }
            }

            // Add new passenger name fields
            for (int i = 0; i < passengerCount; i++) {
                passengerNameFields[i] = new JTextField();
                gbc.gridx = 0;
                gbc.gridy = i + 7;
                bookTrainPanel.add(new JLabel("Passenger Name:"), gbc);
                gbc.gridx = 1;
                gbc.gridy = i + 7;
                gbc.gridheight = 1; // Set grid height to 1
                gbc.weightx = 1.0; // Make the text field expand horizontally
                gbc.fill = GridBagConstraints.HORIZONTAL; // Allow the text field to fill the available space
                bookTrainPanel.add(passengerNameFields[i], gbc);
            }

            bookTrainPanel.revalidate();
            bookTrainPanel.repaint();
        }
    });

    sourceComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Update boarding station options based on the selected source
            updateBoardingStations(sourceComboBox.getSelectedItem().toString(), boardingStationComboBox);
        }
    });

    gbc.gridx = 0;
    gbc.gridy = 0;
    bookTrainPanel.add(new JLabel("No. of Passengers:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    bookTrainPanel.add(passengerCountComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    bookTrainPanel.add(new JLabel("Gender:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    bookTrainPanel.add(genderPanel, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    bookTrainPanel.add(new JLabel("Source:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    bookTrainPanel.add(sourceComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    bookTrainPanel.add(new JLabel("Destination:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 3;
    bookTrainPanel.add(destinationComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    bookTrainPanel.add(new JLabel("Boarding Station:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 4;
    bookTrainPanel.add(boardingStationComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    bookTrainPanel.add(new JLabel("Seat Type:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 5;
    bookTrainPanel.add(seatTypeComboBox, gbc);

    JXDatePicker datePicker = new JXDatePicker();
    datePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

    // Disable all previous dates
    Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 0);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);
    today.set(Calendar.MILLISECOND, 0);

    datePicker.getMonthView().setSelectionDate(today.getTime());
    datePicker.getMonthView().setSelectionMode(SelectionMode.SINGLE_SELECTION);

    JXMonthView monthView = datePicker.getMonthView();
    monthView.setDayForeground(Calendar.SUNDAY, Color.RED); // Customize the appearance of Sundays
    monthView.setDayForeground(Calendar.SATURDAY, Color.BLUE);

    datePicker.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date selectedDate = datePicker.getDate();
            if (selectedDate != null && selectedDate.before(today.getTime())) {
                datePicker.setDate(null);
                JOptionPane.showMessageDialog(null, "Date must be between " + new SimpleDateFormat("dd-MM-yyyy").format(today.getTime()) + " and " + new SimpleDateFormat("dd-MM-yyyy").format(getOneWeekAheadDate(today.getTime())), "Invalid Date", JOptionPane.WARNING_MESSAGE);
            }
        }
    });

    gbc.gridx = 0;
    gbc.gridy = 6;
    bookTrainPanel.add(new JLabel("Date of Journey:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 6;
    bookTrainPanel.add(datePicker, gbc);

    // Create the dialog box for booking train
    JScrollPane scrollPane = new JScrollPane(bookTrainPanel);
    scrollPane.setPreferredSize(new Dimension(400, 500));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Show available trains button
    JButton showTrainsButton = new JButton("Show Available Trains");
    showTrainsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String source = sourceComboBox.getSelectedItem().toString();
            String destination = destinationComboBox.getSelectedItem().toString();
            AvailableTrains(source, destination);
        }
    });

    // Panel for the show trains button
    JPanel showTrainsPanel = new JPanel();
    showTrainsPanel.add(showTrainsButton);

    // Create the main panel for the dialog
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(scrollPane, BorderLayout.CENTER);
    mainPanel.add(showTrainsPanel, BorderLayout.LINE_END);

    // Display custom message instead of OK button
    int option = JOptionPane.showOptionDialog(this, mainPanel, "Book Train", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Fast Track \u23E9 ", "Go Back"}, "Book Train");
        if (option == JOptionPane.OK_OPTION) {
            String passengerCount = passengerCountComboBox.getSelectedItem().toString();
            String[] passengerNames = new String[Integer.parseInt(passengerCount)];
            String gender = "";

            if (maleRadioButton.isSelected()) {
                gender = maleRadioButton.getText();
            } else if (femaleRadioButton.isSelected()) {
                gender = femaleRadioButton.getText();
            }

            String source = sourceComboBox.getSelectedItem().toString();
            String destination = destinationComboBox.getSelectedItem().toString();
            String boardingStation = boardingStationComboBox.getSelectedItem().toString();
            String seatType = seatTypeComboBox.getSelectedItem().toString();
            Date selectedDate = datePicker.getDate();

            for (int i = 0; i < passengerNames.length; i++) {
                passengerNames[i] = passengerNameFields[i].getText();
            }

            if (!passengerCount.isEmpty() && !gender.isEmpty() && !source.isEmpty() && !destination.isEmpty() && !boardingStation.isEmpty() && !seatType.isEmpty() && selectedDate != null) {
                // Generate a unique 10-digit ticket ID
                String ticketId = generateTicketId();
                double amount = calculateAmount(seatType, Integer.parseInt(passengerCount));


                // Prompt the user to enter phone number
                JPanel phoneNumberPanel = new JPanel(new BorderLayout());
                phoneNumberPanel.add(new JLabel("Phone Number:"), BorderLayout.NORTH);
                JTextField phoneNumberField = new JTextField(10);
                JLabel countryCodeLabel = new JLabel("+91");
                countryCodeLabel.setPreferredSize(new Dimension(30, 20));
                phoneNumberPanel.add(countryCodeLabel, BorderLayout.WEST);
                phoneNumberPanel.add(phoneNumberField, BorderLayout.CENTER);

                int phoneNumberOption = JOptionPane.showConfirmDialog(this, phoneNumberPanel, "Enter Phone Number", JOptionPane.OK_CANCEL_OPTION);
                if (phoneNumberOption == JOptionPane.OK_OPTION) {
                    String phoneNumber = countryCodeLabel.getText() + phoneNumberField.getText();

                    String seatNumber=generateSeatNumber();
                    saveBookingDetails(ticketId, source, destination, boardingStation, passengerCount, passengerNames, gender, seatType,selectedDate,seatNumber);

                //AvailableTrains();
    String trainName = "";
    String trainNumber = "";
    String arrivalTime = "";


                    // Display the ticket
                    displayTicket(ticketId, source, destination, boardingStation, passengerCount, passengerNames, gender, seatType, selectedDate, amount, phoneNumber, trainName, trainNumber, arrivalTime);


                } else {
                    // User canceled phone number entry, return without displaying the ticket
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Passenger count, gender, source, destination, boarding station, seat type, and date of journey are required.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // User closed the dialog box, return without further action
            return;
        }
    }


private Date getOneWeekAheadDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, 7);
    return calendar.getTime();
}


private double calculateAmount(String seatType, int passengerCount) {
    double amount = 0.0;

    // Calculate the amount based on the selected seat type and passenger count
    if (seatType.equals("Second Sitting")) {
        amount = 200.0 * passengerCount; // Set the amount for Second Sitting
    } else if (seatType.equals("Sleeper")) {
        amount = 400.0 * passengerCount; // Set the amount for Sleeper
    } else if (seatType.equals("2 Tier AC")) {
        amount = 900.0 * passengerCount; // Set the amount for 2 Tier AC
    } else if (seatType.equals("3 Tier AC")) {
        amount = 1000.0 * passengerCount; // Set the amount for 3 Tier AC
    }
    return amount;
}

private void saveBookingDetails(String ticketId, String source, String destination, String boardingStation, String passengerCount, String[] passengerNames, String gender, String seatType, Date dateOfJourney,String seatNumber) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("passenger.txt", true));
        writer.write("Ticket ID: " + ticketId + "\n");
        writer.write("Source: " + source + "\n");
        writer.write("Destination: " + destination + "\n");
        writer.write("Boarding Station: " + boardingStation + "\n");
        writer.write("Passenger Count: " + passengerCount + "\n");

        for (int i = 0; i < passengerNames.length; i++) {
            writer.write("Passenger Name " + (i + 1) + ": " + passengerNames[i] + "\n");
            writer.write("Seat Number " + (i + 1) + ": " + generateSeatNumber() + "\n");
        }

        writer.write("Gender: " + gender + "\n");
        writer.write("Seat Type: " + seatType + "\n");
        writer.write("Date of Journey: " + formatDate(dateOfJourney) + "\n");
        writer.newLine();
        writer.close();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error occurred while saving the booking details.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private String formatDate(Date date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return dateFormat.format(date);
}






private void AvailableTrains(String source, String destination) {
    if (!source.isEmpty() && !destination.isEmpty()) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("access.txt"));
            String line;
            boolean isTrainAvailable = false;
            ButtonGroup trainOptionsGroup = new ButtonGroup();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Source: " + source + ", Destination: " + destination)) {
                    isTrainAvailable = true;
                    String trainName = reader.readLine();
                    String arrivalTime = reader.readLine();
                    String departureTime = reader.readLine();
                    JRadioButton radioButton = new JRadioButton(trainName +", "+ arrivalTime +", "+ departureTime + ")");
                    trainOptionsGroup.add(radioButton);
                    radioButton.setActionCommand(trainName);
                }
            }
            reader.close();

            if (isTrainAvailable) {
                JPanel trainOptionsPanel = new JPanel();
                trainOptionsPanel.setLayout(new BoxLayout(trainOptionsPanel, BoxLayout.Y_AXIS));

                Enumeration<AbstractButton> radioButtonEnum = trainOptionsGroup.getElements();
                while (radioButtonEnum.hasMoreElements()) {
                    JRadioButton radioButton = (JRadioButton) radioButtonEnum.nextElement();
                    trainOptionsPanel.add(radioButton);
                }

                int option = JOptionPane.showConfirmDialog(this, trainOptionsPanel, "Select Train", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String selectedTrain = trainOptionsGroup.getSelection().getActionCommand();
                    // Store selected train details in a text file
                    String selectedTrainDetails = "Selected Train: " + selectedTrain;
                    BufferedWriter writer = new BufferedWriter(new FileWriter("selected_train.txt"));
                    writer.write(selectedTrainDetails);
                    writer.newLine();
                    writer.close();
                    // Process the selected train
                    // ...
                }
            } else {
                JOptionPane.showMessageDialog(this, "No trains available for the selected location.", "Available Trains", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while loading train details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Source and destination are required.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}







private String generateTicketId() {
    // Generate a random 10-digit ticket ID
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10; i++) {
        int digit = (int) (Math.random() * 10);
        sb.append(digit);
    }
    return sb.toString();
}



private String generateSeatNumber() {
    // Generate a random seat number
    String[] seatLetters = {"A", "B", "C", "D", "E", "F", "G", "H"};
    Random random = new Random();
    int letterIndex = random.nextInt(seatLetters.length);
    int seatNumber = random.nextInt(10) + 1; // Random number between 1 and 10
    return seatLetters[letterIndex] + seatNumber;
}

private void displayTicket(String ticketId, String source, String destination, String boardingStation, String passengerCount, String[] passengerNames, String gender, String seatType, Date dateOfJourney, double amount, String phoneNumber,String trainName,String trainNumber,String arrivalTime) {
    trainName = "";
    trainNumber = "";
     arrivalTime = "09:00";

     try {
            BufferedReader reader = new BufferedReader(new FileReader("selected_train.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Selected Train:")) {
                    line = line.replace("Selected Train: ", ""); // Remove the "Selected Train:" prefix
                    String[] trainDetails = line.split(", ");
                    for (String detail : trainDetails) {
                        if (detail.startsWith("Train Name:")) {
                            trainName = detail.substring(detail.indexOf(":") + 2); // Extract train name
                        } else if (detail.startsWith("Train Number:")) {
                            trainNumber = detail.substring(detail.indexOf(":") + 2); // Extract train number
                        } else if (detail.startsWith("Arrival Time:")) {
                            arrivalTime = detail.substring(detail.indexOf(":") + 2); // Extract arrival time
                        }
                    }
                    break; // Exit the loop after finding the train details
                }
            }
        reader.close();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error occurred while reading the selected train details.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Ticket Details:\n");
    sb.append("Ticket ID: ").append(ticketId).append("\n");
    sb.append("Train Name: ").append(trainName).append("\n");
    sb.append("Train Number: ").append(trainNumber).append("\n");
    sb.append("Source: ").append(source).append("\n");
    sb.append("Destination: ").append(destination).append("\n");
    sb.append("Boarding Station: ").append(boardingStation).append("\n");
    sb.append("Passenger Count: ").append(passengerCount).append("\n");

    for (int i = 0; i < passengerNames.length; i++) {
        sb.append("Passenger Name ").append(i + 1).append(": ").append(passengerNames[i]).append("\n");
    }

    sb.append("Gender: ").append(gender).append("\n");
    sb.append("Seat Type: ").append(seatType).append("\n");
    sb.append("Date of Journey: ").append(formatDate(dateOfJourney)).append("\n");
    sb.append("Arrival Time: ").append(arrivalTime).append("\n");
    sb.append("Ticket Fare: ").append(amount).append("\n");
    sb.append("Phone Number: ").append(phoneNumber);

    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ticket.txt"));
        writer.write(sb.toString());
        writer.close();

        // Show a dialog with the ticket details and a download button
        JTextArea ticketTextArea = new JTextArea(sb.toString());
        ticketTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ticketTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        int option = JOptionPane.showOptionDialog(
                this,
                scrollPane,
                "Ticket Details",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Download Ticket", "Close"},
                null
        );

        if (option == 0) {
            // User clicked the "Download Ticket" button
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Ticket as PDF");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                saveTicketAsPDF(fileToSave.getAbsolutePath(), sb.toString());
            }
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error occurred while printing the ticket.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void saveTicketAsPDF(String filePath, String ticketContent) {
    Document document = new Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        com.itextpdf.text.Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        Paragraph title = new Paragraph("Ticket Details", titleFont);
        title.setSpacingAfter(20);
        document.add(title);

        document.add(new Paragraph(ticketContent, contentFont));

        document.close();

        JOptionPane.showMessageDialog(this, "Ticket saved as PDF successfully!", "Ticket Saved", JOptionPane.INFORMATION_MESSAGE);
    } catch (DocumentException | FileNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "Error occurred while saving the ticket as PDF.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}






private String[] getStates() {
    // Return an array of 10 Indian states for the source and destination selection
    return new String[]{"Mumbai", "New Delhi", "Bangalore", "Chennai", "Kolkata", "Hyderabad", "Ahmedabad", "Pune", "Jaipur", "Lucknow"};
}
private String[] getSeat() {
    // Return an array of 10 Indian states for the source and destination selection
    return new String[]{"Second Sitting", "Sleeper", "2 Tier AC", "3 Tier AC"};
}


private void updateBoardingStations(String selectedSource, JComboBox<String> boardingStationComboBox) {
    boardingStationComboBox.removeAllItems();
    //boardingStationComboBox.addItem(selectedSource);
         try {
        BufferedReader reader = new BufferedReader(new FileReader("boardings.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            String source = parts[0];

            if (source.equalsIgnoreCase(selectedSource)) {
                for (int i = 1; i < parts.length; i++) {
                    String boardingStation = parts[i];
                    if (boardingStation.equals(".")) {
                        break; // Stop adding boarding stations when encountering a period (.)
                    }
                    boardingStationComboBox.addItem(boardingStation);
                }
                break;
            }
        }
        reader.close();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error occurred while loading boarding stations.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}



private void showAvailableTrains() {
    JComboBox<String> sourceComboBox = new JComboBox<>(getStates());
    JComboBox<String> destinationComboBox = new JComboBox<>(getStates());

    JPanel searchTrainsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    searchTrainsPanel.add(new JLabel("Source:"));
    searchTrainsPanel.add(sourceComboBox);
    searchTrainsPanel.add(new JLabel("Destination:"));
    searchTrainsPanel.add(destinationComboBox);

    int option = JOptionPane.showConfirmDialog(this, searchTrainsPanel, "Show Available Trains", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        String source = sourceComboBox.getSelectedItem().toString();
        String destination = destinationComboBox.getSelectedItem().toString();

        if (!source.isEmpty() && !destination.isEmpty()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("access.txt"));
                String line;
                boolean isTrainAvailable = false;
                StringBuilder availableTrains = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Source: " + source + ", Destination: " + destination)) {
                        isTrainAvailable = true;
                        availableTrains.append(line).append("\n");
                        availableTrains.append(reader.readLine()).append("\n");
                        availableTrains.append(reader.readLine()).append("\n");
                        availableTrains.append(reader.readLine()).append("\n");
                       // availableTrains.append(reader.readLine()).append("\n");
                        availableTrains.append("---------------------------------------\n");
                    }
                }
                reader.close();

                if (isTrainAvailable) {
                    JOptionPane.showMessageDialog(this, availableTrains.toString(), "Train Schedule", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No trains available for the selected location.", "Available Trains", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error occurred while loading train details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Source and destination are required.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    showUserOptionsDialog();
}


private void handleAddTrain() {
    JPanel addTrainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
    JTextField trainNumberField = new JTextField();
    JTextField trainNameField = new JTextField();
    JTextField sourceField = new JTextField();
    JTextField destinationField = new JTextField();
    JTextField arrivalField = new JTextField();
    JTextField departureField = new JTextField();
    addTrainPanel.add(new JLabel("Train Number:"));
    addTrainPanel.add(trainNumberField);
    addTrainPanel.add(new JLabel("Train Name:"));
    addTrainPanel.add(trainNameField);
    addTrainPanel.add(new JLabel("Source:"));
    addTrainPanel.add(sourceField);
    addTrainPanel.add(new JLabel("Destination:"));
    addTrainPanel.add(destinationField);
    addTrainPanel.add(new JLabel("Arrival Time:"));
    addTrainPanel.add(arrivalField);
    addTrainPanel.add(new JLabel("Departure Time:"));
    addTrainPanel.add(departureField);

    int option = JOptionPane.showConfirmDialog(this, addTrainPanel, "Add Train", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        String trainName = trainNameField.getText();
        String trainNumber = trainNumberField.getText();
        String source = sourceField.getText();
        String destination = destinationField.getText();
        String arrivalTime = arrivalField.getText();
        String departureTime = departureField.getText();

        if (!trainNumber.isEmpty() && !trainName.isEmpty() && !source.isEmpty() && !destination.isEmpty() && !arrivalTime.isEmpty() && !departureTime.isEmpty()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("trainlist.txt", true));
                String trainDetails = "Train Name: " + trainName + ", Train Number: " + trainNumber + ", Source: " + source + ", Destination: " + destination + ", Arrival Time: " + arrivalTime + ", Departure Time: " + departureTime;
                writer.write(trainDetails);
                writer.newLine();
                writer.close();

                // Calculate journey time
                long arrivalMillis = getTimeInMillis(arrivalTime);
                long departureMillis = getTimeInMillis(departureTime);
                long journeyMillis = Math.abs(arrivalMillis - departureMillis);
                String journeyTime = getFormattedJourneyTime(journeyMillis);

                // Add train details to access.txt
                BufferedWriter accessWriter = new BufferedWriter(new FileWriter("access.txt", true));
                String accessDetails = "Source: " + source + ", Destination: " + destination + "\n" +
                        "Train Name: " + trainName + ", Train Number: " + trainNumber + "\n" +
                        "Arrival Time: " + arrivalTime + ", Departure Time: " + departureTime + "\n" +
                        "Journey Time: " + journeyTime;
                accessWriter.write(accessDetails);
                accessWriter.newLine();
                accessWriter.close();

                JOptionPane.showMessageDialog(this, "Train added successfully!\nTrain Number: " + trainNumber);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error occurred while adding the train.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Train details are required.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    showAdminOptionsDialog();
}






private long getTimeInMillis(String time) {
    try {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = format.parse(time);
        return date.getTime();
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return 0;
}

private String getFormattedJourneyTime(long journeyMillis) {
    long hours = TimeUnit.MILLISECONDS.toHours(journeyMillis);
    long minutes = TimeUnit.MILLISECONDS.toMinutes(journeyMillis) % 60;
    return hours + " hours " + minutes + " minutes";
}

private void handleDeleteTrain() {
    String trainNumber = JOptionPane.showInputDialog(this, "Enter the Train Number to delete:");

    if (trainNumber != null && !trainNumber.isEmpty()) {
        try {
            File trainListFile = new File("trainlist.txt");
            File accessFile = new File("access.txt");

            File tempTrainListFile = new File("temp_trainlist.txt");
            File tempAccessFile = new File("temp_access.txt");

            BufferedReader trainListReader = new BufferedReader(new FileReader(trainListFile));
            BufferedWriter trainListWriter = new BufferedWriter(new FileWriter(tempTrainListFile));

            BufferedReader accessReader = new BufferedReader(new FileReader(accessFile));
            BufferedWriter accessWriter = new BufferedWriter(new FileWriter(tempAccessFile));

            String trainListLine;
            String accessLine;
            boolean isTrainFound = false;

            while ((trainListLine = trainListReader.readLine()) != null && (accessLine = accessReader.readLine()) != null) {
                String[] trainListParts = trainListLine.split(",");
                String[] accessParts = accessLine.split("\n");

                if (trainListParts.length >= 2 && trainListParts[1].trim().startsWith("Train Number: " + trainNumber)) {
                    isTrainFound = true;
                } else {
                    trainListWriter.write(trainListLine);
                    trainListWriter.newLine();
                    accessWriter.write(accessLine);
                    accessWriter.newLine();
                }
            }

            trainListReader.close();
            trainListWriter.close();
            accessReader.close();
            accessWriter.close();

            if (isTrainFound) {
                if (trainListFile.delete() && tempTrainListFile.renameTo(trainListFile) && accessFile.delete() && tempAccessFile.renameTo(accessFile)) {
                    JOptionPane.showMessageDialog(this, "Train deleted successfully!\nTrain Number: " + trainNumber);
                } else {
                    JOptionPane.showMessageDialog(this, "Error occurred while deleting the train.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Train with the specified Train Number not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while deleting the train.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    showAdminOptionsDialog();
}


private void handleUpdateTrain() {
    JPanel trainNumberPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    JTextField trainNumberField = new JTextField();
    trainNumberPanel.add(new JLabel("Train Number:"));
    trainNumberPanel.add(trainNumberField);

    int option = JOptionPane.showConfirmDialog(this, trainNumberPanel, "Update Train - Enter Train Number", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        String trainNumber = trainNumberField.getText();

        if (!trainNumber.isEmpty()) {
            try {
                File inputFile = new File("trainlist.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                String line;
                boolean isTrainFound = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2 && parts[1].trim().startsWith("Train Number: " + trainNumber)) {
                        isTrainFound = true;
                        break;
                    }
                }

                reader.close();

                if (isTrainFound) {
                    JPanel updateTrainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
                    JTextField trainNameField = new JTextField();
                    JTextField sourceField = new JTextField();
                    JTextField destinationField = new JTextField();
                    JTextField arrivalTimeField = new JTextField();
                    JTextField departureTimeField = new JTextField();
                    updateTrainPanel.add(new JLabel("Train Name:"));
                    updateTrainPanel.add(trainNameField);
                    updateTrainPanel.add(new JLabel("Source:"));
                    updateTrainPanel.add(sourceField);
                    updateTrainPanel.add(new JLabel("Destination:"));
                    updateTrainPanel.add(destinationField);
                    updateTrainPanel.add(new JLabel("Arrival Time:"));
                    updateTrainPanel.add(arrivalTimeField);
                    updateTrainPanel.add(new JLabel("Departure Time:"));
                    updateTrainPanel.add(departureTimeField);

                    option = JOptionPane.showConfirmDialog(this, updateTrainPanel, "Update Train - Enter New Details", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {
                        String trainName = trainNameField.getText();
                        String source = sourceField.getText();
                        String destination = destinationField.getText();
                        String arrivalTime = arrivalTimeField.getText();
                        String departureTime = departureTimeField.getText();

                        if (!trainName.isEmpty() && !source.isEmpty() && !destination.isEmpty() && !arrivalTime.isEmpty() && !departureTime.isEmpty()) {
                            File tempFile = new File("temp.txt");
                            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                            reader = new BufferedReader(new FileReader(inputFile));

                            while ((line = reader.readLine()) != null) {
                                String[] parts = line.split(",");
                                if (parts.length >= 2 && parts[1].trim().startsWith("Train Number: " + trainNumber)) {
                                    writer.write("Train Name: " + trainName + ", Train Number: " + trainNumber + ", Source: " + source + ", Destination: " + destination + ", Arrival Time: " + arrivalTime + ", Departure Time: " + departureTime);
                                    writer.newLine();
                                } else {
                                    writer.write(line);
                                    writer.newLine();
                                }
                            }

                            reader.close();
                            writer.close();

                            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                                JOptionPane.showMessageDialog(this, "Train updated successfully!\nTrain Number: " + trainNumber);
                                updateAccessFile(trainName, trainNumber, source, destination, calculateJourneyTime(arrivalTime, departureTime));
                            } else {
                                JOptionPane.showMessageDialog(this, "Error occurred while updating the train.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Train details are required.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Train with the specified Train Number not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error occurred while updating the train.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Train number is required.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    showAdminOptionsDialog();
}

private void updateAccessFile(String trainName, String trainNumber, String source, String destination, String journeyTime) {
    try {
        File accessFile = new File("access.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(accessFile, true));

        writer.write("Source: " + source + ", Destination: " + destination);
        writer.newLine();
        writer.write("Train Name: " + trainName + ", Train Number: " + trainNumber);
        writer.newLine();
        writer.write("Journey Time: " + journeyTime);
        writer.newLine();

        writer.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}


private String calculateJourneyTime(String arrivalTime, String departureTime) {
    
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    try {
        Date arrival = format.parse(arrivalTime);
        Date departure = format.parse(departureTime);

        // Calculate the difference in milliseconds between arrival and departure
        long differenceInMillis = arrival.getTime() - departure.getTime();

        // Convert milliseconds to hours and minutes
        long hours = TimeUnit.MILLISECONDS.toHours(differenceInMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMillis) % 60;

        // Format the journey time string
        return hours + " hours " + minutes + " minutes";
    } catch (ParseException e) {
        e.printStackTrace();
    }

    return "N/A";
}


public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new RailwayTicketBookingSystem().setVisible(true);

            SwingUtilities.invokeLater(RailwayTicketBookingSystem::new);
            
        }
    });
}
}
