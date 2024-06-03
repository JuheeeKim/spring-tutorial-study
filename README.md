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
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/fd0ee40d-bff0-4730-88f8-acc31d7e8522.png"  width="580" height="180"/> </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/99675ec2-0e80-47ea-8b92-b5f1777e0425.png"  width="580" height="190"/> </br>

#### 📖회원 도메인 개발 </br>
회원 등급, 회원 엔티티, 회원 저장소 인터페이스, 메모리 회원 저장소 구현체, 회원 서비스 인터페이스, 회원 서비스 구현체를 만든다. </br>

#### 📖회원 도메인 실행과 테스트 </br>
Test 코드를 작성해 실행하면 정상적으로 작동하는 것을 알 수 있다. </br>

#### 📖주문과 할인 도메인 설계 </br>
* 주문과 할인 정책 </br>
   - 회원은 상품을 주문할 수 있다. </br>
   - 회원 등급에 따라 할인 정책을 적용할 수 있다. </br>
</br>

**주문 도메인 협력, 역할, 책임**</br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/53adee49-639c-490e-832c-46eb6de59a29"  width="570" height="370"/> </br>

#### 📖주문과 할인 도메인 개발 </br>
할인 정책 인터페이스, 정액 할인 정책 구현체, 주문 엔티티, 주문 서비스 인터페이스, 주문 서비스 구현체를 만든다. </br>

#### 📖주문과 할인 도메인 실행과 테스트 </br>
Test 코드를 작성해 실행하면 정상적으로 작동하는 것을 알 수 있다. </br>

</br>

### 📒섹션3 스프링 핵심 원리 이해 - 객체 지향 원리 적용</br>
#### 📖새로운 할인 정책 개발 </br>
주문한 금액의 %를 할인해주는 새로운 정률 할인 정책을 추가한다. </br>
<img src="https://github.com/JuheeeKim/spring-tutorial-study/assets/123529128/0a944688-1200-4be2-af83-e931487162ac.png"  width="500" height="200"/> </br>
</br>

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

### 📒섹션7 의존관계 자동 주입 </br>
#### 📖다양한 의존관계 주입 방법 </br>
**생성자 주입** </br>
```java
@Component
public class OrderServiceImpl implements OrderService{

    private final ...
    private final ...

    // 생성자
    @Autowired // 의존 관계 자동 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		...
    }
}
```
* 생성자를 통해 의존 관계를 주입 받는 방법, 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다. </br>
* 생성자로 주입된 것은 수정하면 안 되고, 값이 없으면 안 된다. </br>
* 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다. </br>
</br>

**수정자 주입(setter 주입)** </br>
```java
@Component
public class OrderServiceImpl implements OrderService{

    // 수정자를 사용하기 위해서 final을 없애야 한다.
    private ...
    private ...

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        ...
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        ...
    }
}
```
* setter라는 수정 메서드를 통해 의존 관계를 주입한다. </br>
* 선택, 변경 가능성이 있을 때 사용한다. </br>
* @Autowired(required = false) 를 통해 선택적으로 주입 가능하다. </br>
</br>

**필드 주입** </br>
```java
@Component
public class OrderServiceImpl implements OrderService{

    @Autowired private ...
    @Autowired private ...

    ...
}
```
* 필드에 바로 주입하는 방법이다. </br>
* 코드는 간결하지만, 외부에서 변경이 불가능해 테스트하기 힘들다는 단점이 있다. </br>
* 사용하지 말자! (애플리케이션의 실제 코드와 관계 없는 테스트 코드에서는 사용 가능) </br>
</br>

**일반 메서드 주입** </br>
```java
@Component
public class OrderServiceImpl implements OrderService {
	private ...
	private ...
    
	@Autowired
	public void init(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
		...
	}
}
```
* 일반적으로 잘 사용하지 않는다. </br>
</br>

#### 📖옵션 처리 </br>
주입할 스프링 빈이 없어도 동작해야 할 때, 자동 주입 대상을 옵션으로 처리해야 한다. </br>
```java
public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ...
    }

    static class TestBean {

        // 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
```
* `@Autowired(required=false)`: 자동 주입할 대상이 없으면, 수정자 메서드 자체가 호출되지 않는다. </br>
* `@Nullable`: 자동 주입할 대상이 없으면 `null`이 입력된다. </br>
* `Optional<>`: 자동 주입할 대상이 없으면 `Optional.empty`가 입력된다. </br>
</br>

#### 📖생성자 주입을 선택하라! </br>
* 수정자 주입(setter)는 public으로 열어둬서 누군가 변경할 수 있다. </br>
* 대부분의 의존관계 주입은 한 번 일어나면 애플리케이션 종료 시점까지 의존관계를 변경할 일이 없다. </br>
* 생성자 주입은 객체를 생성할 때 딱 1번 호출되므로 **불변**하게 설계할 수 있다. </br>
* 또한, 주입 데이터를 누락했을 때 컴파일 오류가 발생한다. (데이터 누락 대처 가능) </br>
</br>

```java
@Component
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Autowired
	... // 생성자 주입
}
```
* final 키워드는 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다. </br>
* 오직 생성자 주입 방식만 `final` 키워드를 사용할 수 있다. </br>
</br>

#### 📖롬복과 최신 트랜드 </br>
Lombok 라이브러리가 제공해주는 @RequiredArgsConstructor를 사용해 코드를 간결하게 만들 수 있다. </br>
```java
@Component
@RequiredArgsConstructor // final이 붙은 것을 모아 생성자 자동 생성
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // Lombok이 자동으로 생성자를 만들어줘서 필요없음
//    // 생성자
//    // @Autowired // 의존 관계 자동 주입, 생성자가 딱 1개만 있으면 생략 가능
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
```
</br>

#### 📖조회 빈이 2개 이상 - 문제 </br>
DiscountPolicy의 하위 타입인 FixDiscountPolicy, RateDiscountPolicy 둘 다 스프링 빈으로 선언하고 조회를 하면 문제가 발생한다. </br>
```java
@Component
public class FixDiscountPolicy implements DiscountPolicy{

@Component
public class RateDiscountPolicy implements DiscountPolicy{
```
둘 다 스프링 빈으로 선언하고 Test를 돌리면 `NoUniqueBeanDefinitionException` 오류가 발생한다. </br>
</br>

#### 📖@Autowired 필드 명, @Qualifier, @Primary </br>
**@Autowired 필드 명 매칭** </br>
여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다. </br>
```java
@Component
public class OrderServiceImpl implements OrderService{

    private final ...
    private final ...

    // 생성자
    @Autowired // 의존 관계 자동 주입, 생성자가 딱 1개만 있으면 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        ...
        this.discountPolicy = rateDiscountPolicy;
    }
```
</br>

**@Qualifier 사용** </br>
* 추가 구분자를 붙여주는 방법이다. </br>
* 주입시 추가적인 방법을 제공하는 것이지, 빈 이름이 변경되지 않는다. </br>
```java
@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

@Component
public class OrderServiceImpl implements OrderService{

    private final ...
    private final ...

    // 생성자
    @Autowired // 의존 관계 자동 주입, 생성자가 딱 1개만 있으면 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
		...
    }
```
</br>

만약 수정자 자동 주입일 경우 다음과 같이 수정한다. </br>
```java
@Autowired
public DiscountPolicy setDiscountPolicy(@Qualifier("mainDiscountPolicy")
DiscountPolicy discountPolicy) {
	...
}
```
</br>

**@Primary 사용** </br>
우선순위를 정한다. 의존관계 주입시 `@Primary`가 우선권을 가진다. </br>
```java
@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

@Component
public class FixDiscountPolicy implements DiscountPolicy{

@Component
public class OrderServiceImpl implements OrderService{

    private final ...
    private final ...

    // 생성자
    @Autowired // 의존 관계 자동 주입, 생성자가 딱 1개만 있으면 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		...
    }
```
</br>

#### 📖어노테이션 직접 만들기 </br>
`@Qualifier("mainDiscountPolicy")` 이렇게 문자를 적으면 컴파일시 문자에 대한 타입 체크가 안 된다. 다음과 같은 어노테이션을 만들어서 문제를 해결할 수 있다. </br>
```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    
}
```

```java
@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

@Component
public class OrderServiceImpl implements OrderService{

    private final ...
    private final ...

    // 생성자
    @Autowired // 의존 관계 자동 주입, 생성자가 딱 1개만 있으면 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
		...
    }
```
</br>

#### 📖조회한 빈이 모두 필요할 때, List, Map </br>
```java
public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        // 생성자
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
			...
        }
    }
}

```
Test 코드를 돌리면 DiscountPolicy의 모든 스프링 빈을 조회할 수 있다. </br>
* `Map<String, DiscountPolicy>`: 생성자를 통해 Map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다. </br>
* `List<DiscountPolicy>`: 생성자를 통해 Discountpolicy 타입으로 조회한 모든 스프링 빈을 담아준다. </br>
</br>

#### 📖자동, 수동의 올바른 실무 운영 기준 </br>
* 컴포넌트 스캔을 기본으로 사용하고, 스프링 빈들도 조건이 맞으면 **자동**으로 등록하는 것을 권장한다. </br>
* 그러나 다형성을 적극 활용하는 비지니스 로직이나 애플리케이션에 광범위하게 영향을 미치는 기술 지원 객체는 수동 빈으로 등록해, 설정 정보에 명확하게 드러내는 것이 유지보수에 좋다. </br>
</br>

### 📒섹션8 빈 생명주기 콜백</br>
#### 📖빈 생명주기 콜백 시작 </br>
**스프링 빈의 이벤트 라이프사이클** </br>
: 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의곤관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료 </br>
* 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출 </br>
* 소멸전 콜백: 빈이 소멸되기 직전에 호출 </br>

스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다. </br>

#### 📖인터페이스 InitializingBean, DisposableBean </br>
```java
public class NetworkClient implements InitializingBean, DisposableBean {
	...

    // 의존관계 끝나면 호출
    @Override
    public void afterPropertiesSet() throws Exception {
		...
    }

    // 스프링 종료 전 호출
    @Override
    public void destroy() throws Exception {
		...
    }
}
```
* `InitializingBean`은 `afterPropertiesSet()` 메서드로 초기화 지원하고, `DisposableBean`은 `destory()` 메서드로 소멸을 지원한다. </br>
* 그러나 이 인터페이스는 스프링 전용 인터페이스로, 해당 코드가 스프링 전용 인터페이스에 의존한다. 그리고 메서드의 이름을 변경할 수 없고, 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다. </br>
* 지금은 거의 사용하지 않는다. </br>
</br>

#### 📖빈 등록 초기화, 소멸 메서드 지정 </br>
설정 정보에 초기화, 소멸 메서드를 지정한다. </br>
```java
public class NetworkClient {

	...

    // 의존관계 끝나면 호출(Bean에서 init으로 등록)
    public void init() {
		...
    }

    // 스프링 종료 전 호출(Bean에서 destory으로 등록)
    public void close() {
		...
    }
}
```

```java
...
public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
		...
    }

    @Configuration
    static class LifeCycleConfig {
    	// 설정 정보에 초기화, 소멸 메서드 지정 
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
			...
        }
    }
}
```
설정 정보를 사용하여 메서드 이름을 자유롭게 줄 수 있고, 스프링 빈이 스프링 코드에 의존하지 않으며, 외부 라이브러리에도 적용할 수 있다. </br>
</br>

#### 📖어노테이션 @PostConstruct, @PreDestroy </br>
결론적으로 이 방법을 사용한다. </br>
```java
public class NetworkClient {

	...

    // 의존관계 끝나면 호출
    @PostConstruct
    public void init() {
		...
    }

    // 스프링 종료 전 호출
    @PreDestroy
    public void close() {
		...
    }
}
```
* `@PostConstruct`, `@PreDestroy` 애노테이션을 사용하면 가장 편리하게 초기화 종료를 실행할 수 있다. </br>
* 애노테이션만 붙이면 되므로 매우 편리하고, 자바 표준이라 스프링이 아닌 다른 컨테이너에서도 동작하며 컴포넌트 스캔과 잘 어울린다. </br>
* 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean의 `initMethod`, `destoryMethod`를 사용하자. </br>
</br>

### 📒섹션9 빈 스코프</br>
#### 📖빈 스코프란? </br>
빈 스코프란 빈이 존재할 수 있는 범위를 뜻한다. 기본적으로 스프링 빈은 싱글톤 스코프로 생성된다. </br>
* 싱글톤: 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지된다. </br>
* 프로토타입: 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관여하지 않는다. </br>
* 웹 관련 스코프 </br>
   - request: 웹 요청이 들어오고 나갈 때까지 유지된다. </br>
</br>

#### 📖프로토타입 스코프 </br>
프로토타입 스코프는 스프링 컨테이너에 요청할 때마다 새로 생성된다. </br>
```java
public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        ...
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        ...
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

		...

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            ...
        }

        @PreDestroy
        public void destroy() {
            ...
        }
    }
}
```
* 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다. </br>
* 클라이언트에 빈을 반환한고, 컨테이너는 더이상 빈을 관리하지 않는다. 그래서 `@PreDestroy` 같은 종료 메서드가 호출되지 않는다. </br>
</br>

#### 📖프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점 </br>
**싱글톤 빈에서 프로토타입 빈 사용** </br>
```java
public class SingletonWithPrototypeTest1 {

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ...
        Assertions.assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean; // 생성시점에 주입

        @Autowired // 의존관계 주입
        public ClientBean(PrototypeBean prototypeBean) {
            ...
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            ...
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        ...
    }
}
```
싱글톤 빈은 생성시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되지만, 싱글톤 빈과 함께 계속 유지되는 것이 문제다. </br>
</br>

#### 📖프로토타입 스코프 - 싱글톤 빈과 함께 사용시 Provider로 문제 해결 </br>
**ObjectFactory, ObjectProvider** </br>
```java
static class ClientBean {

    @Autowired
    private ObjectProvider<PrototypeBean> prototypeBeanProvider;

    public int logic() {
        PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
        ...
    }
}
```
* 지정한 빈을 컨테이너에서 대신 찾아주는 DL 기능을 제공한다. </br>
* 실행해보면 `prototypeBeanProvider.getObject()`을 통해서 항상 새로운 프로토타입 빈이 생성된다. </br>
</br>

**JSR-330 Provider** </br>
```java
dependencies {
	...	    
	jakarta.inject:jakarta.inject-api:2.0.1
	...
}
static class ClientBean {

    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider;

    public int logic() {
        PrototypeBean prototypeBean = prototypeBeanProvider.get();
        ...
    }
}
```
* `provider.get()`을 통해 항상 새로운 프로토타입 빈이 생성된다. </br>
* `provider`의 `get()`을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.(**DL**) </br>
</br>

#### 📖웹 스코프 </br>
* 웹 스코프는 웹 환경에서만 동작한다. </br>
* 스프링이 해당 스코프의 종료시점까지 관리하여, 종료 메서드가 호출된다. </br>
* 웹 스코프 종류에는 request, session, application, websocket이 있다. </br>
</br>

#### 📖request 스코프 예제 만들기 </br>
* 동시에 여러 HTTP 요청이 오면 정확히 어떤 요청이 남긴 로그인지 구분이 어렵다. </br>
* 이럴때 사용하기 딱 좋은 것이 바로 request 스코프이다. </br>
```java
@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    // requestURL setter
    ...

    public void log(String message) {
        ...
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        ...
    }

    @PreDestroy
    public void close() {
        ...
    }
}
```
@Scope(value = "request")를 사용해서 request 스코프로 지정했다. HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸된다. </br>
</br>

```java
@Controller
@RequiredArgsConstructor // 생성자 자동 주입
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo") // log-demo 페이지에 접근
    @ResponseBody // 화면 없이 응답 데이터만 받을 수 있음
    public String logDemo(HttpServletRequest request) { // HttpServletRequest: 고객요청 정보 받을 수 있음
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        ...
    }
}
```
HttpServletRequest를 통해 요청 URL을 받는다. </br>
</br>

```java
@Service
@RequiredArgsConstructor
public class LogDemoService {
    
    private final MyLogger myLogger;
    
    public void logic(String id) {
        ...
    }
}
```
하지만 작성한 코드들을 실행해보면 에러가 발생한다. 스프링이 실행될 때에는 request 요청이 없기 때문이다. </br>

#### 📖스코프와 Provider </br>
위의 문제를 해결하기 위해 Provider를 사용한다. </br>
```java
@Controller
@RequiredArgsConstructor // 생성자 자동 주입
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider; // 주입이 아니라 Look up

    @RequestMapping("log-demo") // log-demo 페이지에 접근
    @ResponseBody // 화면 없이 응답 데이터만 받을 수 있음
    public String logDemo(HttpServletRequest request) { // HttpServletRequest: 고객요청 정보 받을 수 있음
        ...
        MyLogger myLogger = myLoggerProvider.getObject(); // 여기서 꺼냄
        ...
    }
}
```

```java
@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        MyLogger myLogger = myLoggerProvider.getObject();
        ...
    }
}
```
* 위와 같이 수정하고, 웹 브라우저에 `http://localhost:8080/log-demo`를 입력하면 log가 잘 찍히는 것을 확인 할 수 있다. </br>
* 요청을 한 번 더 하면 다른 uuid를 가진 것을 확인할 수 있다. </br>
* `ObjectProvider` 덕분에 `ObjectProvider.getObject()`를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다. </br>
</br>

#### 📖스코프와 프록시 </br>
MyLogger의 가짜 프록시 클래스를 만들어두고, HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다. </br>
```java
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
	...
}
```
</br>

**웹 스코프와 프록시 동작 원리**
* CGLIB라는 바이트코드를 조작하는 라이브러리를 사용해서, MyLogger를 상속받는 가짜 프록시 객체를 생성한다. </br>
* 스프링 컨테이너에 진짜 대신에 **가짜 프록시 객체를 등록**한다. </br>
* 그래서 의존관계 주입도 이 가짜 프록시 객체가 주입된다. </br>
* 가짜 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어 있다. </br>
</br>

#### 인프런 - "스프링 입문 - 스프링 핵심 원리 - 기본편" 강의를 참고하여 공부한 내용을 바탕으로 작성하였습니다.
