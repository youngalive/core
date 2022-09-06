package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) { //1. 주문생성 요청이 들어오면
        Member member = memberRepository.findById(memberId);  //2. 회원정보를 조회를 해서
        int discountPrice = discountPolicy.discount(member, itemPrice); //3. 할인정책에 회원을 넘김
        // OrderServiceImpl은 discountPolicy에 대해서 모름 => 단일 책임의 원칙이 잘 지켜짐

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
