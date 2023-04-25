package ru.croc.task14;

/**
 * Кинотеатр "Места для поцелуев" открыл стриминговый сервис для онлайн-
 * просмотра фильмов. За несколько месяцев работы сервиса накопилась история
 *
 * просмотров разными пользователями и владельцы решили внедрить в него си-
 * стему рекомендаций, которая предлагала бы пользователям интересный фильм
 *
 * на основе их истории просмотров.
 * У вас есть два файла:
 *
 * 1. Список доступных фильмов. Каждая строка содержит числовой идентифи-
 * катор фильма и его название, разделенные запятой. Например:
 *
 *
 * 1,Мстители: Финал
 * 2,Хатико
 * 3,Дюна
 * 4,Унесенные призраками
 *
 *
 * 2. История просмотров по всем пользователям сервиса. Каждая строка файла
 * содержит список идентификаторов фильмов, просмотренных одним человеком за
 *
 * все время пользования сервисом. Идентификаторы разделены запятыми. Напри-
 * мер:
 *
 * 2,1,3
 * 1,4,3
 * 2,2,2,2,2,3
 *
 * На основе этих данных реализуйте алгоритм рекомендаций, который бы для
 * списка просмотров конкретного пользователя рекомендовал следующий фильм.
 * Алгоритм выбора рекомендации:
 *
 * 1. Для просмотров пользователя из историй по всем пользователям выби-
 * раются те, у которых хотя бы половина фильмов совпадает с заданной. (То
 *
 * есть, выбираются все пользователи, которые посмотрели минимум половину
 * фильмов пользователя, для которого формируется рекомендация.)
 *
 * 2. Из отобранных списков исключаются все, которые пользователь уже по-
 * смотрел.
 *
 * 3. Для оставшегося списка фильмов подсчитывается суммарное количество
 * просмотров среди всех пользователей сервиса и фильм с максимальным числом
 *
 * просмотров выбирается как рекомендация (если таких фильмов оказалось не-
 * сколько, выбирается любой из них).
 *
 * Список просмотров текущего пользователя задается через пользовательский
 * ввод, рекомендация выдается в виде названия фильма в System.out. Пути к
 * файлам с названиями фильмов и истории просмотров пользователей сервиса
 * могут быть определены в виде констант в приложении.
 *
 * Пример
 * [in]
 * 2,4
 * [out]
 * Дюна
 *
 *
 */
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
public class Main {

    //this path for windows users!
    private static final String moviesList = "C:\\movies.txt";
    private static final String viewHistory = "C:\\history.txt";

    //this path for mac users
   // private static final String moviesList = "/Users/mike/movies.txt";
   // private static final String viewHistory = "/Users/mike/history.txt";


    /** Записываем из файла в HashMap **/
    static HashMap<Integer,String> filmsToHashMap(String moviesList) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(moviesList));
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла.");
            return null;
        }

        HashMap<Integer,String> films = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] strFilms = scanner.nextLine().split(",");
            films.put(Integer.parseInt(strFilms[0]), strFilms[1]);
        }
        return films;
    }

    /** Записываем из файла в ArrayList **/
    static ArrayList<String> historyToList(String viewHistory) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(viewHistory));
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла. Либо файл не найден");
            return null;
        }

        ArrayList<String> history = new ArrayList<>();
        while (scanner.hasNextLine()) {
            history.add(scanner.nextLine());
        }
        return history;
    }

    /** Выбираем юзеров которые смотрели те же фильмы,
     *  что и выбранный юзер **/
    static List<User> chooseUsers(User userForRecommendation, List<User> users) {
        List<User> usersWatchedSameFilms = new ArrayList<>();
        for (User user : users) {
            if (compareUsers(userForRecommendation, user))
                usersWatchedSameFilms.add(user);
        }

        return usersWatchedSameFilms;

    }

    /** Сравниваем двух юзеров по просмотрам.
     * Возвращаем истину если юзер посмотрел хотя бы половину фильмов другого пользователя. **/
    static boolean compareUsers(User user1, User user2) {
        String[] user1Films = user1.toString().split(",");
        String[] user2Films = user2.toString().split(",");
        Set<String> sameFilms = new HashSet<>();

        for (String film : user1Films) {
            for (String film2 : user2Films) {
                if (film.equals(film2))
                    sameFilms.add(film2);
            }
        }

        if (sameFilms.size() >= Math.round(user1Films.length / 2.0))
            return true;
        return false;
    }


    /** Ищем фильмы-кандидаты исключая те которые смотрел выбранный юзер **/
    static ArrayList<String> candidatesForRecommendation(User userForRecommendation, List<User> choseUsers) {
        ArrayList<String> candidateFilms = new ArrayList<>();
        for (User user : choseUsers) {
            String[] userFilms = user.toString().split(",");
            for (String film : userFilms) {
                if (!userForRecommendation.toString().contains(film))
                    candidateFilms.add(film);
            }
        }
        return candidateFilms;
    }

    /** возвращаю индентификатор фильма для рекомендации **/
    static int recommendFilm(ArrayList<String> candidates) {
        //Ищем часто встречающийся индентификатор
        HashMap<String, Integer> hm = new HashMap<>();
        int max = 1;
        String temp = "";

        for (String candidate : candidates) {
            if (hm.get(candidate) != null) {
                int count = hm.get(candidate);
                count++;
                hm.put(candidate, count);

                if (count>max) {
                    max = count;
                    temp = candidate;
                }
            } else {
                hm.put(candidate,1);
            }
        }
        //когда в списке фильмов кандидатов находится одинаковое количество уникальных фильмов
        try {
            return Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            return hm.get("1");
        }

    }


    public static void main(String[] args) {
       // System.out.println("solution for task 14");

        HashMap<Integer,String> films = filmsToHashMap(moviesList);
        ArrayList<String> history = historyToList(viewHistory);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < history.size(); i++) {
            users.add(new User(history.get(i)));
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите идентификаторы фильмов, которые вы смотрели (через запятую без пробелов): ");
        String inputFilms = sc.next();
        User userForRecommendation = new User(inputFilms);

        List<User> choseUsers = chooseUsers(userForRecommendation, users);
        if (choseUsers.size() == 0) {
            System.out.println("Для Вас нет рекомендаций!");
        } else {
            ArrayList<String> candidateFilms = candidatesForRecommendation(userForRecommendation, choseUsers);
            System.out.println("Рекомендуем посмотреть этот фильм: " + films.get(recommendFilm(candidateFilms)));
        }
    }
}
