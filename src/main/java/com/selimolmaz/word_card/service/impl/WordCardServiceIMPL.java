package com.selimolmaz.word_card.service.impl;

import com.selimolmaz.word_card.dto.ChapterDTO;
import com.selimolmaz.word_card.dto.WordCardDTO;
import com.selimolmaz.word_card.mapper.ChapterMapper;
import com.selimolmaz.word_card.mapper.WordCardMapper;
import com.selimolmaz.word_card.model.Chapter;
import com.selimolmaz.word_card.model.WordCard;
import com.selimolmaz.word_card.repository.WordCardRepository;
import com.selimolmaz.word_card.service.WordCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordCardServiceIMPL implements WordCardService {
    @Autowired
    private WordCardRepository repository;


    @Override
    public WordCardDTO save(WordCardDTO dto) {
        WordCard entity = WordCardMapper.toEntity(dto);
        WordCard savedEntity = repository.save(entity);
        return WordCardMapper.toDTO(savedEntity);
    }

    @Override
    public Optional<WordCardDTO> findById(int id) {
        return repository.findById(id)
                .map(WordCardMapper::toDTO);
    }

    @Override
    public List<WordCardDTO> findAll() {
        return repository.findAll().stream()
                .map(WordCardMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<WordCardDTO> findByEnglishWord(String englishWord) {
        return repository.findByEnglishWord(englishWord).map(WordCardMapper::toDTO);
    }

    @Override
    public List<ChapterDTO> getWordCardChaptersById(int wordCardId) {
        return repository.findChaptersByWordCardId(wordCardId).stream()
                .map(ChapterMapper::toDTO)
                .collect(Collectors.toList());
    }


}
