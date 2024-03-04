# 프로젝트 전체
- [x] : lotto 상위 package 추가... 
- [x] : 한 메서드에 오직 한 단계의 들여쓰기만 한다.
- [x] : 함수의 길이가 15라인을 넘어가지 않도록 구현한다
## View
- [x] : 수동으로 구매할 로또 수 입력 받기(input) - n
- [x] : 수동으로 구매할 번호들을 입력(input) - List<List<Int>> 반환
- [x] : lotto 구매 구매 현황 수동 + 자동 현황 View 추가 (output)

## Controller
- [x] : 수동 구매할 로또 수  
- [x] : purchaseExpense Int -> Money로 수정

## Model
- [ ] : 테스트 코드 상수화 (코드 리뷰) - 일단 보류
- [ ] : 랜덤값도 테스트 해보자! (코드 리뷰)
- [x] : Money - times operator 함수 만들 도록 하자
- [x] : Money - compareTo operator 함수 구현
- [X] : List<Int> -> Lotto 팩토리 메서드 함수 추가
- [x] : 수동 로또 생성기 추가 구현
- [x] : Lotto Machine - (구매 금액 >= 수동 로또 수 * 로또 가격) 비교 기능
- [x] : Lotto Machine - 수동 로또, 자동 로또 생성 책임
- [x] : 자동 로또 생성기 네이밍 변경 (LottieGenerator -> AutoLottieGenerator)
