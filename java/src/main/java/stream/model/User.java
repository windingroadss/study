package stream.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -5917443760203949290L;

    private String id;
    private String name;
    private int age;
}
