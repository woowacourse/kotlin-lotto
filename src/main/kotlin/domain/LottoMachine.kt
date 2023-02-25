package domain

class LottoMachine(val lottoGenerator: LottoGeneratorInterface) {

    fun generateAutoLotto(): Lotto {
        return lottoGenerator.generateLotto()
    }

    fun generateAutoLotteries(count: Count): List<Lotto> {
        return List(count.value) { generateAutoLotto() }
    }
}
