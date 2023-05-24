package com.shop.repository;

import com.shop.dto.CartDetailDto;
import com.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.shop.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);
    //CartDetailDto 생성자를 이용하여 DTO 를 반환할때는
    //new com.shop.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl)
    // 위에 처럼 new 키워드와 해당 dTo 패키지, 클래스명을 적어 줍니다.
    //엔티티 객체를 그대로 반환 하는것이 아니라 특정필들 선택해서 반환할 수 있다.

   // CartItem 과  ItemImg 엔티티를 조인하고 CartDetailDto의 생성자를 사용하여 원하는 필드를
    // 선택적으로 가져온다.
    //from CartItem ci, ItemImg im    - CartItem 과  ItemImg 를 조인하고
    //



}
