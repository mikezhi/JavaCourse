package ru.croc.task17;
/**
 * Вы разрабатываете бэкенд интернет-магазина. Магазин уже функционирует
 * и принимает заказы. Информация о заказах регистрируется в Excel-таблице
 * вручную. В нее внесены данные за все время существования магазина. Заказчик
 * выгрузил и передал вам эту таблицу в виде CSV-файла.
 * Формат файла
 * <номер_заказа:integer>,<логин_пользователя:string>,<артикул_то-
 * вара:string>,<название_товара:string>,<цена_в_рублях:integer>
 * Содержимое
 * 1,vasya,Т1,Монитор,500
 * 1,vasya,Т2,Мышь,50
 * 2,petya,Т2,Мышь,50
 * 2,petya,Т2,Мышь,50
 * 2,petya,Т3,Клавиатура,150
 * 1,vasya,Т4,Блок питания, 200
 * 3,nikita,Т4,Блок питания, 200
 * 4,olga,Т4,Блок питания, 200
 * 3,nikita,Т5,Видеокарта,15000
 * 3,nikita,Т5,Видеокарта,15000
 * 4,olga,Т5,Видеокарта,15000
 * 4,olga,Т5,Видеокарта,15000
 * Дополнительно заказчик сообщил, что товары с одинаковым артикулом все-
 * гда продавались с одними и теми же названием и ценой.
 * Спроектируйте структуру таблиц для представления информации о заказах
 * в базе данных и загрузите в них данные из файла. Для этого разработайте
 * приложение, которое:
 * Создаст в базе данных все необходимые таблицы.
 * Импортирует в базу данных все записи исходного файла.
 * Путь к файлу с данными передается программе в качестве аргумента ко-
 * мандной строки при запуске.
 */

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

/**
 * change empty password for user 'sa', new password - 'tiger'
 * ALTER USER sa SET PASSWORD 'NEW_PASSWORD'
 */


public class Csv2DbInserter {

    //запрос создания таблицы с полями для категории Товары (Products)
    private static final String createProducts = "CREATE TABLE Products" +
            "(ArticleID VARCHAR(255) PRIMARY KEY," +
            "Product VARCHAR(255) NOT NULL, " +
            "Price INT NOT NULL);";

    //запрос создания таблицы с полями для категории Заказы (Orders)
    private static final String createOrders = "CREATE TABLE Orders" +
            "(ID INT NOT NULL, " +
            "UserName VARCHAR(255) NOT NULL, " +
            "Article VARCHAR(255), " +
            "foreign key (Article) references Products(ArticleID));";


    public static void main(String[] args) {

        //System.exit(0);
        //в этот список запишем заказы (ID, UserName, Article)
       List<Order> orders = new ArrayList<>();
        //в этот список запишем товары ArticleID, Product, Price
        List<Product> products = new ArrayList<>();


        readCSV(args[0], orders, products);

        // credentials for DB
        String jdbcURL = "jdbc:h2:tcp://localhost/~/test";
        String jdbcUserName = "sa";
        String jdbcPassword = "tiger";

        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword)){
            createTableIntoDB(conn);
            products2DB(conn, products);
            orders2DB(conn, orders);
        } catch (SQLException e){
           e.printStackTrace();
        }
    }

    static void readCSV(String path, List<Order> orders, List<Product> products){
        Scanner scan;
        try {
            scan = new Scanner(Paths.get(path));
        } catch (IOException e){
            System.out.println("Error opening file");
            return;
        }
        // добавляем артикулы товаров для соблюдения уникальности
        Set<String> Articles  = new HashSet<>();
        String[] temp;
        while (scan.hasNextLine()){
            temp = scan.nextLine().split(",");

            //добавляем в список orders обьект класса
            orders.add(new Order(Integer.parseInt(temp[0]),temp[1],temp[2]));

            //если нет с коллекции такого артикля то добавляем в список новый продукт
            if (Articles.add(temp[2]))
                products.add(new Product(temp[2], temp[3], Integer.parseInt(temp[4])));
        }
    }

    // создаем таблицы в БД
    static void createTableIntoDB(Connection connection) throws SQLException{
        try(Statement statement = connection.createStatement()){
            statement.execute(Csv2DbInserter.createProducts);
            statement.execute(Csv2DbInserter.createOrders);
        }
    }

    //заполняем таблицу Products
    static void products2DB(Connection connection, List<Product> products) throws SQLException{
        String query = "INSERT INTO Products VALUES(?,?,?)";
        for (Product product : products){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, product.getProductCode());
                statement.setString(2, product.getProductName());
                statement.setString(3, product.getPrice());
                statement.execute();
            }
        }
    }

    static void orders2DB(Connection connection, List<Order> orders) throws SQLException{
        String query = "INSERT INTO Orders VALUES(?,?,?)";
        for(Order order : orders){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, order.getID());
                statement.setString(2, order.getuLogin());
                statement.setString(3, order.getArticle());
            }
        }
    }

}