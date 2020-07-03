package com.zup.crud.dao;

import com.zup.crud.entities.User;
import com.zup.crud.mapper.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImplPostgre implements UserDAO {

    public UserDAOImplPostgre(NamedParameterJdbcTemplate template){
        this.template = template;
    }
    NamedParameterJdbcTemplate template;


    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM tabUser", new UserRowMapper());
    }

    @Override
    public void insertUser(User user) {
        final String sql = "insert into tabUser(nome, idade, CPF, email, telefone, endereco)values(:nome,:idade,:CPF,:email,:telefone,:endereco)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("nome", user.getNome())
                .addValue("idade", user.getIdade())
                .addValue("CPF", user.getCPF())
                .addValue("email", user.getEmail())
                .addValue("telefone", user.getTelefone())
                .addValue("endereco", user.getEndereco());
        template.update(sql, param, holder);
    }

    @Override
    public void updateUser(User user) {
        final String sql = "update tabUser set nome=:nome, idade=:idade, email=:email, telefone=:telefone, endereco=:endereco where id=:id";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("nome", user.getNome())
                .addValue("idade", user.getNome())
                .addValue("email", user.getEmail())
                .addValue("telefone", user.getTelefone())
                .addValue("endereco", user.getEndereco());
        template.update(sql, param, holder);
    }

    @Override
    public void executeUpdateUser(User user) {
        final String sql = "update tabUser " +
                "set nome=:nome, idade=:idade, email=:email, telefone=:telefone, endereco=:endereco " +
                "where id=:id";

        Map<String, Object> map= new HashMap<String, Object>();
        map.put("id", user.getId());
        map.put("nome", user.getNome());
        map.put("idade", user.getIdade());
        map.put("email", user.getEmail());
        map.put("telefone", user.getTelefone());
        map.put("endereco", user.getEndereco());

        template.execute(sql, map, new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });

    }

    @Override
    public void deleteUser(User user) {
        final String sql = "delete from tabUser where id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", user.getId());

        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
