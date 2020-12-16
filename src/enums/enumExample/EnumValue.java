package enums.enumExample;

public class EnumValue {

    private String key;
    private String value;

    public EnumValue(EnumModel enumModel) {
        this.key = enumModel.getKey();
        this.value = enumModel.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
