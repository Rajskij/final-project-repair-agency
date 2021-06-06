package com.repair.agency.model.dao;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao extends GenericDao {
    User findUser(String email, String password);

    User findUserByLogin(String login);

    boolean insertUser(String email, String login, String password);

    List<Invoice> selectInvoicesByEmail(String email);

    boolean insertInvoice(String brand, String model, String description, String email);

    boolean insertFeedback(String comment, String id);

    boolean updateWalletByLogin(String invoiceUser, BigDecimal price);

    User getUserByEmail(String email);
}
