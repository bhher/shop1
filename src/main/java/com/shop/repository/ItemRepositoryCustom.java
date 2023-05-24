package com.shop.repository;

import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.shop.dto.MainItemDto;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
//Page<Item>(페이지 아이템의 데이터와 페이지정보) Data jpa 제공하는 페이지네이션 기능을 제공
//Pageable 은 페이지 번호, 페이지 크기, 정렬 기준 등의 정보를 포함
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}