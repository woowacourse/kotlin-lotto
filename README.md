# 🚀 로또

## 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 기능 목록
### 로또
- [x] 6개의 중복되지 않는 로또 번호 인스턴스를 갖는다.
- [x] 로또 번호는 오름차순으로 정렬하여 반환한다.
- [x] 수동으로 입력된 번호를 받아 초기화된 자신의 인스턴스를 반환한다.
- [x] 자동으로 생성된 번호를 통해 초기화된 자신의 인스턴스를 반환한다.

### 로또 발매기
- [x] 로또 지불 정보와 수동 로또 번호들을 읽고 로또 묶음 인스턴스로 반환한다.

### 로또 묶음
- [x] 당첨 로또 인스턴스를 입력받아 로또 묶음의 당첨 통계 인스턴스를 반환한다.

### 로또 당첨 통계
- [x] 당첨 통계 정보와 로또 구매 정보를 갖는다.
- [x] 당첨 통계 정보와 로또 구매 정보를 이용해 수익률을 계산하여 수익률 인스턴스로 반환한다.
- [x] MISS를 제외한 전체 당첨 통계 정보를 반환한다.

### 당첨 로또
- [x] 당첨 로또 번호로 구성된 로또 인스턴스와 보너스 번호를 갖는다.
- [x] 당첨 번호와 보너스 번호는 서로 중복되지 않는다.
- [x] 로또 객체를 입력받아 자신이 가지고 있는 당첨 정보와 비교하여 해당하는 Rank를 반환한다.

### 랜덤 로또 생성기
- [x] 랜덤한 로또 번호 인스턴스로 이루어진 컬렉션을 반환한다.

### 수익률 정보
- [x] 0.0 이상의 실수를 가진다.
- [x] 수익률을 가져올 경우 소수점 아래 2자리로 반올림된다.
- [x] 이익, 본전, 손해 카테고리 정보를 반환한다.

### 로또 번호
- [x] 1이상 이하의 정수를 가진다.

### 로또 지불 정보
- [x] 로또 구매 금액은 1000원 단위이다.
- [x] 로또 구매 금액은 1000원 이상이다.
- [x] 수동 로또 구매 수량을 반환한다.
- [x] 자동 로또 구매 수량을 계산을 통해 반환한다.

## 리팩터링 기능 목록
- [ ] 전체 과정에서 테스트부터 재작성하고 구현한다. 예외 발생 여부 확인이 아닌 구현 자체를 테스트 하도록 노력한다.
- [x] 이득, 본전, 손해를 구분하는 상태값을 정의하며 수익률에 따라 상태값을 반환시킨다.
- [x] 출력 뷰에서 상태값을 읽어 이익, 본전, 손실을 나타낼 문자열을 구성해 출력한다.
- [x] 불필요한 toSet()을 제거하고 set을 입력 받던 곳에서 유연하게 컬렉션을 입력 받을 수 있도록 한다.
- [x] 로또 발매기를 인터페이스로 추상화하고 구체화해서 사용하며 원시값을 사용하지 않는다.
- [x] 비논리 오류 예외를 컨트롤러에서 null로 처리한다.
- [x] 모호한 함수명을 명확화한다.
- [x] 패키지명이 의도에 맞는지, 역할별로 모아져 있는지 다시 확인한다.

## 동작 과정
1. 컨트롤러가 게임 실행 및 진행을 관리한다
2. 구매 금액과 수동 구매 수량을 입력 뷰에서 받아 `로또 구매 정보` 인스턴스를 생성한다.
3. `로또 구매 정보`를 입력 뷰에게 넘겨 수동 구매 수량만큼 수동 번호들을 가져온다
4. 수동 번호와 자동 구매 로또들을 사용해 `로또 묶음` 객체를 생성한다.
5. 출력 뷰에게 `로또 구매 정보`을 전달하여 자동 및 수동 구매 수량을 콘솔 출력한다.
6. 출력 뷰에게 `로또 묶음`을 전달하여 구매한 전체 로또 번호들을 콘솔 출력한다.
7. 입력 뷰에서 당첨 로또 번호와 보너스 번호를 입력받아 `당첨 로또` 인스턴스를 생성한다.
8. `로또 묶음`에게 `당첨 로또`인스턴스를 전달하여 `로또 당첨 통계` 인스턴스를 가져온다.
9. 출력 뷰에게 `로또 당첨 통계` 인스턴스를 전달하여 당첨 통계를 콘솔 출력한다.
10. `로또 당첨 통계` 인스턴스에서 `수익률 정보`를 받아와 출력 뷰를 통해 콘솔 출력한다.

