package com.ssafy.luminous.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // 2000 - 2300 희찬
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    NOT_MATCHED_CAMPING(false, 2100, "일치하는 캠핑장이 없습니다."),

    // users
    NOT_MATCHED_ID(false, 2050, "일치하는 아이디가 없습니다."),
    DUPLICATED_ID(false, 2051, "중복된 아이디입니다."),
    FAILED_TO_LOGIN(false,2052,"아이디 혹은 비밀번호가 일치하지 않습니다."),


    // 2301 - 2600 준혁
    NOT_OWNER(false,2301,"게시글 권한이 없습니다."),
    CAN_NOT_FOUND_PLACE(false, 2302, "마이플레이스를 찾을 수 없습니다."),
    GPT_API_ERROR(false, 2303, "현재 ChatGPT 사용에 문제가 발생했습니다."),
    CAN_NOT_CALL_TODAY_FORTUNE(false,2304,"오늘의 운세를 불러올 수 없습니다."),






    // 2601 - 3000 준모


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    FAILED_TO_REGISTER(false, 3050, "회원가입에 실패했습니다."),
    FAILED_TO_DELETE(false, 3051, "회원탈퇴에 실패했습니다."),

    /**
     * 4000 : Database, Server 오류
     */

    DATABASE_ERROR(false, 4000, "데이터베이스에 문제가 발생했습니다."),
    API_ERROR(false, 4100, "API호출에 문제가 발생했습니다.");
    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
