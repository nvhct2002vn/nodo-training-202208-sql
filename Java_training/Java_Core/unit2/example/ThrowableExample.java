package demo.unit2.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThrowableExample {

    private final static Logger LOGGER = Logger.getLogger(ThrowableExample.class.getName());

    public static int toNumber(String value) throws SaiSoException {
        try {
            Integer integer = Integer.parseInt(value);
            return integer.intValue();
        } catch (NumberFormatException e) {
            throw new SaiSoException(value);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("number = " + toNumber("12"));
        } catch (SaiSoException e) {
//            System.out.println(e.getMessage());
            LOGGER.log(Level.WARNING, e.getMessage());
        }

    }
}
