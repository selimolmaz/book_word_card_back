package com.selimolmaz.word_card.mapper;

import com.selimolmaz.word_card.dto.ChapterDTO;
import com.selimolmaz.word_card.model.Chapter;
import com.selimolmaz.word_card.model.WordCard;

import java.util.Set;
import java.util.stream.Collectors;

public class ChapterMapper {

    public static ChapterDTO toDTO(Chapter chapter) {
        if (chapter == null) {
            return null;
        }

        ChapterDTO dto = ChapterDTO.builder()
                .id(chapter.getId())
                .chapterName(chapter.getChapterName())
                .wordCardIds(null)
                .build();

        if(chapter.getWordCards() != null){
            dto.setWordCardIds(chapter.getWordCards().stream()
                    .map(WordCard::getId)
                    .collect(Collectors.toSet()));
        }


        return dto;
    }

    public static Chapter toEntity(ChapterDTO chapterDTO) {
        if (chapterDTO == null) {
            return null;
        }

        Chapter chapter = Chapter.builder()
                .id(chapterDTO.getId())
                .chapterName(chapterDTO.getChapterName())
                .build();

        if (chapterDTO.getWordCardIds() != null) {
            Set<WordCard> wordCards = chapterDTO.getWordCardIds().stream()
                    .map(id -> {
                        WordCard wordCard = new WordCard();
                        wordCard.setId(id);
                        return wordCard;
                    })
                    .collect(Collectors.toSet());

            chapter.setWordCards(wordCards);
        } else chapter.setWordCards(null);

        return chapter;
    }
}
