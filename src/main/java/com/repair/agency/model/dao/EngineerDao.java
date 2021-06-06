package com.repair.agency.model.dao;

import com.repair.agency.model.entity.Invoice;
import com.repair.agency.model.entity.User;

import java.util.List;

public interface EngineerDao extends GenericDao {
    List<User> getAllEngineers();
    List<Invoice> getInvoicesByEmail(String engineerEmail);
}
