package com.repair.agency.model.dao.constanse;

public interface SqlConst {
    // --------------- ADMIN SQL STATEMENTS --------------//
    String SET_USERS_WALLET = "UPDATE user SET wallet=? WHERE id=?";
    String UPDATE_INVOICE_ENGINEER = "UPDATE invoice SET engineer_id=? WHERE invoice_id=?";
    String UPDATE_INVOICE_PRICE = "UPDATE invoice SET price=? WHERE invoice_id=?";
    String UPDATE_INVOICE_STATUS = "UPDATE invoice SET status=? WHERE invoice_id=?";
    String SELECT_INVOICES_BY_STATUS = "SELECT * FROM invoice WHERE status=?";
    String FINED_INVOICE_BY_ID = "SELECT * FROM user inner JOIN invoice " +
            "ON invoice.invoice_id = ? and user.id = invoice.user_id or invoice.invoice_id = ? and user.id = invoice.engineer_id;";
    String SELECT_INVOICES = "SELECT * FROM invoice";
    String SELECT_ALL_USERS = "SELECT * FROM user WHERE role='USER'";
    // --------------- ENGINEER SQL STATEMENTS --------------//
    String GET_ENGINEERS = "SELECT * FROM user WHERE role = 'ENGINEER'";
    String GET_INVOICES_BY_EMAIL = "SELECT * FROM invoice WHERE engineer_id IN(SELECT id FROM user WHERE email=?)";
    // --------------- USER SQL STATEMENTS --------------//
    String SELECT_INVOICES_BY_EMAIL = "SELECT * FROM invoice WHERE user_id IN (SELECT id FROM user WHERE email=?)";
    String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";
    String GET_ENGINEER_BY_ID = "SELECT login FROM user WHERE id=?";
    String CREATE_NEW_INVOICE = "INSERT INTO invoice (brand, model, description, user_id) VALUES (?, ?, ?, (SELECT id FROM user WHERE email=?))";
    String INSERT_FEEDBACK = "UPDATE invoice SET feedback=? WHERE invoice_id=?";
    String FINED_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    String UPDATE_WALLET_BY_LOGIN = "UPDATE user SET wallet=? WHERE login=?";
    String FINED_USER = "SELECT * FROM user WHERE email = ? and password = ?";
    String INSERT_USER = "INSERT INTO user (email, login, password) VALUES (?, ?, ?)";
}
