package hello.core.member;

public interface MemberRepository {

    // member의 정보를 저장하는 기능
    void save(Member member);

    // member의 Id로 member를 찾는 기능
    Member findById(Long memberId);
}
