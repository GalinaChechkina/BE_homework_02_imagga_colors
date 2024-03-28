package ait.imagga.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString

public class ColorsDto {

    private List<BackgroundColorDto> background_colors;
    private List<BackgroundColorDto> foreground_colors;
    private List<BackgroundColorDto> image_colors;


}
