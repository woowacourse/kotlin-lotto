package domain.strategy

import domain.model.Lotto
import domain.model.LottoNumber

class AutoLottoGenerator(
    totalLottoAmount: Int,
    manualLottoAmount: Int,
) : LottoGeneratorType {
    val autoLottoAmount = totalLottoAmount - manualLottoAmount

    override fun generateNumber(): List<Lotto> {
        return List(autoLottoAmount) {
            Lotto((LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE).map { LottoNumber(it) })
        }
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
        const val LOTTO_SIZE = 6
    }
}
