package chap08.main;

import chap08.config.AppCtx;
import chap08.spring.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainForMemberDao {
    private static MemberDao memberDao;

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        memberDao = ctx.getBean(MemberDao.class);

        selectAll();
        updateMember();
        insertMember();

        ctx.close();

    }

    private static void selectAll() {
        System.out.println("----- selectAll");
        int total = memberDao.count();
        System.out.println("전체 데이터 " + total);
        List<Member> members = memberDao.selectAll();
        for (Member m : members) {
            System.out.println(m.getId() + ":" + m.getEmail() + "," + m.getName());
        }
    }

    private static void updateMember() {
        System.out.println("----- updateMember");
        Member member = memberDao.selectByEmail("madvirus@madvirus.net");
        String oldPw = member.getPassword();
        String newPw = Double.toHexString(Math.random());
        member.changePassword(oldPw, newPw);

        memberDao.update(member);
        System.out.println("암호 변경: " + oldPw + " > " + newPw);
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

    private static void insertMember() {
        System.out.println("----- inserMember");
        String prefix = formatter.format(LocalDateTime.now());
        System.out.println(prefix);
        Member member = new Member(prefix + "@test.com", prefix, "myname", LocalDateTime.now());
        memberDao.insert(member);
        System.out.println(member.getId() + " 데이터 추가");
    }
}
