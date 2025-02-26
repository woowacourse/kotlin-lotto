package lotto.model

data class Amount(
    val money: Int,
) {
    init {
        require(money >= 0) { MONEY_UNDER_ZERO }
    }

    fun payMoney(inputMoney: Amount): Amount = Amount(money - inputMoney.money)

    fun moneySplit(splitMoney: Amount): List<Amount> {
        val moneyList: MutableList<Amount> = mutableListOf()
        repeat(money / splitMoney.money) { moneyList.add(splitMoney) }
        return moneyList
    }

    fun validate(inputMoney: Amount): ValidationResult {
        if (money < inputMoney.money) return ValidationResult.Error.OverMoneyError
        return ValidationResult.Success
    }

    companion object {
        const val MONEY_UNDER_ZERO = "[ERROR] 금액은 0 이상이여야 합니다."
    }
}
