package core.util;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by WAMS-10 on 26/07/2017.
 */
public class Validar {

    public static boolean campoVacio(TextField... txt) throws Myexception {
        boolean esValido = true;
        for (int i = 0; i < txt.length; i++) {
            if (txt[i].getText() == null || txt[i].getText().trim().isEmpty()) {
                esValido = false;
            }
        }
        if (esValido)
            return esValido;
        else
            throw new Myexception("Campo Vacío");
    }

    public static boolean limmpiarCampos(TextField... txt) throws Myexception {
        boolean esValido = true;
        for (int i = 0; i < txt.length; i++) {
            txt[i].setText("");
        }
        if (esValido)
            return esValido;
        else
            throw new Myexception("Erro message");
    }

    /**
     * Validar una sola palabra
     */
    public static void unaPalabra(String... txt) throws Myexception {
        for (String s : txt) {
            if (s.contains(" ")) {
                String[] parts = s.split(" ");
                String part2 = parts[1];
                if (part2 != null) throw new Myexception("Campo tiene mas de una palabra");
            }
        }
    }

    public static String recuperarSegundaPalabra(String regex, String frase) throws Myexception {
        String[] parts = frase.split(regex);
        String part2 = parts[1];
        if (part2 == null) throw new Myexception("Error recuperando segundo valor");
        else return part2;
    }

    public static void hideControl(Control... control) {
        for (Control ctr : control) {
            ctr.setVisible(false);
        }
    }

    public static void disableControl(TextInputControl... txt) {
        for (TextInputControl txtInput : txt)
            txtInput.setDisable(false);
    }

    public static void entradaNumerica(TextInputControl... txt) {
        for (TextInputControl txtInput : txt) {
            txtInput.promptTextProperty().addListener((observable, oldValue, newValue) -> {
                Validar.inputNumber(txtInput, newValue);
            });
        }
    }

    private static void inputNumber(TextInputControl txt, String newValue) {
        if (!newValue.matches("\\d*")) {
            txt.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }

    /**
     * Comportamiento del campo
     *
     * @param txt
     * @param type 1: deshabilitado, 2: Visible, 3: No editable
     */
    public static void desahabilitarCampo(TextInputControl txt, int type) throws Myexception {
        switch (type) {
            case 1:
                txt.setDisable(true);
                break;
            case 2:
                txt.setVisible(true);
                break;
            case 3:
                txt.setEditable(true);
        }
    }

    public static void compararStringIguales(String[] valorUno, String... valorDos) throws Myexception {
        for (int i = 0; i < valorUno.length; i++)
            if (valorUno[i].equals(valorDos[i]))
                throw new Myexception("Los valores no son iguales");
    }

    public static void comboBoxVacio(ComboBox... comboBoxes) throws Myexception {
        for (ComboBox comboBox : comboBoxes) {
            boolean isMyComboBoxEmpty = comboBox.getSelectionModel().isEmpty();
            if (isMyComboBoxEmpty)
                throw new Myexception("Selecciona una opcion");
        }
    }

    public static void datePickerVacio(DatePicker... datePickers) throws Myexception {
        for (DatePicker datePicker : datePickers) {
            if (datePicker.getValue() == null)
                throw new Myexception("Fecha No correcta");
        }
    }

    public static void datePickerRango(Date value) throws Myexception, ParseException {
        int years = FechaUtil.getYearsDiff(value);
        if (years <= 18 || years >= 50)
            throw new Myexception("Debe tener una edad entre 18 y 50");
    }

    public static void compararStringIguales(String[] valorUno, TextField... valorDos) throws Myexception {
        for (int i = 0; i < valorUno.length; i++) {
            String text = valorDos[i].getText();
            if (!valorUno[i].equals(text))
                throw new Myexception("Los valores no son iguales");
        }
    }

    public static String quitarEspaciosVacios(TextInputControl txt) throws Myexception {
        return txt.getText().replaceAll("\\s+", "");
    }

    private static void soloNumero(TextInputControl txt) throws Myexception {
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            // if (!newValue.matches("\\d*")) {
            if (!newValue.matches("[0-9]*")) {
                txt.setText(newValue.replaceAll("[0-9]*", ""));
            }
        });
    }

    private static void isNumerico(TextInputControl txt) throws Myexception {
        txt.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!txt.getText().matches("[1-5](\\.[0-9]{1,2}){0,1}|6(\\.0{1,2}){0,1}")) {
                    // when it not matches the pattern (1.0 - 6.0)
                    // set the txt empty
                    txt.setText("");
                }
            }
        });
    }

    public static void isNumber(String... number) throws Myexception {
        for (String s : number) {
            if (!s.matches("[0-9]+")) {
                throw new Myexception("El campo no puede contener texto");
            }
        }
    }

    public static void isLetter(String... name) throws Myexception {
        for (String s : name) {
            if (!s.matches("^[\\p{L} .'-]+$"))
                throw new Myexception("El campo no puede contener numeros");
        }
    }

    public static void isLetterSpeed(String... name) throws Myexception {
        for (String s : name) {
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (Character.isLetter(c))
                    throw new Myexception("El campo no puede contener numeros");
            }
        }
    }

    /* Numeric Validation Limit the  characters to maxLengh AND to ONLY DigitS *************************************/
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) throws Myexception {
        return e -> {
            TextField txt_TextField = (TextField) e.getSource();
            if (txt_TextField.getText().length() >= max_Lengh) {
                e.consume();
            }
            if (e.getCharacter().matches("[0-9.]")) {
                if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                    e.consume();
                } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                    e.consume();
                }
            } else {
                e.consume();
            }
        };
    }

    /*****************************************************************************************/

 /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) throws Myexception {
        return e -> {
            TextField txt_TextField = (TextField) e.getSource();
            if (txt_TextField.getText().length() >= max_Lengh) {
                e.consume();
            }
            if (e.getCharacter().matches("[A-Za-z]")) {
            } else {
                e.consume();
            }
        };
    }
}
