package chap03.config;

import chap03.spring.Member;
import chap03.spring.MemberPrinter;

public class AppCtxNoMemberPrinterBean {
    private MemberPrinter printer = new MemberPrinter();
}
