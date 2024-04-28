package model.dao.impl;

import db.DB;
import db.DbException;
import model.doa.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendedorDaoJDBC  implements VendedorDao {

    private Connection connection;

    public VendedorDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    public VendedorDaoJDBC() {

    }

    @Override
    public void insertVendedor(Vendedor obj) {

    }

    @Override
    public void updateVendedor(Vendedor obj) {

    }

    @Override
    public void deleteVendedor(Vendedor obj) {

    }

    @Override
    public Vendedor findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "select vendedor.*, departamento.Nome as NomeDep" +
                            "from vendedor INNER JOIN departamento"+
                            "on vendedor.idDepartamento = departamento.id"+
                            "WHERE vendedor.id = ?");

                st.setInt(1,id);
                rs = st.executeQuery();
                if(rs.next()) {
                    Departamento dep = new Departamento();
                    dep.setId(rs.getInt("IdDepartamento"));
                    dep.setNome(rs.getString("NomeDep"));

                    Vendedor obj = new Vendedor();
                    obj.setId(rs.getInt("Id"));
                    obj.setNome(rs.getString("Nome"));
                    obj.setEmail(rs.getString("Email"));
                    obj.setSalario(rs.getDouble("Salario"));
                    obj.setDataNascimento(rs.getDate("DataNascimento"));
                    obj.setDepartamento(dep);
                    return obj;
                }
                return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Vendedor> findAll() {
        return List.of();
    }
}
