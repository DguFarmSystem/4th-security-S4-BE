package farmsystem.memekly;

import farmsystem.memekly.service.YouTubeService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MemeklyApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String uri = dotenv.get("MONGODB_URI");
        String db = dotenv.get("MONGODB_DB");
        System.setProperty("youtube.api.key", dotenv.get("YOUTUBE_API_KEY"));

        if (uri == null || db == null) {
            throw new IllegalStateException("❌ 환경변수 MONGODB_URI 또는 MONGODB_DB 가 설정되지 않았습니다.");
        }

        System.setProperty("spring.data.mongodb.uri", uri);
        System.setProperty("spring.data.mongodb.database", db);

        SpringApplication.run(MemeklyApplication.class, args);
//        System.out.println("📦 .env YOUTUBE_API_KEY: " + dotenv.get("YOUTUBE_API_KEY")); // MemeklyApplication.java 내부

    }

    // 앱 실행 시 YouTube에서 밈 데이터를 불러와 저장
    @Bean
    CommandLineRunner fetchInitialMemes(YouTubeService youTubeService) {
        return args -> {
            youTubeService.fetchMemesFromYouTube("트렌드 밈");
        };
    }
}
