package domain

class LottoMachine(val count: Count, val lottoGenerator: LottoGeneratorInterface) {

    fun generateLotto(): Lotto {
        return lottoGenerator.generateLotto()
    }

    fun generateLotteries(): List<Lotto> {
        return List(count.value) { generateLotto() }
    }
}
