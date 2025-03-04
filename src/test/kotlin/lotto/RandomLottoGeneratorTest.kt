package lotto

import lotto.model.RandomLottoGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {
    @Test
    fun `요청한 개수만큼 로또를 생성한다`() {
        val generator = RandomLottoGenerator()
        val lottos = generator.generate(5)
        assertEquals(5, lottos.size)
    }

    @Test
    fun `생성된 로또는 모두 유효한 번호를 가진다`() {
        val generator = RandomLottoGenerator()
        val lottos = generator.generate(100)
        lottos.forEach { lotto ->
            assertEquals(6, lotto.numbers.size)
            assertTrue(lotto.numbers.all { it.toString().toInt() in 1..45 })
        }
    }
}
