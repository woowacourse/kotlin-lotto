package lotto.model

class LottoMachine(amount: Int) {
    init {
        require(amount >= 0) { "[ERROR] 0원 이상의 금액으로 입력해 주세요. 입력값: $amount" }
        require(amount % 1000 == 0) { "[ERROR] 1,000원 단위의 금액으로 입력해 주세요. 입력값: $amount" }
    }
}
