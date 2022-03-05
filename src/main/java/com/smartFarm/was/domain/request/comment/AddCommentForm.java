package com.smartFarm.was.domain.request.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Getter
@RequiredArgsConstructor
public class AddCommentForm {

    private final String commentContent;
    private final String commentStatus;
}
