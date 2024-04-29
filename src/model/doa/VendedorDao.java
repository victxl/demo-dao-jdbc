package model.doa;

import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.List;

public interface VendedorDao {

    void insertVendedor(Vendedor obj);
    void updateVendedor(Vendedor obj);
    void deleteVendedor(int obj);
    Vendedor findById(int id);
    List<Vendedor> findAll();
    List<Vendedor> findByDepartamento(Departamento departamento);


}
