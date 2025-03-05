package domain.strategy

import domain.model.Lotto
import domain.model.Lotto.Companion.LOTTO_MAX
import domain.model.Lotto.Companion.LOTTO_MIN
import domain.model.Lotto.Companion.LOTTO_SIZE
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
}
