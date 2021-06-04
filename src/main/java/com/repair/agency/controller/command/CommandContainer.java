package com.repair.agency.controller.command;

import com.repair.agency.controller.command.admin.*;
import com.repair.agency.controller.command.engineer.EngineerPageCommand;
import com.repair.agency.controller.command.user.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("logOut", new LogoutCommand());
        commands.put("login", new LoginPageCommand());
        commands.put("register", new RegisterPageCommand());
        commands.put("account", new ValidationPageCommand());
        commands.put("registerSuccess", new RegisterSuccessPageCommand());
        commands.put("selectInvoice", new InvoicePageCommand());
        commands.put("topUpAccount", new TopUpCommand());
        commands.put("adminPage", new AdminPageCommand());
        commands.put("engineerPage", new EngineerPageCommand());
        commands.put("error", new ErrorPageCommand());
        commands.put("addNewInvoice", new AddInvoiceCommand());
        commands.put("openNewInvoice", new OpenInvoiceCommand());
        commands.put("userPage", new UserPageCommand());
        commands.put("feedback", new UserFeedbackCommand());
        commands.put("thankYou", new ThankYouCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("error");
        }
        return commands.get(commandName);
    }

}
