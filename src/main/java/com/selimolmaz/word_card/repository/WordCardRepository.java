package com.selimolmaz.word_card.repository;

import com.selimolmaz.word_card.model.Chapter;
import com.selimolmaz.word_card.model.WordCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordCardRepository extends JpaRepository<WordCard, Integer> {
    Optional<WordCard> findByEnglishWord(String english_word);

    @Query("SELECT c FROM Chapter c JOIN c.wordCards wc WHERE wc.id = :wordCardId")
    List<Chapter> findChaptersByWordCardId(@Param("wordCardId") int wordCardId);
}
