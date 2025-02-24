# kotlin-lotto

# 기능 요구사항

- [x] 로또 번호는 6개이다.
- [x] 로또 번호를 1부터 45까지 입력 받는다.
- [x] 로또 번호는 중복이 없다.
- [x] 보너스 번호는 로또 당첨 번호와 중복될 수 없다.
- [x] 당첨 결과에 따라 순위를 계산한다.

```
1등 : 6개 일치 [상금 2,000,000,000원]
2등 : 5개 일치 + 보너스 번호 일치 [상금 30,000,000원]
3등 : 5개 일치 [상금 : 1,500,000원]
4등 : 4개 일치 [상금 : 50,000원]
5등 : 3개 일치 [상금 : 5,000원]
당첨 실패 : 2개 이하 [상금 : 0원]
```

- [x] 구입 금액을 입력 받는다.
- [x] 구입 금액은 최소 1000원 부터, 1000원 단위로 입력 받는다.
- [x] 구매한 로또를 발행 한다.
- [x] 구매한 로또 번호와 개수를 출력 한다.
- [x] 지난 주 당첨 번호를 입력 받는다.
- [x] 보너스 볼을 입력 받는다.
- [x] 보너스 볼은 1부터 45까지 입력 받는다.
- [x] 한 장의 Lotto에 대한 당첨 결과를 구한다.
- [x] 로또 결과에 따른 수익률을 구한다.
- [x] 당첨 통계와 수익률을 출력한다.
- [x] 수익률이 1보다 낮으면 손해임을 알려준다.

## 피드백 리팩토링
- [x] (Null값과 타입)검증 로직을 controller에서 view로 이동
- [x] 로또의 범위 검증에 필요한 숫자 상수화
- [x] LottoResult의 기본 결과 맵을 companion object로 이동
- [x] 중복 한정자 제거
- [x] 나라에 따른 로또의 범위 및 갯수를 바꿀 수 있도록 수정
- [x] 테스트 네이밍을 명확하게 작성
- [x] 테스트 코드의 가독성 향상을 위한 test Fixture 적용
- [x] 테스트 코드의 에러 메시지 상수화
- [x] 불필요한 래핑 제거

## 두번째 피드백 리팩토링
- [x] Lotto클래스 프로퍼티의 list내 Int를 LottoNumber로 래핑
- [x] 로또 객체 변환 및 추출 로직을 LottoFactory로 함수화
- [x] 공통 에러 메시지를 Const 파일로 분리
- [x] LottoResult의 result를 Immutable Map으로 변경
