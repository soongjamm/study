package etc.enums.enumExample;

import java.util.*;

public class EnumExample1 {

    private ContractRepository contractRepository;

    public static void run() {
//        result(enumAdd());
//        EnumController controller = new EnumController();

        AppConfig appconfig = new AppConfig();
        EnumMapper mapper = appconfig.enumMapper();
        mapper.put("COMMISSION", EnumContract.CommissionTest.class);
        result(mapper.getAll());
        result(mapper.get("commissionType"));

    }

    private static void result(Map<String, List<EnumValue>> map) {
        Set<String> keySet = map.keySet();
        Iterator<String> keyIterator = keySet.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            List<EnumValue> value = map.get(key);

            System.out.println(key);
            for (EnumValue e : value) {
                System.out.println("{\n\tKey : " + e.getKey());
                System.out.println("\tValue : " + e.getValue() + "\n},");
            }
            System.out.println();
        }
    }

    private EnumContract enumAdd() {
        return new EnumContract(
                "배고픈회사",
                1.0,
                EnumContract.CommissionType.MONEY,
                EnumContract.CommissionCutting.CEIL);
    }

    private void add() {
        Contract contract = new Contract(
                "밥잘주는회사",
                1.0,
                "percent",
                "round"
        );
    }

    private void result(EnumContract enumContract) {
        System.out.println(enumContract.getCompany());
        System.out.println(enumContract.getCommission());
        System.out.println(enumContract.getCommissionType());
        System.out.println(enumContract.getCummissionCutting());
    }

}
