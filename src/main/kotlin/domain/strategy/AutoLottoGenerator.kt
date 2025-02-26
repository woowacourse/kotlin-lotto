package domain.strategy

import domain.model.Lotto
import domain.model.LottoNumber

class AutoLottoGenerator(private val autoLottoAmount: Int) : LottoGeneratorType {
    override fun generateNumber(): List<Lotto> {
        val lottoMachine = (LOTTO_MIN..LOTTO_MAX).shuffled().toMutableList()
        val lotto = lottoMachine.take(6).map { LottoNumber(it) }
        return List(autoLottoAmount) { Lotto(lotto) }
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
        const val LOTTO_SIZE = 6
    }
}
