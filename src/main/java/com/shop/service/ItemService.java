package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);
        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }
    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId) {
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        //itemId에 해당하는  ItemImg 목록을 조회  - itemImgList 결과를 할당
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }
        //itemImgList 순회하면서 각각의 ItemImg 를 ItemIMgDto 로 변환하여 itemImgDtoList 추가

        //itemId에 해당하는 Item을 조회
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        //ItemFormDto.of(item) 호출해서 item 을  itemFormDto로 변환 이때 itemFormDto 변수에 결과를 할당
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        //itemFormDto 의 setItemImgDtoList(itemImgDtoList) 메서드를 사용하여 변환된 ItemImgDto 목록 설정
        return itemFormDto;
    }
        public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
            //상품 수정
            Item item = itemRepository.findById(itemFormDto.getId())
                    .orElseThrow(EntityNotFoundException::new);
            item.updateItem(itemFormDto);
            List<Long> itemImgIds = itemFormDto.getItemImgIds();

            //이미지 등록
            for(int i=0;i<itemImgFileList.size();i++){
                itemImgService.updateItemImg(itemImgIds.get(i),
                        itemImgFileList.get(i));
            }

            return item.getId();
        }
    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }
    //itemSearchDto ,  pageable 를 매개변수로 받아
    //itemRepository 에서 Item 데이터를 조회하는 작업

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }

    }





