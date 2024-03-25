$(document).ready(function (){
    $("#payment").click(function (){
        // 서버로부터 현재 구독권의 가격을 가져와서 비교
        $.ajax({
            type: "GET",
            url: "/getSubscriptionPrice",
            success: function (response) {
                var subscriptionPrice = parseInt(JSON.parse(response).price); // JSON 문자열을 파싱하여 price 추출
                var amount = 5000; // 결제할 금액

                if (amount === subscriptionPrice) {
                    // 가격이 일치하면 결제 진행
                    payment();
                } else {
                    alert("구독권 가격이 일치하지 않습니다.");
                }
            },
            error: function(xhr, status, error) {
                console.error("가격 가져오기 실패:", error);
            }
        });
    });
});

function payment() {
    var merchant_uid = 'iamport_see_' + new Date().getTime();
    var amount = 5000; // 결제할 금액
    var ticket_date = new Date().toISOString().split('T')[0];

    IMP.init('imp56304776');
    IMP.request_pay({
        pg: "kakaopay.TCSUBSCRIP",
        pay_method: "card",
        merchant_uid: merchant_uid,
        name: "정기구독권",
        amount: amount,
        ticket_date: ticket_date,
        buyer_name: "홍길동",
    }, function (rsp){
        if(rsp.success){
            // 결제 성공 시 데이터를 저장
            savePaymentData(rsp.imp_uid, rsp.merchant_uid, "정기구독권", "홍길동", amount, ticket_date);
            alert("완료 -> imp_uid: " + rsp.imp_uid + " / merchant_uid(orderKdy) : " + rsp.merchant_uid);
        } else {
            // 결제 실패 시 데이터를 저장
            savePaymentData(rsp.imp_uid, rsp.merchant_uid, "", "", "", rsp.error_code, rsp.error_msg);
            alert("실패 : 코드(" + rsp.error_code+ ") / 메세지(" + rsp.error_msg + ") ");
        }
    });
}

function savePaymentData(ticket_id, ticket_uid, ticket_name, ticket_username, amount, ticket_date) {
    $.ajax({
        type: "POST",
        url: "/payment",
        data: {
            ticket_id: ticket_id,
            ticket_uid: ticket_uid,
            ticket_name: ticket_name,
            ticket_username: ticket_username,
            amount: amount, // 결제 금액을 서버로 전송
            ticket_date: ticket_date // 결제 날짜를 서버로 전송
        },
        success: function (response) {
            console.log("결제 정보 저장 완료");
        },
        error: function(xhr, status, error) {
            console.error("결제 정보 저장 실패:", error);
        }
    });
}
