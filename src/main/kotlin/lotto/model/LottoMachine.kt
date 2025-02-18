package lotto.model

class LottoMachine(amount: Int) {
    init {
        require(amount % 1000 == 0) { "[ERROR] 1,000원 단위의 금액으로 입력해 주세요." }
    }
}
