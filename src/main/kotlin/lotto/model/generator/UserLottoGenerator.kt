package lotto.model.generator

import lotto.model.Lotto
import lotto.model.UserLotto

class UserLottoGenerator(
    private val generator: LottoNumberGenerator = LottoNumberGenerator(),
) {
    fun generateLotto(manualLotto: List<Lotto>, numberOfAutoLotto: Int): UserLotto {
        val lotto = mutableListOf<Lotto>()
        repeat(numberOfAutoLotto) {
            lotto.add(generator.generate())
        }
        return UserLotto(manualLotto + lotto)
    }
}
