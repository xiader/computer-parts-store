package com.gmail.sasha.servlets;



import com.gmail.sasha.servlets.command.Command;
import com.gmail.sasha.servlets.command.impl.ItemsCommand;
import com.gmail.sasha.servlets.command.impl.LoginCommand;
import com.gmail.sasha.servlets.command.impl.UsersCommand;
import com.gmail.sasha.servlets.filter.AuthenticationFilter;
import com.gmail.sasha.servlets.model.CommandEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);
    private static final Map<CommandEnum, Command> commands = new HashMap<>();

    @Override
    public void init() {
        logger.info("DispatcherServlet init!");
        commands.put(CommandEnum.LOGIN, new LoginCommand());
        commands.put(CommandEnum.USERS, new UsersCommand());
        commands.put(CommandEnum.ITEMS, new ItemsCommand());
    }

    @Override
    public void destroy() {
        logger.info("DispatcherServlet destroy!");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        Command commandAction = null;
        try {
            commandAction = commands.get(CommandEnum.getCommand(command));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
        if (commandAction != null) {
            try {
                String page = commandAction.execute(request, response);
                if (page != null) {
                    getServletContext().getRequestDispatcher(page).forward(request, response);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            logger.info("Command " + command + " does not exists!");
        }
    }
}
