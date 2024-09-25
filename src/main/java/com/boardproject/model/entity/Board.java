package com.boardproject.model.entity;

import com.boardproject.model.DeleteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE board SET DELETE_STATUS = 'DELETE' WHERE board_id = ?")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String body;

    //soft delete 구현 => 삭제 여부
    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @SQLRestriction("DELETE_STATUS= 'ACTIVE'")
    private List<Comment> comments = new ArrayList<>();


    public Board addComment(String commentBody) {
        Comment comment = new Comment();
        comment.setBody(commentBody);
        comment.setBoard(this);
        comment.setDeleteStatus(DeleteStatus.ACTIVE);

        this.getComments().add(comment);

        return this;
    }
}
