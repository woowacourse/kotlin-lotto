package lotto.model

import kotlin.collections.mutableListOf

class LottoMachine(
    private val amount: Amount,
    private val lottoPrize: Amount,
) {
    init {
        require(amount.money > lottoPrize.money) { MONEY_UNDER_MIN }
        require(amount.money % lottoPrize.money == 0) { MONEY_UNIT_MESSAGE }
    }

    private val numbers: List<Int> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).toList()

    fun publishLottoTickets(): List<Lotto> {
        val lottoTickets = mutableListOf<Lotto>()
        repeat(amount.money / lottoPrize.money) {
            val lotto = publishLotto()
            lottoTickets.add(lotto)
        }
        return lottoTickets
    }

    private fun publishLotto(): Lotto {
        val lottoNumbers =
            numbers
                .shuffled()
                .take(LOTTO_NUMBER_COUNT)
                .sorted()
                .map { LottoNumber(it) }
        return Lotto(LottoNumbers(lottoNumbers))
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
        private const val MONEY_UNIT_MESSAGE = "[ERROR] 구입 금액은 로또 가격의 배수여야 합니다."
        private const val MONEY_UNDER_MIN = "[ERROR] 구입 금액이 최소 금액보다 작습니다."
    }
}
