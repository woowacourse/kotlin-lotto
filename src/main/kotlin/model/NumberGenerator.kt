package lotto.model

import model.Lotto

fun interface NumberGenerator {
    fun generate(
        size: Int,
        minNumber: Int,
        maxNumber: Int,
    ): Lotto
}
