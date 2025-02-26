package lotto.model

import kotlin.collections.mutableListOf

interface ShuffleStrategy {
    fun shuffle(list: List<Int>): List<Int>
}

class NormalShuffle : ShuffleStrategy {
    override fun shuffle(list: List<Int>): List<Int> = list.shuffled()
}

class LottoMachine(
    private val shuffle: ShuffleStrategy = NormalShuffle(),
) {
    private val numbers: List<Int> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).toList()

    fun publishAutoTickets(money: Amount): List<Lotto> {
        val lottoTickets = mutableListOf<Lotto>()
        repeat(money.moneySplit(LOTTO_PRIZE).size) {
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
        private val LOTTO_PRIZE = Amount(1000)
    }
}
