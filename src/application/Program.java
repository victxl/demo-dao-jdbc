package application;

import model.doa.DaoFactory;
import model.doa.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.Date;

public class Program {
    public static void main(String[] args) {



        VendedorDao vendedorDao = DaoFactory.createVendedorDao();
        System.out.println("***** TESTE 1: findByID VENDEDOR *****");
        Vendedor vendedor = vendedorDao.findById(1);


        System.out.println(vendedor);


    }
}
