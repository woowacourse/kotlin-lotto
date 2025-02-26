package lotto.domain

class Lotto(
    private val _numbers: Set<LottoNumber>,
) {
    val numbers: List<Int> = _numbers.map { number: LottoNumber -> number.value }

    init {
        require(_numbers.size == NUMBERS_SIZE) { ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS }
    }

    fun contains(lottoNumber: LottoNumber): Boolean = _numbers.contains(lottoNumber)

    fun countMatch(winLotto: WinLotto): Int = _numbers.count { lottoNumber: LottoNumber -> winLotto.hasNumber(lottoNumber) }

    companion object {
        const val PRICE = 1_000
        const val NUMBERS_SIZE = 6
        private const val ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS = "로또는 6개의 중복되지 않는 숫자를 갖고 있어야 합니다."
    }
}

fun Lotto(numbers: List<Int>): Lotto = Lotto(numbers.map { number: Int -> LottoNumber(number) }.toSet())
