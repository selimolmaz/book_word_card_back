package com.selimolmaz.word_card.controller;

import com.selimolmaz.word_card.dto.ChapterDTO;
import com.selimolmaz.word_card.dto.WordCardDTO;
import com.selimolmaz.word_card.service.WordCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/word")
public class WordCardController {

    @Autowired
    private WordCardService service;

    // Create or Update
    @PostMapping
    public ResponseEntity<WordCardDTO> create(@RequestBody WordCardDTO dto) {
        WordCardDTO savedDto = service.save(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<WordCardDTO> getById(@PathVariable int id) {
        Optional<WordCardDTO> dto = service.findById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<WordCardDTO>> getAll() {
        List<WordCardDTO> allWords = service.findAll();
        return ResponseEntity.ok(allWords);
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Read by English Word
    @GetMapping("/search")
    public ResponseEntity<WordCardDTO> getByEnglishWord(@RequestParam String englishWord) {
        Optional<WordCardDTO> dto = service.findByEnglishWord(englishWord);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/chapters")
    public ResponseEntity<List<ChapterDTO>> getWordCardChapters(@RequestParam int wordCardId) {
        List<ChapterDTO> chapterDTOS = service.getWordCardChaptersById(wordCardId);
        return ResponseEntity.ok(chapterDTOS);
    }
}
