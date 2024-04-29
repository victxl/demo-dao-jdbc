package model.dao.impl;

import model.doa.DepartamentoDao;
import model.entities.Departamento;

import java.sql.Connection;
import java.util.List;

public class DepartamentoDaoJDBC implements DepartamentoDao {
    public DepartamentoDaoJDBC(Connection connection) {
    }

    @Override
    public void insert(Departamento obj) {
        
    }

    @Override
    public void update(Departamento obj) {

    }

    @Override
    public void delete(Departamento obj) {

    }

    @Override
    public Departamento findById(int id) {
        return null;
    }

    @Override
    public List<Departamento> findAll() {
        return List.of();
    }
}
