package org.cgruver.home_library.catalog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "book_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"isbn"}, name = "isbn")}, indexes = { @Index(name = "idx_isbn", columnList = "isbn"), @Index(name = "idx_title", columnList = "title")} )
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookInfo", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    private List<Author> authors = null;

    public static BookInfo getBookInfoByIsbn(String isbn){
        return find("isbn", isbn).firstResult();
    }
}
