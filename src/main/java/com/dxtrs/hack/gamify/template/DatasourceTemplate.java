package com.dxtrs.hack.gamify.template;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatasourceTemplate {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatasourceTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
