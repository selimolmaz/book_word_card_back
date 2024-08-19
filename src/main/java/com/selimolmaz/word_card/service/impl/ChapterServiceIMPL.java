package com.selimolmaz.word_card.service.impl;

import com.selimolmaz.word_card.dto.ChapterDTO;
import com.selimolmaz.word_card.dto.WordCardDTO;
import com.selimolmaz.word_card.mapper.ChapterMapper;
import com.selimolmaz.word_card.mapper.WordCardMapper;
import com.selimolmaz.word_card.model.Chapter;
import com.selimolmaz.word_card.model.WordCard;
import com.selimolmaz.word_card.repository.ChapterRepository;
import com.selimolmaz.word_card.repository.WordCardRepository;
import com.selimolmaz.word_card.service.ChapterService;
import com.selimolmaz.word_card.service.WordCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChapterServiceIMPL implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private WordCardRepository wordCardRepository;


    @Override
    public List<ChapterDTO> getAllChapters() {
        List<Chapter> chapters = chapterRepository.findAll();
        return chapters.stream()
                .map(ChapterMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChapterDTO saveChapter(ChapterDTO chapterDTO) {
        Chapter chapter = ChapterMapper.toEntity(chapterDTO);
        Chapter savedChapter = chapterRepository.save(chapter);
        return ChapterMapper.toDTO(savedChapter);
    }

    @Override
    public Optional<ChapterDTO> findChapterById(int id) {
            return chapterRepository.findById(id)
                    .map(ChapterMapper::toDTO);
        }

    @Override
    public void deleteChapterById(int id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public Optional<ChapterDTO> findChapterByName(String chapterName) {
        return chapterRepository.findByChapterName(chapterName).map(ChapterMapper::toDTO);
    }

    @Override
    public List<WordCardDTO> getChapterWordCardsByChapterId(int chapter_id) {
        List<WordCard> wordCards = chapterRepository.findWordCardsByChapterId(chapter_id);
        return wordCards.stream()
                .map(WordCardMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChapterDTO addWordCardToChapter(int chapterId, int wordCardId) {
        // Chapter'ı repository'den bul
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new IllegalArgumentException("Chapter not found"));

        // WordCard'ı WordCardServiceIMPL'den bul
        WordCard wordCard = wordCardRepository.findById(wordCardId)
                .orElseThrow(() -> new IllegalArgumentException("WordCard not found"));

        // WordCard'ı Chapter'a ekle
        chapter.getWordCards().add(wordCard);

        // Chapter'ı kaydet
        Chapter savedChapter = chapterRepository.save(chapter);

        // Güncellenmiş Chapter'ı DTO'ya dönüştür ve geri döndür
        return ChapterMapper.toDTO(savedChapter);
    }

    @Override
    public ChapterDTO removeWordCardFromChapter(int chapterId, int wordCardId) {
        // Chapter'ı repository'den bul
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new IllegalArgumentException("Chapter not found"));

        // WordCard'ı WordCardServiceIMPL'den bul
        WordCard wordCard = wordCardRepository.findById(wordCardId)
                .orElseThrow(() -> new IllegalArgumentException("WordCard not found"));

        // WordCard'ı Chapter'dan çıkar
        chapter.getWordCards().remove(wordCard);

        // Chapter'ı kaydet
        Chapter savedChapter = chapterRepository.save(chapter);

        // Güncellenmiş Chapter'ı DTO'ya dönüştür ve geri döndür
        return ChapterMapper.toDTO(savedChapter);
    }

}

