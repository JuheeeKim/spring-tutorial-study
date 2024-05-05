package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // join에서 save를 호출하면, 다형성에 의해서 MemberRepository 인터페이스가 아니라 MemoryMemberRepository의 오버라이드 한 메서드가 호출됨.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
