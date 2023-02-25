package lotto.model.generator

import lotto.model.Lotto
import lotto.model.ManualLotto
import lotto.model.UserLotto

class UserLottoGenerator(
    private val generator: LottoNumberGenerator = LottoNumberGenerator(),
) {
    fun generateLotto(manualLotto: ManualLotto, totalNumberOfLotto: Int): UserLotto {
        val lotto = mutableListOf<Lotto>()
        repeat(totalNumberOfLotto - manualLotto.numberOfLotto) {
            lotto.add(generator.generate())
        }
        return UserLotto(manualLotto.lotto + lotto)
    }
}
