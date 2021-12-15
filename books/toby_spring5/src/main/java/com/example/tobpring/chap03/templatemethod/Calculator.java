package com.example.tobpring.chap03.templatemethod;

import java.io.*;

public class Calculator {
    private CalculatorCommonTemplate template = new CalculatorCommonTemplate();

    public String concatenate(String path) throws IOException {
        LineCallback<String> concatenateCallback = new LineCallback<String>() {
            @Override
            public String doSomethingWithLine(String line, String value) {
                return value + line;
            }
        };
        return template.lineTemplate(path, concatenateCallback, "");
//        return lineTemplate(path, concatenateCallback, "");
    }

    public Integer multiply(String path) throws IOException {
        return template.lineTemplate(path, new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return Integer.valueOf(line) * value;
            }
        }, 1);
    }

    public Integer sum(String path) throws IOException {
        return template.lineTemplate(path, new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return Integer.valueOf(line) + value;
            }
        }, 0);

    }
//
//
//    private <T> T lineTemplate(String path, LineCallback<T> callback, T initValue) throws IOException {
//        BufferedReader br = null;
//        String line = null;
//        T res = initValue;
//
//        try {
//            br = new BufferedReader(new FileReader(path));
//            while ((line = br.readLine()) != null) {
//                res = callback.doSomethingWithLine(line, res);
//            }
//            return res;
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            throw e;
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
