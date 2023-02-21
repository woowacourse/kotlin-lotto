package lotto.domain

class LottoBunch(val value: List<Lotto>) {

    constructor(
        manualLottoBunch: LottoBunch,
        autoLottoBunch: LottoBunch,
    ) : this(manualLottoBunch.value + autoLottoBunch.value)

    init {
        validateLottoCount()
    }

    private fun validateLottoCount() {
        require(value.size in MINIMUM_LOTTO_COUNT..MAXIMUM_LOTTO_COUNT) { ERROR_LOTTO_COUNT_RANGE }
    }

    companion object {
        private const val MINIMUM_LOTTO_COUNT = 0
        private const val MAXIMUM_LOTTO_COUNT = 100
        private const val ERROR_LOTTO_COUNT_RANGE = "1이상 100 이하의 개수로만 구성될 수 있습니다."
    }
}
