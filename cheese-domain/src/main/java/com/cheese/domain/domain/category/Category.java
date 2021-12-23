package com.cheese.domain.domain.category;

import com.cheese.domain.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // internal

    @Column(nullable = false, length = 32)
    private String name;

    @Column
    private Integer sort;

    @Column
    private Boolean isUse;

    @Column(name = "deleted_at", insertable = false)
    private LocalDateTime deletedAt;

}
