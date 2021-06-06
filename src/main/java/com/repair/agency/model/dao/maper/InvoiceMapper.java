package com.repair.agency.model.dao.maper;

import com.repair.agency.model.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper implements ObjectMapper{
    @Override
    public Invoice mapFromResultSet(ResultSet rs) throws SQLException {
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
        invoice.setDate(rs.getString("date"));
        return invoice;
    }
}
