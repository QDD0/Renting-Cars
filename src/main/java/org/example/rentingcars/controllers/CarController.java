package org.example.rentingcars.controllers;

import org.example.rentingcars.dao.CarDAO;
import org.example.rentingcars.source.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class CarController {
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private CarDAO carDAO;

    @GetMapping("/catalog")
    public String index(Model model) {
        List<Car> cars = carDAO.getMainInfo();
        model.addAttribute("cars", cars);
        return "catalog";
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadCar(
            @RequestParam("type") String type,
            @RequestParam("city") String city,
            @RequestParam("price") double price,
            @RequestParam("mark") String mark,
            @RequestParam("model") String model,
            @RequestParam("file_main_view") MultipartFile mainFile,
            @RequestParam("file_back_view") MultipartFile backFile,
            @RequestParam("file_left_view") MultipartFile leftFile,
            @RequestParam("file_into_view") MultipartFile intoFile,
            Model modelAttr) throws IOException {

        if (mark.isEmpty() || model.isEmpty() || mainFile.isEmpty()) {
            modelAttr.addAttribute("error", "Заполните все обязательные поля");
            return "upload";
        }

        Car car = new Car();
        car.setType(type);
        car.setCity(city);
        car.setPrice(price);
        car.setMark(mark);
        car.setModel(model);

        String mainFilePath = saveFile(mainFile);
        car.setFile_main_view(mainFilePath);

        if (!backFile.isEmpty()) {
            car.setFile_back_view(saveFile(backFile));
        }

        if (!leftFile.isEmpty()) {
            car.setFile_left_view(saveFile(leftFile));
        }

        if (!intoFile.isEmpty()) {
            car.setFile_into_view(saveFile(intoFile));
        }

        carDAO.save(car);

        modelAttr.addAttribute("success", "Автомобиль успешно добавлен!");
        return "upload";
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.write(filePath, file.getBytes());

        return uniqueFilename;
    }

    @GetMapping("/catalog/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Car car = carDAO.showById(id);

        if (car != null) {
            System.out.println("Найден автомобиль: " + car.getMark() + " " + car.getModel());
            System.out.println("file_main_view: " + car.getFile_main_view());
            System.out.println("file_back_view: " + car.getFile_back_view());
            System.out.println("file_left_view: " + car.getFile_left_view());
            System.out.println("file_into_view: " + car.getFile_into_view());

            model.addAttribute("car", car);
            return "carId";
        } else {
            System.out.println("Автомобиль с id=" + id + " не найден");
            return "redirect:/catalog";
        }
    }

    @GetMapping("/catalog/sport")
    public String sportPage(Model model) {
        List<Car> cars = carDAO.getAllSportCars();  // ← Теперь получаем ВЕСЬ список
        model.addAttribute("cars", cars);
        return "types";
    }

    @GetMapping("/catalog/prime")
    public String primePage(Model model) {
        List<Car> cars = carDAO.getAllPrimeCars();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/light")
    public String lightPage(Model model) {
        List<Car> cars = carDAO.getAllLightCars();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/suv")
    public String suvPage(Model model) {
        List<Car> cars = carDAO.getAllSuvCars();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/electro")
    public String electroPage(Model model) {
        List<Car> cars = carDAO.getElectroCars();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/cabrio")
    public String cabrioPage(Model model) {
        List<Car> cars = carDAO.getCabrioCars();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/special/cadillac")
    public String firstSpecialPage(Model model) {
        List<Car> cars = carDAO.getSpecialOneCar();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/special/mercedes")
    public String secondSpecialPage(Model model) {
        List<Car> cars = carDAO.getSpecialTwoCar();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/special/bmw")
    public String thirdSpecialPage(Model model) {
        List<Car> cars = carDAO.getSpecialThreeCar();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/special/audi")
    public String fourthSpecialPage(Model model) {
        List<Car> cars = carDAO.getSpecialFourCar();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/special/lixiang")
    public String fifthSpecialPage(Model model) {
        List<Car> cars = carDAO.getSpecialFiveCar();
        model.addAttribute("cars", cars);

        return "types";
    }

    @GetMapping("/catalog/special/toyota")
    public String sixthSpecialPage(Model model) {
        List<Car> cars = carDAO.getSpecialSixCar();
        model.addAttribute("cars", cars);

        return "types";
    }
}