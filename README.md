# ecommerce


## 프로젝트 기능 및 설계
 * 회원가입 기능<br>
   &#9675;사용자(customer), 판매자(seller)로 구분하여 회원가입을 받는다.<br>
   &#9675;사용자 타겟은 국내에 거주하는 내국인, 외국인으로 한다.<br>
   &#9675;이메일, 비밀번호, 비밀번호 확인, 이름, 전화번호, 배송지를 입력받는다.<br>
   &#9675;이메일 varchar(320)으로 한다. 이메일의 총 길이 제한은 254자이다. 로컬 파트 64자, 도메인 파트 255자이다. 하지만 로컬파트 + 도메인 파트가 254자를 넘을 수 없다. worst case에 대비해 255보다 여유롭게 잡는다. 앞의 근거는 RFC 5321을 따른다.<br>
   &#9675;비밀번호 varchar(128)으로 한다. 사용자의 편의성과 보안성을 근거로한다. 너무 길거나 복잡하지 않고 충분한 길이로 보안성을 강화할수 있다.<br>
   &#9675;이름 varchar(50)으로 한다. 다양한 국가와 문화에 맞추어, 긴 이름도 허용해야한다. 50자면 이름이 긴 중동 및 남미 국가까지 커버할 수 있다.<br>
   &#9675;전화번호 varchar(11)으로 한다. 다음의 정책상 이유가 근거이다. 한국식 전화번호를 기준으로 한다. 하이픈은 제외한다. 국제 전화번호 표준인 E.164를 따르지 않는다. <br>
   &#9675;배송지는 varchar(150)으로 한다.이유는 다음과 같다. 한국의 기본주소+상세주소 형식을 충분히 지원하기 때문이다. 기본주소는 도로명 주소를 기준으로 40~80자이기에 기본주소를 100으로 잡는다.
   상세주소는 아파트 동, 호수, 층 등의 상세주소는 보통 간결하지만, 긴 호수나 동을 표현할 수 있도록 50으로 설정하고 추가적인 상세주소를 적을 공간을 고려한 것이다. 이 둘을 합치면 100+50=150이 된다.<br>
   &#9675;이메일 인증을 해야 회원정보수정, 상품명 검색, 상품 장바구니 기능에 접근할 수 있다.<br>
   &#9675;이메일 인증 없이 로그인 시 "이메일 인증을 해주세요"라는 안내 메세지를 보여주는 페이지로 이동한다.

   
 * 로그인 기능<br>
   &#9675;이메일, 비밀번호를 입력받아 로그인한다.<br>
   &#9675; Srping Security와 JJWT를 활용한 토큰 로그인 방식으로 구현한다.

   
* 회원정보 수정<br>
   &#9675; 비밀번호, 이름, 전화번호, 배송지를를 변경할 수 있다.<br>


* 상품 등록, 수정, 삭제 기능<br>
   &#9675; 상품을 등록하는 기능을 제공한다.<br>
    -상품명(VARCHAR(100)), 가격(INT), 수량(INT), 상품 이미지(VARCHAR(255))를 입력받는다.
   &#9675; 상품을 수정하는 기능을 제공한다.<br>
   &#9675; 상품을 삭제하는 기능을 제공한다.<br>
   
* 상품명 검색 기능<br>
  &#9675;상품명을 검색하면 상품명, 가격을 보여준다.<br>
  &#9675;상품을 선택하면 상세페이지로 이동한다.<br>
  &#9675;상세페이지에서는 상품명, 가격, 상품 이미지를 보여준다.<br>
  &#9675;상세페이지에서는 상품 수량 선택, 장바구니 담기 기능을 제공한다.<br>
  &#9675;상품명(검색어)을 입력하면 자동완성기능이 활성화되어 검색어와 관련된 항목을 하단에 보여준다.<br>
  Trie 자료구조와 Like연산을 이용한 구현 중에 어느것으로 구현해야 할까요?
지금 제 프로젝트를 기준으로 생각하면 DB에 부하가 갈 만한 트래픽이 발생하지 않을 것이기 때문에 Like연산이 적절하다고 생각합니다. 이유는 구현이 간단하다. Trie라이브러리를 임포트하지 않아도 되고 회사명을 저장할때마다 Trie에 저장하는 작업을 따로 하지 않아도 된다.  하지만 개인프로젝트 특강에서 다른 멘토님이 대용량 트래픽 운영 경험이 중요하다고 하셨고 신입은 경험할수 없기에 상상하고 대처해보라고 하셨습니다.
그렇다면 빠른 연산이 가능하고 시간복잡도가 O(L):L은 문자열의길이이어서 문자열의 빠른 저장과 검색이 가능한한 Trie 자료구조를 사용해서 구현하고 “n+1, 다수 동시 접속, 대용량 접속”등을 상상해서 대처방법을 생각하고 작성해보아야 할까요?


  
* 상품 장바구니 기능<br>
  &#9675;상품담기

  
   - 상품 상세페이지에서 장바구니 담기 버튼을 클릭하면 해당 상품이 장바구에 추가된다.
 
     
  &#9675;장바구니 조회


   - '장바구니 조회' 버튼을 클릭하면 담긴 상품의 목록과 각 상품의 이름, 가격을 보여준다.
 
     
   - 상품 수량 선택 버튼을 제공한다.
 
     
   
  &#9675;장바구니 상품 삭제

  
   - '삭제' 버튼을 클릭하면 선택된 상품이 장바구에서 제거된다.
 
     
* 로그인/로그아웃에 따른 장바구니 접근 허가 기능<br>
  &#9675; 로그인/로그아웃에 따른 접근은 Spring Security에서 url 접근 코드를 작성하여 설정한다.<br>
  &#9675; 사용자가 로그인하면 해당 사용자 ID에 맞는 장바구니 목록을 조회할 수 있다.<br>
  &#9675; 로그아웃 상태에서는 장바구니에 접근할 수 없으며, "로그인해주세요"라는 안내 메시지를 표시한다. 그리고 로그인 페이지로 이동한다.<br>

  
* 주문<br>
  &#9675;주문페이지에서 받는 분 성함, 전화번호, 배송지, 배송 요청 사항, 상품명, 가격, 수량, 총가격을 입력 받는다.

   -  받는 분 성함은 varchar(50)으로 한다.
  
   - 전화번호는 varchar(11)로 한다.
  
   - 배송지는 varchar(320)으로 한다.
  
   -  배송 요청 사항은 varchar(200)으로 한다.
  
   - 상품명은 varchar(100)으로 한다.
  
   - 가격은 INT로 한다.
  
   - 수량은 INT로 한다.
  
   - 총 가격은 INT로 한다.

  &#9675; 주문 페이지에서 구매하기 버튼을 제공한다.

  &#9675; 구매하기 버튼을 클릭하면 주문 완료 메세지를 보여준다.


아래는 1차 개발이 완료되면 추가로 개발한다.


   &#9675;결제

   
   &#9675;주문상태관리(예: 준비중, 배송 중, 배송완료)

   
   &#9675;상품 재고 표시


   


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
