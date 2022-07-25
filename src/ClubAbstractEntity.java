
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.Serializable;

/**
 * Abstract class to represent the clubber's entity (Person, Soldier, Student)
 */
public abstract class ClubAbstractEntity extends JFrame {

    protected static String[] labelsStr = {"ID", "Name", "Surname", "Tel"};
    private JPanel centerPanel;
    private JButton okButton, cancelButton;
    private ButtonHandler buttonHandler;

    /**
     * Default empty abstract constructor to initiate the relevant fields before
     * calling the derived class' constructor
     */
    public ClubAbstractEntity() {
        centerPanel = new JPanel();
        BorderLayout l1 = new BorderLayout();
        FlowLayout l2 = new FlowLayout(FlowLayout.RIGHT, 3, 3);
        centerPanel.setLayout(l2);
        setLayout(l1);
        setVisible(true);
        getContentPane().setBackground(Color.gray);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JPanel[] jPanels = new JPanel[]{new JPanel(), new JPanel()};
        jPanels[0].setPreferredSize(new Dimension(50, 50));
        jPanels[1].setPreferredSize(new Dimension(50, 50));
        // buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("cancel");
        buttonHandler = new ButtonHandler(this);
        okButton.addActionListener(buttonHandler);
        cancelButton.addActionListener(buttonHandler);
        jPanels[0].add(okButton);
        jPanels[0].add(cancelButton);
        // add the components to this jframe
        add(jPanels[0], BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(jPanels[1], BorderLayout.WEST);

    }

    /**
     * Adds the guiComponent to the center of this panel (JFrame's)
     * @param guiComponent The component to add to the center
     */
    protected void addToCenter(Component guiComponent) {
        centerPanel.add(guiComponent);
    }

    /**
     * Abstract function to match the input string with this entity's keys
     * @param key the key to match
     * @return true if it matches, false if it doesn't match
     */
    public abstract boolean match(String key);

    /**
     * Abstract function to validate data method to check if the TextInputs's text is matched with conditions
     * @return true if the data validates, false if it doesn't validate
     */
    protected abstract boolean validateData();

    /**
     * Commiting the text inside TextInputs to the actual String fields inside the entity
     */
    protected abstract void commit();


    /**
     * Rolls back the TextInputs' text to have the String fields' values
     */
    protected abstract void rollBack();

    /**
     * This method is a helper function to get the cancel button for this window
     * @return The JButton that represent the cancel button in this window
     */
    protected JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Inner class ButtonHandler to manage the handling of the OK/Cancel
     * Buttons inside this JFrame
     */
    private class ButtonHandler implements ActionListener, Serializable {

        JFrame jframe;

        /**
         * Constructor for this handler that recieves the jframe it's working on
         * @param jframe The JFrame to work on
         */
        protected ButtonHandler(JFrame jframe) { this.jframe = jframe; }

        /**
         * The method to pass for the handling of the buttons. it will handle the OK/Cancel
         * buttons according to their command value
         * @param event
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("OK") && validateData()) {
                commit();
                cancelButton.setEnabled(true);
                jframe.setVisible(false);
            } else if (event.getActionCommand().equals("cancel")) {
                rollBack();
                jframe.setVisible(false);
            }
        }
    }
}