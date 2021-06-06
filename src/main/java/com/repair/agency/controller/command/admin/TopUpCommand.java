package com.repair.agency.controller.command.admin;

import com.repair.agency.Path;
import com.repair.agency.controller.command.Command;
import com.repair.agency.model.dao.jdbc.JdbcAdminDao;
import com.repair.agency.model.dao.service.AdminService;
import com.repair.agency.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TopUpCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // JdbcAdminDao adminDao = new JdbcAdminDao();
        AdminService adminService = new AdminService();
        String wallet = request.getParameter("usersWallet");
        String usersId = request.getParameter("usersId");
        String usersLogin = request.getParameter("usersLogin");
        String editUsersWallet = request.getParameter("usersEditWallet");

        if (editUsersWallet != null && usersId != null) {
           boolean result = adminService.setUsersWallet(usersId, editUsersWallet);
           wallet = result ? editUsersWallet + ".00"
                   : "input mistake";
        }
        if (wallet == null) {
            wallet = "no user selected";
        }
        if (usersLogin == null) {
            usersLogin = "User";
        }
        if (!(usersId == null)) {
            request.setAttribute("currentId", usersId);
        }
        List<User> users = adminService.getAllUsers();

        request.setAttribute("currentUser", usersLogin);
        request.setAttribute("currentWallet", wallet);
        request.setAttribute("users", users);
        return Path.TOP_UP_PAGE;
    }
}
