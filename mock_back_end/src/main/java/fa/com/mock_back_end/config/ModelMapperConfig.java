package fa.com.mock_back_end.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @Author QUANGNA7
* @Version 1.0
* @Since 9/28/2023
*/
@Configuration
public class ModelMapperConfig {

   /**
   * @Description modelMapper
   * @Param
   * @Return ModelMapper
   */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
