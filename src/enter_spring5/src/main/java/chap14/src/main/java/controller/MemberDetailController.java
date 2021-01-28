package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {

    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/members/{id}")
    public String detail(@PathVariable("id") Long memberId, Model model) {
        Member member = memberDao.selectById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }
}
