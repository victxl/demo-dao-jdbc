package model.doa;

import model.entities.Vendedor;

import java.util.List;

public interface VendedorDao {

    void insertVendedor(Vendedor obj);
    void updateVendedor(Vendedor obj);
    void deleteVendedor(Vendedor obj);
    Vendedor findById(int id);
    List<Vendedor> findAll();


}
