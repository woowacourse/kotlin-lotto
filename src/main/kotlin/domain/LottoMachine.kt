package domain

class LottoMachine(val lottoGenerator: LottoGeneratorInterface) {

    fun generateLotto(): Lotto {
        return lottoGenerator.generateLotto()
    }

    fun generateLotteries(count: Count): List<Lotto> {
        return List(count.value) { generateLotto() }
    }
}
