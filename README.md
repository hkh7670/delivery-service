# Delivery Service (바로고 실무과제)

1. 테이블 설명
   1. ADM_INFO : 법정동 코드 테이블 (출처 : https://www.code.go.kr/stdcode/regCodeL.do)
   2. MENU_INFO : 메뉴 정보
   3. MERCHANT : 가맹점 정보
   4. MERCHANT_DELIVERY_INFO : 특정 가맹점이 배달 가능한 법정동 정보
   5. ORDER : 주문 정보
   6. ORDER_DETAIL : 주문 상세 정보 (메뉴ID, 메뉴갯수 등등..)
   7. USER_INFO : 유저 정보

2. Swagger : http://localhost:8080/swagger-ui.html

3. 배달주소 변경 시 체크 로직
   1. 배달 중 이거나 이미 배달이 완료된 경우 주소 변경 불가
   2. 가맹점이 배달 불가능한 법정동 리스트인 경우 변경 불가 
   3. ADM_INFO 테이블을 참조하여 폐지유무를 파악한 후 폐지된 법정동으로 수정을 시도할 경우 변경 불가

4. 배달내역 조회
   1. 조회기간 3일 초과 시 exception
   2. 배달내역 조회 시 배달가맹점 정보, 주문상세정보도 같이 반환

5. 로그인 관련
   1. 로그인 성공 시 accessToken을 반환
   2. 존재하지않는 ID or 패스워드 오입력 시 exception

6. 회원가입 관련
   1. 비밀번호 포맷을 만족하지 않는 경우 exception
   2. 비밀번호 길이가 12자리 미만일 경우 exception
