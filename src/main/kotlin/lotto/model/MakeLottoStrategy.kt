package lotto.model

sealed interface MakeLottoStrategy {
    fun makeLotto(): Lotto
}

class MakeRandomLotto(private val numberGenerator: (IntRange) -> List<Int> = { it.shuffled().take(LOTTO_SIZE) }) :
    MakeLottoStrategy {
    override fun makeLotto(): Lotto {
        val randomNumbers = numberGenerator(LOTTO_MINIMUM_NUMBER..LOTTO_MAXIMUM_NUMBER)
        return Lotto(randomNumbers)
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val LOTTO_MINIMUM_NUMBER = 1
        const val LOTTO_MAXIMUM_NUMBER = 45
    }
}

class MakeManualLotto(private val number: List<Int>) : MakeLottoStrategy {
    override fun makeLotto(): Lotto {
        return Lotto(number)
    }
}
