package com.shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;
    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;
}
//validation 을 pom.xml에 추가
//@NotNull, @Size, @Email, @Pattern 등의 어노테이션을 사용하여 필드 또는 메소드의 제약 조건을 정의

//NotBlank 공백문자열(" ")인지도 검사  - 값이 존재하고  비어있지 않아야 유효
//NotEmpty null 여부와 빈 문자열을 검사하지만, 공백문자열은 허용합니다.

//  a = ""; 빈문자열
// a  = " "; 공백문자
//주소 같은 필드는 일반적으로 공배문자열을 허용하는 것이 맞다.
//@NotBlank 어노테이션은 해당필드에 공백 문자열이 있는 것은 유효하지 않는다고 판단
//이때는 @NotEmpty 사용하는 것이 적절
//@NotEmptyOrBlank
//private String address;

