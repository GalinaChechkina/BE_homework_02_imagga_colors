package ait.imagga;

import ait.imagga.dto.BackgroundColorDto;
import ait.imagga.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

public class ImaggaAppl {
    private static final String AUTHORIZATION = "Basic YWNjX2U0ZjNkMGFiZTNlYjVmMTo5NGUzMmYyMWYwZWQ5ZGI5Y2UwNzc5ODBkZDRkNjNhNg==";

    public static void main(String[] args) {

// Создаем объект класса RestTemplate для того, чтобы можно было отправлять HTTP-запросы и
// получать HTTP-ответы с API
        RestTemplate restTemplate = new RestTemplate();

// Объект класса HttpHeaders представляет HTTP-заголовки для запроса или ответа.
// У нас этот объект используется для добавления заголовка Authorization к HTTP-запросу.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AUTHORIZATION);

// Объект класса UriComponentsBuilder помогает построить URL для HTTP-запроса с помощью метода fromHttpUrl
// и метода добавления параметров queryParam.
// У нас URL будет использоваться для отправки запроса к API сервиса Imagga для анализа цветов изображения
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.imagga.com/v2/colors")
                .queryParam("image_url","https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg")
                .queryParam("threshold",30)
                .queryParam("language","en");

// С помощью метода build() строим URI, вызываем метод toUri(), чтобы построить объект (полный URL)
        URI url = builder.build().toUri();

        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);

        ResponseEntity<ResponseDto> response =restTemplate.exchange(request, ResponseDto.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders().get("Content-type"));
        List<BackgroundColorDto> list1 = response.getBody().getResult().getColors().getBackground_colors();
        List<BackgroundColorDto> list2 = response.getBody().getResult().getColors().getImage_colors();
        List<BackgroundColorDto> list3 = response.getBody().getResult().getColors().getForeground_colors();

        System.out.println("Color name\t\tParent color name\t\tCoverage percent");
        printColors(list1);
        printColors(list2);
        printColors(list3);

    }

    public static void printColors(List<BackgroundColorDto> list){

        list.forEach(e -> {
            System.out.printf("%-20s\t%-20s\t%-20s\n", e.getClosest_palette_color(), e.getClosest_palette_color_parent(), e.getPercent());
        });
    }
}
