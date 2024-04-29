package application;

import model.doa.DaoFactory;
import model.doa.VendedorDao;

public class Program2 {
    public static void main(String[] args) {

        VendedorDao vendedorDao = DaoFactory.createVendedorDao();
    }
}
