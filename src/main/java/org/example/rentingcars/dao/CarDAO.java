package org.example.rentingcars.dao;

import org.example.rentingcars.source.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.http.HttpHeaders.FROM;

@Component
public class CarDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Car car) {
        jdbcTemplate.update("INSERT INTO cars(type, city, price, mark, model, file_main_view, file_back_view, file_left_view, file_into_view) VALUES (?,?,?,?,?,?,?,?,?)",
                car.getType(), car.getCity(), car.getPrice(), car.getMark(), car.getModel(),
                car.getFile_main_view(), car.getFile_back_view(), car.getFile_left_view(), car.getFile_into_view());
    }

    public List<Car> getMainInfo() {
        return jdbcTemplate.query("SELECT id_car, city, mark, model, price, file_main_view FROM cars", (rs, rowNum) -> {
            Car car = new Car();
            car.setId_car(rs.getInt("id_car"));
            car.setCity(rs.getString("city"));
            car.setMark(rs.getString("mark"));
            car.setModel(rs.getString("model"));
            car.setPrice(rs.getDouble("price"));
            car.setFile_main_view(rs.getString("file_main_view"));
            return car;
        });
    }

    public Car showById(int id) {
        return jdbcTemplate.query("SELECT * FROM cars WHERE id_car = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Car.class)).stream().findAny().orElse(null);
    }

    public List<Car> getAllSportCars() {
        return jdbcTemplate.query(
                "SELECT * FROM cars WHERE type = 'Спорт'",
                new BeanPropertyRowMapper<>(Car.class)
        );
    }

    public List<Car> getAllPrimeCars() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE type = 'Премиум'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getAllLightCars() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE type = 'Седан'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getAllSuvCars() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE type = 'Внедорожник'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getElectroCars() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE type = 'Электро'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getCabrioCars() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE type = 'Кабриолет'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getSpecialOneCar() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE model = 'Escalade V'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getSpecialTwoCar() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE model = 'AMG GT 43'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getSpecialThreeCar() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE model = 'X7 M-SPORT'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getSpecialFourCar() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE model = 'A7'",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getSpecialFiveCar() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE model = 'l9 '",
                new BeanPropertyRowMapper<>(Car.class));
    }

    public List<Car> getSpecialSixCar() {
        return jdbcTemplate.query("SELECT * FROM cars WHERE model = 'Camry 70'",
                new BeanPropertyRowMapper<>(Car.class));
    }
}