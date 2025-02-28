package lotto

import lotto.model.generator.ManualLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {
    @Test
    fun `수동 로또를 2개 발행한다`() {
        val manualCount = 2
        val manualLottoBundle = listOf(listOf(1, 2, 3, 4, 5, 6), listOf(3, 4, 5, 6, 7, 8))
        assertThat(ManualLottoGenerator(manualLottoBundle).generate(manualCount).size).isEqualTo(2)
    }
}
