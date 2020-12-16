package enums.enumExample;

// Bean
public class AppConfig {
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("commissionType", EnumContract.CommissionType.class);
        enumMapper.put("commissionCutting", EnumContract.CommissionCutting.class);
        return enumMapper;
    }
}
