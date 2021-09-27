package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

public class AccidentJdbcTemplate {
    
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Accident> getAccidents() {
        List<Accident> rsl = jdbc.query("select * from accident", (rsAc, rowAc) -> {
            Accident accident = new Accident(
                    rsAc.getInt("id"),
                    rsAc.getString("name"),
                    rsAc.getString("description"),
                    rsAc.getString("address")
            );
            accident.setType(jdbc.queryForObject("select * from type where id = ?", (rsType, rowType) ->
                    AccidentType.of(
                            rsType.getInt("id"),
                            rsType.getString("name")
                    ), rsAc.getInt("accident_type_id")));
            for (Integer ruleId :
                    jdbc.query("select * from accident_rule where accident_id = ?", (rsAcRule, rowAcRule) ->
                            rsAcRule.getInt("rule_id"), accident.getId())) {
                accident.addRule(jdbc.queryForObject("select * from rule where id = ?", (rs, row) ->
                        Rule.of(
                                rs.getInt("id"),
                                rs.getString("name")
                        ), ruleId));
            }
            return accident;
        });
        return rsl;
    }

    public Collection<AccidentType> getTypes() {
        return jdbc.query("select * from type", (rs, row) ->
                AccidentType.of(rs.getInt("id"), rs.getString("name"))
        );
    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("select * from type where id =?", (rs, row) ->
                AccidentType.of(rs.getInt("id"), rs.getString("name")), id
        );
    }

    public Accident findAccidentById(int id) {
        Accident rsl = jdbc.queryForObject("select * from accident where id = ?", (rsAc, rowAc) -> {
            Accident accident = new Accident(
                    rsAc.getInt("id"),
                    rsAc.getString("name"),
                    rsAc.getString("description"),
                    rsAc.getString("address")
            );
            accident.setType(jdbc.queryForObject("select * from type where id = ?", (rsType, rowType) ->
                    AccidentType.of(
                            rsType.getInt("id"),
                            rsType.getString("name")
                    ), rsAc.getInt("accident_type_id")));
            for (Integer ruleId :
                    jdbc.query("select * from accident_rule where accident_id = ?", (rsAcRule, rowAcRule) ->
                            rsAcRule.getInt("rule_id"), accident.getId())) {
                accident.addRule(jdbc.queryForObject("select * from rule where id = ?", (rs, row) ->
                        Rule.of(
                                rs.getInt("id"),
                                rs.getString("name")
                        ), ruleId));
            }
            return accident;
        }, id);
        return rsl;
    }

    public Collection<Rule> getRules() {
        return jdbc.query("select * from rule", (rs, row) ->
                Rule.of(rs.getInt("id"), rs.getString("name"))
        );
    }

    public void update(Accident accident) {
        jdbc.update("update accident set name=?, description=?, address=?, accident_type_id=? where id=?",
                accident.getName(),
                accident.getDescription(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        jdbc.update("delete from accident_rule where accident_id = ?", accident.getId());
        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "insert into accident_rule(accident_id, rule_id) values(?, ?)",
                    accident.getId(),
                    rule.getId()
            );
        }
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject("select * from rule where id = ?", (rs, row) ->
                Rule.of(rs.getInt("id"), rs.getString("name")), id
        );

    }

    public void add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident(name, description, address, accident_type_id) values(?, ?, ?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getDescription());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((Integer) keyHolder.getKey());
        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "insert into accident_rule(accident_id, rule_id) values(?, ?)",
                    accident.getId(),
                    rule.getId()
            );
        }
    }
}
