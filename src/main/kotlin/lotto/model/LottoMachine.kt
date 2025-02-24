package lotto.model

import kotlin.collections.mutableListOf
interface ShuffleStrategy{
    fun shuffle(list:List<Int>) : List<Int>
}

class NormalShuffle:ShuffleStrategy{
    override fun shuffle(list:List<Int>): List<Int> {
        return list.shuffled()
    }
}

class LottoMachine(
    private val amount: Amount,
    private val lottoPrize: Amount,
    private val shuffle : ShuffleStrategy = NormalShuffle()
) {
    init {
        require(amount.money >= lottoPrize.money) { MONEY_UNDER_MIN }
        require(amount.money % lottoPrize.money == 0) { MONEY_UNIT_MESSAGE }
    }

    private val numbers: List<Int> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).toList()

    fun publishLottoTickets(): List<Lotto> {
        val lottoTickets = mutableListOf<Lotto>()
        val moneyList = amount.moneySplit(lottoPrize)
        repeat(moneyList.size) {
            val lotto = publishLotto(lottoPrize)
            lottoTickets.add(lotto)
        }
        return lottoTickets
    }

    private fun publishLotto(prize: Amount): Lotto {
        val lottoNumbers =
            shuffle.shuffle(numbers)
                .take(LOTTO_NUMBER_COUNT)
                .sorted()
                .map { LottoNumber(it) }
        return Lotto(LottoNumbers(lottoNumbers), prize)
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
        private const val MONEY_UNIT_MESSAGE = "[ERROR] 구입 금액은 로또 가격의 배수여야 합니다."
        private const val MONEY_UNDER_MIN = "[ERROR] 구입 금액이 최소 금액보다 작습니다."
    }
}
