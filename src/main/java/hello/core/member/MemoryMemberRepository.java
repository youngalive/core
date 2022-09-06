package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    // 저장소니까 담을 곳이 필요함
   /* 데이터베이스가 아직 확정이 안되었다. 그래도 개발은 진행해야 하니 가장 단순한, 메모리 회원 저장소를
    구현해서 우선 개발을 진행하자.
            > 참고: HashMap 은 동시성 이슈가 발생할 수 있다. 이런 경우 ConcurrentHashMap 을 사용하자.*/
    private static Map<Long, Member> store = new HashMap<>();
    // static 이 붙은 필드나 메서드는, '인스턴스의 소유'가 아닌, '클래스의 소유'

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
        //오류 처리도 해줘야하지만 예제의 핵심이 아니기 때문에 넘어감
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
