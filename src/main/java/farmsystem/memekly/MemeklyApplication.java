package farmsystem.memekly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class MemeklyApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String uri = dotenv.get("MONGODB_URI");
        String db = dotenv.get("MONGODB_DB");

        if (uri == null || db == null) {
            throw new IllegalStateException("❌ 환경변수 MONGODB_URI 또는 MONGODB_DB 가 설정되지 않았습니다.");
        }

        System.setProperty("spring.data.mongodb.uri", uri);
        System.setProperty("spring.data.mongodb.database", db);

        SpringApplication.run(MemeklyApplication.class, args);
    }
}

