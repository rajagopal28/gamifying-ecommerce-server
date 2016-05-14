package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.model.BoardEntry;
import com.dxtrs.hack.gamify.repository.BoardRepository;
import com.dxtrs.hack.gamify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    String SEGMENT_TYPE_CATEGORY = "category";
    private final String SEGMENT_VALUE_ALL = "ALL";

    @RequestMapping(value = "/api/board/all", method = RequestMethod.GET)
    public List<BoardEntry> getOrdersByCustomer(@RequestParam(value = "boardSegmentType", required = true) String segmentType,
                                                @RequestParam(value = "boardSegmentValue", defaultValue = SEGMENT_VALUE_ALL) String segmentValue) {
        List<BoardEntry> boardEntries;
        if (SEGMENT_VALUE_ALL.equals(segmentValue)) {
            if (SEGMENT_TYPE_CATEGORY.equals(segmentType)) {
                boardEntries = boardRepository.getAllCategoryBoardEntries();
            } else {
                boardEntries = boardRepository.getAllSubCategoryBoardEntries();
            }

        } else {
            if (SEGMENT_TYPE_CATEGORY.equals(segmentType)) {
                boardEntries = boardRepository.getCategoryBoardEntriesOf(segmentValue);
            } else {
                boardEntries = boardRepository.getSubCategoryBoardEntriesOf(segmentValue);
            }
        }
        return boardEntries;
    }


}
