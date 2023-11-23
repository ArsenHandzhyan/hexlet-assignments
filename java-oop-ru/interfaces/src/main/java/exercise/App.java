package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int elements) {
        // Сортируем список объектов по площади
        apartments.sort(Comparator.comparingDouble(Home::getArea));

        // Берем первые n элементов
        List<Home> selectedApartments = apartments.subList(0, Math.min(elements, apartments.size()));

        // Создаем список строковых представлений
        List<String> result = new ArrayList<>();
        for (Home apartment : selectedApartments) {
            result.add(apartment.toString());
        }

        return result;
    }
}
