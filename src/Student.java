
import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

/**
 * Class Student for managing the clubber window/entity which is the student
 */
public class Student extends Person implements Serializable {
    private static int frameWidth = 450, frameHeight = 250;
    private static String frameTitle = "Student Clubber's Data";
    private String studentId;
    private JTextField studentIdField;
    private JLabel redDotExtra;

    /**
     * Default empty constructor for Student entity object
     */
    public Student() {
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        // adds new extra red dot label for *
        redDotExtra = new JLabel("*");
        redDotExtra.setForeground(Color.red);
        redDotExtra.setVisible(false);
        JLabel pnl = new JLabel("Student ID");
        studentIdField = new JTextField(25);
        pnl.setPreferredSize(new Dimension(80, 20));
        addToCenter(pnl);
        // adds the extra fields to the frame and setting the cancel button accordingly
        addToCenter(studentIdField);
        addToCenter(redDotExtra);
        getCancelButton().setEnabled(false);
    }

    /**
     * Constructor for Student entity object with parameters
     * @param id Student's actual id
     * @param name Student's name
     * @param surname Student's surname
     * @param tel Student's telephone number
     * @param studentId Student's student id number
     */
    public Student(String id, String name, String surname, String tel, String studentId) {
        super(id, name, surname, tel);
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        // adds new extra red dot label for *
        redDotExtra = new JLabel("*");
        redDotExtra.setForeground(Color.red);
        redDotExtra.setVisible(false);
        JLabel pnl = new JLabel("Student ID");
        this.studentId = studentId;
        studentIdField = new JTextField(25);
        pnl.setPreferredSize(new Dimension(60, 20));
        addToCenter(pnl);
        // adds the extra fields to the frame and setting the cancel button accordingly
        addToCenter(studentIdField);
        addToCenter(redDotExtra);
        getCancelButton().setEnabled(true);
    }

    /**
     * Match method to match the input string with this entity's keys
     * @param key the key to match
     * @return true if it matches, false if it doesn't match
     */
    public boolean match(String key) {
        return super.match(key) || studentId.substring(4).equals(key);
    }

    /**
     * Validate data method to check if the TextInputs's text is matched with conditions
     * @return true if the data validates, false if it doesn't validate
     */
    protected boolean validateData() {
        redDotExtra.setVisible(!studentIdField.getText().matches("[A-Z]{3}\\/[1-9]\\d{4}"));
        return super.validateData() && !redDotExtra.isVisible();
    }

    /**
     * Commiting the text inside TextInputs to the actual String fields inside the entity
     */
    protected void commit() {
        super.commit();
        this.studentId = studentIdField.getText();
    }

    /**
     * Rolls back the TextInputs' text to have the String fields' values
     */
    protected void rollBack() {
        super.rollBack();
        this.studentIdField.setText(studentId);
    }
}