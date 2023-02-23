package lotto.domain

class LottoBunch(val value: List<Lotto>) {

    // 로또 다발의 크기가 무한할 수 있기 때문에 가질 수 있는 로또는 최대 100개로 제한한다
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
        private const val MINIMUM_LOTTO_COUNT = 1
        private const val MAXIMUM_LOTTO_COUNT = 100
        private const val ERROR_LOTTO_COUNT_RANGE = "1이상 100 이하의 개수로만 구성될 수 있습니다."
    }
}
