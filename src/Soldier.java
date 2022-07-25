
import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

/**
 * Class Soldier for managing the clubber window/entity which is the soldier
 */
public class Soldier extends Person implements Serializable {
    private static int frameWidth = 450, frameHeight = 250;
    private static String frameTitle = "Soldier Clubber's Data";
    private String personalNum;
    private JTextField personalNumField;
    private JLabel redDotExtra;

    /**
     * Default empty constructor for Soldier entity object
     */
    public Soldier() {
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        // adds new extra red dot label for *
        redDotExtra = new JLabel("*");
        redDotExtra.setForeground(Color.red);
        redDotExtra.setVisible(false);
        JLabel pnl = new JLabel("Personal No.");
        personalNumField = new JTextField(25);
        pnl.setPreferredSize(new Dimension(80, 20));
        addToCenter(pnl);
        // adds the extra fields to the frame and setting the cancel button accordingly
        addToCenter(personalNumField);
        addToCenter(redDotExtra);
        getCancelButton().setEnabled(false);
    }

    /**
     * Constructor for Soldier entity object with parameters
     * @param id Soldier's actual id
     * @param name Soldier's name
     * @param surname Soldier's surname
     * @param tel Soldier's telephone number
     * @param personalNum Soldier's person number in army
     */
    public Soldier(String id, String name, String surname, String tel, String personalNum) {
        super(id, name, surname, tel);
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        // adds new extra red dot label for *
        redDotExtra = new JLabel("*");
        redDotExtra.setForeground(Color.red);
        redDotExtra.setVisible(false);
        JLabel pnl = new JLabel("Personal No.");
        this.personalNum = personalNum;
        personalNumField = new JTextField(25);
        personalNumField.setText(this.personalNum);
        pnl.setPreferredSize(new Dimension(80, 20));
        addToCenter(pnl);
        // adds the extra fields to the frame and setting the cancel button accordingly
        addToCenter(personalNumField);
        addToCenter(redDotExtra);
        getCancelButton().setEnabled(true);
    }

    /**
     * Match method to match the input string with this entity's keys
     * @param key the key to match
     * @return true if it matches, false if it doesn't match
     */
    public boolean match(String key) {
        return super.match(key) || personalNum.equals(key);
    }

    /**
     * Validate data method to check if the TextInputs's text is matched with conditions
     * @return true if the data validates, false if it doesn't validate
     */
    protected boolean validateData() {
        redDotExtra.setVisible(!personalNumField.getText().matches("[ROC]\\/[1-9]\\d{6}"));
        return super.validateData() && !redDotExtra.isVisible();

    }

    /**
     * Commiting the text inside TextInputs to the actual String fields inside the entity
     */
    protected void commit() {
        super.commit();
        this.personalNum = personalNumField.getText();
    }

    /**
     * Rolls back the TextInputs' text to have the String fields' values
     */
    protected void rollBack() {
        super.rollBack();
        this.personalNumField.setText(personalNum);
    }
}