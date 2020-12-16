package enums.enumExample;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumController {
    public static Map<String, List<EnumValue>> getEnumValue() {
        Map<String, List<EnumValue>> enums = new LinkedHashMap<>();

        Class commissionType = EnumContract.CommissionType.class;
        Class commissionCutting = EnumContract.CommissionCutting.class;

        enums.put("commissionType", toEnumValues(commissionType));
        enums.put("commissionCutting", toEnumValues(commissionCutting));
        return enums;

    }

    // EnumModel 배열을 EnumValue 리스트로 전환하는 일
    private static List<EnumValue> toEnumValues(Class<? extends EnumModel> e) {
        return Arrays
                .stream(e.getEnumConstants())
                .map(EnumValue::new)
                .collect(Collectors.toList());
    }
}
