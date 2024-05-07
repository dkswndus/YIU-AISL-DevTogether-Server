package yiu.aisl.devTogether.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long imageId;

    @Column
    private Integer type;

    @Column
    private Integer typeId;

    @Column(columnDefinition = "TEXT")
    private String originName;

    @Column(columnDefinition = "TEXT")
    private String storageName;

    @Column(columnDefinition = "TEXT")
    private String path;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;


}
