package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    /*private final MemberRepository memberRepository = new MemoryMemberRepository(); //final은 무조건 값이 할당되어야함
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();*/

    private final MemberRepository memberRepository; //interface에만 의존
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) { //1. 주문생성 요청이 들어오면
        Member member = memberRepository.findById(memberId);  //2. 회원정보를 조회를 해서
        int discountPrice = discountPolicy.discount(member, itemPrice); //3. 할인정책에 회원을 넘김
        // OrderServiceImpl은 discountPolicy에 대해서 모름 => 단일 책임의 원칙이 잘 지켜짐

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
