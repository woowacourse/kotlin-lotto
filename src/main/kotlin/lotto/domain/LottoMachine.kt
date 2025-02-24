package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine(private val randomGenerator: LottoGenerator = LottoGenerator()) {
    fun buyLottos(orderSheet: OrderSheet): List<Lotto> {
        return orderSheet.manualLottoNumber + buyAutoLottos(orderSheet.autoCount)
    }

    private fun buyAutoLottos(lottoCount: Int): List<Lotto> {
        return List(lottoCount) { Lotto(randomGenerator.getRandomLottoNumbers(NUMBER_OF_LOTTO_NUMBER)) }
    }

    fun buyManualLottos(lottos: List<String>): List<Lotto> {
        return lottos.map { Lotto(stringToLottoNumber(it)) }
    }

    private fun stringToLottoNumber(string: String): List<LottoNumber> {
        val splitString = string.split(DELIMITERS).map { it.trim() }
        return splitString.map { LottoNumber(it.toInt()) }
    }

    companion object {
        private const val DELIMITERS = ","
        private const val NUMBER_OF_LOTTO_NUMBER: Int = 6
        const val LOTTO_PRICE: Int = 1_000
    }
}
