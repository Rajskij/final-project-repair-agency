package com.repair.agency.model.dao;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface UserDao extends GenericDao{
    public List<User> getAllEngineers();

    public List<Invoice> getInvoicesByEmail(String engineerEmail);
    boolean updateInvoiceEngineer(int engineerId, int invoiceId);

    boolean updateInvoicePrice(String price, int invoiceId);

    boolean updateInvoiceStatus(String status, int invoiceId);

    List<Invoice> selectAllInvoices();

    Invoice getInvoiceById(int id);

    List<User> getAllUsers();

    boolean setUsersWallet(String userId, String walletValue);

    List<Invoice> getInvoiceByStatus(String status);
}
