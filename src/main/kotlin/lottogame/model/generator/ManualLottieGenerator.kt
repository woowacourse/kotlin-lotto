package lottogame.model.generator

import lottogame.model.Lotto

class DefaultManualLottieGenerator() : ManualLottieGenerator {
    override fun generate(lottieNumbers: List<List<Int>>): List<Lotto> {
        return lottieNumbers.map { Lotto(it) }
    }
}

fun interface ManualLottieGenerator {
    fun generate(lottieNumbers: List<List<Int>>): List<Lotto>
}
