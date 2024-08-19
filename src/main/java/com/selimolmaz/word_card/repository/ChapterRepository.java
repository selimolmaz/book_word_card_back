package com.selimolmaz.word_card.repository;


import com.selimolmaz.word_card.model.Chapter;
import com.selimolmaz.word_card.model.WordCard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    Optional<Chapter> findByChapterName(String chapterName);

    @EntityGraph(attributePaths = "wordCards")
    Optional<Chapter> findById(int id);

    @Query("SELECT wc FROM WordCard wc JOIN FETCH wc.chapters c WHERE c.id = :chapterId")
    List<WordCard> findWordCardsByChapterId(@Param("chapterId") int chapterId);

}
