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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public VendedorDaoJDBC() {

    }

    @Override
    public void insertVendedor(Vendedor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO vendedor (Nome, Email, DataNascimento, SalarioBase, IdDepartamento) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
            st.setDouble(4, obj.getSalario());
            st.setInt(5, obj.getDepartamento().getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro ao inserir vendedor");
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao inserir novo vendedor: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void updateVendedor(Vendedor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
            "UPDATE vendedor set Nome = ?, Email = ?, DataNascimento = ?, SalarioBase = ?, IdDepartamento = ? WHERE Id = ?");

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
            st.setDouble(4, obj.getSalario());
            st.setInt(5, obj.getDepartamento().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao inserir novo vendedor: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteVendedor(Vendedor obj) {

    }

    @Override
    public Vendedor findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(

                    "SELECT vendedor.*, departamento.Nome AS NomeDep " +
                            "FROM vendedor JOIN departamento " +
                            "ON vendedor.idDepartamento = departamento.id " +
                            "WHERE vendedor.id = ?");


            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {

                Departamento dep = instanciarDepartamento(rs);

                Vendedor obj = instanciarVendedor(rs, dep);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Nome"));
        obj.setEmail(rs.getString("Email"));
        obj.setSalario(rs.getDouble("SalarioBase"));
        obj.setDataNascimento(rs.getDate("DataNascimento"));
        obj.setDepartamento(dep);
        return obj;
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("IdDepartamento"));
        dep.setNome(rs.getString("NomeDep"));
        return dep;
    }

    @Override
    public List<Vendedor> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT vendedor.*, departamento.Nome AS NomeDep " +
                            "FROM vendedor JOIN departamento " +
                            "ON vendedor.idDepartamento = departamento.id " +
                            "ORDER BY Nome"
            );

            rs = st.executeQuery();

            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {
                Departamento dep = map.get(rs.getInt("IdDepartamento"));

                if (dep == null) {
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("IdDepartamento"), dep);
                }

                Vendedor obj = instanciarVendedor(rs, dep);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> findByDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT vendedor.*, departamento.Nome AS NomeDep " +
                            "FROM vendedor JOIN departamento " +
                            "ON vendedor.idDepartamento = departamento.id " +
                            "WHERE idDepartamento = ? " +
                            "ORDER BY Nome"
            );

            st.setInt(1, departamento.getId());

            rs = st.executeQuery();

            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()) {
                Departamento dep = map.get(rs.getInt("IdDepartamento"));

                if (dep == null) {
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("IdDepartamento"), dep);
                }

                Vendedor obj = instanciarVendedor(rs, dep);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
