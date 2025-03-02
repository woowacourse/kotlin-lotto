# 🍀 로또

## 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 구현할 기능 목록
- [x] 입력 뷰에서 구입금액을 Int로 입력받아 컨트롤러에서 lottoPaymentMoney로 인스턴스화 한다
  (lottoPaymentMoney에서 논리오류 예외를 던진 뒤 컨트롤러에서 캐칭 후 재입력 처리)

- [x] 입력 뷰에서 수동 구매수량을 Int로 입력 받아 컨트롤러에서 LottoQuantity로 인스턴스화 한다
  (LottoQuantity에서 논리오류 예외를 던진 뒤 컨트롤러에서 캐칭 후 재입력 처리)

- [x] 입력 뷰에서 <List<Int>>로 티켓 한장 분량의 수동 번호 정보를 입력받는다

- [ ] 컨트롤러에서 ManualLottoTicket 구현체를 통해 수동 LottoQuantity만큼 수동 번호 정보를 입력받아 LottoTicket 리스트를 생성한다
  (LottoTicket에서 논리오류 예외를 던진 뒤 컨트롤러에서 캐칭 후 재입력 처리)

- [ ] lottoPaymentMoney에 수동 LottoQuantity를 전달해 자동 LottoQuantity를 받는다

- [ ] 자동 LottoQuantity만큼 AutoLottoTicket 구현체를 통해 List<LottoTicket>로 인스턴스화 한다

- [ ] 출력 뷰에 수동, 자동 LottoQuantity와 List<LottoTicket>를 전달해 구매 정보를 콘솔 출력한다

- [ ] 입력 뷰에 서 List<Int>로 당첨 번호 정보를, Int로 보너스 번호를 입력받아 WinTicketInfo로 인스턴스화 한다
  (WinTicketInfo에서 논리오류 예외를 던진 뒤 컨트롤러에서 캐칭 후 재입력 처리)

- [ ] List<LottoTicket>을 통해 당첨 통계 맵이 담긴 WinningStatistics 인스턴스를 만든다

- [ ] 출력 뷰에게 WinningStatistics를 전달해 당첨 통계를 콘솔 출력한다.

- [ ] lottoPaymentMoney를 WinningStatistics에 전달해 EarningInfo 인스턴스를 만든다
  (EarningInfo는 수익률을 2자리로 반올림한 값을 가지고, 이익, 본전, 손실 상태값을 반환할 수 있어야 한다)

- [ ] 출력 뷰에게 EarningInfo를 전달해 수익률과 이익, 본전, 손실 상태를 콘솔 출력한다