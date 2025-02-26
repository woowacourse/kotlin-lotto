package domain.fixture

import domain.model.LottoNumber
import domain.model.lotto.Lotto

fun createLotto(vararg numbers: Int = intArrayOf(1, 2, 3, 4, 5, 6)): Lotto {
    return Lotto(numbers.map { LottoNumber(it) })
}
