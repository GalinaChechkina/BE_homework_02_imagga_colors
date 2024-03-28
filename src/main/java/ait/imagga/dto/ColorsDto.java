package ait.imagga.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class ColorsDto {
     private String closest_palette_color;
     private String closest_palette_color_parent;
     private Double percent;
}
