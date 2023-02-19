package next.dao;

import java.sql.SQLException;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import next.dao.sql.UserSql;
import next.dao.template.DataAccessException;
import next.dao.template.JdbcTemplate;
import next.model.User;

@AllArgsConstructor
@Slf4j
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    public void insert(User user) {
        jdbcTemplate.update(UserSql.CREATE, (preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
        }));
    }
    public User findByUserId(String userId) {
        return jdbcTemplate.queryForObject(UserSql.FIND_USER_BY_ID, preparedStatement -> preparedStatement.setString(1,userId),
                resultSet -> User.of(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                )
        );
    }
    public List<User> findAll() {
        return jdbcTemplate.query(UserSql.FIND_ALL, preparedStatement -> {},
                resultSet -> User.of(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                )
        );
    }
    public void update(final User user) {
        jdbcTemplate.update(UserSql.UPDATE, preparedStatement -> {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserId());
        });
    }


}
