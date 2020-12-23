package etc.first_class_collection;

import java.util.List;
import java.util.function.Predicate;

public class PayGroups {
    private List<Pay> pays;

    public PayGroups(List<Pay> pays) {
        this.pays = pays;
    }

    public Long getNaverPaySum() {
        return getFilteredPays(pay -> PayType.isNaverPay(pay.getPayType()));
    }

    public Long getKakaoPaySum() {
        return getFilteredPays(pay -> PayType.isKakaoPay(pay.getPayType()));
    }

    public Long getTossPaySum() {
        return getFilteredPays(pay -> PayType.isTossPay(pay.getPayType()));
    }

    private Long getFilteredPays(Predicate<Pay> predicate) {
        return pays.stream()
                .filter(predicate)
                .mapToLong(Pay::getAmount)
                .sum();
    }
}
