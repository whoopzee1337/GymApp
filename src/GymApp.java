import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GymApp extends JFrame {
    private JTextArea input = new JTextArea(10 , 5 );
    private JButton buttonApply = new JButton("Добавить");
    private JButton buttonSave = new JButton("Сохранить");
    private JTextArea nameOfFile = new JTextArea("                      ");

    String[] items = {
            "Отжимания",
            "Подтягивания",
            "Тяга горизонатльного блока"
    };
    Integer[] setsCount = {
            1,
            2,
            3,
            4,
            5
    };
    JComboBox editComboBoxExercises = new JComboBox(items);
    JComboBox editComboBoxSets = new JComboBox(setsCount);
    public GymApp(){
        super("GymApp");
        setSize(500 , 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 20;
        constraints.weighty = 0.5;
        constraints.gridx = 0;
        container.add(nameOfFile , constraints);

        constraints.gridy = 1;
        container.add(editComboBoxExercises , constraints);

        constraints.gridy = 2;
        container.add(editComboBoxSets , constraints);

        constraints.gridy = 3;
        container.add(buttonApply, constraints);

        constraints.gridy = 4;
        container.add(buttonSave , constraints);

        constraints.ipady = 5;
        constraints.gridy = 5;
        constraints.gridx = 1;
        constraints.gridwidth = 100;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 300;
        constraints.weighty = 30;
        container.add(input , constraints);
        buttonApply.addActionListener(new ButtonEventListener());
        buttonSave.addActionListener(new ButtonSaveListener());
    }
    class ButtonEventListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent a){
            Integer chosenNumberOfSets = (Integer)editComboBoxSets.getSelectedItem();
            String chosenNameOfExercise = (String)editComboBoxExercises.getSelectedItem();
            input.setText(input.getText()+"\n       " +chosenNameOfExercise+ "\n       № подхода                Повторения       Вес\n") ;
            for (int i = 1 ; i <= chosenNumberOfSets ; i++) {
                input.setText(input.getText() + "       Подход " + i + ":" + "\n");
            }
        }
    }
    class ButtonSaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            File file = new File("C:\\Users\\СКУМБРИЯ\\Desktop\\" + nameOfFile.getText() + ".txt");
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(input.getText());
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }
    }
}
