@JvmInline
value class LottoNumber(val number: Int) {

    init {
        require(number in MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER) { LOTTO_NUMBER_RANGE_ERROR_MESSAGE }
    }

    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호는 1이상 45 이하여야 합니다."
    }
}
