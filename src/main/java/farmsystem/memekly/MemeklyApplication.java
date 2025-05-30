package farmsystem.memekly;

import farmsystem.memekly.service.YouTubeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MemeklyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemeklyApplication.class, args);
    }

    // 앱 실행 시 YouTube에서 밈 데이터를 불러와 저장
    @Bean
    CommandLineRunner fetchInitialMemes(YouTubeService youTubeService) {
        return args -> {
            youTubeService.fetchMemesFromYouTube("트렌드 밈");
        };
    }
}
