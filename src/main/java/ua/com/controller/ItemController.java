package ua.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.dao.ItemDAO;
import ua.com.dao.JacksonMapper;
import ua.com.entity.Item;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by ShkarupaN on 12.10.2016.
 */

@RestController
public class ItemController {
    @Autowired
    ItemDAO itemDAO;
    final static Logger log = Logger.getLogger(ItemController.class);

    public ItemController() throws IOException {
    }

    @CrossOrigin
    @RequestMapping(value = "/item", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItem(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "date", required = false) String date) throws IOException {
        HttpHeaders headers = new HttpHeaders();

        if (null != date) {

            headers.setAccessControlAllowOrigin("http://185.102.164.14");
            return new ResponseEntity<List<Item>>(itemDAO.getbyDate(date), headers, HttpStatus.OK);

        } else {
            headers.setAccessControlAllowOrigin("http://185.102.164.14");
            return new ResponseEntity<List<Item>>(itemDAO.getbyId(java.util.UUID.fromString(id)),  headers,  HttpStatus.OK);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/item", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody Item item) throws IOException {
        itemDAO.saveItem(item);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @CrossOrigin
    @RequestMapping(value = "/item/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Item>> listAllUsers() throws Exception {
        List<Item> items = itemDAO.getAll();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");

        if (items == null) {
            log.error("Users not found");
            return new ResponseEntity<List<Item>>(headers,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Item>>(items, headers, HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value = "/item/execute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getUser(@RequestParam("action") String action, @RequestParam("date") String date) throws Exception {
        log.info("Execute action:  " + action);

        ObjectMapper mapper = JacksonMapper.getInstance();
        log.info("mapper " + mapper);
        URL url = new URL("http://api.tvmaze.com/schedule?date=" + date);
        log.info(url);
        Item[] items = mapper.readValue(url, Item[].class);

        for (Item item : items) {
            log.info("Save item: " + item);
            itemDAO.saveItem(item);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("http://185.102.164.14");
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}
