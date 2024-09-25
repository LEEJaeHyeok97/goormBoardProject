package com.boardproject.repository;

import com.boardproject.model.DeleteStatus;
import com.boardproject.model.entity.Board;
import com.boardproject.model.response.BoardListResponse;
import com.boardproject.model.response.BoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


    Page<Board> findAllByDeleteStatus(DeleteStatus deleteStatus, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.comments c WHERE b.id = :boardId")
    Optional<Board> findBoardWithCommentsByBoardId(@Param("boardId") Long boardId);
}
