package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;
    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg,ItemImgDto.class);
    }
    //of 메서드 ItemImg 객체를  ItemImgDto 객체로 변환하는 역할을 수행
    //변환은  ModelMapper

//    public static ItemImgDto of(ItemImg itemImg) {
//        ItemImgDto itemImgDto = new ItemImgDto();
//        itemImgDto.setId(itemImg.getId());
//        itemImgDto.setName(itemImg.getName());
//        // 필요한 필드들을 모두 복사
//
//        return itemImgDto;
//    }
    //이렇게 작성될 것을 ModelMapper 를 이용해서 간단히 작성가능

}
