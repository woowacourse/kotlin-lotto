package lotto.domain

class Lotto(
    val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == NUMBERS_SIZE) { ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS }
    }

    fun containLottoNumber(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)

    companion object {
        const val PRICE = 1_000
        const val NUMBERS_SIZE = 6
        private const val ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS = "로또는 6개의 중복되지 않는 숫자를 갖고 있어야 합니다."
    }
}
