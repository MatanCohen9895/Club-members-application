
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/***
 * A class for managing the Night Club App
 */
public class NightClubMgmtApp {
    private ArrayList<ClubAbstractEntity> listOfClubEntities;
    private JFrame jFrame;
    private static String frameTitle = "Matan and David Night Club";
    private static int frameWidth = 700, frameHeight = 600;


    /**
     * Main method for creating the Night Club App window and populate it with frames and buttons
     */
    public NightClubMgmtApp() {
        jFrame = new JFrame(frameTitle);
        jFrame.setLayout(new GridLayout(2, 1));
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.pack();
        //Initializing clubbers
        listOfClubEntities = new ArrayList<>();
        //read from file when app begins
        loadClubbersDBFromFile();
//        loadDummy();
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        //Picture add to panel
        JLabel imgLabel = new JLabel(new ImageIcon(getClass().getResource("clubpic.jpg")));
        JPanel imgPanel = new JPanel();
        imgPanel.add(imgLabel);
        imgPanel.setSize(new Dimension(200, 400));
        // search button
        JButton b1 = new JButton("Search a clubber");
        buttonsPanel.add(b1);
        // create clubber button
        JButton b2 = new JButton("Create a clubber");
        buttonsPanel.add(b2);
        // adding panels to main frame
        jFrame.add(buttonsPanel);
        jFrame.add(imgPanel);

        imgPanel.revalidate();
        imgPanel.validate();
        imgPanel.repaint();

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                manipulateDB();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choosing = JOptionPane.showInputDialog(null, "Enter a new clubber:" +
                        "\nFor Person - type 1\nFor Soldier - type 2\nFor Student - type 3\nTo go back to main menu - type 0");
                if (choosing != null) {
                    switch (choosing) {
                        case "0":
                            break;
                        case "1":
                            listOfClubEntities.add(new Person());
                            break;
                        case "2":
                            listOfClubEntities.add(new Soldier());
                            break;
                        case "3":
                            listOfClubEntities.add(new Student());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "You've entered wrong input", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                    }

                }
            }
        });

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(jFrame, "Do you want to exit?", "Close",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    writeClubbersDBtoFile();
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Method for searching the DB for entities (Student, Person, Soldier)
     */
    private void manipulateDB() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Please Enter The Clubber's Key");
            if (input == null || input.equals("")) {
                return;
            }
            for (ClubAbstractEntity clubber : listOfClubEntities)
                if (clubber.match(input)) {
                    clubber.setVisible(true);
                    return;
                }
            JOptionPane.showMessageDialog(null, "Clubber with this key does not exist\n", "Wait!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Method for loading clubbers for file
     */
    private void loadClubbersDBFromFile() {
        try {
            FileInputStream fis = new FileInputStream("BKCustomers.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listOfClubEntities = (ArrayList<ClubAbstractEntity>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception e) {
            
        }
    }

    /**
     * Method for writing clubbers to file
     */
    private void writeClubbersDBtoFile() {
        try {
            FileOutputStream fos = new FileOutputStream("BKCustomers.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listOfClubEntities);
            fos.flush();
            oos.close();
            JOptionPane.showMessageDialog(null, "Successfully wrote to file.\n", "Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Method for loading dummy entities (for testing)
     */
    private void loadDummy()
    {
        listOfClubEntities.add(new Person("0-2423535|1", "Mark", "Mc'Cormic",
                "+(1)4-9520205"));
        listOfClubEntities.add(new Soldier("0-2223335|1", "Zohar", "Couper-Berg",
                "+(44)206-8208167", "O/4684109"));
        listOfClubEntities.add(new Student("2-5554445|3", "Avi", "Avrahami-O'Mally",
                "+(972)50-6663210", "SCE/12345"));
        for (ClubAbstractEntity entity : listOfClubEntities) {
            entity.setVisible(false);
        }
    }

    /**
     * Main application to run the app
     * @param args
     */
    public static void main(String[] args) {
        NightClubMgmtApp nightClubMgmtApp = new NightClubMgmtApp();
    }
}