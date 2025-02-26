# kotlin-lotto

## Level 1

### 기능 요구 사항

- [x] 로또는 6개의 숫자를 가진다.
- [x] 로또의 각 번호는 1~45이하의 숫자를 가진다.
- [x] 당첨 번호는 6개의 숫자를 가진다.
- [x] 구매 금액으로 로또를 구매한다.
- [x] 당첨 등수를 계산할 수 있다.
- [x] 총 수익률을 계산할 수 있다.
- [x] 구매 금액 입력 기능 구현
- [x] 구매한 로또 개수 출력 기능 구현
- [x] 로또 번호 정렬 기능 구현
- [x] 구매한 로또 번호 출력 기능 구현
- [x] 당첨 번호 입력 기능 구현
- [x] 보너스 번호 입력 기능 구현
- [x] 당첨 통계 출력 기능 구현
- [x] 총 수익률 출력 기능 구현
- [x] 보너스 번호 검증 기능 구현
- [x] 당첨 번호 중복 검증 기능 구현
- [x] 로또 등수를 반환하는 기능 구현
- [x] 로또 진행 흐름 구현
- [x] 최소 로또 구매 개수 검증 기능 구현

### 피드백 반영

- [x] 입력 문자열 trim 처리
- [x] LottoRank 계산 로직 변경
- [x] LottoNumbers 책임을 Lotto로 이전
- [x] Lotto의 numbers 타입을 Set으로 변경
- [x] 로또 관련 상수 LottoRules Enum Class로 관리하도록 변경
- [x] LottoRanks 일급 컬렉션 추가
- [x] Controller에서 Domain 로직 분리
- [x] LottoRankCalculator 로직 LottoRanks로 이전

### 2차 피드백 수정 사항

- refactor: WinningNumbers 로또 등수 계산 결과 Map으로 변경
- refactor: LottoResult List -> Map으로 자료구조 변경
- refactor: Scanner 대신 ConsoleKt readlnOrNull 사용
- refactor: Lotto 객체로 일치하는 번호 수 계산 & 보너스 번호 존재 여부 확인 로직 이동
- refactor: 로또 결과 출력 로직 수정
- refactor: LottoMachine에서 구매 금액에 대한 책임 분리
- feat: PurchaseAmount 추가
- chore: LottoMachine의 로또 가격 상수화
- refactor: LottoNumber Instance 재사용
- chore: LottoRanks -> LottoResult로 클래스명 변경
- refactor: LottoRules에서 로또 가격 제거
- feat: LottoGenerator 역할 분리
- feat: 로또 생성 성공 테스트 추가

<br>

---

## Level 2

### 기능 구현 전 Level 1 피드백 반영

- [x] 테스트를 위한 고정 번호 생성기 위치 변경
- [x] 함수 매개변수 네이밍 수정
- [x] Lotto의 isMatchBonusNumber 함수를 재사용 가능하도록 변경
- [x] LottoNumber 인스턴스 재사용 방식 변경
- [x] WinningNumbers 검증 시 기존 함수 활용하도록 변경
- [x] 테스트 코드 형식 변경 (give / when /then)
- [x] 로또 번호 정렬 테스트 코드 변경

<br>

### 기능 요구 사항

- [x] 수동으로 구매할 개수 만큼 직접 번호를 입력할 수 있다.
- [x] 직접 입력받은 번호로 로또를 생성할 수 있다.
- [x] 로또 번들을 결합할 수 있다.

- [x] 예외처리를 통해 에러가 발생하지 않도록 한다.
    - 논리적인 오류일 때만 예외를 던진다.
    - 논리적인 오류가 아니면 예외를 던지지 말고 null을 반환한다.
    - 실패하는 경우가 복잡해서 null로 처리할 수 없으면 sealed class를 반환한다.
    