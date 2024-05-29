## Spring tutorial study

### 📒색션1 객체 지향 설계와 스프링</br>
#### 📖Spring 이란? </br>
스프링은 하나의 기술이 아니라 여러 기술들의 모음이다. </br>
스프링의 핵심은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크라는 것이다. </br>

#### 📖좋은 객체 지향 프로그램이란? </br>
객체 설계시 역할(인터페이스)를 안정적으로 잘 설계하고, 그 역할을 수행하는 구현 객체를 만든다. </br>
역할과 구현을 명확히 분리하여 다형성을 활용하는 것이 중요하다. </br>

#### 📖좋은 객체 지향 원칙(SOLID) </br>
* SRP 단일 책임 원칙: 한 클래스는 하나의 책임만 가져야 한다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것. </br>
* OCP 개방-폐쇄 원칙: 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다. 다형성 활용. </br>
* LSP 리스코프 치환 원칙: 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다. </br>
* ISP 인터페이스 분리 원칙: 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다. </br>
* DIP 의존관계 원칙: 구현 클래스에 의존하지 말고, 역할(인터페이스)에 의존해야 한다. </br>

#### 📖객체 지향 설계와 스프링 </br>
스프링은 다형성, OCP, DIP를 가능하게 지원하고 클라이언트 코드의 변경없이 기능 확장을 돕는다. </br>

</br>

### 📒섹션2 스프링 핵심 원리 이해 - 예제 만들기</br>
#### 📖프로젝트 생성 </br>
Java 21, InteliJ를 사용하였다. </br>
[스프링 부트 스타터](https://start.spring.io/) 에서 스프링 프로젝트 생성. </br>

#### 📖비즈니스 요구사항과 설계 </br>
* 회원 요구사항 </br>
   - 회원을 가입하고 조회할 수 있다. </br>
   - 회원은 일반과 VIP 두 가지 등급이 있다. </br>
* 주문과 할인 정책 </br>
   - 회원은 상품을 주문할 수 있다. </br>
   - 회원 등급에 따라 할인 정책을 적용할 수 있다. </br>
   - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용한다. </br>

#### 📖회원 도메인 설계 </br>


### 📒섹션3 스프링 핵심 원리 이해 - 객체 지향 원리 적용</br>

### 📒섹션4 스프링 컨테이너와 스프링 빈</br>

### 📒섹션5 싱글톤 컨테이너</br>

### 📒섹션6 컴포넌트 스캔</br>

### 📒섹션7 의존관계 자동 주입</br>

### 📒섹션8 빈 생명주기 콜백</br>

### 📒섹션9 빈 스코프</br>


</br>
</br>

#### 인프런 - "스프링 입문 - 스프링 핵심 원리 - 기본편" 강의를 참고하여 공부한 내용을 바탕으로 작성하였습니다.