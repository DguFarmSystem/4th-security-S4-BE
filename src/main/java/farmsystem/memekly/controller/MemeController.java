package farmsystem.memekly.controller;

import farmsystem.memekly.domain.Meme;
import farmsystem.memekly.service.MemeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemeController {

    private final MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    // 1. 급상승 밈 Top 5
    @GetMapping("/trends/rising")
    public List<Meme> getRisingMemes() {
        return memeService.getRisingMemes();
    }

    // 2. 전체 인기 밈 리스트 (페이징 지원)
    @GetMapping("/trends/ranking")
    public Page<Meme> getRankedMemes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return memeService.getRankedMemes(page, limit);
    }

    // 3. 특정 밈 상세 정보
    @GetMapping("/memes/{id}")
    public Meme getMemeById(@PathVariable String id) {
        return memeService.getMemeById(id)
                .orElseThrow(() -> new RuntimeException("해당 밈을 찾을 수 없습니다: " + id));
    }
}

