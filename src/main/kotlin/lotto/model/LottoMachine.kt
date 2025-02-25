package lotto.model

import kotlin.collections.mutableListOf

interface ShuffleStrategy {
    fun shuffle(list: List<Int>): List<Int>
}

class NormalShuffle : ShuffleStrategy {
    override fun shuffle(list: List<Int>): List<Int> = list.shuffled()
}

class LottoMachine(
    private val amount: Amount,
    private val shuffle: ShuffleStrategy = NormalShuffle(),
) {
    private val numbers: List<Int> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).toList()

    fun publishLottoTickets(lottoPrize: Amount): List<Lotto> {
        val lottoTickets = mutableListOf<Lotto>()
        val moneyList = amount.moneySplit(lottoPrize)
        repeat(moneyList.size) {
            val lotto = publishLotto()
            lottoTickets.add(lotto)
        }
        return lottoTickets
    }

    private fun publishLotto(): Lotto {
        val lottoNumbers =
            shuffle
                .shuffle(numbers)
                .take(LOTTO_NUMBER_COUNT)
                .sorted()
                .map { LottoNumber(it) }
        return Lotto(LottoNumbers(lottoNumbers))
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
    }
}
