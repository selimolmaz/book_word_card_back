package com.selimolmaz.word_card.service;

import com.selimolmaz.word_card.dto.ChapterDTO;
import com.selimolmaz.word_card.dto.WordCardDTO;

import java.util.List;
import java.util.Optional;

public interface WordCardService {
    WordCardDTO save(WordCardDTO dto);
    Optional<WordCardDTO> findById(int id);
    List<WordCardDTO> findAll();
    void deleteById(int id);
    Optional<WordCardDTO> findByEnglishWord(String englishWord);
    List<ChapterDTO> getWordCardChaptersById(int wordCardId);
}
