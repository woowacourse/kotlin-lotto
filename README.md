# kotlin-lotto

## 기능 요구 사항

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

### 2차 피드백 반영

- [x] 사용자 입력 예외 발생 시에 사용자 입력 값과 필요 설명 추가
- [x] 로또 숫자 생성기에서 로또 생성기로 기능 변경
- [x] 로또 규칙을 각자 어울리는 클래스의 상수로 이전
- Enum의 명시순서에 의존하지 않도록 로직 수정
- 객체의 값을 꺼내서 사용하기 보다 메시지를 던지도록 수정
- 객체를 재사용하기 위해서 수정
- 메서드 체이닝 단점 분석
- assertJ 메서드 체이닝 활용
- CsvSource로 변경 가능한 테스트 수정
- 총 수익률 계산 표본 관련 고민 및 수정