package com.practice.validation.post.domain;


import com.practice.validation.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User writer;

    private String title;

    private String content;

    public static Post of(final User writer,
                          final String title,
                          final String content) {
        return Post.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();
    }
}
