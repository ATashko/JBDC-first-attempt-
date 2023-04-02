package main;

import org.apache.log4j.Logger;

import java.sql.*;


public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class);

    private static final String SQL_CREATE_TABLE = "DROP TABLE IF EXISTS ANIMALES; CREATE TABLE ANIMALES"
            + "("
            + "ID INT PRIMARY KEY,"
            + "NOMBRE varchar(100) NOT NULL, "
            + "TIPO varchar(100) NOT NULL "
            + ")";

    private static final String SQL_INSERT = "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (1, 'Majo', 'Perro')";

    private static final String SQL_INSERT2 = "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (2, 'Mati', 'Perro')";

    private static final String SQL_INSERT3 = "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (3, 'Moñito', 'Perro')";

    private static final String SQL_INSERT4 = "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (4, 'Ernesto', 'Perro')";

    private static final String SQL_INSERT5 = "INSERT INTO ANIMALES (ID, NOMBRE, TIPO) VALUES (5, 'Pan', 'Perro')";

    private static final String SQL_DELETE = "DELETE FROM ANIMALES WHERE ID=2";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE);

            Statement statementInsert = connection.createStatement();
            statementInsert.execute(SQL_INSERT);

            Statement statementInsert2 = connection.createStatement();
            statementInsert2.execute(SQL_INSERT2);

            Statement statementInsert3 = connection.createStatement();
            statementInsert3.execute(SQL_INSERT3);

            Statement statementInsert4 = connection.createStatement();
            statementInsert4.execute(SQL_INSERT4);

            Statement statementInsert5 = connection.createStatement();
            statementInsert5.execute(SQL_INSERT5);

            LOGGER.info("Se ejecutaron todos los inserts");

            verAnimales(connection);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            connection.close();
        }


    }

    private static void verAnimales(Connection connection) throws SQLException {

        String sql = "SELECT * FROM ANIMALES";
        Statement sqlStatement = connection.createStatement();
        ResultSet rs = sqlStatement.executeQuery(sql);
        while (rs.next()) {

            System.out.println(rs.getInt(1) + "-"
                    + rs.getString(2) + "-"
                    + rs.getString(3));
        }

    }


    private static Connection getConnection() {

        DriverManager driverManager = null;
        Connection c = null;

        try {
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
            c = driverManager.getConnection("jdbc:h2:~/h2-database", "root", "");
        } catch (Exception e) {

            LOGGER.error("Hubo un problema conectado la base de datos", e);

        }

        return c;

    }
}

