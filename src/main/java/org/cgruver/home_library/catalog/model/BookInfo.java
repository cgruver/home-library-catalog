package org.cgruver.home_library.catalog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "book_info", indexes = { @Index(name = "idx_isbn", columnList = "isbn"), @Index(name = "idx_title", columnList = "title")} )
public class BookInfo extends PanacheEntityBase {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column()
    private String isbn;

    @Column()
    private String title;

    @Column()
    private String openLibraryUrl;

    @Column()
    private Long numberOfPages;

    @Column()
    private String coverImageUrl;

    @Column()
    private Date publishDate;

    private List<Author> authors = null;
}
