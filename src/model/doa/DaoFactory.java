package model.doa;

import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {

    public static VendedorDao createVendedorDao() {
        return new VendedorDaoJDBC(DB.getConnection());
    }

    public static DepartamentoDao createDepartamentoDao() {
        return new DepartamentoDaoJDBC(DB.getConnection());
    }
}
