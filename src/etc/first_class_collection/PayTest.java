package etc.first_class_collection;

import java.util.Arrays;
import java.util.List;

import static etc.first_class_collection.PayType.*;

public class PayTest {
    public static void run() {
        List<Pay> pays = Arrays.asList(
                new Pay(NAVER_PAY, 1000),
                new Pay(NAVER_PAY, 1500),
                new Pay(KAKAO_PAY, 1000),
                new Pay(TOSS, 1500));

        PayGroups payGroups = new PayGroups(pays);
        Long naverPaySum = payGroups.getNaverPaySum();
        System.out.println(naverPaySum);

        Long kakaoPaySum = payGroups.getKakaoPaySum();
        System.out.println(kakaoPaySum);

    }
}
