package com.zup.crud.mapper;

import com.zup.crud.entities.User;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int arg1)throws SQLException {

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setNome(rs.getString("nome"));
        user.setIdade(rs.getInt("idade"));
        user.setEndereco(rs.getString("endereco"));
        user.setCPF(rs.getString("CPF"));
        user.setTelefone(rs.getInt("telefone"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
