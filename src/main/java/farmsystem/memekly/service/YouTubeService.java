package farmsystem.memekly.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import farmsystem.memekly.domain.Meme;
import farmsystem.memekly.repository.MemeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YouTubeService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final MemeRepository memeRepository;

    private final String apiKey = System.getProperty("youtube.api.key");

    public YouTubeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
//        System.out.println("🔑 API KEY Loaded: " + apiKey); // YouTubeService.java 내부
    }

    public void fetchMemesFromYouTube(String keyword) {
        try {
            String apiUrl = "https://www.googleapis.com/youtube/v3/search"
                    + "?part=snippet"
                    + "&type=video"
                    + "&maxResults=5"
                    + "&regionCode=KR"
                    + "&q=" + keyword
                    + "&key=" + apiKey;

            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            String body = response.getBody();

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> responseMap = mapper.readValue(body, new TypeReference<>() {});
            List<Map<String, Object>> items = (List<Map<String, Object>>) responseMap.get("items");

            List<Meme> memes = new ArrayList<>();
            for (Map<String, Object> item : items) {
                Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
                Map<String, Object> thumbnails = (Map<String, Object>) snippet.get("thumbnails");
                Map<String, Object> defaultThumb = (Map<String, Object>) thumbnails.get("default");

                String videoId = ((Map<String, String>) item.get("id")).get("videoId");

                Meme meme = new Meme();
                meme.setTitle((String) snippet.get("title"));
                meme.setPlatform("YouTube");
                meme.setUrl("https://www.youtube.com/watch?v=" + videoId);
                meme.setThumbnailUrl((String) defaultThumb.get("url"));
                meme.setHashtags(List.of("#YouTube", "#Trend"));
                meme.setLikeCount(0);
                meme.setCommentCount(0);
                meme.setViewCount(0);
                meme.setShareCount(0);
                meme.setUploadTime(Instant.now());

                System.out.println("✅ Fetched meme: " + meme);
                memes.add(meme);
            }

            memeRepository.saveAll(memes);
            System.out.println("✅ 총 저장된 밈 수: " + memes.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
