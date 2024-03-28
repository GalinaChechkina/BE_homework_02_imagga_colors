package ait.imagga.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString

public class ColorListsDto {

    private List<ColorsDto> background_colors;
    private List<ColorsDto> foreground_colors;
    private List<ColorsDto> image_colors;


}
