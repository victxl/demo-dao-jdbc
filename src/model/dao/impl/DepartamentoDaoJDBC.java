package model.dao.impl;

import db.DB;
import db.DbException;
import model.doa.DepartamentoDao;
import model.entities.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoJDBC implements DepartamentoDao {
    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Departamento obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "insert into departamento (nome) values (?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Erro ao inserir o departamento");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void update(Departamento obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE  departamento set Nome = ? WHERE id = ?");

            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }


    }

    @Override
    public void delete(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM departamento WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }


    }

    @Override
    public Departamento findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM cursojava.departamento WHERE id = ? ");
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Departamento dep = instanciarDepartamento(rs);
                return dep;
            } else {
                return null; // Não encontrou nenhum departamento com o ID fornecido
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("id"));
        dep.setNome(rs.getString("nome"));
        return dep;
    }


    @Override
    public List<Departamento> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM cursojava.departamento ORDER BY id ASC ");

            rs = st.executeQuery();

            List<Departamento> departamentos = new ArrayList<>();
            while (rs.next()) {
                Departamento dep = instanciarDepartamento(rs);
                departamentos.add(dep);
            }
            return departamentos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }


}

