package lotto.domain.factory

import lotto.domain.Lotto

interface LottoFactory {
    fun createLotto(): Lotto
}
