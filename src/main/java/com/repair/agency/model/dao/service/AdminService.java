package com.repair.agency.model.dao.service;

import com.repair.agency.model.dao.AdminDao;
import com.repair.agency.model.dao.DaoFactory;
import com.repair.agency.model.dao.jdbc.JdbcAdminDao;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(JdbcAdminDao.class.getName());

    public boolean updateInvoiceEngineer(int engineerId, int invoiceId) {
        boolean result = false;
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            result = adminDao.updateInvoiceEngineer(engineerId, invoiceId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean updateInvoicePrice(String price, int invoiceId) {
        boolean result = false;
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            result = adminDao.updateInvoicePrice(price, invoiceId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean updateInvoiceStatus(String status, int invoiceId) {
        boolean result = false;
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            result = adminDao.updateInvoiceStatus(status, invoiceId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public List<Invoice> selectAllInvoices() {
        List<Invoice> invoicesList = new ArrayList<>();
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            invoicesList = adminDao.selectAllInvoices();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return invoicesList;
    }

    public Invoice getInvoiceById(int id) {
        Invoice invoice = new Invoice();
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            invoice = adminDao.getInvoiceById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return invoice;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            userList = adminDao.getAllUsers();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return userList;
    }

    public boolean setUsersWallet(String userId, String walletValue) {
        boolean result = false;
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            result = adminDao.setUsersWallet(userId, walletValue);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public List<Invoice> getInvoiceByStatus(String status) {
        List<Invoice> invoicesList = new ArrayList<>();
        try (AdminDao adminDao = daoFactory.createAdminDao()) {
            invoicesList = adminDao.getInvoiceByStatus(status);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return invoicesList;
    }
}
