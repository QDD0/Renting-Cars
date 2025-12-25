package org.example.rentingcars.dao;

import org.example.rentingcars.source.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return jdbcTemplate.query("SELECT id_car, mark, model, price, file_main_view FROM cars", (rs, rowNum) -> {
            Car car = new Car();
            car.setId_car(rs.getInt("id_car"));
            car.setMark(rs.getString("mark"));
            car.setModel(rs.getString("model"));
            car.setPrice(rs.getDouble("price"));
            car.setFile_main_view(rs.getString("file_main_view"));
            return car;
        });
    }
}