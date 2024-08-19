package com.selimolmaz.word_card.service;

import com.selimolmaz.word_card.dto.ChapterDTO;
import com.selimolmaz.word_card.dto.WordCardDTO;

import java.util.List;
import java.util.Optional;

public interface ChapterService {
    List<ChapterDTO> getAllChapters();
    ChapterDTO saveChapter(ChapterDTO chapter);
    Optional<ChapterDTO> findChapterById(int id);
    void deleteChapterById(int id);
    Optional<ChapterDTO> findChapterByName(String chapter_name);
    List<WordCardDTO> getChapterWordCardsByChapterId(int chapter_id);
    public ChapterDTO addWordCardToChapter(int chapterId, int wordCardId);
    public ChapterDTO removeWordCardFromChapter(int chapterId, int wordCardId);
}
