package domain.strategy

import domain.model.LottoNumber

class KoreanLottoGenerator : LottoCountry {
    override fun generateNumber(): List<LottoNumber> {
        val lottoMachine = (LOTTO_MIN..LOTTO_MAX).shuffled().toMutableList()
        val lotto = mutableListOf<LottoNumber>()
        repeat(LOTTO_SIZE) {
            lotto.add(LottoNumber(lottoMachine.removeFirst()))
        }
        return lotto
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
        const val LOTTO_SIZE = 6
    }
}
