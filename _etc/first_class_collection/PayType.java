package etc.first_class_collection;

public enum PayType {
    KAKAO_PAY,
    NAVER_PAY,
    TOSS;

    public static boolean isNaverPay(PayType payType) {
        return NAVER_PAY.equals(payType);
    }

    public static boolean isKakaoPay(PayType payType) {
        return KAKAO_PAY.equals(payType);
    }

    public static boolean isTossPay(PayType payType) {
        return TOSS.equals(payType);
    }
}
