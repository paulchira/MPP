package Server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Chira Paul on 4/1/2017.
 */
public class StartObjectServer {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-server.xml");
       // IRepositoryAngajat repoAngajat = (RepositoryAngajat)applicationContext.getBean("repoAngajat");
        //System.out.println(repoAngajat.getIdAngajat("admin","admin"));
    }
}
