package org.example.nfchats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.example.nfchats.dao")
public class NFchatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NFchatsApplication.class, args);
    }

}
