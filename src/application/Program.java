package application;

import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Departamento departamento = new Departamento(1,"Book");

        Vendedor vendedor = new Vendedor(21,"Victor","victxl@gamil.com",new Date(),3000,departamento);

        System.out.println(departamento);
        System.out.println(vendedor);


    }
}
