package portfolio.tracker.Common;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonMethods {

    public String getExceptionMessageForAddUpdate() {
    String message = "You cannot sell more than you have.";
    return message;}

    public String getExceptionMessageForDelete() {
        String message = "You cannot delete the trade because total quantity of the " +
                "security should always be positive.";
        return message;}

    public String getExceptionMessageWhenSecurityTickerIsUpdated(String existingTradeTicker) {
        String message = "You cannot update the trade because " + existingTradeTicker +
                " total quantity will get negative.";
        return message;}
}
