package myapp.rest;

import javafx.application.Application;
import myapp.Model.Zbor;
import myapp.Repository.IRepositoryZbor;
import myapp.Repository.RepositoryZbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Created by Chira Paul on 5/18/2017.
 */
@RestController
@RequestMapping("/myapp/zbor")
public class AppController {
    private static final String template = "Hello, %s!";

    public static Properties props() {
        Properties serverProps = new Properties();
        try {
            serverProps.load(AppController.class.getResourceAsStream("/serverApplication.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatserver.properties " + e);
            return null;
        }
        return serverProps;
    }

    private IRepositoryZbor repositoryZbor = new RepositoryZbor(props());

    @RequestMapping("/HelloWorld")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Zbor create(@RequestBody Zbor zbor) {
        repositoryZbor.save(zbor);
        return zbor;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Zbor update(@RequestBody Zbor zbor, @PathVariable int id) {
        System.out.println("Updating zbor ...");
        repositoryZbor.update(zbor, id);
        return zbor;
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        System.out.println("deleting zbor");
        try {
            repositoryZbor.delete(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("execption occured");
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getByID(@PathVariable int id) {
        Zbor z = repositoryZbor.findByID(id);
        if (z == null) {
            return new ResponseEntity<String>("Zbor not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Zbor>(z, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Zbor[] getAll() {
        return repositoryZbor.getAllZbor().toArray(new Zbor[repositoryZbor.getAllZbor().size()]);
    }
}
