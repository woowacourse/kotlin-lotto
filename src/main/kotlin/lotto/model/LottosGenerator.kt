package lotto.model

interface LottosGenerator {
    fun generate(numberOfLottos: Int): List<Lotto>
}
