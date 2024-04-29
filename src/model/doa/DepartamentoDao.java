package model.doa;

import model.entities.Departamento;

import java.util.List;

public interface DepartamentoDao {

    void insert(Departamento obj);
    void update(Departamento obj);
    void delete(int id);
    Departamento findById(int id);
    List<Departamento> findAll();
}
