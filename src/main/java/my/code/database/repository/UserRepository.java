package my.code.database.repository;

import jakarta.annotation.PostConstruct;
import my.code.database.entity.Gender;
import my.code.database.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/car_store_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String FIND_ALL_USER_SQL = "select * from users";
    private static final String FIND_BY_ID_USER_SQL = "select * from users where id = ?";
    private static final String FIND_BY_EMAIL_USER_SQL = "select * from users where email = ?";
    private static final String INSERT_USER_SQL = "insert into users (id, first_name, last_name, email, username, password, gender, balance, age) " +
                                                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_SQL = "update users set first_name=?, last_name=?, email=?, username=?, password=?, gender=?, balance=?, age=? where id=?";
    private static final String DELETE_USER_SQL = "delete from users where id = ?";


    @PostConstruct
    public void init() {
        User user = User.builder()
                .id(1L)
                .firstName("TestOne")
                .lastName("UserOne")
                .email("test@mail.com")
                .username("TestUser")
                .password("123")
                .gender(Gender.MALE)
                .balance(20000.00)
                .age(18)
                .build();
//        save(user);
        User byID = findById(user.getId());
        System.out.println(user);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USER_SQL)) {
            connection.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findById(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_USER_SQL)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findByEmail(String email) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_USER_SQL)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, String.valueOf(user.getGender()));
            preparedStatement.setDouble(8, user.getBalance());
            preparedStatement.setInt(9, user.getAge());

            preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user, long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)){
            connection.setAutoCommit(false);


            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, String.valueOf(user.getGender()));
            preparedStatement.setDouble(7, user.getBalance());
            preparedStatement.setInt(8, user.getAge());
            preparedStatement.setLong(9, id);

            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)){
            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User deleted successfully");
            } else {
                System.out.println("No user found with the given id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setGender(Gender.valueOf(resultSet.getString("gender")));
        user.setBalance(resultSet.getDouble("balance"));
        user.setAge(resultSet.getInt("age"));
        return user;
    }


}
