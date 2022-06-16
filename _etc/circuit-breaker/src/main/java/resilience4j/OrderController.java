package resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    public static final String CIRCUIT_BREAKER_INSTANCE_NAME = "order";

    @GetMapping(value = "/{delay}")
    @CircuitBreaker(name = CIRCUIT_BREAKER_INSTANCE_NAME)
    public List<Payment> getPaymentWithDelay(@PathVariable int delay) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(delay);
        return buildPaymentList();
    }

    private List<Payment> buildPaymentList() {
        Payment payment1 = Payment.builder()
                .id(1)
                .productName("productName1")
                .build();
        Payment payment2 = Payment.builder()
                .id(2)
                .productName("productName2")
                .build();
        return List.of(payment1, payment2);
    }

    @Getter
    public static class Payment {
        private long id;
        private String productName;

        @Builder
        public Payment(long id, String productName) {
            this.id = id;
            this.productName = productName;
        }
    }
}
