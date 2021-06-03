package com.repair.agency.controller.command;

import com.repair.agency.controller.command.admin.*;
import com.repair.agency.controller.command.engineer.EngineerPageCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandContainer {
    private static final Logger log = Logger.getLogger(String.valueOf(CommandContainer.class));

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("backToMain", new MainPageCommand());
        commands.put("login", new LoginPageCommand());
        commands.put("register", new RegisterPageCommand());
        commands.put("account", new ValidationPageCommand());
        commands.put("registerSuccess", new RegisterSuccessPageCommand());
        commands.put("selectInvoice", new InvoicePageCommand());
        commands.put("topUpAccount", new TopUpCommand());
        commands.put("adminPage", new AdminPageCommand());
        commands.put("engineerPage", new EngineerPageCommand());
        commands.put("error", new ErrorPageCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("error");
        }
        return commands.get(commandName);
    }

}
