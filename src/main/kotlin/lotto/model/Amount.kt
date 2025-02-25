package lotto.model

data class Amount(
    var money: Int,
) {
    init {
        require(money >= 0) { MONEY_UNDER_ZERO }
    }

    fun payMoney(inputMoney: Amount) {
        require(inputMoney.money <= money) { MONEY_OVER_INPUT }
        money -= inputMoney.money
    }

    fun moneySplit(splitMoney: Amount): List<Amount> {
        val moneyList: MutableList<Amount> = mutableListOf()
        require(money % splitMoney.money == 0) { MONEY_SPILT_ERROR }
        repeat(money / splitMoney.money) { moneyList.add(splitMoney) }
        return moneyList
    }

    companion object {
        const val MONEY_UNDER_ZERO = "[ERROR] 금액은 양수이어야 합니다."
        const val MONEY_OVER_INPUT = "[ERROR] 입력된 금액보다 더 많은 수량의 로또는 살 수 없습니다."
        const val MONEY_SPILT_ERROR = "[ERROR] 로또 금액에 맞게 나눌 수 없습니다"
    }
}
