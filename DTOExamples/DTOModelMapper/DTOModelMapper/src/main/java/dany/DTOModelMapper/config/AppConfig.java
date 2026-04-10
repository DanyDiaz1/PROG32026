package dany.DTOModelMapper.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration indica que esta clase declara beans para el contexto de Spring
// Es el lugar correcto para configurar librerías de terceros como ModelMapper
@Configuration
public class AppConfig {

    // @Bean registra este objeto en el contexto de Spring
    // A partir de aquí se puede inyectar un ModelMapper en cualquier clase
    // con @Autowired o @RequiredArgsConstructor
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
