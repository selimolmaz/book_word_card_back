package com.selimolmaz.word_card.mapper;

import com.selimolmaz.word_card.dto.WordCardDTO;
import com.selimolmaz.word_card.model.Chapter;
import com.selimolmaz.word_card.model.WordCard;

import java.util.Set;
import java.util.stream.Collectors;

public class WordCardMapper {

    public static WordCardDTO toDTO(WordCard wordCard) {
        if (wordCard == null) {
            return null;
        }

        WordCardDTO dto = WordCardDTO.builder()
                .id(wordCard.getId())
                .englishWord(wordCard.getEnglishWord())
                .meaning(wordCard.getMeaning())
                .isKnown(wordCard.isKnown())
                .chapterIds(null)
                .build();

        if (wordCard.getChapters() !=null) {
            dto.setChapterIds(wordCard.getChapters().stream()
                    .map(Chapter::getId)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static WordCard toEntity(WordCardDTO wordCardDTO) {
        if (wordCardDTO == null) {
            return null;
        }

        WordCard wordCard = WordCard.builder()
                .id(wordCardDTO.getId())
                .englishWord(wordCardDTO.getEnglishWord())
                .meaning(wordCardDTO.getMeaning())
                .isKnown(wordCardDTO.isKnown())
                .build();
        if (wordCardDTO.getChapterIds() != null){
            Set<Chapter> chapters = wordCardDTO.getChapterIds().stream()
                    .map(id -> {
                        Chapter chapter = new Chapter();
                        chapter.setId(id);
                        return chapter;
                    })
                    .collect(Collectors.toSet());
            wordCard.setChapters(chapters);
        } else wordCard.setChapters(null);



        return wordCard;
    }
}
