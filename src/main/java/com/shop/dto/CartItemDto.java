package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartItemDto {
    @NotNull(message = "상품 아이디는 필수 입력 값 입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;
//상품 상세 페이지에서 상품의 수량을 클릭하고 '장바구니 담기' 클릭하면 상품 id와 수량을
// controller 전달하게 됩니다.  이를 받을 Dto 객체를 만든다.

}
