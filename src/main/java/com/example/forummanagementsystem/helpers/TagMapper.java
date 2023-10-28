package com.example.forummanagementsystem.helpers;

import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.dto.TagDto;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public Tag fromTagDto(TagDto tagDto){
        Tag tag=new Tag();
        tag.setContent(tagDto.getContent().toLowerCase());
        return tag;
    }
}
