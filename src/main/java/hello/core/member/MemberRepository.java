package hello.core.member;

public interface MemberRepository {

    // member 저장 기능
    void save(Member member);

    // memberId로 member를 찾는 기능
    Member findById(Long memberId);
}
