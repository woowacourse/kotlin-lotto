package lotto

import lotto.domain.factory.RandomLottoFactory

fun main() {
    LottoController(RandomLottoFactory()).runLotto()
}
