package com.repair.agency.model.dao.service;

import com.repair.agency.model.dao.DaoFactory;
import com.repair.agency.model.dao.UserDao;
import com.repair.agency.model.dao.jdbc.JdbcAdminDao;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(JdbcAdminDao.class.getName());

    public User findUser(String email, String password) {
        User user = new User();
        try (UserDao userDao = daoFactory.createUserDao()) {
            user = userDao.findUser(email, password);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    public User findUserByLogin(String login) {
        User user = new User();
        try (UserDao userDao = daoFactory.createUserDao()) {
            user = userDao.findUserByLogin(login);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    public boolean insertUser(String email, String login, String password) {
        boolean result = false;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.insertUser(email, login, password);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public List<Invoice> selectInvoicesByEmail(String email) {
        List<Invoice> userInvoicesList = new ArrayList<>();
        try (UserDao userDao = daoFactory.createUserDao()) {
            userInvoicesList = userDao.selectInvoicesByEmail(email);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return userInvoicesList;
    }

    public boolean insertInvoice(String brand, String model, String description, String email) {
        boolean result = false;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.insertInvoice(brand, model, description, email);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean insertFeedback(String comment, String id) {
        boolean result = false;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.insertFeedback(comment, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean updateWalletByLogin(String invoiceUser, BigDecimal price) {
        boolean result = false;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.updateWalletByLogin(invoiceUser, price);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        try (UserDao userDao = daoFactory.createUserDao()) {
            user = userDao.getUserByEmail(email);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }
}
