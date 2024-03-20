// 문서가 준비되면 제일 먼저 실행
$(document).ready(function (){
    $("#payment").click(function (){
        payment();
    });
});

// 버튼을 클릭하면 실행
function payment() {
    // 가맹점 식별 코드(아직 로그인한 사람만 결제 진행 구현x, 고유한 merchant_uid를 생성하지 않으면 중복결제 문제가 발생)
    var merchant_uid = 'iamport_see_' + new Date().getTime(); // 고유한 merchant_uid 생성

    IMP.init('imp56304776');
    IMP.request_pay({
        pg: "kakaopay.TCSUBSCRIP",
        pay_method: "card",
        merchant_uid: merchant_uid,
        name: "정기구독권",
        amount: 1,
        buyer_email: "qkrwjdgus1441@naver.com",
        buyer_name: "홍길동",
        buyer_tel: "01077215987"
    }, function (rsp){
        if(rsp.success){
            alert("완료 -> imp_uid: " + rsp.imp_uid + " / merchant_uid(orderKdy) : " + rsp.merchant_uid);
        } else {
            alert("실패 : 코드(" + rsp.error_code+ ") / 메세지(" + rsp.error_msg + ") ");
        }
    });
}
