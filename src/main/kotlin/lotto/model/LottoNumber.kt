package lotto.model

class LottoNumber private constructor(
    val value: Int,
) {
    companion object {
        const val LOTTO_NUMBER_MIN_RANGE = 1
        const val LOTTO_NUMBER_MAX_RANGE = 45

        private val CACHE_LOTTO_NUMBER = mutableMapOf<Int, LottoNumber>()

        fun from(number: Int): LottoNumber {
            require(number in LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE) {
                "[ERROR] 로또 번호의 범위는 $LOTTO_NUMBER_MIN_RANGE 이상 $LOTTO_NUMBER_MAX_RANGE 이하여야 합니다."
            }
            return CACHE_LOTTO_NUMBER.getOrPut(number) { LottoNumber(number) }
        }
    }
}
