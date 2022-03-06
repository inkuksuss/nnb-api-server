package com.smartFarm.was.domain.dto.board;

import com.smartFarm.was.domain.dto.comment.CommentDto;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardDetailDto {

    private long boardId;
    private long boardView;
    private long memberId;
    private String memberName;
    private String boardContent;
    private String boardTitle;
    private String categoryValue;
    private String boardStatus;
    private Timestamp boardCreated;
    private Timestamp boardLastUpdated;
    private List<CommentDto> commentDtoList;
}
