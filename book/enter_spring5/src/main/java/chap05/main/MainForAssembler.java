package chap05.main;

import chap05.assembler.Assembler;
import chap05.spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {

    private static Assembler assember = new Assembler();

    private static void processNewCommand(String[] args) {
        if (args.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService regSvc = assember.getMemberRegisterService();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(args[1]);
        req.setName(args[2]);
        req.setPassword(args[3]);
        req.setConfirmPassword(args[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.");
            return;
        }
        try {
            regSvc.register(req);
            System.out.println("등록했습니다.\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }

    private static void processChangeCommand(String[] args) {
        if (args.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService changePwdSvc = assember.getChangePasswordService();
        try {
            changePwdSvc.changePassword(args[1], args[2], args[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch(MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch(WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재암호 변경암호");
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요.");
            String command = reader.readLine();
            if (command.equals("exit")) {
                System.out.println("종료합니다.");
                break;
            }
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            }
            printHelp();
        }
    }
}
