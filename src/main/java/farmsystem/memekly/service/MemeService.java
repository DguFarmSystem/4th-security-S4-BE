package farmsystem.memekly.service;

import farmsystem.memekly.domain.Meme;
import farmsystem.memekly.repository.MemeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MemeService {
    private final MemeRepository memeRepository;

    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public List<Meme> getRisingMemes() {
        return memeRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Meme::getViewCount).reversed())
                .limit(5)
                .toList();
    }

    public Page<Meme> getRankedMemes(int page, int size) {
        return memeRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Meme> getMemeById(String id) {
        return memeRepository.findById(id);
    }
}
