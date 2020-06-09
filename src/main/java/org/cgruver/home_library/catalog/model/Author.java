package org.cgruver.home_library.catalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "author", indexes = {@Index(name = "idx_name", columnList = "name")})
public class Author extends PanacheEntityBase {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column()
    private String openLibraryUrl;

    @Column()
    private String name;

    @ManyToOne
    @JoinColumn(name = "book_info_id", nullable = false)
    private BookInfo bookInfo;
}