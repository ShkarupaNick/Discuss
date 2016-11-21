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
    public ResponseEntity<Item> getItem(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "uuid", required = false) String uuid) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        Item item;
        if (null != id) {
            item = itemDAO.getbyUUID(java.util.UUID.fromString(uuid));
        } else {
            item = itemDAO.getbyId(id);
        }
        if (null == item) {
            return new ResponseEntity<Item>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Item>(itemDAO.getbyUUID(java.util.UUID.fromString(uuid)), headers, HttpStatus.OK);
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
    public
    @ResponseBody
    ResponseEntity<List<Item>> listUsers(@RequestParam(value = "date", required = false) String date,
                                         @RequestParam(value = "offset", required = false) Integer offset,
                                         @RequestParam(value = "maxResults", required = false) Integer maxResults) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        List<Item> items;
        if (null != date) {
            items = itemDAO.getbyDate(date, offset, maxResults);
        } else {
            items = itemDAO.getAll();
        }

        if (items == null) {
            log.error("Users not found");
            return new ResponseEntity<List<Item>>(headers, HttpStatus.NOT_FOUND);
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
        itemDAO.saveItemList(items);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("http://185.102.164.14");
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}
