
import java.awt.*;
import javax.swing.*;
import java.io.Serializable;

/**
 * Class Person for managing the clubber window/entity which is the person
 */
public class Person extends ClubAbstractEntity implements Serializable {

    private static int frameWidth = 450, frameHeight = 220;
    private static String frameTitle = "Person Clubber's Data";
    private String id, name, surname, tel;
    private JTextField[] fields;
    private JLabel[] redDots;

    /**
     * Default empty constructor for Student entity object
     */
    public Person() {
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        fields = new JTextField[4];
        redDots = new JLabel[4];
        for (int i = 0; i < 4; i++)
        {
            JLabel lbl = new JLabel(labelsStr[i]);
            redDots[i] = new JLabel("*");
            redDots[i].setForeground(Color.red);
            redDots[i].setVisible(false);
            lbl.setPreferredSize(new Dimension(60, 20));
            fields[i] = new JTextField(25);
            super.addToCenter(lbl);
            super.addToCenter(fields[i]);
            super.addToCenter(redDots[i]);
        }
        super.getCancelButton().setEnabled(false);
    }

    /**
     * Constructor for Person entity object with parameters
     * @param id Person's actual id
     * @param name Person's name
     * @param surname Person's surname
     * @param tel Person's telephone number
     */
    public Person(String id, String name, String surname, String tel) {
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        fields = new JTextField[4];
        redDots = new JLabel[4];
        String[] fieldsVals = new String[]{id, name, surname, tel};
        for (int i = 0; i < 4; i++)
        {
            JLabel Label = new JLabel(labelsStr[i]);
            redDots[i] = new JLabel("*");
            redDots[i].setVisible(false);
            redDots[i].setForeground(Color.red);
            super.addToCenter(redDots[i]);
            Label.setPreferredSize(new Dimension(60, 20));
            super.addToCenter(Label);
            fields[i] = new JTextField(25);
            fields[i].setText(fieldsVals[i]);
            super.addToCenter(fields[i]);
        }
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
    }

    /**
     * Match method to match the input string with this entity's keys
     * @param key the key to match
     * @return true if it matches, false if it doesn't match
     */
    public boolean match(String key) {
        return id.equals(key);
    }

    /**
     * Validate data method to check if the TextInputs's text is matched with conditions
     * @return true if the data validates, false if it doesn't validate
     */
    protected boolean validateData() {
        redDots[0].setVisible(!fields[0].getText().matches("[0-9]-\\d{7}\\|[1-9]"));
        redDots[1].setVisible(!fields[1].getText().matches("[A-Z][a-z]+"));
        redDots[2].setVisible(!fields[2].getText().matches("([A-Z][a-z]*[’'-]{0,1})+"));
        redDots[3].setVisible(!fields[3].getText().matches("\\+\\([1-9]\\d{0,2}\\)[1-9]\\d{0,2}-[1-9]\\d{6}"));
        if (redDots[0].isVisible() || redDots[1].isVisible() || redDots[2].isVisible() || redDots[3].isVisible()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Commiting the text inside TextInputs to the actual String fields inside the entity
     */
    protected void commit() {
        id = fields[0].getText();
        name = fields[1].getText();
        surname = fields[2].getText();
        tel = fields[3].getText();
    }

    /**
     * Rolls back the TextInputs' text to have the String fields' values
     */
    protected void rollBack() {
        fields[0].setText(id);
        fields[1].setText(name);
        fields[2].setText(surname);
        fields[3].setText(tel);
    }
}