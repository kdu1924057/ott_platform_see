package com.care.test.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentsController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketInfoRepository ticketInfoRepository;

    @PostMapping("/payment")
    public String iamport(@ModelAttribute("payment") Payment payment) {
        System.out.println("PostMapping /payment");
        System.out.println("Ticket id: " + payment.getTicket_id());
        System.out.println("Ticket uid: " + payment.getTicket_uid());
        System.out.println("Ticket Name: " + payment.getTicket_name());
        System.out.println("Ticket Username: " + payment.getTicket_username());
        System.out.println("Ticket date : " + payment.getTicket_date());
        System.out.println("Ticket Price: " + payment.getAmount());

        // 데이터베이스에서 구독권 가격을 조회하여 결제된 가격과 비교
        // 후검증 결제 진행후 DB와 가격 비교후 데이터 저장 불일치 저장x
        // 불일치시 결제 취소코드 추가 예정
        TicketInfo ticketInfo = ticketInfoRepository.findFirstByOrderByPriceAsc();
        String paymentAmount = ticketInfo.getPrice();

        // 결제된 가격과 구독권 가격 비교
        if (paymentAmount.equals(payment.getAmount())) {
            // 가격이 일치하면 결제 정보 저장
            paymentRepository.save(payment);
            System.out.println("결제가 완료되었습니다.");
        } else {
            // 가격이 일치하지 않으면 오류 처리
            System.out.println("결제 오류: 가격이 일치하지 않습니다.");
        }

        return "index";
    }

}
