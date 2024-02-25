# 수정할 사항

## 프로젝트
- [x] : EOF 에러 처리
- [ ] : 

## Controller
- [ ] : inputView 에 대한 Validation View 에서 하자!

## model

- [x] : getRankMap().let 로직 가독성 좋게 수정
- [x] : RandomNumberGenerator min, max 를 클라이언트가 바꿀 수 있도록 수정
- [ ] : LottoGameResultTest.kt - 랜덤으로 번호가 주여졌을 경우도 테스트
- [x] : MoneyTest - 하드 코딩으로 들어간 -1, 10, 1에 대해서 상수 값으로 분류를 해보는 것도 고민
- [ ] : RandomLottoGeneratorTest.kt - setUp() 랜덤하게 구현될 수 있도록 해보자!!
- [x] : LottoGameResultTest - expectedEarningRate 부분 하드 코딩으로 수정
- [x] : LottoNumber 추상화 - LottoNumber, GeneralLottoNumber 로 분리 (만약, 기획 변경에 의해 lottoNumber range 가 바뀔 수 있기 때문)
- [x] : BonusNumber 와 WinningLotto 의 중복 로직 BonusLottoNumber 에게 책임을 인가
