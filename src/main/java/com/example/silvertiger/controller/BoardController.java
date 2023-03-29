package com.example.silvertiger.controller;

import com.example.silvertiger.dto.BoardDto;
import com.example.silvertiger.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequiredArgsConstructor
//경로가 반복될 경우 대표 주소를 작성해서 하위 매서드에서 반복적으로 작성하지 않아도 된다
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    @PostMapping("/save")
    public ResponseEntity<BoardDto> save(@RequestBody BoardDto boardDto){
        BoardDto saveBoardDto = boardService.save(boardDto);
        return ResponseEntity.ok(saveBoardDto);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BoardDto> update(@RequestBody BoardDto boardDto) {
        BoardDto updatedBoardDto = boardService.update(boardDto);
        return ResponseEntity.ok(updatedBoardDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<BoardDto>> findAll() {
        List<BoardDto> boardDtoList = boardService.findAll();
        return new ResponseEntity<>(boardDtoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> findById(@PathVariable Long id) {
        boardService.updateHits(id);
        BoardDto boardDto = boardService.findById(id);
        return ResponseEntity.ok(boardDto);
    }

    @GetMapping("/update")
    public ResponseEntity<BoardDto> updateForm(@PathVariable Long id) {
        BoardDto boardDto = boardService.findById(id);
        return ResponseEntity.ok(boardDto);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}