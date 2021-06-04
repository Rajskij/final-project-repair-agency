package com.repair.agency.model.service;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {

    public Invoice getInvoice(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("invoice_id"));
        invoice.setBrand(rs.getString("brand"));
        invoice.setModel(rs.getString("model"));
        invoice.setDescription(rs.getString("description"));
        invoice.setPrice(rs.getBigDecimal("price"));
        invoice.setFeedback(rs.getString("feedback"));
        invoice.setUser_id(rs.getInt("user_id"));
        invoice.setEngineer_id(rs.getInt("engineer_id"));
        invoice.setStatus(rs.getString("status"));
        return invoice;
    }

    public User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setWallet(rs.getBigDecimal("wallet"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
