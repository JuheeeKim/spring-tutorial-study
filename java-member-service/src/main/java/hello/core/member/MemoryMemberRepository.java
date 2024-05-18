package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // Map의 key: long타입->Id저장 ,, Map의 value: id, name, grade 인스턴스를 가진 Member 객체 저장
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    // Map에서 해당 memberId에 해당하는 Member 객체를 가져옴, 키값을 통해 Map의 값(Member객체)을 찾아옴
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
