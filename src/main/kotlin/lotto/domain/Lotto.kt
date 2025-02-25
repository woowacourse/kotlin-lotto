package lotto.domain

class Lotto(
    val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == NUMBERS_SIZE) { ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS }
    }

    fun contains(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)

    fun countMatch(winLotto: WinLotto): Int = numbers.count { lottoNumber: LottoNumber -> winLotto.hasNumber(lottoNumber) }

    companion object {
        const val PRICE = 1_000
        const val NUMBERS_SIZE = 6
        private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "최소 구입 금액은 ${PRICE}원입니다."
        private const val ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT = "구입 금액과 로또의 개수가 일치하지 않습니다."
        private const val ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS = "로또는 6개의 중복되지 않는 숫자를 갖고 있어야 합니다."

        fun buyLottos(
            pay: Int,
            wantedNumbers: List<List<Int>>,
        ): List<Lotto> {
            require(pay >= PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
            val lottoTicketCount: Int = pay / PRICE
            require(lottoTicketCount == wantedNumbers.size) { ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT }

            val lottos: List<Lotto> = wantedNumbers.map { numbers: List<Int> -> Lotto(numbers) }
            return lottos
        }

        fun buyRandomLottos(pay: Int): List<Lotto> {
            val lottoNumbers: List<List<Int>> = List(pay / PRICE) { makeRandomLottoNumbers() }
            return buyLottos(pay, lottoNumbers)
        }

        private fun makeRandomLottoNumbers(): List<Int> =
            (LottoNumber.MIN..LottoNumber.MAX)
                .shuffled()
                .subList(0, NUMBERS_SIZE)
    }
}

fun Lotto(numbers: List<Int>): Lotto = Lotto(numbers.map { number: Int -> LottoNumber(number) }.toSet())
