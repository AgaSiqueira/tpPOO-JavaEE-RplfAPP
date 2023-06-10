package api;

import classes.Alugador;
import classes.Livro;
import classes.Reserva;
import classes.User;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Date;
import java.sql.*;

@WebListener
public class AppListener implements ServletContextListener {
    public static final String CLASS_NAME = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:bookcheck.db";
    public static String initializeLog = "";
    public static Exception exception = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            Connection c = AppListener.getConnection();
            Statement s = c.createStatement();
            initializeLog += new Date() + ": Iniciada criação do Banco de dados; ";
            initializeLog += "Criando tabela user se não existir; ";
            s.execute(User.getCreateStatement());
            if(User.getUsers().isEmpty()) {
                initializeLog += "Adicionando usuario default: ";
                User.insertUser("bibliotecario1", "123456");
                initializeLog += "bibliotecario1 adicionado; ";
            }
            initializeLog += "Criando tabela alugador se não existir";
            s.execute(Alugador.getCreateStatement());
            initializeLog += "Criando tabela livro se não existir";
            s.execute(Livro.getCreateStatement());
            initializeLog += "Criando tabela reserva se não existir";
            s.execute(Reserva.getCreateStatement());
            initializeLog += "Criação concluida";
            
            s.close();
            c.close();
        } catch (Exception e) {
            initializeLog += "Erro: " + e.getMessage();
            exception = e;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    public static Connection getConnection() throws Exception {
        Class.forName(CLASS_NAME);
        return DriverManager.getConnection(URL);
    }
    
}
