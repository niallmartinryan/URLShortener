package com.NR.UrlShortener;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.*;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

@RequestMapping("/rest/url")
@RestController
public class URLShortenerResource{
    @Autowired
    StringRedisTemplate redisTemp;
    // Jedis jedis;

    @GetMapping("/{id}")
    public RedirectView getLongUrl(@PathVariable String id){

        String url = redisTemp.opsForValue().get(id);
        System.out.println("URL Returned: " + url);
        if (url == null) {
            throw new RuntimeException("Short URL does not exist for : " + id);
        }
        
        return new RedirectView(url);
        // return url;
    }

    @PostMapping
    public String createID(@RequestBody String url){
        
        UrlValidator urlValidator = new UrlValidator( new String[]{"http", "https"});

        if(urlValidator.isValid(url)){
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            System.out.println("Id created: " + id);
            redisTemp.opsForValue().set(id,url);

            return id;
        }
        throw new RuntimeException("URL is Invalid" + url);
    }
}