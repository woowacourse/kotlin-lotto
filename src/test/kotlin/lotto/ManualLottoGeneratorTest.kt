package lotto

import lotto.model.ManualLottoGenerator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoGeneratorTest {
    @Test
    fun `입력된 번호로 로또를 생성한다`() {
        val manualNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            )
        val generator = ManualLottoGenerator(manualNumbers)
        val lottos = generator.generate(2)

        assertEquals(2, lottos.size)
        assertEquals(setOf(1, 2, 3, 4, 5, 6), lottos[0].numbers.map { it.toString().toInt() }.toSet())
        assertEquals(setOf(7, 8, 9, 10, 11, 12), lottos[1].numbers.map { it.toString().toInt() }.toSet())
    }

    @Test
    fun `입력된 번호 개수와 요청 개수가 일치하지 않으면 예외가 발생한다`() {
        val manualNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
            )
        val generator = ManualLottoGenerator(manualNumbers)

        val exception =
            assertThrows<IllegalArgumentException> {
                generator.generate(2)
            }
        assertEquals("로또 개수와 입력된 번호 개수가 일치하지 않습니다.", exception.message)
    }
}
