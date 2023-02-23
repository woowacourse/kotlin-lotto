package lotto.domain

import lotto.constant.LOTTO_MAXIMUM_NUMBER
import lotto.constant.LOTTO_MINIMUM_NUMBER
import lotto.constant.LOTTO_SIZE

class LottoFactory {

    fun createManualLotto(numbers: List<Int>): Lotto = createLotto(numbers)

    fun createAutoLotto(): Lotto = createLotto()

    private fun createLotto(numbers: List<Int> = getRandomNumbers()): Lotto =
        Lotto(numbers.map { number -> LottoNumber.from(number) }.toSet())

    private fun getRandomNumbers(): List<Int> = (LOTTO_MINIMUM_NUMBER..LOTTO_MAXIMUM_NUMBER).shuffled().take(LOTTO_SIZE)
}
