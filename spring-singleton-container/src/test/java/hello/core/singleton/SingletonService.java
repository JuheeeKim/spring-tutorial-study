package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체 instance를 미리 하나 생성해서 올려둠
    private static final SingletonService instance = new SingletonService();

    // 2. 이 객체 인스턴스가 필요하면 오직 getInstance()를 통해서만 조회가능, 호출하면 항상 같은 인스턴스를 반환함
    public static SingletonService getInstance(){
        return instance;
    }

    // 3. 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막음
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
