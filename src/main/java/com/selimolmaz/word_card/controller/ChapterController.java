package com.selimolmaz.word_card.controller;


import com.selimolmaz.word_card.dto.ChapterDTO;
import com.selimolmaz.word_card.dto.WordCardDTO;
import com.selimolmaz.word_card.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @PostMapping
    public ResponseEntity<ChapterDTO> create(@RequestBody ChapterDTO chapterDTO) {
        ChapterDTO dto_saved = chapterService.saveChapter(chapterDTO);
        return new ResponseEntity<>(dto_saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDTO> getById(@PathVariable int id) {
        Optional<ChapterDTO> chapterDTO = chapterService.findChapterById(id);
        return chapterDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ChapterDTO>> getAllChapters() {
        List<ChapterDTO> chapterDTOS = chapterService.getAllChapters();
        return ResponseEntity.ok(chapterDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable int id) {
        chapterService.deleteChapterById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ChapterDTO> getChapterByName(@RequestParam String chapterName) {
        Optional<ChapterDTO> chapterDTO = chapterService.findChapterByName(chapterName);
        return chapterDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cards")
    public ResponseEntity<List<WordCardDTO>> getChapterWordCards(@RequestParam int chapter_id) {
        List<WordCardDTO> wordCardDTOS = chapterService.getChapterWordCardsByChapterId(chapter_id);
        return ResponseEntity.ok(wordCardDTOS);
    }

    @PostMapping("/{chapterId}/wordcards/{wordCardId}")
    public ResponseEntity<ChapterDTO> addWordCardToChapter(@PathVariable  int chapterId, @PathVariable int wordCardId) {
        ChapterDTO chapterDTO = chapterService.addWordCardToChapter(chapterId, wordCardId);
        return ResponseEntity.ok(chapterDTO);
    }

    @DeleteMapping("/{chapterId}/wordcards/{wordCardId}")
    public ResponseEntity<ChapterDTO> removeCardFromChapter(@PathVariable  int chapterId, @PathVariable int wordCardId) {
        ChapterDTO chapterDTO = chapterService.removeWordCardFromChapter(chapterId, wordCardId);
        return ResponseEntity.ok(chapterDTO);
    }
}
