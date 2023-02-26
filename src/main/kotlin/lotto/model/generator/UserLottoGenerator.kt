package lotto.model.generator

import lotto.model.Lotto
import lotto.model.ManualLottos
import lotto.model.UserLotto

class UserLottoGenerator(
    private val autoLottoGenerator: LottoNumberGenerator = LottoNumberGenerator(),
) {
    fun generateLotto(
        input: () -> Lotto,
        manualLotto: ManualLottos,
        totalNumberOfLotto: Int,
    ): UserLotto {
        val manualLottos = generateManualLottos(input, manualLotto)
        val autoLottos = generateAutoLottos(totalNumberOfLotto - manualLotto.numberOfLotto)

        return UserLotto(manualLottos + autoLottos)
    }

    private fun generateAutoLottos(numberOfAutoLotto: Int): List<Lotto> {
        val lotto = mutableListOf<Lotto>()
        repeat(numberOfAutoLotto) {
            lotto.add(autoLottoGenerator.generate())
        }
        return lotto
    }

    private fun generateManualLottos(input: () -> Lotto, manualLotto: ManualLottos): List<Lotto> {
        repeat(manualLotto.numberOfLotto) {
            manualLotto.add(input())
        }
        return manualLotto.lotto
    }
}
