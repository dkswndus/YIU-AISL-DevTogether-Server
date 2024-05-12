package yiu.aisl.devTogether.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yiu.aisl.devTogether.domain.state.RoleCategory;

@Data
@NoArgsConstructor // 기본 생성자 추가

public class RegisterDto {


    private String  email;
    private String  pwd;
    private String  name;
    private String  nickname;
    private Integer role;
    private Integer genderCategory;
    private String  img;
    private Integer age;
    private String method;
    private Integer  fee;
    private String  location1;
    private String  location2;
    private String  location3;
    private String subject1;
    private String subject2;
    private String subject3;
    private String subject4;
    private String subject5;






}