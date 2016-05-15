package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.dto.ChartData;
import com.dxtrs.hack.gamify.model.BoardEntry;
import com.dxtrs.hack.gamify.repository.BoardRepository;
import com.dxtrs.hack.gamify.repository.ChartDataRepository;
import com.dxtrs.hack.gamify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ChartDataRepository chartDataRepository;

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

    @RequestMapping(value = "/api/board/count", method = RequestMethod.GET)
    public List<ChartData> getBoardChartBySegment(@RequestParam(value = "boardSegmentType", required = true) String segmentType,
                                                  @RequestParam(value = "boardSegmentValue", required = true) String segmentValue) {
        List<ChartData> chartEntries;

        if (SEGMENT_TYPE_CATEGORY.equals(segmentType)) {
            chartEntries = chartDataRepository.countOfCategoryBoard(segmentValue);
        } else {
            chartEntries = chartDataRepository.countOfSubCategoryBoard(segmentValue);
        }

        return chartEntries;
    }


}
