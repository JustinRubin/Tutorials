import java.awt.*;
import javax.swing.*;

public class NewEmail extends JFrame {
    public NewEmail() {
        super("New E-mail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(580, 410);
        // create menu
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu insertMenu = new JMenu("Insert");
        JMenu messageMenu = new JMenu("Message");
        JMenu formatMenu = new JMenu("Format");
        JMenu helpMenu = new JMenu("Help");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(insertMenu);
        menuBar.add(messageMenu);
        menuBar.add(formatMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        // create toolbar
        ImageIcon sendIcon = new ImageIcon("send.gif");
        ImageIcon queueIcon = new ImageIcon("queue.gif");
        ImageIcon draftIcon = new ImageIcon("draft.gif");
        ImageIcon attachIcon = new ImageIcon("attach.gif");
        ImageIcon styledIcon = new ImageIcon("styled.gif");
        ImageIcon spellingIcon = new ImageIcon("spelling.gif");
        ImageIcon backIcon = new ImageIcon("back.gif");
        JButton send = new JButton("Send", sendIcon);
        JButton queue = new JButton("Queue", queueIcon);
        JButton draft = new JButton("Draft", draftIcon);
        JButton attach = new JButton("Attach", attachIcon);
        JButton styled = new JButton("Styled", styledIcon);
        JButton spelling = new JButton("Spelling", spellingIcon);
        JButton back = new JButton("Back", backIcon);
        JToolBar toolBar = new JToolBar();
        toolBar.add(send);
        toolBar.add(queue);
        toolBar.add(draft);
        toolBar.add(attach);
        toolBar.add(styled);
        toolBar.add(spelling);
        toolBar.add(back);
        setLayout(new BorderLayout());
        MainPanel mainPanel = new MainPanel();
        add("North", toolBar);
        add("Center", mainPanel);
        SwingUtilities.updateComponentTreeUI(this);
        setVisible(true);
    }
 
    public static void main(String[] arguments) {
        String[] lookAndFeel = {
            "javax.swing.plaf.metal.MetalLookAndFeel",
            "com.sun.java.swing.plaf.mac.MacLookAndFeel",
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
           "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
            "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
        };
        if (arguments.length < 1) {
            System.out.println("Usage: java NewEmail lookAndFeelNumber\n" +
                "0: Metal\n1: Mac\n2: Motif\n3: Windows\n4: Windows Classic");
            System.exit(-1);
        }
        try {
            int choice = Integer.parseInt(arguments[0]);
            UIManager.setLookAndFeel(lookAndFeel[choice]);
        } catch (Exception e) {
            System.out.println("Can't set look and feel: " + e.getMessage());
            e.printStackTrace();
       }
        new NewEmail();
        System.out.println("Available look and feels:\n");
        UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < laf.length; i++) {
            System.out.println("Class name: " + laf[i].getClassName());
            System.out.println("Name: " + laf[i].getName() + "\n");
        }
    }
}

class MainPanel extends JPanel {
    MainPanel() {
        // create button bar
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel fromLabel = new JLabel("From: ");
        JComboBox addressBox = new JComboBox();
        addressBox.addItem("rcade@example.com");
        ImageIcon modifyIcon = new ImageIcon("modify.gif");
        ImageIcon templatesIcon = new ImageIcon("templates.gif");
        JButton modify = new JButton("Modify Accounts", modifyIcon);
        JButton templates = new JButton("Templates", templatesIcon);
        topPanel.add(fromLabel);
        topPanel.add(addressBox);
        topPanel.add(modify);
        topPanel.add(templates); 
        MailPanel mailPanel = new MailPanel();
        setLayout(new BorderLayout());
        add("North", topPanel);
        add("Center", mailPanel);
    }
}

class MailPanel extends JTabbedPane {
    MailPanel() {
        super();
        // set up new message entry form
        JPanel mailTab = new JPanel();
        mailTab.setLayout(new BorderLayout());
        JTextArea comments = new JTextArea();
        JScrollPane textPanel = new JScrollPane(comments,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mailTab.add("North", new MailFormPanel());
        mailTab.add("Center", textPanel);
        // add entry form to tabbed pane
        addTab("Main", mailTab);
        addTab("Optional", new JPanel());
    }
}

class MailFormPanel extends JPanel {
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints constraints;
       
    MailFormPanel() {
        super();
        JLabel toLabel = new JLabel("To: ");
        JTextField to = new JTextField();
        JLabel subjectLabel = new JLabel("Subject: ");
        JTextField subject = new JTextField();
        JLabel ccLabel = new JLabel("CC: ");
        JTextField cc = new JTextField();
        JLabel bccLabel = new JLabel("BCC: ");
        JTextField bcc = new JTextField();
        setLayout(gridbag);
        addComponent(toLabel, 0, 0, 1, 1, 5, 100,
            GridBagConstraints.NONE, GridBagConstraints.EAST);
        addComponent(to, 1, 0, 9, 1, 195, 100,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        addComponent(subjectLabel, 0, 1, 1, 1, 5, 100,
            GridBagConstraints.NONE, GridBagConstraints.EAST);
        addComponent(subject, 1, 1, 9, 1, 195, 100,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        addComponent(ccLabel, 0, 2, 1, 1, 5, 100,
            GridBagConstraints.NONE, GridBagConstraints.EAST);
        addComponent(cc, 1, 2, 4, 1, 95, 100,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        addComponent(bccLabel, 5, 2, 1, 1, 5, 100, 
            GridBagConstraints.NONE, GridBagConstraints.EAST);
        addComponent(bcc, 6, 2, 4, 1, 95, 100,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
    }

    private void addComponent(Component component, int gridx, int gridy,
        int gridwidth, int gridheight, int weightx, int weighty, int fill,
        int anchor) {
     
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = fill;
        constraints.anchor = anchor;
        gridbag.setConstraints(component, constraints);
        add(component);
    }

    public Insets getInsets() {
        return new Insets(2, 2, 2, 2);
    }
}
