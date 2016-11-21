package ua.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.dao.ShowDAO;
import ua.com.dao.JacksonMapper;
import ua.com.entity.Show;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by ShkarupaN on 12.10.2016.
 */

@RestController
public class ShowController {
    @Autowired
    ShowDAO showDAO;
    final static Logger log = Logger.getLogger(ShowController.class);

    public ShowController() throws IOException {
    }

    @CrossOrigin
    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Show> getShow(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "uuid", required = false) String uuid) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        Show show;
        if (null != uuid) {
            show = showDAO.getbyUUID(java.util.UUID.fromString(uuid));
        } else if(null!=id){
            show = showDAO.getbyId(id);
        }
        else {
            return new ResponseEntity<Show>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Show>(showDAO.getbyUUID(java.util.UUID.fromString(uuid)), headers, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/show", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveShow(@RequestBody Show show) throws IOException {
        showDAO.saveShow(show);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/show/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<List<Show>> listUsers(
                                         @RequestParam(value = "offset", required = false) Integer offset,
                                         @RequestParam(value = "maxResults", required = false) Integer maxResults) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        List<Show> shows;
        if (offset != null||maxResults!=null) {
            shows = showDAO.getPaginated(offset, maxResults);
        } else {
            shows = showDAO.getAll();
        }

        if (shows == null) {
            log.error("Users not found");
            return new ResponseEntity<List<Show>>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Show>>(shows, headers, HttpStatus.OK);
    }
}
