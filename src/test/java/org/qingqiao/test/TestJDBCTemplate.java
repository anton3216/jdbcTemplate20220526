package org.qingqiao.test;

import org.junit.Before;
import org.junit.Test;
import org.qingqiao.bean.User;
import org.qingqiao.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class TestJDBCTemplate {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        jdbcTemplate = ctx.getBean(JdbcTemplate.class);
    }

    @Test
    public void testInsert(){
        int i = jdbcTemplate.update("insert into user values(null,?,?)", "liujiajia", "jinan");
        System.out.println(i);
    }

    @Test
    public void testUpdate(){
        int i = jdbcTemplate.update("update user set username=?,address=? where id=?", "刘佳佳", "济南", 1);
        System.out.println(i);
    }

    @Test
    public void testDelete(){
        int i = jdbcTemplate.update("delete from user where id = ?",1);
        System.out.println(i);
    }

    @Test
    public void testQueryAll(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        for (Map<String, Object> map : maps) {
            System.out.print(map.get("id"));
            System.out.print(map.get("username"));
            System.out.print(map.get("address"));
            System.out.println();
        }
    }

    @Test
    public void testQueryById(){
        User user = jdbcTemplate.queryForObject("select * from user where id = ?", new BeanPropertyRowMapper<User>(User.class), 1);
        System.out.println(user);
    }
}
