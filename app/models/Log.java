package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "logs")
public class Log extends BaseModel
{
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "content")
    private String content;

    @Column(name = "source")
    private String source;

    @Column(name = "created_at")
    private Date createdAt;
}
