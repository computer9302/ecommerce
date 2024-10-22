# ecommerce


## 프로젝트 기능 및 설계
 * 회원가입 기능<br>
   &#9675;사용자(customer), 판매자(seller)로 구분하여 회원가입을 받는다.<br>
   &#9675;이메일, 비밀번호, 비밀번호 확인, 이름, 전화번호를 입력받는다.<br>
   &#9675;이메일 인증을 해야 회원정보수정, 상품명 검색, 상품 장바구니 기능에 접근할 수 있다.<br>
   &#9675;이메일 인증 없이 로그인 시 "이메일 인증을 해주세요"라는 안내 메세지를 보여주는 페이지로 이동한다.

   
 * 로그인 기능<br>
   &#9675;이메일, 비밀번호를 입력받아 로그인한다.<br>
   &#9675;1차 개발이 완료되고 추가 개발이 가능하다면 휴대폰번호를 사용한 로그인 기능을 구현한다.<br>

   
* 회원정보 수정<br>
   &#9675; 이메일, 이름, 휴대폰 번호, 비밀번호 변경할 수 있다.<br>
   &#9675; 배송지 주소관리는 회원정보 수정 페이지에서 한다.<br>

   
* 상품명 검색 기능<br>
  &#9675;상품명을 검색하면 상품명, 가격을 보여준다.<br>
  &#9675;상품을 선택하면 상세페이지로 이동한다.<br>
  &#9675;상세페이지에서는 상품명, 가격, 상품 이미지를 보여준다.<br>
  &#9675;상세페이지에서는 상품 수량 선택, 장바구니 담기 기능을 제공한다.<br>
  &#9675;상품명(검색어)을 입력하면 자동완성기능이 활성화되어 검색어와 관련된 항목을 하단에 보여준다.<br>

  
* 상품 장바구니 기능<br>
  &#9675;상품담기

  
   - 상품 상세페이지에서 장바구니 담기 버튼을 클릭하면 해당 상품이 장바구에 추가된다.
 
     
  &#9675;장바구니 조회


   - '장바구니 조회' 버튼을 클릭하면 담긴 상품의 목록과 각 상품의 이름, 가격을 보여준다.
 
     
   - 상품 수량 선택 버튼을 제공한다.
 
     
   
  &#9675;장바구니 상품 삭제

  
   - '삭제' 버튼을 클릭하면 선택된 상품이 장바구에서 제거된다.
 
     
* 로그인/로그아웃에 따른 장바구니 접근 허가 기능<br>
  &#9675; 사용자가 로그인하면 해당 사용자 ID에 맞는 장바구니 목록을 조회할 수 있다.<br>
  &#9675; 로그아웃 상태에서는 장바구니에 접근할 수 없으며, "로그인해주세요"라는 안내 메시지를 표시한다. 그리고 로그인 페이지로 이동한다.<br>

  
* 주문<br>
개인프로젝트의 필수 기능이 모두 개발 완료된 후, 주문 기능을 추가로 개발한다.


## ERD
![Copy of ecommerce (2)](https://github.com/user-attachments/assets/8e9deebc-a50f-40e2-af56-ffbc35129fab)







## Tech Stack


* Language : Java 17

  
* Framework : Spring Boot 3.3.4

  
* Bulid Tool : Gradle 8.10.2

  
* Database : Mysql

  
* Cashing : Redis

  
* Cloud: AWS

  
* Containerization : Docker

  
* Framework : Spring Security, JPA

  
* Libararies: Lombok, JJWT
