package org.example.controller;

import org.example.service.MemberService;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.Scanner;

public class MemberController {


    private Connection conn;
    private Scanner sc;
    private MemberService memberserivce;

    public MemberController(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
        this.memberserivce = new MemberService();
    }

    public void doJoin() {

        String loginId = null;
        String loginPw = null;
        String loginPwConfirm = null;
        String name = null;

        System.out.println("이름 입력");
        name = sc.nextLine().

                trim();
        SecSql sql = new SecSql();
        while (true) {
            System.out.println("아이디 입력");
            loginId = sc.nextLine().trim();
            if (loginId.length() == 0 || loginId.contains(" ")) {
                System.out.println("아이디 똑바로 써");
                continue;
            }

            boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);

            System.out.println(isLoginIdDup);

            if (isLoginIdDup) {
                System.out.println(loginId + "아이디는 이미 사용중");
                continue;
            }
            break;

        }
        while(true) {
            System.out.println("비밀번호");
            loginPw = sc.nextLine().trim();
            if (loginPw.length() == 0 || loginPw.contains(" ")) {
                System.out.println("비밀번호 똑바로 입력해");
                continue;
            }


            boolean loginCheckPw = true;
            while (true) {
                System.out.println("비번 확인:");
                loginPwConfirm = sc.nextLine().trim();

                if (loginPwConfirm.length() == 0 || loginPwConfirm.contains(" ")) {

                    System.out.println("비밀번호 똑바로 입력해");
                    continue;
                } if (loginPw.equals(loginPwConfirm) == false) {
                    System.out.println("일치하지않아");
                    loginCheckPw = false;

                }
                break;
            }
            if (loginCheckPw) {
                break;

            }
             while(true){
                 System.out.println("이름 입력:");
                 name = sc.nextLine().trim();

                 if(name.length()==0 || name.contains(" ")){
                     System.out.println("이름 똑바로 작성해");
                     continue;
                 }
                 break;
             }
             int id = memberserivce.doJoin(conn,loginId , loginPw,name);

            System.out.println(id + "번 회원이 가입됨");


//            sql.append("INSERT INTO member");
//            sql.append("SET regDate = NOW(),");
//            sql.append("updateDate = NOW(),");
//            sql.append("'name' = ?,", name);
//            sql.append("loginId = ?;", loginId);
//            sql.append("loginPw = ?;", loginPw);
//             id = DBUtil.insert(conn, sql);
//            System.out.println(id + "번 회원이 가입됨 생성됨");
        }
    }
}