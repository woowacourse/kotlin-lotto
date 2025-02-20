package lotto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachine(
    val count: Int,
) {
    val lottos: List<Lotto> = generateLottos(count)

    companion object {
        private fun generateLottos(count: Int): List<Lotto> {
            val lottos = mutableListOf<Lotto>()
            repeat(count) {
                lottos.add(Lotto())
            }
            return lottos
        }
    }
}

class LottoMachineTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 3, 4])
    fun `로또 구입 개수만큼 로또를 발행한다`(count: Int) {
        val lottoMachine = LottoMachine(count)
        assertEquals(lottoMachine.lottos.size, count)
    }
}
