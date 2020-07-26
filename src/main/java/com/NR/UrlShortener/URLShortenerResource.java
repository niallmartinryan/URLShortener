package com.NR.UrlShortener;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.*;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

// Entry Point
@RequestMapping("/rest/url")
@RestController
public class URLShortenerResource{
    //Redis Db template
    @Autowired
    StringRedisTemplate redisTemp;
    

    // Get request with short URL
    @GetMapping("/{id}")
    public RedirectView getLongUrl(@PathVariable String id){

        // retrieving long url from redis dict
        String url = redisTemp.opsForValue().get(id);
        System.out.println("URL Returned: " + url);

        // null exception protection if URL does not exist
        if (url == null) {
            throw new RuntimeException("Short URL does not exist for : " + id);
        }
        
        // redirect user to long url
        return new RedirectView(url);
        
    }
    // Post request Long URL
    @PostMapping
    public String createID(@RequestBody String url){
        
        //Validation of Long url
        UrlValidator urlValidator = new UrlValidator( new String[]{"http", "https"});

        if(urlValidator.isValid(url)){
            // Using Murmur hash to get short url which is id
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            System.out.println("Id created: " + id);
            
            // insert short url (id) and long url (url) into db as keyset
            redisTemp.opsForValue().set(id,url);

            return id;
        }
        throw new RuntimeException("URL is Invalid" + url);
    }

    // Delete Entry
    @DeleteMapping
    public String deleteEntry(@RequestBody String id){

        // remove ID from database
        redisTemp.delete(id);

        // return id of the url which was removed if succesful
        return id;  
    }
}