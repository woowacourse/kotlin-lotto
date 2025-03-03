package lotto.domain

class ManualLottoAmount private constructor(
    val amount: Int,
) {
    constructor(amount: Int, totalAmount: Int) : this(amount) {
        require(amount in MIN_MANUAL_AMOUNT..totalAmount) { "수동 로또 개수가 잘못되었습니다." }
    }

    companion object {
        private const val MIN_MANUAL_AMOUNT = 0
    }
}
