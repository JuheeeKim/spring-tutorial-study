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
* OCP 개방-폐쇄 원칙: 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다. 클라이언트 코드를 변경하지 않아도 된다. </br>
* LSP 리스코프 치환 원칙: 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다. </br>
* ISP 인터페이스 분리 원칙: 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다. </br>
* DIP 의존관계 원칙: 구현 클래스에 의존하지 말고, 역할(인터페이스)에 의존해야 한다. </br>

#### 📖객체 지향 설계와 스프링 </br>
스프링은 다형성, OCP, DIP를 가능하게 지원하고 클라이언트 코드의 변경없이 기능 확장을 돕는다. </br>

</br>

### 📒섹션2 스프링 핵심 원리 이해 - 예제 만들기</br>
#### 📖프로젝트 생성 </br>
Java 21, InteliJ를 사용했다. </br>
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
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/fd0ee40d-bff0-4730-88f8-acc31d7e8522.png"  width="600" height="200"/> </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/99675ec2-0e80-47ea-8b92-b5f1777e0425.png"  width="600" height="210"/> </br>

#### 📖회원 도메인 개발 </br>
회원 등급, 회원 엔티티, 회원 저장소 인터페이스, 메모리 회원 저장소 구현체, 회원 서비스 인터페이스, 회원 서비스 구현체를 만든다. </br>

#### 📖회원 도메인 실행과 테스트 </br>
Test 코드를 작성해 실행하면 아래 사진처럼 정상적으로 작동하는 것을 알 수 있다. </br>
</br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/7bf0ad4d-fd98-4888-808a-733302203330"  width="300" height="180"/> </br>

#### 📖주문과 할인 도메인 설계 </br>
* 주문과 할인 정책 </br>
   - 회원은 상품을 주문할 수 있다. </br>
   - 회원 등급에 따라 할인 정책을 적용할 수 있다. </br>
</br>

**주문 도메인 협력, 역할, 책임**</br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/53adee49-639c-490e-832c-46eb6de59a29"  width="600" height="350"/> </br>

#### 📖주문과 할인 도메인 개발 </br>
할인 정책 인터페이스, 정액 할인 정책 구현체, 주문 엔티티, 주문 서비스 인터페이스, 주문 서비스 구현체를 만든다.

#### 📖주문과 할인 도메인 실행과 테스트 </br>
Test 코드를 작성해 실행하면 정상적으로 작동하는 것을 알 수 있다. </br>

</br>

### 📒섹션3 스프링 핵심 원리 이해 - 객체 지향 원리 적용</br>
#### 📖새로운 할인 정책 개발 </br>
주문한 금액의 %를 할인해주는 새로운 정률 할인 정책을 추가한다. </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/0a944688-1200-4be2-af83-e931487162ac.png"  width="500" height="200"/> </br>

#### 📖새로운 할인 정책 적용과 문제점 </br>
```java
// 변경 전
private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
// 변경 후
private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
```

* 역할과 구현을 충실하게 분리했다. -> OK </br>
* 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다. -> OK </br>
* 추상(인터페이스)뿐만 아니라 구체(구현)클래스에도 의존하고 있다. -> DIP 위반 </br>
* 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다. -> OCP 위반 </br>
</br>

**DIP를 위반하지 않도록 인터페이스에만 의존하도록 코드를 변경** </br>
```java
// 수정 후
private DiscountPolicy discountPolicy;
```

하지만 이 상태로 실행하면 오류가 발생한다.</br>
</br> 

#### 📖관심사의 분리 </br>
위 오류를 해결하려면 누군가가 구현 객체를 대신 생성하고 주입해주어야 한다. </br> 
 
**AppConfig의 등장과 생성자 주입** </br>
* 애플리케이션의 전체 동작 방식을 구성(Config)하기 위해 `구현 객체를 생성`하고, `연결`하는 책임을 가지는 별도의 설정 클래스를 만든다. </br>
* AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 `생성자를 통해 주입(연결)`해준다. </br>
  - `MemberServiceImpl` -> `MemoryMemberRepository` </br>
  - `OrderServiceImpl`  -> `MemoryMemberRepository`, `FixDiscountPolicy` </br>

```java
public class AppConfig {
    public MemberService memberService() {
       return new MemberServiceImpl(new MemoryMemberRepository());
    }

   public OrderService orderService() {
       return new OrderServiceImpl(
              new MemoryMemberRepository(),
              new FixDiscountPolicy());
    }
}
```

<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/04f82b30-7bda-4451-b18a-f0f24aab67a0.png"  width="500" height="330"/> </br>
* 객체의 생성과 연결은 AppConfig가 담당한다. </br> 
* **DIP 완성**: 객체 클래스는 추상(인터페이스)에만 의존하면 된다. </br> 
* 관심사의 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다. </br> 
</br> 

#### 📖AppConfig 리팩터링 </br>
```java
public class AppConfig {

    // MemberService -> memberRepository()
    public MemberService memberService() {
         return new MemberServiceImpl(memberRepository());
    }

    // OrderService -> memberRepository(), discountPolicy()
    public OrderService orderService() {
         return new OrderServiceImpl(
            memberRepository(),
            discountPolicy());
    }

    // memberRepository()
    public MemberRepository memberRepository() {
         return new MemoryMemberRepository();
    }

    // discountPolicy()
    public DiscountPolicy discountPolicy() {
         return new FixDiscountPolicy();
    }
}
```
* `new MemoryMemberRepository()` 부분 중복이 제거되었다. `MemoryMemberRepository`를 다른 구현체로 변경할 때 한 부분만 변경하면 된다. </br> 
* 리턴 타입을 통해 역할과 구현 클래스를 한 눈에 파악 가능하다. </br> 
</br> 

#### 📖새로운 구조와 할인 정책 적용 </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/82c225b8-65d2-43f6-924f-49b590cd0b8a.png"  width="500" height="330"/> </br>
* `FixDiscountPolicy` -> `RateDiscountPolicy`로 변경해도 구성 영역만 영향을 받고, 사용 영역은 전혀 영향을 받지 않는다. </br>
</br>

**AppConfig 클래스 수정 부분**
```java
   public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
       return new RateDiscountPolicy();
    }
```
할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다. </br>
클라이언트 코드인 `OrderServiceImpl`를 포함해서 사용영역의 어떤 코드도 변경할 필요가 없다. </br>
</br> 

#### 📖IoC, DI, 그리고 컨테이너 </br>
**제어의 역전 IoC** </br>
* 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성, 연결, 실행했다. </br>
* AppConfig가 등장한 이후에 구현 객체는 자신의 로직을 실행하는 역할만 담당한다. 제어 흐름은 AppConfig가 가져간다. </br>
* 프로그램의 **제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것**을 `제어의 역전(IoC)`이라 한다. </br>

**의존관계 주입 DI** </br>
* 의존관계는 정적인 클래스 의존관계와 실행 시점에 결정되는 동적인 객체(인스턴스) 의존관계를 분리해서 생각해야 한다. </br>
* **정적인 의존관계**: 정적 클래스 의존관계 만으로는 실제 어떤 객체가 `OrderServiceImpl`에 주입 될지 알 수 없다. </br>
</br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/5c459b33-0928-41fa-8407-e3bb78f5896b.png"  width="500" height="330"/> </br>
  
* **동적인 의존관계**: 애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된다. </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/9203164a-375a-43bf-b22a-d3ad42427e08.png"  width="500" height="170"/> </br>

* 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 **의존관계 주입**이라 한다. </br>
* 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다. </br>

**IoC 컨테이너, DI 컨테이너** </br>
* AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해주는 것을 </br>
* IoC 컨테이너, DI 컨테이너라 한다. </br>
</br>

### 📒섹션4 스프링 컨테이너와 스프링 빈</br>
#### 📖스프링 컨테이너 생성 </br>
```java
ApplicationContext ac = 
            new AnnotationConfigApplicationContext(AppConfig.class);
```
* `ApplicationContext`를 스프링 컨테이너라 한다. </br>
* `ApplicationContext` 는 인터페이스이다. </br>
* `new AnnotationConfigApplicationContext();` 이 클래스는 `ApplicationContext` 인터페이스의 구현체이다. </br>
</br>

**스프링 컨테이너 생성 과정** </br>
</br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/28f00671-732e-478d-8793-2171cbeb52a7.png"  width="550" height="250"/> </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/46d72436-4714-49bc-adb6-8d801418d89b.png"  width="550" height="250"/> </br>
1. 스프링 컨테이너 생성 </br>
* `AppConfig.class`를 구성 정보로 지정한다. </br>
* AppConfig 클래스에 설정을 구성한다는 뜻의 `@Configuration`을 붙여준다. </br>
2. 스프링 빈 등록 </br>
* `@Bean`, 빈 이름은 보통 메서드 이름을 사용한다. </br>
* 스프링 컨테이너에 메서드를 호출해서 반환된 객체를 스프링 빈으로 등록한다. </br>
3. 스프링 빈 의존관계 설정 </br>
* 스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)한다. </br>
</br>

#### 📖컨테이너에 등록된 모든 빈 조회 </br>
```java
//스프링 컨테이너 생성
AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(AppConfig.class);

// 스프링에 등록된 모든 빈 이름을 조회
ac.getBeanDefinitionNames();

 // "..." 이름을 가진 빈 객체(인스턴스)를 조회
ac.getBean(...);

// 직접 등록한 애플리케이션 빈
ROLE_APPLICATION
// 스프링이 내부에서 사용하는 빈
ROLE_INFRASTRUCTURE
```
</br>

#### 📖스프링 빈 조회 - 기본 </br>
```java
//스프링 컨테이너 생성
AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(AppConfig.class);

// bean 이름으로 조회
ac.getBean("memberService", MemberService.class);

// 이름없이 type으로만 조회
ac.getBean(MemberService.class);

// 구체 type으로 조회
ac.getBean("memberService",MemberServiceImpl.class);

// 없는 이름 조회하면 오류 발생
NoSuchBeanDefinitionException
```
</br>

#### 📖스프링 빈 조회 - 동일한 타입이 둘 이상 </br>
```java
//스프링 컨테이너 생성
AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(SameBeanConfig.class);

//타입으로 조회시 같은 타입이 둘 이상이면, 중복 오류 발생
NoUniqueBeanDefinitionException

//타입으로 조회시 같은 타입이 둘 이상이면, 빈 이름 지정
ac.getBean("memberRepository1", MemberRepository.class);

//특정 타입을 모두 조회
ac.getBeansOfType(MemberRepository.class);
```
</br>

#### 📖스프링 빈 조회 - 상속관계 </br>
* 부모 타입으로 조회하면, 자식 타입도 함께 조회한다. </br>
* 그래서 모든 자바 객체의 최고 부모인 `Object` 타입으로 조회하면, 모든 스프링 빈을 조회한다. </br>
```java
//스프링 컨테이너 생성
AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(TestConfig.class);

//부모 타입으로 조회시, 자식 둘 이상이면 중복 오류 발생
NoUniqueBeanDefinitionException

//부모 타입으로 조회시, 자식 둘 이상이면 빈 이름 지정
ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

//특정 하위 타입으로 조회
ac.getBean(RateDiscountPolicy.class);

//부모 타입으로 모두 조회
ac.getBeansOfType(DiscountPolicy.class);

//부모 타입으로 모두 조회 - Object
ac.getBeansOfType(Object.class);
```
</br>

#### 📖BeanFactory와 ApplicationContext </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/f69bbff6-b254-4a6f-9cb2-5d08b871a93f.png"  width="200" height="300"/> </br>
* ApplicationContext는 BeanFactory의 기능을 상속받아, 빈 관리 기능 + 부가 기능을 제공한다. </br>
* BeanFactory를 직접 사용할 일은 거의 없다. 부가기능이 포함된 ApplicationContext를 사용한다. </br>
* BeanFactory나 ApplicationContext를 스프링 컨테이너라 한다. </br>
</br>

#### 📖다양한 설정 형식 지원 - 자바 코드, XML </br>
```java
ApplicationContext ac =
            new GenericXmlApplicationContext("appConfig.xml");
```
스프링 컨테이너는 다양한 형식의 설정 정보를 받아들일 수 있게 유연하게 설계되어있다. (자바 코드, XML 등등) </br>
</br>

#### 📖ApplicationContext Vs AnnotationConfigApplicationContext </br>
* ApplicationContext는 AnnotationConfigApplicationContext의 **상위 interface**이다. </br>
* 그래서 ApplicationContext는 기능이 적고, AnnotationConfigApplicationContext는 비교적 많은 기능이 있다. </br>
* 실제 스프링 애플리케이션을 개발할 때에는 **ApplicationContext**를 사용한다. </br>
</br>

### 📒섹션5 싱글톤 컨테이너</br>
#### 📖웹 애플리케이션과 싱글톤 </br>
```java
//스프링이 없는 순수한 DI 컨테이너 테스트
AppConfig appConfig = new AppConfig();

//1. 조회: 호출할 때마다 객체 생성
//2. 조회: 호출할 때마다 객체 생성

//참조값이 다른 것을 확인
```
* 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때마다 객체를 새로 생성한다. </br>
* 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> **싱글톤 패턴** </br>
</br>

#### 📖싱글톤 패턴 </br>
객체가 1개만 생성되도록 하기 위해, **private**을 사용하는 싱글톤 패턴을 만든다. </br>
```java
public class SingletonService {
   //static 영역 -> 딱 하나만 존재하게 됨
   private static final SingletonService instance = new SingletonService();

   //getInstance 메소드를 통해 한번만 생성된 객체를 가져옴
   public static SingletonService getInstance() {
       return instance;
   }

   //생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막음
   private SingletonService() {

   }
}
```
* 인스턴스를 딱 하나 생성하고, Test를 만들어 실행하면 참조값이 같아 호출할 때마다 같은 인스턴스를 반환하는 것을 확인할 수 있다. </br>
* 이러한 싱글톤 패턴은 많은 문제점을 가지고 있다. 해당 문제점을 스프링을 활용한 싱글톤 컨테이너로 해결할 수 있다. </br>
</br>

#### 📖싱글톤 컨테이너 </br>
```java
ApplicationContext ac =
            new AnnotationConfigApplicationContext(AppConfig.class);

//1. 조회: 호출할 때마다 같은 객체 반환 (`ac.getBean()` 사용)
//2. 조회: 호출할 때마다 같은 객체 반환 (`ac.getBean()` 사용)

//참조값이 다른 것을 확인
```
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/75e6aa1d-fcf5-4111-b040-cacfa7f9f914"  width="500" height="300"/> </br>
**스프링 컨테이너** 덕분에 고객의 요청이 올 때마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 **효율적으로** 재사용할 수 있다. </br>
</br>

#### 📖싱글톤 방식의 주의점 </br>
* 객체 인스턴스를 하나만 생성해서 공유하는 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 **무상태(stateless)** 로 설계해야 한다. </br>
* 무상태(stateless)란 변경할 수 있는 상태가 아닌, 가급적 읽기만 가능한 상태를 말한다. </br>
* 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애 발생 위험이 있다!! </br>
</br>

#### 📖@Configuration과 싱글톤 </br>
```java
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    
	...
}
```
* memberService와 orderService빈 동일하게 `memberRepository()`를 호출한다. </br>
* 결과적으로 각각 다른 2개의 `MemoryMemberRepository`가 생성되면서 싱글톤이 깨지는 것처럼 보여 테스트를 진행한다. </br>
</br>

```java
public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        
		...

        // 같은 인스턴스가 조회된다.
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

    }
}
```
하지만 테스트 코드를 진행해보면, 같은 인스턴스가 조회되는 것을 알 수 있다. </br>
</br>

#### 📖@Configuration과 바이트코드 조작의 마법 </br>
스프링 컨테이너는 스프링 빈이 싱글톤이 되도록 보장해주어야 한다. </br>
```java
@Test
void configurationDeep() {
	...

	AppConfig bean = ac.getBean(AppConfig.class);
        
	// bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$d7ded4e7 출력
	System.out.println("bean = " + bean.getClass());
}
```
* AppConfig class를 출력해보면 우리가 만든 AppConfig가 아닌 CGLIB이라는 다른 클래스가 나오는 것을 확인할 수 있다. </br>
</br>

**CGLIB 예상 코드**
```java
 @Bean
 public MemberRepository memberRepository() {
       if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
             return 스프링 컨테이너에서 찾아서 반환;
       } else { //스프링 컨테이너에 없으면
            기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
            return 반환
      }
}
```
* CGLIB 라는 조작된 클래스가 싱글톤을 보장해준다. </br>
</br>

@Bean만 사용해도 스프링 빈으로 등록되지만, @Configuration을 적용하지 않으면 싱글톤이 보장되지 않는다. </br>
따라서, 스프링 설정 정보는 항상 `@Configuration`을 사용하도록 한다. </br>
</br>

### 📒섹션6 컴포넌트 스캔</br>
#### 📖컴포넌트 스캔과 의존관계 자동 주입 시작하기 </br>
`@ComponentScan`을 설정정보에 붙여서 컴포넌트 스캔을 해준다. </br>
```java
@Configuration
@ComponentScan
public class AutoAppConfig {
    
}
```
* 기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 하나도 없다!  </br>
</br>

```java
@Component
public class OrderServiceImpl implements OrderService{

	...

    @Autowired // 의존 관계 자동 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		...
    }
```
* 컴포넌트 스캔은 이름 그대로 `@Component` 어노테이션이 붙은 클래스를 스캔해서 스프링으로 등록하는 것을 말한다. </br>
* 생성자에 `@Autowired`를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 의존관계를 주입해준다. </br>
</br>

#### 📖탐색 위치와 기본 스캔 대상 </br>
```
@Configuration
@ComponentScan( 
	basePackages = "hello.core.member"
)
public class AutoAppConfig {
    
}
```
* `basePackages:`를 통해 탐색할 패키지의 시작 위치를 지정할 수 있다. 패키지를 포함한 하위 패키지를 모두 탐색한다. </br>
* 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다. </br>
</br>

#### 📖필터 </br>
* `includeFilters`: 컴포넌트 스캔 대상을 추가로 지정한다. </br>
* `excludeFilters`: 컴포넌트 스캔에서 제외할 대상을 지정한다. </br>

**컴포넌트 스캔 대상에 추가할 어노테이션** </br>
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    // 컴포넌트 스캔 대상에 추가할 애노테이션
}
```
</br>

**컴포넌트 스캔 대상에 추가할 클래스** </br>
```java
@MyIncludeComponent
public class BeanA {
    // 컴포넌트 스캔 대상에 추가할 클래스
}
```
</br>

**테스트 코드**
```java
public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		// Test 코드 작성
    }

    @Configuration
    // ANNOTATION이 MyIncludeComponent인 것은 포함
    // ANNOTATION이 MyExcludeComponent인 것은 미포함
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
            classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
            classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
```
* `includeFilters`에 `MyIncludeComponent`인 어노테이션을 추가해서 BeanA가 스프링 빈에 등록된다. </br>
* `excludeFilters`에 `MyExcludeComponent`인 어노테이션을 추가해서 BeanB는 스프링 빈에 등록되지 않는다. </br>
</br>

#### 📖중복 등록과 충돌 </br>
**자동 빈 등록 vs 자동 빈 등록** </br>
`ConflictingBeanDefinitionException` 예외 발생 </br>
</br>

**수동 빈 등록 vs 자동 빈 등록** </br>
```java
// 자동 빈 등록
@Component
public class MemoryMemberRepository implements MemberRepository {}

// 수동 빈 등록
@Configuration
public class AutoAppConfig {
    
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```
* 이 경우, Test에서는 수동 빈 등록이 우선권을 가진다.
* 하지만 스프링 부트인 CoreApplication을 실행해보면 오류를 볼 수 있다.
* 스프링 부트 에러: `Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true`
</br>

### 📒섹션7 의존관계 자동 주입</br>

### 📒섹션8 빈 생명주기 콜백</br>

### 📒섹션9 빈 스코프</br>


</br>
</br>

#### 인프런 - "스프링 입문 - 스프링 핵심 원리 - 기본편" 강의를 참고하여 공부한 내용을 바탕으로 작성하였습니다.
