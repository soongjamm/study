package resilience4j;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class OrderControllerTest {

    @LocalServerPort
    private int port;

    @ParameterizedTest
    @ValueSource(ints = {100, 400, 500, 550, 600, 650, 700, 750, 800, 850, 900})
    public void test(int delay) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        URI uri = URI.create("http://localhost:" + port + "/order/" + delay);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(httpResponse.body());
        assertEquals(200, httpResponse.statusCode());
    }

}