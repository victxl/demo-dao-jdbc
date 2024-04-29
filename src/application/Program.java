package application;

import model.doa.DaoFactory;
import model.doa.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VendedorDao vendedorDao = DaoFactory.createVendedorDao();

        System.out.println("*****      TESTE 1: findByID VENDEDOR      *****");
        Vendedor vendedor = vendedorDao.findById(3);
        System.out.println(vendedor);

        System.out.println();
        System.out.println("***** TESTE 2: findByDepartamento VENDEDOR *****");
        Departamento departamento = new Departamento(2,null);
        List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
        for(Vendedor v : list){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("***** TESTE 3: findAll VENDEDOR *****");
        list = vendedorDao.findAll();
        for(Vendedor v : list){
            System.out.println(v);
        }

        System.out.println();
        System.out.println("***** TESTE 4: insert VENDEDOR *****");
        Vendedor novoVendedor = new Vendedor(null,"Victor","victxl@gmail.com",new Date(), 4000,departamento);
        vendedorDao.insertVendedor(novoVendedor);
        System.out.println("Vendedor inserido com sucesso! "+novoVendedor.getId());

        System.out.println();
        System.out.println("***** TESTE 5: update VENDEDOR *****");
        vendedor  = vendedorDao.findById(1);
        vendedor.setNome("VICTOR NOVO novo");
        vendedorDao.updateVendedor(vendedor);
        System.out.println("Vendedor atualizado com sucesso! "+vendedor.getNome());


        System.out.println();
        System.out.println("***** TESTE 6: update VENDEDOR *****");
        System.out.println("Digite um id para deletar um vendedor");
        int id = sc.nextInt();
        vendedorDao.deleteVendedor(id);
        System.out.println("Vendedor deletado com sucesso! "+id);
    }
}
