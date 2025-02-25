package lotto.model

import kotlin.collections.mutableListOf

interface ShuffleStrategy {
    fun shuffle(list: List<Int>): List<Int>
}

class NormalShuffle : ShuffleStrategy {
    override fun shuffle(list: List<Int>): List<Int> = list.shuffled()
}

class LottoMachine(
    var amount: Amount,
    private val shuffle: ShuffleStrategy = NormalShuffle(),
) {
    private val numbers: List<Int> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).toList()

    fun useMoney(inputMoney: Amount): Boolean {
        if (inputMoney.money > amount.money) return false
        amount.payMoney(inputMoney)
        return true
    }

    fun publishManualLottoList(lottoNumberList: List<List<Int>>): List<Lotto> = lottoNumberList.mapNotNull { publishManualLotto(it) }

    private fun publishManualLotto(numberList: List<Int>): Lotto? {
        val lottoNumbers: LottoNumbers? = LottoNumbers.create(numberList.mapNotNull { it -> LottoNumber.create(it) })
        return lottoNumbers?.let { Lotto(lottoNumbers) }
    }

    fun publishAutoTickets(lottoPrize: Amount): List<Lotto> {
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
