package com.repair.agency.model.dao.service;

import com.repair.agency.model.dao.DaoFactory;
import com.repair.agency.model.dao.EngineerDao;
import com.repair.agency.model.dao.jdbc.JdbcAdminDao;
import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EngineerService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogger(JdbcAdminDao.class.getName());


    public List<User> getAllEngineers() {
        List<User> engineers = new ArrayList<>();
        try (EngineerDao engineerDao = daoFactory.createEngineerDao()) {
            engineers = engineerDao.getAllEngineers();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return engineers;
    }

    public List<Invoice> getInvoicesByEmail(String engineerEmail) {
        List<Invoice> invoiceList = new ArrayList<>();
        try (EngineerDao engineerDao = daoFactory.createEngineerDao()) {
            invoiceList = engineerDao.getInvoicesByEmail(engineerEmail);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return invoiceList;
    }
}
