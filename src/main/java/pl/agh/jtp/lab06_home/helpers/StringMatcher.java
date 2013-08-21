package pl.agh.jtp.lab06_home.helpers;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class StringMatcher {

    public static boolean match(String command, String regex) {
        return command.matches(regex);
    }
}
