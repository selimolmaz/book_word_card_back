package com.selimolmaz.word_card.dto;

import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor // Lombok's annotation for no-argument constructor
@AllArgsConstructor // Lombok's annotation for all-argument constructor
public class WordCardDTO {
    private int id;
    private String englishWord;
    private String meaning;
    private boolean isKnown;
    private Set<Integer> chapterIds;
}
