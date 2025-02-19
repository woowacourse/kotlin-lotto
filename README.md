# 🚀 로또

## 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 기능 목록
### 로또 번호
- [x] 1~45 사이의 숫자이다.

### 구매 금액
- [ ] 0 이상의 숫자이다.

### 구매 수량
- [x] 구입 금액은 1000원 단위이다.
- [x] 구입 금액은 1000원 이상이다.

### 수익률
- [ ] 0.0 이상 1.0 이하인 실수이다.

### 로또
- [x] 6개의 로또 번호를 갖는다.
- [x] 로또 번호는 중복되지 않는다.
- [ ] 로또 번호는 오름차순으로 정렬된다.

### 당첨 로또
- [x] 6개의 당첨 번호와 보너스 번호를 갖는다.
- [x] 당첨 번호와 보너스 번호는 중복되지 않는다.
- [x] 로또 번호를 비교하여 로또 등수를 구한다.

### 로또 발매기
- [x] 로또 구매 수량에 해당하는 만큼의 로또를 발급한다.

### 자동 로또 생성기
- [x] 랜덤한 `로또 번호`객체로 이루어진 `로또`인스턴스를 반환한다.

### 로또 당첨 판별기
- [ ] 당첨 로또와 구입한 로또를 비교하여 당첨 정보를 구한다.

### 당첨 정보
- [ ] 등수별 당첨 횟수를 저장한다.
- [ ] 당첨 수익률을 계산한다.


## 동작 과정
1. 컨트롤러가 게임 실행 및 진행 관리
2. 입력 뷰에서 `로또 구매 금액` 인스턴스를 가져온다.
3. 구매 금액을 바탕으로 구매 수량을 구한다.
4. 출력 뷰에게 `로또 구매 수량`을 전달하여 구매 수량을 콘솔 출력한다.
5. `로또 발매기`에게 `로또 구매 수량`을 전달하여 `자동 로또 생성기`를 통해 구매한 `로또` 리스트를 가져온다.
6. 구매한 `로또` 리스트 출력 뷰에게 전달하여 구입한 전체 로또들 숫자를 출력한다.
7. 입력 뷰에서 `당첨 로또` 인스턴스를 가져온다.
8. `로또 당첨 판별기`에게 `당첨 로또`와 구매한 `로또` 리스트를 전달하여 `당첨 정보` 인스턴스를 가져온다.
9. 결과 뷰에게 `당첨 정보` 인스턴스를 전달하여 당첨 통계를 콘솔 출력한다.
10. `당첨 정보`에서 `수익률`을 가져온다.
11. 결과 뷰에게 `수익률` 인스턴스를 전달하여 수익률을 콘솔 출력한다.