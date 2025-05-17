package farmsystem.memekly.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "videos")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Meme {
    @Id
    private String id;
    private String title;
    private String platform;
    private String url;
    private String thumbnailUrl;
    private List<String> hashtags;
    private long likeCount;
    private long commentCount;
    private long viewCount;
    private long shareCount;
    private Instant uploadTime;
}

