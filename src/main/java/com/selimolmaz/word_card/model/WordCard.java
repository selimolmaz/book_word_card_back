package com.selimolmaz.word_card.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "word_card")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "english_word", nullable = false, unique = true)
    private String englishWord;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Column(name = "is_known", nullable = false)
    private boolean isKnown;

    @ManyToMany(mappedBy = "wordCards", fetch = FetchType.LAZY) // mappedBy attribute'üne dikkat edin
    private Set<Chapter> chapters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordCard wordCard = (WordCard) o;

        return id == wordCard.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
