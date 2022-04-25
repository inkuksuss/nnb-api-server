package com.smartFarm.was.web.service.impl;

import com.smartFarm.was.domain.dto.comment.*;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.repository.CommentRepository;
import com.smartFarm.was.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.smartFarm.was.web.utils.StatusCheckUtils.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public ResultResponse<List<CommentDto>> getCommentList(GetCommentDto getCommentDto) throws SQLException {

        List<CommentDto> commentDtoList = commentRepository.getCommentList(getCommentDto);

        if (getCommentDto.isOwnerFlag()) {

            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), commentDtoList);
        } else {
            List<CommentDto> filteredCommentDtoList = filteredCommentList(commentDtoList);

            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), filteredCommentDtoList);
        }
    }

    @Override
    public ResultResponse<List<CommentDto>> addComment(AddCommentDto addCommentDto) throws SQLException {

        boolean isOwnerFlag = addCommentDto.isOwnerFlag();
        int result = commentRepository.addComment(addCommentDto);

        if (querySuccess(result)) {
            GetCommentDto getCommentDto;

            if (isOwnerFlag) {
                getCommentDto = GetCommentDto.forOwner(addCommentDto.getBoardId());
            } else {
                getCommentDto = GetCommentDto.forGuest(addCommentDto.getBoardId());
            }

            List<CommentDto> commentList = commentRepository.getCommentList(getCommentDto);

            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), commentList);

        } else {

            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    @Override
    public ResultResponse<List<CommentDto>> updateComment(UpdateCommentDto updateCommentDto) throws SQLException {

        boolean isOwnerFlag = updateCommentDto.isOwnerFlag();
        int result = commentRepository.updateComment(updateCommentDto);

        if (querySuccess(result)) {

            GetCommentDto getCommentDto;

            if (isOwnerFlag) {
                getCommentDto = GetCommentDto.forOwner(updateCommentDto.getBoardId());
            } else {
                getCommentDto = GetCommentDto.forGuest(updateCommentDto.getCommentId());
            }

            List<CommentDto> commentList = commentRepository.getCommentList(getCommentDto);

            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), commentList);
        } else  {

            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    @Override
    public ResultResponse<List<CommentDto>> deleteComment(DeleteCommentDto deleteCommentDto) throws SQLException {


        boolean isOwnerFlag = deleteCommentDto.isOwnerFlag();
        int result = commentRepository.deleteComment(deleteCommentDto);

        if (querySuccess(result)) {

            GetCommentDto getCommentDto;

            if (isOwnerFlag) {
                getCommentDto = GetCommentDto.forOwner(deleteCommentDto.getBoardId());
            } else {
                getCommentDto = GetCommentDto.forGuest(deleteCommentDto.getBoardId());
            }

            List<CommentDto> commentList = commentRepository.getCommentList(getCommentDto);

            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), commentList);
        } else  {

            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    @Override
    public List<CommentDto> filteredCommentList(List<CommentDto> commentDtoList) {

        return commentDtoList.stream()
                .map(this::doFiltering).collect(Collectors.toList());
    }

    private CommentDto doFiltering(CommentDto commentDto) {
        if (isPrivate(commentDto.getCommentStatus())) {
            return CommentDto.getFiltered(commentDto);
        } else {
            return commentDto;
        }
    }
}
