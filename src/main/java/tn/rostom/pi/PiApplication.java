package tn.rostom.pi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.rostom.pi.entities.Role;
import tn.rostom.pi.entities.enums.TypeRole;
import tn.rostom.pi.repositories.RoleRepository;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@EnableCaching
@EnableAsync
public class PiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }
    @Bean
    CommandLineRunner run(RoleRepository roleRepository){
        return args -> {
            if(!roleRepository.findByType(TypeRole.ADMIN).isPresent())
                roleRepository.save(new Role(1L, "ADMIN", TypeRole.ADMIN));
            if(!roleRepository.findByType(TypeRole.USER).isPresent())
                roleRepository.save(new Role(2L, "USER", TypeRole.USER));
            if(!roleRepository.findByType(TypeRole.OLD).isPresent())
                roleRepository.save(new Role(3L, "USER", TypeRole.OLD));
        };
    }

}
