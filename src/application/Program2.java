package application;

import model.doa.DaoFactory;
import model.doa.DepartamentoDao;
import model.doa.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("*****      TESTE 1: findByID DEPARTAMENTO      *****");
        Departamento departamento = departamentoDao.findById(1);
        System.out.println(departamento);
        System.out.println();

        System.out.println("*****      TESTE 2: findALL DEPARTAMENTO      *****");
        List<Departamento> departamentos = departamentoDao.findAll();
        for (Departamento obj : departamentos) {
            System.out.println(obj);
        }
        System.out.println();

        System.out.println("*****      TESTE 3: insert DEPARTAMENTO      *****");
        Departamento novoDepartamento = new Departamento(null, "novoDepartamento");
        departamentoDao.insert(novoDepartamento);
        System.out.println(novoDepartamento);
        System.out.println();

        System.out.println("*****      TESTE 4: update DEPARTAMENTO      *****");
        departamento = departamentoDao.findById(5);
        departamento.setNome("Updated Departamento");
        departamentoDao.update(departamento);
        System.out.println("Update feito "+departamento.getNome());
        System.out.println();

        System.out.println("*****      TESTE 5: delete DEPARTAMENTO      *****");
        System.out.println("Digite o id do departaemnto para deletar");
        int id = sc.nextInt();
        departamentoDao.delete(id);
        System.out.println("Departamento deletado com sucesso");
        System.out.println();
    }
}


