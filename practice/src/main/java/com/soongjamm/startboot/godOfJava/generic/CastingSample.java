package com.soongjamm.startboot.godOfJava.generic;

public class CastingSample {
    public static void main(String[] args) {
        CastingSample sample = new CastingSample();
        sample.castingObject();
        sample.castingGeneric();
    }

    private void castingGeneric() {
        // 선언 시점에 어떤 타입이 들어갈지 명시한다.
        // 그래서 컴파일타임에 미리 에러를 받을 수 있다.
        CastingDtoGeneric<String> dto1 = new CastingDtoGeneric();
        dto1.setObject(new String());

        CastingDtoGeneric<StringBuilder> dto2 = new CastingDtoGeneric();
        dto2.setObject(new StringBuilder());

        CastingDtoGeneric<StringBuffer> dto3 = new CastingDtoGeneric();
        dto3.setObject(new StringBuffer());

        // 제네릭을 쓰면 일일이 형변환을 하지 않아도 된다.
        String o1 = dto1.getObject();
        // String o1 = dto2.getObject(); // 잘못된 타입으로 받으려하면 컴파일 에러 발생
        StringBuilder o2 = dto2.getObject();
        StringBuffer o3 = dto3.getObject();
    }

    public void castingObject() {
        CastingDtoObject dto1 = new CastingDtoObject();
        dto1.setObject(new String());

        CastingDtoObject dto2 = new CastingDtoObject();
        dto2.setObject(new StringBuilder());

        CastingDtoObject dto3 = new CastingDtoObject();
        dto3.setObject(new StringBuffer());

        // 제네릭을 안쓰면 Object를 일일이 형변환 해줘야한다. 형변환을 잘못하면 런타임에 잡힌다. 컴파일 타임엔 모른다.
        String o1 = (String) dto1.getObject();
        StringBuilder o2 = (StringBuilder) dto2.getObject();
        StringBuffer o3 = (StringBuffer) dto3.getObject();
    }
}
